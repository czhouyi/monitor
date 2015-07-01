package com.chinadaas.domain.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.dao.TypeMismatchDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chinadaas.domain.dao.BaseDao;
import com.chinadaas.domain.dao.IOrderDao;
import com.chinadaas.domain.dao.rm.OrderRowMapper;
import com.chinadaas.domain.entity.Order;

/**
 * projectName: chinadaas-data<br>
 * desc: 订单时序数据访问实现<br>
 * date: 2015年3月19日 下午4:03:21<br>
 * @author 开发者真实姓名[Andy]
 */
@Repository
public class OrderDaoImpl extends BaseDao<Order> implements IOrderDao {
	
	@Override
	public Map<String, List<Object>> getCurrent() {
		String sql = " SELECT COUNT(1) VALUE, O.ORDER_FROM, "
				+ " TO_CHAR(O.SUBMIT_TIME,'HH24:MI') SERIES "
				+ " FROM T_ORDER O "
				+ " WHERE O.PARTITION_KEY = TO_CHAR(SYSDATE,'YYMMDD') "
				+ " GROUP BY TO_CHAR(O.SUBMIT_TIME,'HH24:MI'), O.ORDER_FROM ";
		List<Order> rs = (List<Order>) find(sql, new Object[]{}, new OrderRowMapper(true));
		
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> series = new ArrayList<Object>();	// 时序
		List<Object> value0 = new ArrayList<Object>();	// 外网
		List<Object> value1 = new ArrayList<Object>();	// 专线
		List<Object> value2 = new ArrayList<Object>();	// 互联网
		
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		for (int i = 0; i <= hour; i++) {
			for (int j = 0; (i<hour && j < 60) || (i==hour && j <= minute); j++) {
				series.add(String.format("%02d:%02d", i, j));
				value0.add(0.0);
				value1.add(0.0);
				value2.add(0.0);
			}
		}
		
		for(Order o : rs) {
			String[] ms = o.getSeries().split(":");
			int index = Integer.valueOf(ms[0]) * 60 + Integer.valueOf(ms[1]);
			String from = o.getFrom();
			if ("0".equals(from)) {
				value0.set(index, o.getValue());
			} else if ("1".equals(from)) {
				value1.set(index, o.getValue());
			} else {
				value2.set(index, o.getValue());
			}
		}
		
		map.put("series", series);
		map.put("value0", value0);
		map.put("value1", value1);
		map.put("value2", value2);
		return map;
	}
	
	@Override
	public Map<String, Object> getIncrease() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String minutedate = sdf.format(calendar.getTime());
		
		String sql = " SELECT COUNT(1) VALUE, O.ORDER_FROM, "
				+ " TO_CHAR(O.SUBMIT_TIME,'HH24:MI') SERIES "
				+ " FROM T_ORDER O "
				+ " WHERE O.PARTITION_KEY = ? "
				+ " AND TO_CHAR(O.SUBMIT_TIME,'YYYY-MM-DD HH24:MI') = ? "
				+ " GROUP BY TO_CHAR(O.SUBMIT_TIME,'HH24:MI'), O.ORDER_FROM ";
		List<Order> rs = (List<Order>) find(sql, new Object[]{minutedate.replace("-", "").substring(2, 8), 
				minutedate}, new OrderRowMapper(true));
		
		Map<String, Object> map = new TreeMap<String, Object>();

		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		map.put("series", String.format("%02d:%02d", hour, minute));
		map.put("value0", 0.0);
		map.put("value1", 0.0);
		map.put("value2", 0.0);
		
		for(Order o : rs) {
			String from = o.getFrom();
			if ("0".equals(from)) {
				map.put("value0", o.getValue());
			} else if ("1".equals(from)) {
				map.put("value1", o.getValue());
			} else {
				map.put("value2", o.getValue());
			}
		}
		
		return map;
	}
	
	@Override
	public Map<String, Double> delayData(String from, String to) {
		String sql = " SELECT ROUND(AVG(O.FINISH_TIME-O.SUBMIT_TIME)*24*60*60,2) VALUE, "
				+ " TO_CHAR(O.SUBMIT_TIME,'YYMMDD HH24:MI') SERIES "
				+ " FROM T_ORDER O "
				+ " WHERE O.PARTITION_KEY >= ? "
				+ " AND O.PARTITION_KEY <= ? "
				+ " GROUP BY TO_CHAR(O.SUBMIT_TIME,'YYMMDD HH24:MI') ";
		List<Order> rs = (List<Order>) find(sql, new Object[]{from.replace("-", "").substring(2, 8), 
				to.replace("-", "").substring(2, 8)}, new OrderRowMapper(false));
		
		Map<String, Double> map = new TreeMap<String, Double>();
		
		Calendar cf = Calendar.getInstance();
		Calendar ct = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat fminute = new SimpleDateFormat("yyMMdd HH:mm");
		try {
			cf.setTime(sdf.parse(from));
			ct.setTime(sdf.parse(to));
		} catch (ParseException e) {
			throw new TypeMismatchDataAccessException(e.getMessage());
		}
		
		while (cf.before(ct)) {
			map.put(fminute.format(cf.getTime()), 0.0);
			cf.add(Calendar.MINUTE, 1);
		}
		
		for(Order o : rs) {
			map.put(o.getSeries(), o.getValue());
		}
		
		return map;
	}
	
	@Override
	public Map<String, Object> distributionData(String from, String to) {
		String sql = " SELECT COUNT(1) VALUE, P.NAME NAME "
				+ " FROM T_ORDER O "
				+ " LEFT JOIN T_PROVINCE P ON P.CODE=O.ID_AREA "
				+ " WHERE P.CODE IS NOT NULL "
				+ " AND O.PARTITION_KEY >= ? "
				+ " AND O.PARTITION_KEY <= ? "
				+ " GROUP BY P.NAME";
		List<Map<String, Object>> rs = (List<Map<String, Object>>) findObj(sql, new Object[]{
				from.replace("-", "").substring(2, 8), to.replace("-", "").substring(2, 8)}, new RowMapper<Map<String, Object>>() {

			@Override
			public Map<String, Object> mapRow(ResultSet rs, int rn)
					throws SQLException {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", rs.getString("name"));
				map.put("value", rs.getDouble("value"));
				return map;
			}
		});
		
		double max = 0.0;
		for (Map<String, Object> m : rs) {
			double cnt = (Double) m.get("value");
			if(cnt > max) {
				max = cnt;
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", rs);
		map.put("max", max);

		return map;
	}
	
	@Override
	public Map<String, Double> tsStaticDataByMonth(String from, String to, String cid, String orderFrom, String product) {
		String sql = " SELECT COUNT(1) VALUE, "
				+ " O.PARTITION_KEY SERIES "
				+ " FROM T_ORDER O "
				+ " LEFT JOIN T_PRODUCT_ORDER PO ON PO.ID=O.ID_PRODUCT_ORDER "
				+ " LEFT JOIN T_USER U ON U.ID=O.ID_USER "
				+ " WHERE O.PARTITION_KEY >= ? "
				+ " AND O.PARTITION_KEY <= ? ";
		if (cid != null && cid.length() > 0) {
			sql += " AND U.ID_CUSTOMER='"+cid+"' ";
		}
		if (orderFrom != null && orderFrom.length() > 0) {
			sql += " AND O.ORDER_FROM='"+orderFrom+"' ";
		}
		if (product != null && product.length() > 0) {
			sql += " AND PO.CODE_PRODUCT_TPL='"+product+"' ";
		}
		sql += " GROUP BY O.PARTITION_KEY ";
		List<Order> rs = (List<Order>) find(sql, new Object[]{from.replace("-", "").substring(2, 8), 
				to.replace("-", "").substring(2, 8)}, new OrderRowMapper(false));
		
		Map<String, Double> map = new TreeMap<String, Double>();
		
		Calendar cf = Calendar.getInstance();
		Calendar ct = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat fmonth = new SimpleDateFormat("yyMM");
		try {
			cf.setTime(sdf.parse(from));
			ct.setTime(sdf.parse(to));
		} catch (ParseException e) {
			throw new TypeMismatchDataAccessException(e.getMessage());
		}
		
		while (cf.before(ct)) {
			map.put(fmonth.format(cf.getTime()), 0.0);
			cf.add(Calendar.MONTH, 1);
		}
		
		for(Order o : rs) {
			String key = o.getSeries().substring(0, 4);
			map.put(key, o.getValue() + map.get(key));
		}
		
		return map;
	}
	
	@Override
	public Map<String, Double> tsStaticDataByDay(String from, String to, String cid, String orderFrom, String product) {
		String sql = " SELECT COUNT(1) VALUE, "
				+ " O.PARTITION_KEY SERIES "
				+ " FROM T_ORDER O "
				+ " LEFT JOIN T_PRODUCT_ORDER PO ON PO.ID=O.ID_PRODUCT_ORDER "
				+ " LEFT JOIN T_USER U ON U.ID=O.ID_USER "
				+ " WHERE O.PARTITION_KEY >= ? "
				+ " AND O.PARTITION_KEY <= ? ";
		if (cid != null && cid.length() > 0) {
			sql += " AND U.ID_CUSTOMER='"+cid+"' ";
		}
		if (orderFrom != null && orderFrom.length() > 0) {
			sql += " AND O.ORDER_FROM='"+orderFrom+"' ";
		}
		if (product != null && product.length() > 0) {
			sql += " AND PO.CODE_PRODUCT_TPL='"+product+"' ";
		}
		sql += " GROUP BY O.PARTITION_KEY ";
		List<Order> rs = (List<Order>) find(sql, new Object[]{from.replace("-", "").substring(2, 8), 
				to.replace("-", "").substring(2, 8)}, new OrderRowMapper(false));
		
		Map<String, Double> map = new TreeMap<String, Double>();
		
		Calendar cf = Calendar.getInstance();
		Calendar ct = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat fday = new SimpleDateFormat("yyMMdd");
		try {
			cf.setTime(sdf.parse(from));
			ct.setTime(sdf.parse(to));
		} catch (ParseException e) {
			throw new TypeMismatchDataAccessException(e.getMessage());
		}
		
		while (cf.before(ct)) {
			map.put(fday.format(cf.getTime()), 0.0);
			cf.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		for(Order o : rs) {
			map.put(o.getSeries(), o.getValue());
		}
		
		return map;
	}
	
	@Override
	public Map<String, Double> tsStaticDataByHour(String from, String to, String cid, String orderFrom, String product) {
		String sql = " SELECT COUNT(1) VALUE, "
				+ " TO_CHAR(O.SUBMIT_TIME,'YYMMDDHH24') SERIES "
				+ " FROM T_ORDER O "
				+ " LEFT JOIN T_PRODUCT_ORDER PO ON PO.ID=O.ID_PRODUCT_ORDER "
				+ " LEFT JOIN T_USER U ON U.ID=O.ID_USER "
				+ " WHERE O.PARTITION_KEY >= ? "
				+ " AND O.PARTITION_KEY <= ? ";
		if (cid != null && cid.length() > 0) {
			sql += " AND U.ID_CUSTOMER='"+cid+"' ";
		}
		if (orderFrom != null && orderFrom.length() > 0) {
			sql += " AND O.ORDER_FROM='"+orderFrom+"' ";
		}
		if (product != null && product.length() > 0) {
			sql += " AND PO.CODE_PRODUCT_TPL='"+product+"' ";
		}
		sql += " GROUP BY TO_CHAR(O.SUBMIT_TIME,'YYMMDDHH24') ";
		List<Order> rs = (List<Order>) find(sql, new Object[]{from.replace("-", "").substring(2, 8), 
				to.replace("-", "").substring(2, 8)}, new OrderRowMapper(false));
		
		Map<String, Double> map = new TreeMap<String, Double>();
		
		Calendar cf = Calendar.getInstance();
		Calendar ct = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		SimpleDateFormat fhour = new SimpleDateFormat("yyMMddHH");
		try {
			cf.setTime(sdf.parse(from));
			ct.setTime(sdf.parse(to));
		} catch (ParseException e) {
			throw new TypeMismatchDataAccessException(e.getMessage());
		}
		
		while (cf.before(ct)) {
			map.put(fhour.format(cf.getTime()), 0.0);
			cf.add(Calendar.HOUR, 1);
		}
		
		for(Order o : rs) {
			map.put(o.getSeries(), o.getValue());
		}
		
		return map;
	}
	
	@Override
	public List<Map<String, Object>> mainCustomerData(String from, String to) {
		String sql = " SELECT * FROM ( "
				+ " SELECT COUNT(1) VALUE, "
				+ " C.FULL_NAME　NAME "
				+ " FROM T_ORDER O "
				+ " LEFT JOIN T_USER U ON U.ID=O.ID_USER "
				+ " LEFT JOIN T_CUSTOMER C ON C.ID=U.ID_CUSTOMER "
				+ " WHERE O.PARTITION_KEY >= ? "
				+ " AND O.PARTITION_KEY <= ? "
				+ " AND C.ID IS NOT NULL "
				+ " GROUP BY C.FULL_NAME　"
				+ " ORDER BY COUNT(1) DESC "
				+ " ) WHERE ROWNUM < 11 ";

		List<Map<String, Object>> rs = (List<Map<String, Object>>) findObj(sql, new Object[]{
				from.replace("-", "").substring(2, 8), to.replace("-", "").substring(2, 8)}, new RowMapper<Map<String, Object>>() {

			@Override
			public Map<String, Object> mapRow(ResultSet rs, int rn)
					throws SQLException {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", rs.getString("name"));
				map.put("value", rs.getDouble("value"));
				return map;
			}
		});

		return rs;
	}
	
	@Override
	public Map<String, Double> calendarData(String cid, String from, String to) {
		String sql = " SELECT COUNT(1) VALUE, "
				+ " O.PARTITION_KEY SERIES "
				+ " FROM T_ORDER O "
				+ " LEFT JOIN T_USER U ON U.ID=O.ID_USER "
				+ " WHERE O.PARTITION_KEY >= ? "
				+ " AND O.PARTITION_KEY <= ? ";
		if (cid != null && cid.length() > 0) {
			sql += " AND U.ID_CUSTOMER='"+cid+"' ";
		}
		sql += " GROUP BY O.PARTITION_KEY ";
		List<Order> rs = (List<Order>) find(sql, new Object[]{from.replace("-", "").substring(2, 8), 
				to.replace("-", "").substring(2, 8)}, new OrderRowMapper(false));
		
		Map<String, Double> map = new TreeMap<String, Double>();
		
		for(Order o : rs) {
			map.put(o.getSeries(), o.getValue());
		}
		
		return map;
	}
	
	@Override
	public List<Map<String, Object>> productCntData(String from, String to) {
		String sql = " SELECT PT.PRODUCT_TPL_NAME NAME, COUNT(1) VALUE FROM T_ORDER O "
				+ " LEFT JOIN T_PRODUCT_ORDER PO ON PO.ID = O.ID_PRODUCT_ORDER "
				+ " LEFT JOIN T_PRODUCT_TPL PT ON PT.CODE = PO.CODE_PRODUCT_TPL "
				+ " WHERE PO.CODE_PRODUCT_TPL IS NOT NULL "
				+ " AND O.PARTITION_KEY >= ? "
				+ " AND O.PARTITION_KEY <= ? "
				+ " GROUP BY PT.PRODUCT_TPL_NAME ";

		List<Map<String, Object>> rs = (List<Map<String, Object>>) findObj(sql, new Object[]{
				from.replace("-", "").substring(2, 8), to.replace("-", "").substring(2, 8)}, new RowMapper<Map<String, Object>>() {

			@Override
			public Map<String, Object> mapRow(ResultSet rs, int rn)
					throws SQLException {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", rs.getString("name"));
				map.put("value", rs.getDouble("value"));
				return map;
			}
		});

		return rs;
	}
	
	@Override
	public List<Map<String, Object>> fromCntData(String from, String to) {
		String sql = " SELECT CASE WHEN O.ORDER_FROM=0 THEN '外网' WHEN O.ORDER_FROM=1 THEN '专线' ELSE '互联网' END NAME, "
				+ " COUNT(1) VALUE FROM T_ORDER O "
				+ " WHERE O.ORDER_FROM IS NOT NULL "
				+ " AND O.PARTITION_KEY >= ? "
				+ " AND O.PARTITION_KEY <= ? "
				+ " GROUP BY O.ORDER_FROM ";

		List<Map<String, Object>> rs = (List<Map<String, Object>>) findObj(sql, new Object[]{
				from.replace("-", "").substring(2, 8), to.replace("-", "").substring(2, 8)}, new RowMapper<Map<String, Object>>() {

			@Override
			public Map<String, Object> mapRow(ResultSet rs, int rn)
					throws SQLException {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", rs.getString("name"));
				map.put("value", rs.getDouble("value"));
				return map;
			}
		});

		return rs;
	}
	
}


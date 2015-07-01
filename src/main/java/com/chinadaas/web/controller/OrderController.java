package com.chinadaas.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadaas.domain.dao.IOrderDao;

/**
 * projectName: chinadaas-data<br>
 * desc: 订单监控控制类<br>
 * date: 2015年3月23日 下午5:53:12<br>
 * 
 * @author 开发者真实姓名[Andy]
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {
	
	@Autowired
	private IOrderDao orderDao;

	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String order() throws Exception {
		return "order/order";
	}
	
	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public String currentPage() throws Exception {
		return "order/current";
	}
	
	@RequestMapping(value = "/current", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public @ResponseBody String current() throws Exception {
		Map<String, List<Object>> map = orderDao.getCurrent();
		
		JSONObject json = new JSONObject();
		
		json.putAll(map);
		
		return json.toJSONString();
	}
	
	@RequestMapping(value = "/increase", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String increase() throws Exception {
		Map<String, Object> map = orderDao.getIncrease();
		
		JSONObject json = new JSONObject();
		json.putAll(map);
		
		return json.toJSONString();
	}
	
	@RequestMapping(value = "/delay", method = RequestMethod.GET)
	public String delay() throws Exception {
		return "order/delay";
	}
	
	/**
	 * desc: 订单处理时间数据获取<br>
	 * date: 2015年3月30日 上午10:59:42<br>
	 * @author 开发者真实姓名[Andy]
	 * @param date
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delay", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public @ResponseBody String delay(@RequestParam("from") String from,
				 					  @RequestParam("to") String to) throws Exception {
		from = from + " 00:00:00";
		to = to + " 23:59:59";
		Map<String, Double> map = orderDao.delayData(from, to);
		JSONObject json = new JSONObject();
		JSONArray series = new JSONArray();
		series.addAll(map.keySet());
		JSONArray value = new JSONArray();
		value.addAll(map.values());
		json.put("series", series);
		json.put("value", value);
		
		return json.toJSONString();
	}
	
	@RequestMapping(value = "/distribution", method = RequestMethod.GET)
	public String distribution() throws Exception {
		return "order/distribution";
	}
	
	/**
	 * desc: 被查询对象分布数据获取<br>
	 * date: 2015年3月30日 上午10:50:38<br>
	 * @author 开发者真实姓名[Andy]
	 * @param from
	 * @param to
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/distribution", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public @ResponseBody String distribution(@RequestParam("from") String from,
		 	 								 @RequestParam("to") String to) throws Exception {
		from = from + " 00:00:00";
		to = to + " 23:59:59";
		Map<String, Object> map = orderDao.distributionData(from, to);
		
		JSONObject json = new JSONObject();
		json.putAll(map);
		
		return json.toJSONString();
	}
	
	@RequestMapping(value = "/ts_static", method = RequestMethod.GET)
	public String tsStatic() throws Exception {
		return "order/ts_static";
	}
	
	/**
	 * desc: 订单历史时序数据<br>
	 * date: 2015年3月30日 上午9:54:50<br>
	 * @author 开发者真实姓名[Andy]
	 * @param from
	 * @param to
	 * @param cid
	 * @param orderFrom
	 * @param product
	 * @param scope
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ts_static", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public @ResponseBody String tsStatic(@RequestParam("from") String from,
			 							 @RequestParam("to") String to,
			 							 @RequestParam("cid") String cid,
										 @RequestParam("orderFrom") String orderFrom,
										 @RequestParam("product") String product,
										 @RequestParam("scope") String scope
										 ) throws Exception {
		from = from + " 00:00:00";
		to = to + " 23:59:59";
		Map<String, Double> map = null;
		if ("month".equals(scope)) {
			map = orderDao.tsStaticDataByMonth(from, to, cid, orderFrom, product);
		} else if ("day".equals(scope)) {
			map = orderDao.tsStaticDataByDay(from, to, cid, orderFrom, product);
		} else if ("hour".equals(scope)) {
			map = orderDao.tsStaticDataByHour(from, to, cid, orderFrom, product);
		}
		
		JSONObject json = new JSONObject();
		JSONArray series = new JSONArray();
		series.addAll(map.keySet());
		JSONArray value = new JSONArray();
		value.addAll(map.values());
		json.put("series", series);
		json.put("value", value);
		
		return json.toJSONString();
	}
	
	@RequestMapping(value = "/main_customer", method = RequestMethod.GET)
	public String mainCustomer() throws Exception {
		return "order/main_customer";
	}
	
	/**
	 * desc: 订单主要客户数据<br>
	 * date: 2015年3月30日 上午9:54:50<br>
	 * @author 开发者真实姓名[Andy]
	 * @param from
	 * @param to
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/main_customer", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public @ResponseBody String mainCustomer(@RequestParam("from") String from,
										 	 @RequestParam("to") String to) throws Exception {
		from = from + " 00:00:00";
		to = to + " 23:59:59";
		List<Map<String, Object>> list = orderDao.mainCustomerData(from, to);
		
		JSONObject json = new JSONObject();
		json.put("data", list);
		List<String> customerlist = new ArrayList<String>();
		for (Map<String, Object> map : list) {
			customerlist.add((String)map.get("name"));
		}
		JSONArray customer = new JSONArray();
		customer.addAll(customerlist);
		json.put("legend", customer);
		
		return json.toJSONString();
	}
	
	@RequestMapping(value = "/product_cnt", method = RequestMethod.GET)
	public String productCnt() throws Exception {
		return "order/product_cnt";
	}
	
	/**
	 * desc: 订单按产品计数<br>
	 * date: 2015年3月30日 上午9:54:50<br>
	 * @author 开发者真实姓名[Andy]
	 * @param from
	 * @param to
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/product_cnt", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public @ResponseBody String productCnt(@RequestParam("from") String from,
										   @RequestParam("to") String to) throws Exception {
		from = from + " 00:00:00";
		to = to + " 23:59:59";
		List<Map<String, Object>> list = orderDao.productCntData(from, to);
		
		JSONObject json = new JSONObject();
		json.put("data", list);
		List<String> legendlist = new ArrayList<String>();
		for (Map<String, Object> map : list) {
			legendlist.add((String)map.get("name"));
		}
		JSONArray legend = new JSONArray();
		legend.addAll(legendlist);
		json.put("legend", legend);
		
		return json.toJSONString();
	}
	
	@RequestMapping(value = "/from_cnt", method = RequestMethod.GET)
	public String fromCnt() throws Exception {
		return "order/from_cnt";
	}
	
	/**
	 * desc: 订单按来源计数<br>
	 * date: 2015年3月30日 上午9:54:50<br>
	 * @author 开发者真实姓名[Andy]
	 * @param from
	 * @param to
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/from_cnt", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public @ResponseBody String fromCnt(@RequestParam("from") String from,
										@RequestParam("to") String to) throws Exception {
		from = from + " 00:00:00";
		to = to + " 23:59:59";
		List<Map<String, Object>> list = orderDao.fromCntData(from, to);
		
		JSONObject json = new JSONObject();
		json.put("data", list);
		List<String> legendlist = new ArrayList<String>();
		for (Map<String, Object> map : list) {
			legendlist.add((String)map.get("name"));
		}
		JSONArray legend = new JSONArray();
		legend.addAll(legendlist);
		json.put("legend", legend);
		
		return json.toJSONString();
	}
	
	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public String calendar() throws Exception {
		return "order/calendar";
	}
	
	/**
	 * desc: 订单数据日历热力图<br>
	 * date: 2015年5月12日 上午9:54:50<br>
	 * @author 开发者真实姓名[Andy]
	 * @param cid
	 * @param year
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/calendarData", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public @ResponseBody String calendar(@RequestParam("cid") String cid,
										 @RequestParam("year") String year) throws Exception {
		String from = year + "-01-01 00:00:00";
		String to = year + "-12-31 23:59:59";
		Map<String, Double> map = orderDao.calendarData(cid, from, to);
		
		JSONObject json = new JSONObject();
		json.putAll(map);
		
		return json.toJSONString();
	}

}

package com.chinadaas.domain.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Repository;

import com.chinadaas.domain.dao.IEntBasicDao;
import com.chinadaas.domain.dao.rm.EntBasicRowMapper;
import com.chinadaas.domain.entity.EntBasic;

/**
 * projectName: chinadaas-data<br>
 * desc: hbase企业表数据访问实现<br>
 * date: 2015年3月19日 下午1:44:20<br>
 * @author 开发者真实姓名[Andy]
 */
@Repository
public class EntBasicDaoImpl implements IEntBasicDao {
	
	@Autowired
	private HbaseTemplate hbaseTemplate;
	
	public void setHbaseTemplate(HbaseTemplate hbaseTemplate) {
		this.hbaseTemplate = hbaseTemplate;
	}
	
	@Override
	public List<EntBasic> find(String rowkey) {
		Get get = new Get(Bytes.toBytes(rowkey));
		Scan scan = new Scan(get);
		
		return hbaseTemplate.find(tableName+"_"+suffix, scan, new EntBasicRowMapper());
	}
	
	@Override
	public List<String> findRowkeyByName(String name) {
		Get get = new Get(Bytes.toBytes(name));
		Scan scan = new Scan(get);

		try {
			List<String> rs = hbaseTemplate.find(indexTable + "_" + suffix, scan, new RowMapper<String>() {

				public String mapRow(Result result, int rowNum) throws Exception {
					Map<byte[], byte[]> fs = result.getFamilyMap(F);
					fs.entrySet();
					return "123";
				}
			});
		} catch (Throwable t) {
			System.out.println(t);
			t.printStackTrace();
		}
		return null;
	}
	
}


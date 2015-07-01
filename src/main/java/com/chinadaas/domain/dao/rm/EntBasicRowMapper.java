package com.chinadaas.domain.dao.rm;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.data.hadoop.hbase.RowMapper;

import com.chinadaas.domain.entity.EntBasic;

/**
 * projectName: chinadaas-data<br>
 * desc: TODO<br>
 * date: 2015年3月19日 下午2:13:36<br>
 * @author 开发者真实姓名[Andy]
 */
public class EntBasicRowMapper implements RowMapper<EntBasic> {
	
	public static final byte[] F = Bytes.toBytes("f");
	private static final byte[] Q = Bytes.toBytes("A");

	public EntBasic mapRow(Result result, int rowNum) throws Exception {
		String id = Bytes.toString(result.getRow());
		String rs = Bytes.toString(result.getValue(F, Q));
		String[] datas = rs.split("\u0001", -1);
		EntBasic entBasic = new EntBasic();
		entBasic.setId(id);
		entBasic.setName(datas[0]);
		entBasic.setRegno(datas[2]);
		return entBasic;
	}

}


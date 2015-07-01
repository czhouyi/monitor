package com.chinadaas.domain.dao;

import java.util.List;

import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.chinadaas.domain.entity.EntBasic;

/**
 * projectName: chinadaas-data<br>
 * desc: hbase企业表数据访问接口<br>
 * date: 2015年3月19日 下午1:44:20<br>
 * @author 开发者真实姓名[Andy]
 */
@Repository
public interface IEntBasicDao {
	
	public static final byte[] F = Bytes.toBytes("f");
	public String tableName = "ENTERPRISEBASEINFOCOLLECT";
	public String suffix = "20140917";
	public byte[] main = Bytes.toBytes("A");
	public String indexTable = "ENTERPRISEBASEINFOCOLLECT_ENTNAME";
	
	public List<EntBasic> find(String rowkey) throws DataAccessException;
	
	public List<String> findRowkeyByName(String name) throws DataAccessException;
	
}


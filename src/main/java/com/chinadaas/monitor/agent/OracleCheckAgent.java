package com.chinadaas.monitor.agent;

import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.chinadaas.monitor.common.CheckResult;

/**
 * projectName: chinadaas-data<br>
 * desc: Oracle数据服务监控类<br>
 * date: 2015年3月31日 上午10:50:17<br>
 * 
 * @author 开发者真实姓名[Andy]
 */
public class OracleCheckAgent extends CheckAgent {

	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	@Override
	public CheckResult check() {
		CheckResult cr = new CheckResult();
		BasicDataSource source = null;
		try {
			source = new BasicDataSource();
			source.setDriverClassName(DRIVER);
			source.setUrl(meta.getUrl());
			source.setUsername(meta.getUsername());
			source.setPassword(meta.getPassword());
			JdbcTemplate template = new JdbcTemplate(source);

			template.queryForList("SELECT SYSDATE FROM DUAL");
			cr.success();
		} catch (DataAccessException e) {
			cr.danger(e.getMessage());
		} finally {
			if (source != null) {
				try {
					source.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return cr;
	}

}

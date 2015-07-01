package com.chinadaas.monitor.agent;

import com.chinadaas.domain.entity.ServerMeta;
import com.chinadaas.monitor.common.CheckStatus;

import junit.framework.TestCase;

/**
 * projectName: chinadaas-data<br>
 * desc: TODO<br>
 * date: 2015年4月3日 上午10:40:26<br>
 * @author 开发者真实姓名[Andy]
 */
public class CheckAgentTest extends TestCase {
	
	public void testTomcatCheck() {
		ServerMeta meta = new ServerMeta();
		meta.setUrl("http://192.168.11.11:7778/gsinfo");
		CheckAgent ca = new TomcatCheckAgent();
		ca.setMeta(meta);
//		assertEquals(CheckStatus.success, ca.check().getStatus());
	}
	
	public void testOracleCheck() {
		ServerMeta meta = new ServerMeta();
		meta.setUrl("jdbc:oracle:thin:@192.168.11.11:1521/orcl");
		meta.setUsername("gsinfo");
		meta.setPassword("gsinfo");
		CheckAgent ca = new OracleCheckAgent();
		ca.setMeta(meta);
//		assertEquals(CheckStatus.success, ca.check().getStatus());
	}
	
	public void testHadoopCheck() {
		ServerMeta meta = new ServerMeta();
		meta.setUrl("http://192.168.11.12:50070");
		CheckAgent ca = new HadoopCheckAgent();
		ca.setMeta(meta);
//		assertEquals(CheckStatus.success, ca.check().getStatus());
	}
	
	public void testSolrCheck() {
		ServerMeta meta = new ServerMeta();
		meta.setUrl("http://192.168.11.11:9999/solr");
		CheckAgent ca = new SolrCheckAgent();
		ca.setMeta(meta);
//		assertEquals(CheckStatus.success, ca.check().getStatus());
	}

}


package com.chinadaas.monitor.agent;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

import com.chinadaas.monitor.common.CheckResult;

/**
 * projectName: chinadaas-data<br>
 * desc: Solr服务校验代理<br>
 * date: 2015年3月31日 上午10:50:17<br>
 * @author 开发者真实姓名[Andy]
 */
public class SolrCheckAgent extends CheckAgent {
	
	@Override
	public CheckResult check() {
		CheckResult cr = new CheckResult();
		try {
			HttpSolrServer server = new HttpSolrServer(meta.getUrl());
			server.setSoTimeout(3000);
			server.setConnectionTimeout(1000);
			server.setDefaultMaxConnectionsPerHost(1000);
			server.setMaxTotalConnections(10);
			server.setFollowRedirects(false);
			server.setAllowCompression(true);
			server.setMaxRetries(1);
			server.getInvariantParams();
			int ping = server.ping().getQTime();
			cr.success();
		} catch (Exception e) {
			cr.danger(e.getMessage());
		}
		return cr;
	}

}


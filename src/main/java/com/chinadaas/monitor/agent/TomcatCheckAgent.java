package com.chinadaas.monitor.agent;

import org.apache.commons.httpclient.HttpStatus;

import com.chinadaas.monitor.common.CheckResult;
import com.chinadaas.util.HttpUtil;

/**
 * projectName: chinadaas-data<br>
 * desc: 应用服务监控类<br>
 * date: 2015年3月31日 上午10:50:17<br>
 * 
 * @author 开发者真实姓名[Andy]
 */
public class TomcatCheckAgent extends CheckAgent {

	@Override
	public CheckResult check() {
		int status = HttpUtil.getStatusCode(meta.getUrl());
		CheckResult cr = new CheckResult();
		if (status == HttpStatus.SC_OK) {
			cr.success();
		} else {
			cr.danger(String.format("Status code : %d", status));
		}
		return cr;
	}

}

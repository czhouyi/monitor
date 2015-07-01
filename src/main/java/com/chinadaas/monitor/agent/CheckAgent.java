package com.chinadaas.monitor.agent;

import com.chinadaas.domain.entity.ServerMeta;
import com.chinadaas.monitor.common.CheckResult;

/**
 * projectName: chinadaas-data<br>
 * desc: 服务校验代理抽象类<br>
 * date: 2015年3月31日 上午10:50:50<br>
 * @author 开发者真实姓名[Andy]
 */
public abstract class CheckAgent {
	
	protected ServerMeta meta;
	
	public void setMeta(ServerMeta meta) {
		this.meta = meta;
	}

	public abstract CheckResult check();
	
}


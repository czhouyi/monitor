package com.chinadaas.monitor.common;

/**
 * projectName: chinadaas-data<br>
 * desc: 服务检测结果<br>
 * date: 2015年3月31日 下午5:36:01<br>
 * @author 开发者真实姓名[Andy]
 */
public class CheckResult {
	
	// 服务检测状态
	private CheckStatus status;
	// 服务检测说明
	private String info;

	public CheckStatus getStatus() {
		return status;
	}

	public void setStatus(CheckStatus status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public void success() {
		this.status = CheckStatus.success;
	}
	
	public void warning(String info) {
		this.status = CheckStatus.warning;
		this.info = info;
	}
	
	public void danger(String info) {
		this.status = CheckStatus.danger;
		this.info = info;
	}
}


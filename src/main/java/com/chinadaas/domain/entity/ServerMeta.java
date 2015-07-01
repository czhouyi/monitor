package com.chinadaas.domain.entity;

import java.io.Serializable;

/**
 * projectName: chinadaas-data<br>
 * desc: 服务配置实体<br>
 * date: 2015年3月31日 上午10:53:02<br>
 * @author 开发者真实姓名[Andy]
 */
public class ServerMeta implements Serializable {
	
	private int id;
	private String name;
	private ServerType type;
	private String url;
	private String username;
	private String password;
	private boolean isMonitor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ServerType getType() {
		return type;
	}
	public void setType(ServerType type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isMonitor() {
		return isMonitor;
	}
	public void setMonitor(boolean isMonitor) {
		this.isMonitor = isMonitor;
	}
	
}


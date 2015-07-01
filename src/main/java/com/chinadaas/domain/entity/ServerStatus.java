package com.chinadaas.domain.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * projectName: chinadaas-data<br>
 * desc: 服务监控状态实体类<br>
 * date: 2015年3月31日 上午10:53:02<br>
 * @author 开发者真实姓名[Andy]
 */
public class ServerStatus implements Serializable{
	
	private int id;
	private ServerType type;
	private String name;
	private String status;
	private Date mdate;
	private String info;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ServerType getType() {
		return type;
	}
	public void setType(ServerType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}


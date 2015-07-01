package com.chinadaas.domain.entity;

import java.io.Serializable;

/**
 * projectName: chinadaas-data<br>
 * desc: 客户实体<br>
 * date: 2015年3月19日 下午4:04:00<br>
 * @author 开发者真实姓名[Andy]
 */
public class Customer implements Serializable {
	
	private String id;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}


package com.chinadaas.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * projectName: chinadaas-data<br>
 * desc: 企业照面信息<br>
 * date: 2015年3月19日 下午1:40:34<br>
 * 
 * @author 开发者真实姓名[Andy]
 */
public class EntBasic implements Serializable {

	private String id;
	private String name;
	private String region;
	private String regno;
	private BigDecimal regcap;
	private String address;
	private Date startDate;

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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public BigDecimal getRegcap() {
		return regcap;
	}

	public void setRegcap(BigDecimal regcap) {
		this.regcap = regcap;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}

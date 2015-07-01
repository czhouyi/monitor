package com.chinadaas.domain.entity;

import java.io.Serializable;

/**
 * projectName: chinadaas-data<br>
 * desc: 订单时序实体<br>
 * date: 2015年3月19日 下午4:04:00<br>
 * 
 * @author 开发者真实姓名[Andy]
 */
public class Order implements Serializable {

	private String series;
	private Double value;
	private String from;

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}

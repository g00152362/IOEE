package com.dot.pojo;

import java.util.Date;

public class TbAlarmData {

	private Integer id;
	
	private Date time;
	
	private Integer type;           //告警类型:0为critical 1为error 2为warning 3为notify
	
	private Integer catalog;           //告警目录类型
	
	private String content;
	
	private Integer status;
	
	private Integer alarm_id;           //故障告警id
	
	private String esn;         //设备的esn

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCatalog() {
		return catalog;
	}

	public void setCatalog(Integer catalog) {
		this.catalog = catalog;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEsn() {
		return esn;
	}

	public void setEsn(String esn) {
		this.esn = esn;
	}

	public Integer getAlarm_id() {
		return alarm_id;
	}

	public void setAlarm_id(Integer alarm_id) {
		this.alarm_id = alarm_id;
	}
	
	
}

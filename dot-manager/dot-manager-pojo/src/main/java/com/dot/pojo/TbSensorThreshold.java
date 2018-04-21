package com.dot.pojo;

import java.util.Date;

public class TbSensorThreshold {             //用于配置业务告警阈值
	
	private String sensor_type;
	
	private long id;

	private long sensor_cat_id;            //数据类型id
	
	private Float value_alarm_max;      //标准值告警上限
	
	private Float value_alarm_min;        //标准值告警下限
	
	private Float value_restore_max;       //标准值恢复上限
	
	private Float value_restore_min;        //标准值恢复下限
	
	private Float value1_alarm_max;
	
	private Float value1_alarm_min;
	
	private Float value1_restore_max;
	
	private Float value1_restore_min;
	
	private Float value2_alarm_max;
	
	private Float value2_alarm_min;
	
	private Float value2_restore_max;
	
	private Float value2_restore_min;
	
	private Float value3_alarm_max;
	
	private Float value3_alarm_min;
	
	private Float value3_restore_max;
	
	private Float value3_restore_min;
	
	private Date update_date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSensor_cat_id() {
		return sensor_cat_id;
	}

	public void setSensor_cat_id(long sensor_cat_id) {
		this.sensor_cat_id = sensor_cat_id;
	}

	public Float getValue_alarm_max() {
		return value_alarm_max;
	}

	public void setValue_alarm_max(Float value_alarm_max) {
		this.value_alarm_max = value_alarm_max;
	}

	public Float getValue_alarm_min() {
		return value_alarm_min;
	}

	public void setValue_alarm_min(Float value_alarm_min) {
		this.value_alarm_min = value_alarm_min;
	}

	public Float getValue_restore_max() {
		return value_restore_max;
	}

	public void setValue_restore_max(Float value_restore_max) {
		this.value_restore_max = value_restore_max;
	}

	public Float getValue_restore_min() {
		return value_restore_min;
	}

	public void setValue_restore_min(Float value_restore_min) {
		this.value_restore_min = value_restore_min;
	}

	public Float getValue1_alarm_max() {
		return value1_alarm_max;
	}

	public void setValue1_alarm_max(Float value1_alarm_max) {
		this.value1_alarm_max = value1_alarm_max;
	}

	public Float getValue1_alarm_min() {
		return value1_alarm_min;
	}

	public void setValue1_alarm_min(Float value1_alarm_min) {
		this.value1_alarm_min = value1_alarm_min;
	}

	public Float getValue1_restore_max() {
		return value1_restore_max;
	}

	public void setValue1_restore_max(Float value1_restore_max) {
		this.value1_restore_max = value1_restore_max;
	}

	public Float getValue1_restore_min() {
		return value1_restore_min;
	}

	public void setValue1_restore_min(Float value1_restore_min) {
		this.value1_restore_min = value1_restore_min;
	}

	public Float getValue2_alarm_max() {
		return value2_alarm_max;
	}

	public void setValue2_alarm_max(Float value2_alarm_max) {
		this.value2_alarm_max = value2_alarm_max;
	}

	public Float getValue2_alarm_min() {
		return value2_alarm_min;
	}

	public void setValue2_alarm_min(Float value2_alarm_min) {
		this.value2_alarm_min = value2_alarm_min;
	}

	public Float getValue2_restore_max() {
		return value2_restore_max;
	}

	public void setValue2_restore_max(Float value2_restore_max) {
		this.value2_restore_max = value2_restore_max;
	}

	public Float getValue2_restore_min() {
		return value2_restore_min;
	}

	public void setValue2_restore_min(Float value2_restore_min) {
		this.value2_restore_min = value2_restore_min;
	}

	public Float getValue3_alarm_max() {
		return value3_alarm_max;
	}

	public void setValue3_alarm_max(Float value3_alarm_max) {
		this.value3_alarm_max = value3_alarm_max;
	}

	public Float getValue3_alarm_min() {
		return value3_alarm_min;
	}

	public void setValue3_alarm_min(Float value3_alarm_min) {
		this.value3_alarm_min = value3_alarm_min;
	}

	public Float getValue3_restore_max() {
		return value3_restore_max;
	}

	public void setValue3_restore_max(Float value3_restore_max) {
		this.value3_restore_max = value3_restore_max;
	}

	public Float getValue3_restore_min() {
		return value3_restore_min;
	}

	public void setValue3_restore_min(Float value3_restore_min) {
		this.value3_restore_min = value3_restore_min;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getSensor_type() {
		return sensor_type;
	}

	public void setSensor_type(String sensor_type) {
		this.sensor_type = sensor_type;
	}

	
}

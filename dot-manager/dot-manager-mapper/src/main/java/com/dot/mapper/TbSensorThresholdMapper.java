package com.dot.mapper;

import org.apache.ibatis.annotations.Param;

import com.dot.pojo.TbSensorThreshold;

public interface TbSensorThresholdMapper {

	void createTable (@Param("tableName") String tableName);
	
	void add(TbSensorThreshold sensorThreshold);
	
	void del(long id);
	
	void update(TbSensorThreshold sensorThreshold);
	
	TbSensorThreshold getTbSensorThreshold(@Param("id")Long id);
	
	TbSensorThreshold[] list(@Param("offset")int offset,@Param("limit")int limit);
	
	int count();
	
	TbSensorThreshold getTbSensorThresholdByCat(@Param("sensor_cat_id")Long sensor_cat_id);
}

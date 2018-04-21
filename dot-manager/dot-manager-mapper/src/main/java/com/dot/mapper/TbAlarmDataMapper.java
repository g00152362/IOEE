package com.dot.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dot.pojo.TbAlarmData;

public interface TbAlarmDataMapper {

	void insert(TbAlarmData tbAlarmData);
	
	List<TbAlarmData> listRestore(@Param("esn")String esn,@Param("content")String content,@Param("status")Integer status,
			@Param("catalog")Integer catalog);            //通过esn和状态和数据类型来获取数据
	
	void update(TbAlarmData tbAlarmData);
	
	List<TbAlarmData> list(@Param("content")String content,@Param("startTime")Date startTime,
			@Param("endTime")Date endTime,@Param("type")Integer type,@Param("catalog")Integer catalog,
			@Param("status")Integer status,@Param("esn")String esn,@Param("offset")Integer offset,@Param("limit")Integer limit);
	
	int count(@Param("content")String content,@Param("startTime")Date startTime,
			@Param("endTime")Date endTime,@Param("type")Integer type,@Param("catalog")Integer catalog,
			@Param("status")Integer status,@Param("esn")String esn);
}

package com.dot.service;


import java.util.List;

import com.dot.pojo.TbSensorData;
import com.dot.pojo.reqDataCondition;

import dot.com.common.result.TaotaoResult;

public interface SensorDataService {
	TaotaoResult insertSensorData(TbSensorData sensorData);
	TaotaoResult deleteSensorDataByMac(String mac);
	
	public  List<TbSensorData> getSensorDataListByCond(reqDataCondition cond, long typeId);
	
	

}

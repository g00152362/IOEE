package com.dot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dot.mapper.TbSensorDataMapper;
import com.dot.pojo.TbSensorData;
import com.dot.pojo.TbSensorDataExample;
import com.dot.pojo.TbSensorDataExample.Criteria;
import com.dot.pojo.reqDataCondition;
import com.dot.service.SensorDataService;

import dot.com.common.result.TaotaoResult;

@Service
public class SensorDataServiceImpl implements SensorDataService{
	
	@Autowired
 	private TbSensorDataMapper itemMapper;
		

	@Override
	public TaotaoResult insertSensorData(TbSensorData sensorData) {
		// TODO Auto-generated method stub
		itemMapper.insert(sensorData);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteSensorDataByMac(String mac) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  List<TbSensorData> getSensorDataListByCond(reqDataCondition cond, long typeId) {
		// TODO Auto-generated method stub
		TbSensorDataExample example = new TbSensorDataExample();
		Criteria cr = example.createCriteria();
		cr.andMacEqualTo(cond.getMac());
		cr.andTypeIdEqualTo(typeId);
		//System.out.println("start"+cond.getStartTimestamp()+"end"+cond.getEndTimestamp());
		if(cond.getEndTimestamp() != 0){
			cr.andTimestampBetween(cond.getStartTimestamp(),cond.getEndTimestamp());
		}else
		{
			cr.andTimestampGreaterThan(cond.getStartTimestamp());
		}

		List<TbSensorData> result = itemMapper.selectByExample(example);
		if(result != null && result.size() >0){
			return result;
		}				 

		
		return null;
	}

}

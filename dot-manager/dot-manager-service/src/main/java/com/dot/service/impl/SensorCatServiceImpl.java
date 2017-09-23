package com.dot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dot.mapper.TbSensorCatMapper;
import com.dot.pojo.TbSensorCat;
import com.dot.pojo.TbSensorCatExample;
import com.dot.pojo.TbSensorCatExample.Criteria;
import com.dot.service.SensorCatService;

@Service
public class SensorCatServiceImpl implements SensorCatService {

	@Autowired
	private TbSensorCatMapper sensorCatMapper;
	
	@Override
	public List<TbSensorCat> getTbSensorCatList(long parentid) {
		// TODO Auto-generated method stub
		TbSensorCatExample example = new TbSensorCatExample();
		Criteria cr = example.createCriteria();
		cr.andParentIdEqualTo(parentid);
		List <TbSensorCat> result = sensorCatMapper.selectByExample(example);
		if(result != null && result.size() >0){
			return result;
		}
		
		return null;
	}

	@Override
	public TbSensorCat getSensorCatItemByName(String catName) {
		// TODO Auto-generated method stub
		TbSensorCatExample example = new TbSensorCatExample();
		Criteria cr = example.createCriteria();
		cr.andNameEqualTo(catName);
		List <TbSensorCat> result = sensorCatMapper.selectByExample(example);
		if(result != null)
		{
			return result.get(0);
		}
		
		return null;
	}

	@Override
	public long getSensorCatIdByName(String catName) {
		// TODO Auto-generated method stub
		TbSensorCatExample example = new TbSensorCatExample();
		Criteria cr = example.createCriteria();
		cr.andNameEqualTo(catName);
		List <TbSensorCat> result = sensorCatMapper.selectByExample(example);
		if(result != null)
		{
			return result.get(0).getId();
		}
		
		return -1;
	}


}

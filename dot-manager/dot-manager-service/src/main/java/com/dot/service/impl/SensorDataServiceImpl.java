package com.dot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dot.mapper.TbSensorDataMapper;
import com.dot.pojo.TbGatewayInfo;
import com.dot.pojo.TbSensorData;
import com.dot.pojo.TbSensorDataExample;
import com.dot.pojo.TbSensorDataExample.Criteria;
import com.dot.pojo.reqDataCondition;
import com.dot.service.SensorDataService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import dot.com.common.pojo.EUDataGridResult;
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
	//	example.setOrderByClause("");
		
		//System.out.println("start"+cond.getStartTimestamp()+"end"+cond.getEndTimestamp());
		if(cond.getEndTimestamp() != 0){
			cr.andTimeseqBetween(cond.getStartTimestamp(),cond.getEndTimestamp());

		}else
		{
			cr.andTimeseqGreaterThan(cond.getStartTimestamp());
		}

		List<TbSensorData> result = itemMapper.selectByExample(example);
		if(result != null && result.size() >0){
			return result;
		}				 

		
		return null;
	}
	
	@Override
	public  EUDataGridResult getSensorSortDataListByCond(reqDataCondition cond, long typeId,int isAsc) {
		// TODO Auto-generated method stub
		TbSensorDataExample example = new TbSensorDataExample();
	//	PageHelper.startPage(cond.getPageNumber(), cond.getPageSize());

		Criteria cr = example.createCriteria();
		cr.andMacEqualTo(cond.getMac());
		cr.andTypeIdEqualTo(typeId);
		//String s = "timeseq DESC" + "LIMIT 10,30";//+ cond.getPageNumber()+","+cond.getPageSize() ;
		if(isAsc == 0)
		{
			example.setOrderByClause("timeseq DESC");
		}
		else {
			example.setOrderByClause("timeseq ASC");
		}
		
		//System.out.println("start"+cond.getStartTimestamp()+"end"+cond.getEndTimestamp());
		if(cond.getEndTimestamp() != 0){
			cr.andTimeseqBetween(cond.getStartTimestamp(),cond.getEndTimestamp());

		}else
		{
			cr.andTimeseqGreaterThan(cond.getStartTimestamp());
		}
//		System.out.println("num"+cond.getPageNumber() + "size:"+cond.getPageSize());
		int total = itemMapper.countByExample(example);
		example.setLimit(cond.getPageSize());
		example.setOffset(cond.getPageNumber());
		List<TbSensorData> list = itemMapper.selectByExample(example);

		EUDataGridResult result = new EUDataGridResult();
		
		result.setTotal( Integer.valueOf(total));
		result.setRows(list);	


		
		return result;
	}
}

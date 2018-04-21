package com.dot.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dot.mapper.TbAlarmDataMapper;
import com.dot.mapper.TbSensorCatMapper;
import com.dot.mapper.TbSensorDataMapper;
import com.dot.mapper.TbSensorThresholdMapper;
import com.dot.pojo.TbAlarmData;
import com.dot.pojo.TbSensorCat;
import com.dot.pojo.TbSensorData;
import com.dot.pojo.TbSensorThreshold;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

@Service
public class AlarmService {

	@Autowired
	private TbSensorThresholdMapper tbSensorThresholdMapper;
	@Autowired
	private TbSensorCatMapper tbSensorCatMapper;
	@Autowired
	private TbAlarmDataMapper tbAlarmDataMapper;
	@Autowired
	private TbSensorDataMapper tbSensorDataMapper;
	
	public void createTbSensorThresholdTable() {           //用于创建表结构
		tbSensorThresholdMapper.createTable("tb_sensor_threshold");
	}
	
	public EUDataGridResult getTbSensorThresholdList(int offset,int limit) {            //获得阈值列表
		EUDataGridResult r = new EUDataGridResult();
		TbSensorThreshold[] sensorThresholds = tbSensorThresholdMapper.list(offset,limit);
		r.setTotal(tbSensorThresholdMapper.count());
		List<TbSensorThreshold> list = new ArrayList<TbSensorThreshold>();
		for(TbSensorThreshold t:sensorThresholds) {
			t.setSensor_type(getSensorCatNameByThresholdID(t.getId()));
			list.add(t);
		}
		r.setRows(list);
		return r;
	}
	
	public List<String> getTbSensorCatMapperListName() {           //读取所有数据类别的名字
		return tbSensorCatMapper.listNameAll();
	}
	
	public TaotaoResult saveSensorThreshold(TbSensorThreshold tbSensorThreshold) {          //存储一个阈值
		if(tbSensorThresholdMapper.getTbSensorThresholdByCat(tbSensorThreshold.getSensor_cat_id())!=null)
			return TaotaoResult.error(TaotaoResult.OBJ_IS_EXSIT);
		tbSensorThresholdMapper.add(tbSensorThreshold);
		return TaotaoResult.ok();
	}
	
	public TbSensorCat getSensorCatByName(String name) {             //通过名称获得对应数据类别对象
		return tbSensorCatMapper.selectByName(name);
	}
	
	public String getSensorCatName(Long id) {                    //根据id获取名字
		return tbSensorCatMapper.selectByPrimaryKey(id).getName();
	}
	
	public String getSensorCatNameByThresholdID(Long id) {                    //根据id获取类别名字
		return getSensorCatName(tbSensorThresholdMapper.getTbSensorThreshold(id).getSensor_cat_id());
	}
	
	public List<String> getSensorCatNameList(int offset,int limit) {
		TbSensorThreshold[] sensorThresholds = tbSensorThresholdMapper.list(offset,limit);
		List<String> names = new ArrayList<String>();
		for(TbSensorThreshold t:sensorThresholds)
			names.add(getSensorCatName(t.getSensor_cat_id()));
		return names;
	}
	
	public void delThresholdById(Long id) {
		tbSensorThresholdMapper.del(id);
	}
	
	public TaotaoResult getTbSensorThresholdByID(Long id) {       //通过ID获取TbSensorThreshold
		TaotaoResult t = new TaotaoResult();
		TbSensorThreshold tbSensorThreshold = tbSensorThresholdMapper.getTbSensorThreshold(id);
		t.setData(tbSensorThreshold);
		t.setStatus(TaotaoResult.SUCCESS);
		return t;
	}
	
	public void editSensorThreshold(TbSensorThreshold tbSensorThreshold) {          //修改一个阈值
		tbSensorThresholdMapper.update(tbSensorThreshold);
	}
	
	public Long scan(Long start) {
		List<TbSensorData> list = tbSensorDataMapper.list(start);
		System.out.println("扫描到的数据数量:"+list.size());
		TbSensorThreshold[] sensorThresholds = tbSensorThresholdMapper.list(0, 10);
		System.out.println("获取到阈值数量:"+sensorThresholds.length);
		for(TbSensorData tbSensorData:list) {
			if(tbSensorData.getTypeId()==null)
				continue;
			long cat = tbSensorData.getTypeId();
			TbSensorThreshold t = null;
			for(TbSensorThreshold tbSensorThreshold:sensorThresholds)
				if(tbSensorThreshold.getSensor_cat_id()==cat)
					t = tbSensorThreshold;
			if(t==null)
				continue;
			//下面是告警扫描逻辑
			boolean alarmFlag = false;
			if(tbSensorData.getValue()!=null)
				if(tbSensorData.getValue()>=t.getValue_alarm_max()||tbSensorData.getValue()<=t.getValue_alarm_min())
					alarmFlag = true;
			if(tbSensorData.getValue1()!=null)
				if(tbSensorData.getValue1()>=t.getValue1_alarm_max()||tbSensorData.getValue1()<=t.getValue1_alarm_min())
					alarmFlag = true;
			if(tbSensorData.getValue2()!=null)
				if(tbSensorData.getValue2()>=t.getValue2_alarm_max()||tbSensorData.getValue2()<=t.getValue2_alarm_min())
					alarmFlag = true;
			if(tbSensorData.getValue3()!=null)
				if(tbSensorData.getValue3()>=t.getValue3_alarm_max()||tbSensorData.getValue3()<=t.getValue3_alarm_min())
					alarmFlag = true;
			if(alarmFlag) {        //告警部分的逻辑
				//存储报警记录
				TbAlarmData tbAlarmData = new TbAlarmData();
				tbAlarmData.setEsn(tbSensorData.getMac());
				tbAlarmData.setTime(tbSensorData.getDate());
				tbAlarmData.setType(1);         //1为error
				tbAlarmData.setCatalog(0);           //0为故障
				tbAlarmData.setContent(getSensorCatName(tbSensorData.getTypeId()));
				tbAlarmData.setStatus(0);         //0为未处理
				tbAlarmDataMapper.insert(tbAlarmData);
			}else {            //恢复告警部分的逻辑
				boolean ff =true;
				if(tbSensorData.getValue()!=null)
					if(tbSensorData.getValue()>=t.getValue_restore_max()||tbSensorData.getValue()<=t.getValue_restore_min())
						ff = false;
				if(tbSensorData.getValue1()!=null)
					if(tbSensorData.getValue1()>=t.getValue1_restore_max()||tbSensorData.getValue1()<=t.getValue1_restore_min())
						ff = false;
				if(tbSensorData.getValue2()!=null)
					if(tbSensorData.getValue2()>=t.getValue2_restore_max()||tbSensorData.getValue2()<=t.getValue2_restore_min())
						ff = false;
				if(tbSensorData.getValue3()!=null)
					if(tbSensorData.getValue3()>=t.getValue3_restore_max()||tbSensorData.getValue3()<=t.getValue3_restore_min())
						ff = false;
				if(!ff)
					continue;
				//根据esn和数据类型获取到数据库中所有尚未被处理的告警data
				List<TbAlarmData> tbAlarmDatas = tbAlarmDataMapper.listRestore(tbSensorData.getMac(),
						getSensorCatName(tbSensorData.getTypeId()), 0,0);
				if(tbAlarmDatas.size()!=0) {
					System.out.println("获取到"+tbAlarmDatas.size()+"个需要取消报警的信息");
					Integer alarm_id=null;
					for(TbAlarmData data:tbAlarmDatas) {
						data.setStatus(2);        //设置为已经处理
						tbAlarmDataMapper.update(data);
						alarm_id = data.getId();
					}
					TbAlarmData data2 = new TbAlarmData();
					data2.setAlarm_id(alarm_id);
					data2.setCatalog(1);          //1为恢复
					data2.setContent(getSensorCatName(tbSensorData.getTypeId()));
					data2.setEsn(tbSensorData.getMac());
					data2.setStatus(0);
					data2.setTime(tbSensorData.getDate());
					data2.setType(3);
					tbAlarmDataMapper.insert(data2);
					System.out.println("增加一条恢复记录");
				}
			}
		}
		if(list.size()==0)
			return -1L;
		return list.get(list.size()-1).getId();	
	}
	
	public EUDataGridResult alarmList(Integer offset,Integer limit,Date start,Date end,
			String mac,String content,Integer type,Integer catalog,Integer status) {                //按照某一种数据类别获取全部的报警信息
		EUDataGridResult euDataGridResult = new EUDataGridResult();
		List<TbAlarmData> tbAlarmDatas = tbAlarmDataMapper.list(content, start, end, type, catalog, status, mac,offset,limit);
		euDataGridResult.setTotal(tbAlarmDataMapper.count("temperature", start, end, type, catalog, status, mac));
		euDataGridResult.setRows(tbAlarmDatas);
		return euDataGridResult;
	}
}

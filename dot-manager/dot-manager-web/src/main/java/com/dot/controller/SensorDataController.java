package com.dot.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dot.pojo.TbSensorCat;
import com.dot.pojo.TbSensorData;
import com.dot.pojo.reqDataCondition;
import com.dot.service.SensorCatService;
import com.dot.service.SensorDataService;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;
import net.sf.json.JSONObject;


@Controller
public class SensorDataController {
	@Autowired
	private SensorDataService dataService;	
	
	@Autowired
	private SensorCatService catService;
	

	@RequestMapping(value = "/pages/sensorData/getdataDetail", method = RequestMethod.POST)	
	@ResponseBody
	public EUDataGridResult getSensorDataDetail(reqDataCondition dataCond){
	//	System.out.println(dataCond.toString() );		
		TbSensorCat sc;
		sc = catService.getSensorCatItemByName(dataCond.getType());

		
		// get result according condition
		
		EUDataGridResult re =  dataService.getSensorSortDataListByCond(dataCond, sc.getId());;
		
		return re;
	}
	
	@RequestMapping(value = "/pages/sensorData/getdata", method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult getSensorData(reqDataCondition dataCond){
		
		//get type_id from type_name
		TbSensorCat sc;
		sc = catService.getSensorCatItemByName(dataCond.getType());
		
		// get result according condition
		List<TbSensorData> result = dataService.getSensorDataListByCond(dataCond, sc.getId());
		
		// get the time and value
		TbSensorData sd;
		ArrayList<String>  time = new ArrayList<String> ();
		ArrayList<Float>  value = new ArrayList<Float> ();		
		ArrayList<Float>  value1 = new ArrayList<Float> ();	
		ArrayList<Float>  value2 = new ArrayList<Float> ();	
		ArrayList<Float>  value3 = new ArrayList<Float> ();	
		if(result == null)
		{
			TaotaoResult r = new TaotaoResult();
			r.setStatus(TaotaoResult.OBJ_IS_NOT_EXSIT);
			return r;
		}
		
		for(int i=0; i<result.size();i++){
			sd = result.get(i);
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 
		
			time.add(sdf.format(sd.getDate()));
			value.add(sd.getValue());
			value1.add(sd.getValue1());
			value2.add(sd.getValue2());
			value3.add(sd.getValue3());
		}
		
		JSONObject resp = new JSONObject();
		resp.put("valueNumber", sc.getValueNumber());		
		resp.put("categories", time);
		resp.put("value0", value);

		if(sc.getValueNumber() >1){
			resp.put("value1", value1);			
		}
		if(sc.getValueNumber() >2){
			resp.put("value2", value2);			
		}	
		if(sc.getValueNumber() >3){
			resp.put("value3", value3);			
		}	

		
		TaotaoResult r = new TaotaoResult();
		r.setStatus(TaotaoResult.SUCCESS);
		r.setData(resp);
		
		//how to response??
		//System.out.println(dataCond);
		//itemService.createDeviceGroup(dgInfo);
		return r;
	}	

}

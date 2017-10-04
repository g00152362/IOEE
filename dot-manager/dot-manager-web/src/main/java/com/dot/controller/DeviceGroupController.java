package com.dot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dot.pojo.TbDeviceGroup;

import com.dot.service.DeviceGroupService;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

@Controller
public class DeviceGroupController {
	@Autowired
	private DeviceGroupService itemService;
	
	
	@RequestMapping("/pages/deviceGroup/list")
	@ResponseBody
	// page 和 rows 要和请求中一致
	public EUDataGridResult getDeviceGroupList(@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="30") Integer rows){
		EUDataGridResult item = itemService.getDeviceGroupList(page, rows);

		return item;
	}	
	
	@RequestMapping(value = "/pages/deviceGroup/add", method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult addDeviceGroup(TbDeviceGroup dgInfo){
		
		itemService.createDeviceGroup(dgInfo);
		return TaotaoResult.ok();
	}
	
	@RequestMapping(value = "/pages/deviceGroup/delete", method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult deleteDeviceGroup(@RequestParam("ids[]")String[] ids){

		for (int i = 0; i < ids.length; i++) {
			int id = Integer.parseInt(ids[i]);
			itemService.deleteDeviceGroupById(id);	
			//System.out.println(id);
		}
		return TaotaoResult.ok();
	}	
	
	@RequestMapping(value = "/pages/deviceGroup/update",method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult updateDeviceGroup(TbDeviceGroup dgInfo){
		TaotaoResult result = itemService.updateDeviceGroup(dgInfo);
		result.setStatus(TaotaoResult.SUCCESS);
		return result;
		
	}	
	
	@RequestMapping("/pages/deviceGroup/listid")
	@ResponseBody
	public TaotaoResult getGatewayListid(@RequestParam("id") Integer index){

		TaotaoResult result = itemService.getDeviceGroupDetailById(index.intValue());
		return result;

	}
	
	@RequestMapping("/pages/deviceGroup/listName")
	@ResponseBody
	public TaotaoResult getGatewayListByName(@RequestParam("name") String name){
		TbDeviceGroup dg= itemService.getDeviceGroupDetailByName(name);
		TaotaoResult result = new TaotaoResult();		
		if(null != dg)
		{

			result.setData(dg);
			result.setStatus (TaotaoResult.SUCCESS);
		}
		else{
			result.setStatus(TaotaoResult.OBJ_IS_NOT_EXSIT);
		}

		return result;

	}
	
	@RequestMapping("/pages/deviceGroup/listallname")
	@ResponseBody
	// page 和 rows 要和请求中一致
	public List<String> getDeviceGroupListAllName(){
		//List<TbDeviceGroup> list =(List<TbDeviceGroup>) item.getRows();
		//List<String,String> newlist = new List<String,String> ;
		/*
		for(int i=0;i<list.size();i++)
		{
			
		}
		*/
		List<String> list = itemService.getAllDeviceGroupNameList();

		return list;
	}		
		
}

package com.dot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dot.pojo.TbDeviceGroup;
import com.dot.pojo.TbDeviceModel;
import com.dot.service.DeviceModelService;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

@Controller
public class DeviceModelController {
	
	@Autowired
	private DeviceModelService itemService;
	
	
	@RequestMapping("/pages/deviceModel/list")
	@ResponseBody
	// page 和 rows 要和请求中一致
	public EUDataGridResult getDeviceGroupList(@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="30") Integer rows){
		EUDataGridResult item = itemService.getDeviceModelList(page, rows);

		return item;
	}
	
	@RequestMapping(value = "/pages/deviceModel/add", method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult addDeviceModel(TbDeviceModel dmInfo){
		
		itemService.createDeviceModel(dmInfo);
		return TaotaoResult.ok();
	}
	
	
	@RequestMapping(value = "/pages/deviceModel/delete", method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult deleteDeviceGroup(@RequestParam("ids[]")String[] ids){

		for (int i = 0; i < ids.length; i++) {

			itemService.deleteDeviceModelById(ids[i]);

		}
		return TaotaoResult.ok();
	}
	
	
	@RequestMapping(value = "/pages/deviceModel/update",method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult updateDeviceModel(TbDeviceModel dmInfo){
		TaotaoResult result = itemService.updateDeviceModel(dmInfo);
		result.setStatus(TaotaoResult.SUCCESS);
		return result;
		
	}
	
	@RequestMapping("/deviceModel/listid")
	@ResponseBody
	public TaotaoResult getGatewayListid(@RequestParam("id") String index){
		TaotaoResult result = itemService.getDeviceModelDetailById(index);
		return result;

	}	
	
	@RequestMapping("/pages/deviceModel/checkname")
	@ResponseBody
	public TaotaoResult checkIdExisted(@RequestParam("id") String index){
		int count = itemService.countDeviceModelByName(index);
		if(count == 0){
			return TaotaoResult.ok();
		}else{
			return TaotaoResult.error(TaotaoResult.OBJ_IS_EXSIT);
		}
	}		
	
}

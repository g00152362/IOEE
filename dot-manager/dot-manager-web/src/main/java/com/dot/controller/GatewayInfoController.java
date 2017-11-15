package com.dot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dot.jms.MessageSender;
import com.dot.pojo.TbDeviceGroup;
import com.dot.pojo.TbGatewayInfo;
import com.dot.pojo.TbGatewayInfoStat;
import com.dot.pojo.TbSoftwareRelease;
import com.dot.service.DeviceGroupService;
import com.dot.service.GatewayInfoService;
import com.dot.service.SoftwareReleaseService;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

@Controller

public class GatewayInfoController {
	@Autowired
	private GatewayInfoService itemService;
	@Autowired	
	private DeviceGroupService itemGroupService;
	
	@Autowired	
	private SoftwareReleaseService itemSoftwareService;	
	
	@Autowired  
    private MessageSender messageSender;	
		
	/**
	 * 网关信息录入添加 controller
	 * <p>Title: addGateway</p>
	 * <p>Description: </p>
	 * @param gwInfo
	 * @return
	 */
	@RequestMapping(value = "/pages/gateway/add", method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult addGateway(TbGatewayInfo gwInfo){
		
		System.out.println("Info:" + "Add a new gateway, esn:" + gwInfo.getSerialNumber());
		
		TaotaoResult result = new TaotaoResult();
		// if esn device is exsit, return error message
		if(null != itemService.getGatewayBySeriesNumber(gwInfo.getSerialNumber()) ){
			result.setStatus(TaotaoResult.OBJ_IS_EXSIT);
			result.setMsg("The gateway is exsit+ , esn:" +gwInfo.getSerialNumber());
			System.out.println("The gateway is exsit+ , esn:" +gwInfo.getSerialNumber());
			return result;
		}

		//if group is not exist, create a group
		if(gwInfo.getGroupName() != null){
			if(null == itemGroupService.getDeviceGroupDetailByName(gwInfo.getGroupName())){
				TbDeviceGroup deviceGroup = new TbDeviceGroup();
				deviceGroup.setName(gwInfo.getGroupName());
				itemGroupService.createDeviceGroup(deviceGroup);
			}
		}
		
		itemService.createGatewayInfoItem(gwInfo);
		return TaotaoResult.ok();
	}
	
	@RequestMapping(value = "/pages/gateway/delete", method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult deleteGateway(@RequestParam("ids[]")String[] ids){

		for (int i = 0; i < ids.length; i++) {
			itemService.deleteGatewayBySeriesNumber(ids[i]);		
		//	System.out.println(ids[i]);
		}
		return TaotaoResult.ok();
	}	
	
	/**
	 * 网关列表显示controller
	 * <p>Title: getGatewayList</p>
	 * <p>Description: </p>
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/pages/gateway/list")
	@ResponseBody

	public EUDataGridResult getGatewayList(@RequestParam(defaultValue="1") Integer offset,
			@RequestParam(defaultValue="30") Integer limit){
		EUDataGridResult item = itemService.getGatewayList(offset, limit);

		return item;
	}	
	
	

	
	@RequestMapping(value = "/pages/gateway/update",method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult updateGatewayStaticInfo(TbGatewayInfo gwInfo){
		

		TaotaoResult result = itemService.updateStaticGatewayInfo(gwInfo);
		
		return result;
		
	}

	@RequestMapping("/pages/gateway/listid")
	@ResponseBody
	public TaotaoResult getGatewayListid( String esn){
		TaotaoResult result = new TaotaoResult();
		TbGatewayInfo item = itemService.getGatewayBySeriesNumber(esn);
		if(item != null){
			result.setData(item);
			result.setStatus(TaotaoResult.SUCCESS);
		}
		else{
			result.setStatus(TaotaoResult.OBJ_IS_NOT_EXSIT);
			result.setMsg("The Gateway is not exist.( esn:" + esn +")");
		}
		 
		return result;
	}

	@RequestMapping("/pages/gateway/listBygroupName")
	@ResponseBody
	public EUDataGridResult getGatewayListByGroupName( String name){
		
		EUDataGridResult result;
		if (name==null){
			result = new EUDataGridResult();
			result.setTotal(0);
			return result;
		}
		
		result = itemService.getGatewayListByGroupName(name);

		return result;
	}	
	
	@RequestMapping("/pages/gateway/statistic")
	@ResponseBody
	public TaotaoResult getGatewayStat(){
		TbGatewayInfoStat stat = new TbGatewayInfoStat();
		stat = itemService.getGatewayStatusStat();		
		TaotaoResult result = new TaotaoResult();	
		result.setData(stat);
		result.setStatus(TaotaoResult.SUCCESS);
		return result;
	}
	
	@RequestMapping(value = "/pages/gateway/updateGroup",method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult updateGatewayGroupInfo(@RequestParam("group")String group, @RequestParam("ids[]")String[] ids){
		
	//	System.out.println(group + ids [0]);
	//	TaotaoResult result = itemService.updateStaticGatewayInfo(gwInfo);
	//	result.setStatus(200);
		TbGatewayInfo gw = new TbGatewayInfo();
		gw.setGroupName(group);
		for(int i=0; i<ids.length;i++)
		{
			gw.setSerialNumber(ids[i]);
			itemService.updateStaticGatewayInfo(gw);
		}
		return TaotaoResult.ok();
		
	}
	/*
	 *  Notify update the gateway version
	 */
	
	@RequestMapping(value = "/pages/gateway/updateVersion", method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult updateVersion(@RequestParam("ids")String ids,@RequestParam("esn")String esn){

		//System.out.println("software id"+ids+"esn:"+esn);
		long id = Long.valueOf(ids);
		//according the softid get version information
		TbSoftwareRelease item = itemSoftwareService.getSoftwareBomById(id);
		String mqttStr = new String();
		long v =(long)( 100 * Float.valueOf(item.getVersion()));
		mqttStr = "{" + "\"esn\":"+"\""+esn +"\","
					  + "\"version\":"+""+v+"," 
		              + "\"path\":"+"\""+item.getPath()+"\"" +"}";
		// send mqtt to the device
		messageSender.sendMessage("versionUpdate",mqttStr);
		return TaotaoResult.ok();
	}	
		
	

}

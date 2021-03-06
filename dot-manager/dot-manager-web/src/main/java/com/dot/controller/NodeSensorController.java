package com.dot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dot.pojo.TbNode;
import com.dot.service.NodeService;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

@Controller
public class NodeSensorController {
	@Autowired 
	private NodeService nodeService;
	
	@RequestMapping(value ="/node/add", method = RequestMethod.POST)
	@ResponseBody
	
	public TaotaoResult addNodeSensor(TbNode sensorNode){
		nodeService.addSensorNode(sensorNode);
		return TaotaoResult.ok();
	}
	
	@RequestMapping("/node/list")
	@ResponseBody
	// page 和 rows 要和请求中一致
	public EUDataGridResult getGatewayList(@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="30") Integer rows){
		EUDataGridResult item = nodeService.getSensorNodeList(page, rows);
		return item;
	}		

	@RequestMapping(value = "/rest/node/delete",method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult deleteGateway(String[] ids){
		
		for (int i = 0; i < ids.length; i++) {
			Long idl = Long.parseLong(ids[i]);
			 nodeService.deleteNodeById(idl);		
			//System.out.println(ids[i]);
		}
		return TaotaoResult.ok();
	}	
}

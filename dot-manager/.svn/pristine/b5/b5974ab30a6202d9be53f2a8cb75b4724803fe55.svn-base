package com.dot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dot.pojo.TbSensorCat;
import com.dot.service.SensorCatService;


@Controller

@RequestMapping("/sensor/cat")
public class SensorCatController {
	@Autowired
	private SensorCatService sensorCatService;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	@ResponseBody
	public List categoryList(@RequestParam(value ="id",defaultValue = "0") long parentId){
		List catlist = new ArrayList();
		List <TbSensorCat> list =  sensorCatService.getTbSensorCatList(parentId);
		for (TbSensorCat tbSensorCat : list) {
			Map node = new HashMap<>();
			node.put("id", tbSensorCat.getId());
			node.put("text", tbSensorCat.getName());
			node.put("state",tbSensorCat.getIsParent()? "closed" : "open");
		//	node.put("state","open");
			catlist.add(node);
	
		}
		return catlist;
	}
}

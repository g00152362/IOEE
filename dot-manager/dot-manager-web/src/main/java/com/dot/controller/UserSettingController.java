package com.dot.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dot.pojo.TbUserSetting;
import com.dot.service.UserSettingService;

import dot.com.common.result.TaotaoResult;

@Controller
public class UserSettingController {
	
	@Autowired
	private UserSettingService setService;
	
	@RequestMapping(value = "/pages/userSetting/update", method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult updateUserSetting(TbUserSetting setting){
		
		setting.setUpdatedTime(new Date());
		if(setting.getId() != 0){
			// update the setting
			setService.updateUserSetting(setting);
		}
		else{
			// add the setting
			setService.insertUserSetting(setting);
		}
		
		
		return TaotaoResult.ok();
	}
	
	@RequestMapping(value = "/pages/userSetting/get", method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult getUserSetting(String pageName){
		TaotaoResult result = new TaotaoResult();
		// tbd: not support username now!
		List<TbUserSetting> list = setService.getSettingListByUserPage(null, pageName);
		if(list.size() != 0){
			TbUserSetting us = list.get(0);
			result.setStatus(TaotaoResult.SUCCESS);
			result.setData(us);
		}else{
			result.setStatus(TaotaoResult.OBJ_IS_NOT_EXSIT);
		}
		return result;
		
	}	

}

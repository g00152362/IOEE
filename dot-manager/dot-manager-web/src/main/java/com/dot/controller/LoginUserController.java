package com.dot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dot.pojo.TbLoginUser;
import com.dot.service.LoginUserService;



@Controller
public class LoginUserController {
	@Autowired
	private LoginUserService itemService;
	
	
	@RequestMapping(value = "/pages/loginuser/login",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userLogin(String username,String password){
		
		TbLoginUser user = itemService.getLoginUserByName(username);
		//login_status success invalid
		Map<String, Object> map = new HashMap<String, Object>();
		if(user == null){
			map.put("login_status", "invalid");
			map.put("login_msg", "用户不存在！");
			return map;
		}

		if(  true != password.equals(user.getPassword()) ){
			map.put("login_status", "invalid");
			map.put("login_msg", "密码错误！");
			return map;
		}
		 map.put("login_status", "success");
		 map.put("redirect_url", "index.html");
		 

	    // String json = new ObjectMapper().writeValueAsString(map);
	   	return map;
		
	}
}

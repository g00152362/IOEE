package com.dot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dot.pojo.TbLoginUser;
import com.dot.service.LoginUserService;

import dot.com.common.pojo.EUDataGridResult;



@Controller
public class LoginUserController {
	@Autowired
	private LoginUserService itemService;
	@RequestMapping(value = "/pages/loginuser/list",method = RequestMethod.GET)
	@ResponseBody	
	// page 和 rows 要和请求中一致
	public EUDataGridResult getDLoginUserList(@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="30") Integer rows){
		EUDataGridResult item = itemService.getLoginUserList (page, rows);

		return item;
	}
		
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

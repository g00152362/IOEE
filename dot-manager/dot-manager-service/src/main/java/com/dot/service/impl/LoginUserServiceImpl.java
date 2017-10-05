package com.dot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dot.mapper.TbLoginUserMapper;
import com.dot.pojo.TbLoginUser;
import com.dot.service.LoginUserService;

@Service
public class LoginUserServiceImpl  implements LoginUserService{
	@Autowired
 	private TbLoginUserMapper itemMapper;	

	@Override
	public TbLoginUser getLoginUserByName(String username) {
		TbLoginUser item = itemMapper.selectByPrimaryKey(username);
		return item;
	}

}

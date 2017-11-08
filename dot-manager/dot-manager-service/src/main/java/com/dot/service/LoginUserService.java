package com.dot.service;

import com.dot.pojo.TbLoginUser;

import dot.com.common.pojo.EUDataGridResult;

public interface LoginUserService {
	TbLoginUser getLoginUserByName(String username);
	EUDataGridResult getLoginUserList(Integer page,Integer rows);	
}

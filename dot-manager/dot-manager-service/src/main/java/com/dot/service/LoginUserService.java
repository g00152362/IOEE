package com.dot.service;

import com.dot.pojo.TbLoginUser;

public interface LoginUserService {
	TbLoginUser getLoginUserByName(String username);
}

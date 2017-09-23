package com.dot.service;

import java.util.List;


import com.dot.pojo.TbUserSetting;


import dot.com.common.result.TaotaoResult;

public interface UserSettingService {
	public TaotaoResult insertUserSetting(TbUserSetting setting);
	public  List<TbUserSetting> getSettingListByUserPage(String username, String pagename);
	public TaotaoResult updateUserSetting(TbUserSetting setting);
}

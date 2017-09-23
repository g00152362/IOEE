package com.dot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dot.mapper.TbUserSettingMapper;
import com.dot.pojo.TbDeviceModel;
import com.dot.pojo.TbUserSetting;
import com.dot.pojo.TbUserSettingExample;
import com.dot.pojo.TbUserSettingExample.Criteria;
import com.dot.service.UserSettingService;

import dot.com.common.result.TaotaoResult;

@Service
public class UserSettingServiceImpl implements UserSettingService{
	@Autowired
 	private TbUserSettingMapper itemMapper;

	@Override
	public TaotaoResult insertUserSetting(TbUserSetting setting) {
		// TODO Auto-generated method stub
		itemMapper.insert(setting);
		return TaotaoResult.ok();
	}

	@Override
	public List<TbUserSetting> getSettingListByUserPage(String username, String pagename) {
		// TODO Auto-generated method stub
		if(pagename == null){
			return null;
		}		
		
		TbUserSettingExample example = new TbUserSettingExample();
		Criteria cr = example.createCriteria();
		
		if(username != null){
			cr.andUserNameEqualTo(username);
		}
		cr.andPageNameEqualTo(pagename);


		List<TbUserSetting> result = itemMapper.selectByExample(example);
		if(result != null && result.size() >0){
			return result;
		}				 

		
		return null;
	}
	
	@Override
	public TaotaoResult updateUserSetting(TbUserSetting setting)  {
		// TODO Auto-generated method stub
		itemMapper.updateByPrimaryKey(setting);
		return TaotaoResult.ok();
	}

}

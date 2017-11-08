package com.dot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dot.mapper.TbLoginUserMapper;
import com.dot.pojo.TbLoginUser;
import com.dot.pojo.TbLoginUserExample;
import com.dot.service.LoginUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import dot.com.common.pojo.EUDataGridResult;

@Service
public class LoginUserServiceImpl  implements LoginUserService{
	@Autowired
 	private TbLoginUserMapper itemMapper;	

	@Override
	public TbLoginUser getLoginUserByName(String username) {
		TbLoginUser item = itemMapper.selectByPrimaryKey(username);
		return item;
	}

	@Override
	public EUDataGridResult getLoginUserList(Integer page, Integer rows) {
		TbLoginUserExample ex = new TbLoginUserExample();
		PageHelper.startPage(page, rows);
		List<TbLoginUser> list = itemMapper.selectByExample(ex);

		EUDataGridResult result = new EUDataGridResult();
		PageInfo<TbLoginUser> pageInfo = new PageInfo<>(list);
		int total = (int) pageInfo.getTotal();
		result.setTotal( Integer.valueOf(total));
		result.setRows(list);			

		return result;
		
	}

}

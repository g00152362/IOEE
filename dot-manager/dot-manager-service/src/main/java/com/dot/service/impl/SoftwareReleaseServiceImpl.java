package com.dot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dot.mapper.TbSoftwareReleaseMapper;
import com.dot.pojo.TbSoftwareRelease;
import com.dot.pojo.TbSoftwareReleaseExample;
import com.dot.service.SoftwareReleaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;


@Service
public class SoftwareReleaseServiceImpl implements SoftwareReleaseService{
	
	@Autowired
 	private TbSoftwareReleaseMapper itemMapper;	

	@Override
	public TaotaoResult insertReleaseBom(TbSoftwareRelease software) {
		// TODO Auto-generated method stub
		itemMapper.insert(software); 		
		return TaotaoResult.ok();		
	}

	@Override
	public EUDataGridResult getSoftwareBomList(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		TbSoftwareReleaseExample ex = new TbSoftwareReleaseExample();
		PageHelper.startPage(page, rows);
		List<TbSoftwareRelease> list = itemMapper.selectByExample(ex);

		EUDataGridResult result = new EUDataGridResult();
		PageInfo<TbSoftwareRelease> pageInfo = new PageInfo<>(list);
		
		int total = (int) pageInfo.getTotal();
		result.setTotal( Integer.valueOf(total));
		result.setRows(list);			

		return result;		
	}

	@Override
	public TaotaoResult updateSoftwareBom(TbSoftwareRelease setting) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TbSoftwareRelease getSoftwareBomById(long id) {
		// TODO Auto-generated method stub
		TbSoftwareRelease item = itemMapper.selectByPrimaryKey(id);
		return item;
	}

}

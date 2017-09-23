package com.dot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dot.mapper.TbNodeMapper;
import com.dot.pojo.TbNode;
import com.dot.pojo.TbNodeExample;
import com.dot.service.NodeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

@Service
public class NodeServiceImpl implements NodeService {
	@Autowired
	TbNodeMapper tbNodeMap;
	
	@Override
	public TaotaoResult addSensorNode(TbNode item) {

		item.setCreated(new Date());
		tbNodeMap.insert(item);
		return TaotaoResult.ok();
	}

	@Override
	public EUDataGridResult getSensorNodeList(Integer page, Integer rows) {

		//S1: lookup the basic information from table sernsorNOde
		TbNodeExample ex = new TbNodeExample();
		PageHelper.startPage(page, rows);
		
		List<TbNode> list = tbNodeMap.selectByExampleWithBLOBs(ex);
		
		//S2 according the records, add the run information
		EUDataGridResult result = new EUDataGridResult();
		
		PageInfo<TbNode> pageInfo = new PageInfo<>(list);
		int total = (int) pageInfo.getTotal();
		result.setTotal( Integer.valueOf(total));
		result.setRows(list);	
		
		return result;				

	}

	@Override
	public TaotaoResult deleteNodeById(Long id) {
		tbNodeMap.deleteByPrimaryKey(id);
		return TaotaoResult.ok();
	}

}

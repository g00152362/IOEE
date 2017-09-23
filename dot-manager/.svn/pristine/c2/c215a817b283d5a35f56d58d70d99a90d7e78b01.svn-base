package com.dot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dot.mapper.TbItemCatMapper;
import com.dot.pojo.TbItemCat;
import com.dot.pojo.TbItemCatExample;
import com.dot.pojo.TbItemCatExample.Criteria;
import com.dot.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired 
	private TbItemCatMapper itemCatMapper;
	@Override
	public  List<TbItemCat> getTbItemCatList(long parentid) {
		// TODO Auto-generated method stub
		TbItemCatExample example = new TbItemCatExample();
		Criteria cr = example.createCriteria();
		cr.andParentIdEqualTo(parentid);
		List <TbItemCat> result = itemCatMapper.selectByExample(example);
		if(result != null && result.size() >0){
			return result;
		}
		
		return null;
	}

}

package com.dot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dot.mapper.TbDeviceGroupMapper;
import com.dot.mapper.TbGatewayInfoMapper;
import com.dot.pojo.TbDeviceGroup;
import com.dot.pojo.TbDeviceGroupExample;
import com.dot.pojo.TbDeviceGroupExample.Criteria;

import com.dot.pojo.TbGatewayInfoStat;
import com.dot.service.DeviceGroupService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

@Service
public class DeviceGroupServiceImpl implements DeviceGroupService{

	@Autowired
 	private TbDeviceGroupMapper itemMapper;
	
	@Autowired
	private TbGatewayInfoMapper gwMapper;
	
 	@Override
 	
	public TaotaoResult createDeviceGroup(TbDeviceGroup deviceGroup){

		itemMapper.insert(deviceGroup); 		
		return TaotaoResult.ok();
	}
 	
	public TaotaoResult updateDeviceGroup(TbDeviceGroup deviceGroup){
		itemMapper.updateByPrimaryKey(deviceGroup);
		return TaotaoResult.ok();
	}
	
	
	public TaotaoResult deleteDeviceGroupById(int groupId){
		itemMapper.deleteByPrimaryKey(groupId);
		return TaotaoResult.ok();

	}
	
	// to be developed
	public TaotaoResult deleteDeviceGroupByName(String groupName){
		return TaotaoResult.ok();
	}
	
	
	public EUDataGridResult getDeviceGroupList(Integer page,Integer rows){
		
		TbDeviceGroupExample ex = new TbDeviceGroupExample();
		PageHelper.startPage(page, rows);
		
		List<TbDeviceGroup> list = itemMapper.selectByExample(ex);
		// update the count of group
		TbDeviceGroup dg= null;
		TbGatewayInfoStat stat = new TbGatewayInfoStat();
	
		for(int i=0;i<list.size();i++)
		{
			dg = list.get(i);
			stat = gwMapper.countStatByGroupName(dg.getName());
			dg.setOfflinenumber(stat.getOffline());
			dg.setOnlinenumber(stat.getOnline());
			dg.setUnregeisterednumber(stat.getUnregistered());
			
		}
		
		
		EUDataGridResult result = new EUDataGridResult();
		PageInfo<TbDeviceGroup> pageInfo = new PageInfo<>(list);
		int total = (int) pageInfo.getTotal();
		result.setTotal( Integer.valueOf(total));
		result.setRows(list);			

		return result;
	}
	
	
	public TaotaoResult getDeviceGroupDetailById(int groupId){
		TaotaoResult result = new TaotaoResult();
		TbDeviceGroup item =itemMapper.selectByPrimaryKey(groupId);
		
		result.setData(item);
		result.setStatus(TaotaoResult.SUCCESS);
		
		return result;
	}
	
	
	public TbDeviceGroup getDeviceGroupDetailByName(String groupName){
		
		TbDeviceGroupExample ex = new TbDeviceGroupExample();
		Criteria cr = ex.createCriteria();
		
		
		cr.andNameEqualTo(groupName);
		List<TbDeviceGroup> list = itemMapper.selectByExample(ex);
		if(list != null && list.size()>0){
			TbDeviceGroup item = list.get(0);

			TbGatewayInfoStat stat = new TbGatewayInfoStat();

			stat = gwMapper.countStatByGroupName(item.getName());
			item.setOfflinenumber(stat.getOffline());
			item.setOnlinenumber(stat.getOnline());
			item.setUnregeisterednumber(stat.getUnregistered());
			return item;
		}
		return null;		

	}

	@Override
	public List <String> getAllDeviceGroupNameList() {
		// TODO Auto-generated method stub
		List <String> list = itemMapper.selectAllGroupName();
		
		return list;
	}
	
	
}

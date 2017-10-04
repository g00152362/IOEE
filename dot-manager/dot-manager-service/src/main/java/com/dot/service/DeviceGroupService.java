package com.dot.service;

import java.util.List;

import com.dot.pojo.TbDeviceGroup;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

public interface DeviceGroupService {
	TaotaoResult createDeviceGroup(TbDeviceGroup deviceGroup);
	TaotaoResult updateDeviceGroup(TbDeviceGroup deviceGroup);
	TaotaoResult deleteDeviceGroupById(int groupId);
	TaotaoResult deleteDeviceGroupByName(String groupName);
	EUDataGridResult getDeviceGroupList(Integer page,Integer rows);
	TaotaoResult getDeviceGroupDetailById(int groupId);
	TbDeviceGroup getDeviceGroupDetailByName(String groupName);
	List <String> getAllDeviceGroupNameList();
	

}

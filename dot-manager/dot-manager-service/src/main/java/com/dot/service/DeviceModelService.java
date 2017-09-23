package com.dot.service;


import com.dot.pojo.TbDeviceModel;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

public interface DeviceModelService {
	TaotaoResult createDeviceModel(TbDeviceModel deviceModel);
	TaotaoResult updateDeviceModel(TbDeviceModel deviceModel);
	TaotaoResult deleteDeviceModelById(String modelId);
	EUDataGridResult getDeviceModelList(Integer page,Integer rows);
	TaotaoResult getDeviceModelDetailById(String modelId);
	int countDeviceModelByName(String modelId);

}

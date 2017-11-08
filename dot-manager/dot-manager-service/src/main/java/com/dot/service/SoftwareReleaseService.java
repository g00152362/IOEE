package com.dot.service;


import com.dot.pojo.TbSoftwareRelease;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

public interface SoftwareReleaseService {
	public TaotaoResult insertReleaseBom(TbSoftwareRelease software);
	public EUDataGridResult getSoftwareBomList(Integer page,Integer rows);
	public TaotaoResult updateSoftwareBom(TbSoftwareRelease setting);
	public TbSoftwareRelease getSoftwareBomById(long id);
	
	
}

package com.dot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dot.mapper.TbGatewayInfoMapper;

import com.dot.pojo.TbGatewayInfo;
import com.dot.pojo.TbGatewayInfoExample;
import com.dot.pojo.TbGatewayInfoExample.Criteria;
import com.dot.pojo.TbGatewayInfoStat;

import com.dot.service.GatewayInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

@Service
public class GatewayInfoServiceImpl implements GatewayInfoService {
	
	final static long TIMEOUT = 3600*1000; //unit ms
	@Autowired
	private TbGatewayInfoMapper itemMapper;
	
	@Override
/**
 *  add gateway basic information to DB	
 * <p>Title: createGatewayInfoItem</p>
 * <p>Description: </p>
 * @param item
 * @return
 * @see com.dot.service.GatewayInfoService#createGatewayInfoItem(com.dot.pojo.TbGatewayInfo)
 */
	public TaotaoResult createGatewayInfoItem(TbGatewayInfo item) {
		// TODO Auto-generated method stub
		item.setCreated(new Date());
	//	item.setReportTime(new Date());
	//	item.setUpdatedTime(new Date());
		
		itemMapper.insert(item);
		
		return TaotaoResult.ok();
	}
	
	/**
	 * get the gateway information to UI display
	 * <p>Title: getGatewayList</p>
	 * <p>Description: </p>
	 * @param page
	 * @param rows
	 * @return
	 * @see com.dot.service.GatewayInfoService#getGatewayList(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public EUDataGridResult getGatewayList(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		//S1: lookup the basic information from table gatewayinfo
		TbGatewayInfoExample ex = new TbGatewayInfoExample();
	//	PageHelper.startPage(page, rows);
		PageHelper.startPage(1, 1);
		List<TbGatewayInfo> list = itemMapper.selectByExample(ex);

		/*SET DEVICE status*/
		for(int i=0; i<list.size();i++){
			TbGatewayInfo tt = list.get(i);
			tt.setStatus(caluStatus(tt));
			
		}	
		
		//S2 according the records, add the run information
		EUDataGridResult result = new EUDataGridResult();
		
		PageInfo<TbGatewayInfo> pageInfo = new PageInfo<>(list);
		//PageInfo pageInfo = new PageInfo(list);
		int total = (int) pageInfo.getTotal();
		result.setTotal( Integer.valueOf(total));
		result.setRows(list);	
		
		return result;				

	}
	
	public int caluStatus(TbGatewayInfo item){
		//get current time
		Date cur = new Date();
		// NEW DEVICE
		if(item == null){
			return 2;
		}
		Date rpt = item.getReportTime();

		if(rpt == null )
		{
			return 2;
		}
		if(cur.getTime()-rpt.getTime() > TIMEOUT){

			return 0;
		}

		return 1;
	}
	
	/**
	 * only update the filed from jms message, include
	 *     software_version,hardware_version,reboot time, reboot type
	 * <p>Title: updateRunGatewayInfo</p>
	 * <p>Description: </p>
	 * @param record
	 * @return
	 */
	@Override
	public TaotaoResult updateRunGatewayInfo(TbGatewayInfo record) {
		// TODO Auto-generated method stub
		itemMapper.updateByPrimaryKeySelective(record);


		return TaotaoResult.ok();
	}
	
	/**
	 * only update the filed from input form, include
	 *     position,device_name, type 
	 * <p>Title: updateStaticGatewayInfo</p>
	 * <p>Description: </p>
	 * @param record
	 * @return
	 */	
	@Override
	public TaotaoResult updateStaticGatewayInfo(TbGatewayInfo record) {
		// TODO Auto-generated method stub
		itemMapper.updateByPrimaryKeySelective(record);

		return TaotaoResult.ok();		
	}

	@Override
	public TaotaoResult deleteGatewayBySeriesNumber(String esn) {
		// TODO Auto-generated method stub
		itemMapper.deleteByPrimaryKey(esn);
		return TaotaoResult.ok();
	}

	@Override
	public TbGatewayInfo getGatewayBySeriesNumber(String esn) {
		// TODO Auto-generated method stub
		TbGatewayInfo item = itemMapper.selectByPrimaryKey(esn);
		if(item != null)
		{
			item.setStatus(caluStatus(item));
		}

		return item;
	}

	@Override
	public TbGatewayInfoStat getGatewayStatusStat() {
		// TODO Auto-generated method stub
		return itemMapper.countAllStat();
	}

	@Override
	public TbGatewayInfoStat getGatewayStatusStatByGroupName(String groupName) {
		// TODO Auto-generated method stub
		return itemMapper.countStatByGroupName(groupName);
	}

	@Override
	public EUDataGridResult getGatewayListByGroupName(String groupName) {
		// TODO Auto-generated method stub
		TbGatewayInfoExample ex = new TbGatewayInfoExample();
		Criteria cr = ex.createCriteria();
		cr.andGroupNameEqualTo(groupName);
		List<TbGatewayInfo> list = itemMapper.selectByExample(ex);

		/*SET DEVICE status*/
		for(int i=0; i<list.size();i++){
			TbGatewayInfo tt = list.get(i);
			tt.setStatus(caluStatus(tt));
			
		}	
		
		//S2 according the records, add the run information
		EUDataGridResult result = new EUDataGridResult();
		
		PageInfo<TbGatewayInfo> pageInfo = new PageInfo<>(list);
		int total = (int) pageInfo.getTotal();
		result.setTotal( Integer.valueOf(total));
		result.setRows(list);	
		
		return result;	

	}
	

}

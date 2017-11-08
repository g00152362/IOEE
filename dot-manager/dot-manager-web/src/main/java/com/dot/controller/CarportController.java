package com.dot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dot.pojo.msgCarport;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

@Controller
public class CarportController {
	@RequestMapping("/pages/carport/list")
	@ResponseBody
	// page 和 rows 要和请求中一致
	//public EUDataGridResult getCarportList(@RequestParam(defaultValue="1") Integer page,
			//@RequestParam(defaultValue="30") Integer rows)
	public EUDataGridResult getCarportList(){
		EUDataGridResult item = new EUDataGridResult();
			item.setTotal(6);
			List<msgCarport> row = new ArrayList<msgCarport>();
			String[] u={"毛泽西","邓大平","习远平"};
			for(int i=0;i<6;i++){
				msgCarport tmp = new msgCarport();
				tmp.setId(i);
				if(i < 3){
					tmp.setStatus(1);
					tmp.setCarPlate("苏A888"+i+"6"+i);
					tmp.setTelephone("1386"+i+"68886"+i);
					tmp.setUserMac("88:66:77:55:44:3"+i);
					tmp.setUsername(u[i]);
					tmp.setAddress("白宫"+i+"号");
				}
				else
				{
					tmp.setStatus(0);
				}
				row.add(tmp);
				

			}
			
			item.setRows(row);
		return item;
	}	
	@RequestMapping("/pages/ownerInfo/add")
	@ResponseBody
	// page 和 rows 要和请求中一致
	//public EUDataGridResult getCarportList(@RequestParam(defaultValue="1") Integer page,
			//@RequestParam(defaultValue="30") Integer rows)
	public TaotaoResult addOwnInfo(msgCarport user){
		TaotaoResult item = new TaotaoResult();
		if(user.getUsername() != null){
			item.setStatus(TaotaoResult.SUCCESS);
		}
		else{
			item.setStatus(TaotaoResult.OBJ_IS_EXSIT);
		}
			
		return item;
	}	
}

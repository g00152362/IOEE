package com.dot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dot.pojo.TbItem;
import com.dot.service.ItemService;


import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemid}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemid){
		TbItem item = itemService.getItemById(itemid);
		return item;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	// page 和 rows 要和请求中一致
	public EUDataGridResult getItemList(@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="30") Integer rows){
		EUDataGridResult item = itemService.getItemList(page, rows);
		
		return item;
	}	
	
	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	@ResponseBody
	//要求表单中的名字和TBITEM中的名字一样！！！
	public TaotaoResult creatItem(TbItem item,String desc) throws Exception{
		TaotaoResult result = itemService.createItem(item,desc);
		return result;
	}
	@RequestMapping("/rest/page/item-edit")
	public String editWindowOpen(){
		return "item-edit";
	}
	
/* test*/	
	@RequestMapping("/rest/item/param/item/query/{itemid}")
	@ResponseBody
	public TaotaoResult getTestitemResult(@PathVariable Long itemid){
		TbItem item = itemService.getItemById(itemid);
		TaotaoResult result =  new TaotaoResult();
		result.setStatus(200);
		result.setData(item);
		
		return result;
	}
}

package com.dot.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dot.pojo.TbDeviceGroup;
import com.dot.pojo.TbDeviceModel;
import com.dot.service.DeviceModelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;
import net.sf.json.JSONObject;

@Controller
public class DeviceModelController {
	
	@Autowired
	private DeviceModelService itemService;
	
	@Autowired
	private ServletContext context; 
	
	@RequestMapping("/pages/deviceModel/list")
	@ResponseBody
	// page 和 rows 要和请求中一致
	public EUDataGridResult getDeviceModelList(@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="30") Integer rows){
		EUDataGridResult item = itemService.getDeviceModelList(page, rows);

		return item;
	}
	
	
	@RequestMapping(value = "/pages/deviceModel/add", method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult addDeviceModel(TbDeviceModel dmInfo){
		
		itemService.createDeviceModel(dmInfo);
		return TaotaoResult.ok();
	}
	
	
	@RequestMapping(value = "/pages/deviceModel/delete", method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult deleteDeviceGroup(@RequestParam("ids[]")String[] ids){

		for (int i = 0; i < ids.length; i++) {

			itemService.deleteDeviceModelById(ids[i]);

		}
		return TaotaoResult.ok();
	}
	
	
	@RequestMapping(value = "/pages/deviceModel/update",method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult updateDeviceModel(TbDeviceModel dmInfo){
		TaotaoResult result = itemService.updateDeviceModel(dmInfo);
		result.setStatus(TaotaoResult.SUCCESS);
		return result;
		
	}
	
	@RequestMapping(value = "/pages/deviceModel/listid" ,method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult getGatewayListid(@RequestParam("id") String index){
		TaotaoResult result = itemService.getDeviceModelDetailById(index);
		return result;

	}	
	
	@RequestMapping("/pages/deviceModel/checkname")
	@ResponseBody
	public TaotaoResult checkIdExisted(@RequestParam("id") String index){
		int count = itemService.countDeviceModelByName(index);
		if(count == 0){
			return TaotaoResult.ok();
		}else{
			return TaotaoResult.error(TaotaoResult.OBJ_IS_EXSIT);
		}
	}		
	@RequestMapping("/pages/deviceModel/fileupload")
	@ResponseBody	

	public String fileUpload( @RequestParam(value = "photofile") MultipartFile file,HttpServletRequest request) throws JsonProcessingException { 
		
		String saveDir = "images/uploadfile";
		String savePath = context.getRealPath("") + "/WEB-INF/"+saveDir;
		//String baseUrl = String.format("%s://%s:%d/WEB-INF/uploadfile/",request.getScheme(),  request.getServerName(), request.getServerPort());

		Map<String, Object> map = new HashMap<String, Object>();
		
		String json;

		 if (file ==null || file.isEmpty()) {
    	     map.put("message", "文件不能为空");
    	     json = new ObjectMapper().writeValueAsString(map);
		     return json;
	      }
		 /*
        if (file.getSize()>file_size) {
            map.put("message", "文件大小不能超过2M");
           return map;
           }*/
		try {
			String originalFilename = file.getOriginalFilename();
			// use random filename to save
			long millis = System.currentTimeMillis();
			Random random = new Random();
			int end3 = random.nextInt(999);
			String str = millis + String.format("%03d", end3);
			

			String newFileName = str+originalFilename.substring(originalFilename.lastIndexOf("."));
			File savefile = new File(savePath,newFileName);
			if(!savefile.getParentFile().exists()) {  
	            //如果目标文件所在的目录不存在，则创建父目录  
	            System.out.println("目标文件所在目录不存在，准备创建它！");  
	            if(!savefile.getParentFile().mkdirs()) {  
	                System.out.println("创建目标文件所在目录失败！");  
	                map.put("message", "创建目标文件所在目录失败！");
	                json = new ObjectMapper().writeValueAsString(map);
	                return json;  
	            }  
	        }  			
			
			FileUtils.copyInputStreamToFile(file.getInputStream(), savefile);
			map.put("message", "Y");// 文件上传成功
			map.put("fileName", "images/uploadfile/"+newFileName);
			

		} catch (IOException e) {
			map.put("message", "N");// 文件上传失败
		}
		json = new ObjectMapper().writeValueAsString(map);
		return json;
	} 
	
	@RequestMapping("/pages/deviceModel/listallname")
	@ResponseBody
	// page 和 rows 要和请求中一致
	public List<String> getDeviceModelListAllName(){

		List<String> list = itemService.getAllDeviceModelNameList();

		return list;
	}		
			
	
}

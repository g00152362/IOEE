<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="nodeAddForm" class="nodeForm" method="post">
	    <table cellpadding="5">

	        <tr>
	            <td>接入点ID:</td>
	            <td><input class="easyui-textbox" type="text" name="esn" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>接入点名称:</td>
	            <td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
		        
	        <tr>
	            <td>安装地点:</td>
	            <td><input class="easyui-textbox" type="text" name="position" style="width: 280px;"></input></td>
	        </tr>	
	        
	        <tr>
	      	  <td>传感能力:</td>
	        	<td>
	        	<a href="javascript:void(0)" class="easyui-linkbutton selectSensorCat"  onclick = "selcectSensorCap()">选择传感项</a>
	    		<input type="hidden" id="sensor_Item" name="sensorItem"/>	        	
	   	        	<span id="output" ></span>
	        	</td>
	        </tr>		         
              
	    </table>
	    <input type="hidden" name="id"/>
	    <input type="hidden" name="sensorNode"/>	    

	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
 <div id="sensorCatSelectWindow" class="easyui-window" title="选择传感能力" data-options="modal:true,closed:true,iconCls:'icon-save',href:'sensor_select'" style="width:80%;height:80%;padding:10px;">
</div>
<script type="text/javascript">
	//页面初始化完毕后执行此方法
//	$(function(){
		//创建富文本编辑器
	//	itemAddEditor = TAOTAO.createEditor("#itemAddForm [name=desc]");
		//初始化类目选择和图片上传器
//		TAOTAO.init({fun:function(node){
			//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
			//TAOTAO.changeItemParam(node, "itemAddForm");
//		}});
//	});
	
	function selcectSensorCap(){
		$('#sensorCatSelectWindow').window('open');
		
	}

	//提交表单
	function submitForm(){
		//有效性验证
		if(!$('#nodeAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		var x=document.getElementById("nodeAddForm");
		for (var i=0;i<x.length;i++)
		{
			console.log(x.elements[i].name,x.elements[i].value);
		}

		$.post("/node/add",$("#nodeAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','录入接入点成功!');
			}
		});
	}
	
	function clearForm(){
		$('#nodeAddForm').form('reset');
		itemAddEditor.html('');
	}
</script>

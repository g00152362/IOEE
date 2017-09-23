<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="gatewayEditForm" class="gatewayForm" method="post">
		<input type="hidden" name="id"/>
		    <table cellpadding="5">
	        <tr>
	            <td>设备号码:</td>
	            <td><input class="easyui-textbox" type="text" name="serialNumber" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>设备名称:</td>
	            <td><input class="easyui-textbox" type="text" name="deviceName" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>设备类型:</td>
	            <td><input class="easyui-textbox" type="text" name="type" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>		        
	        <tr>
	            <td>安装地点:</td>
	            <td><input class="easyui-textbox" type="text" name="position" style="width: 280px;"></input></td>
	        </tr>	 
              
	    </table>
	    <input type="hidden" name="gatewayParams"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	function submitForm(){
		if(!$('#gatewayEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
//		$("#itemeEditForm [name=price]").val(eval($("#itemeEditForm [name=priceView]").val()) * 1000);
//		itemEditEditor.sync();
		
		var paramJson = [];
		$("#gatewayEditForm .params li").each(function(i,e){
			var trs = $(e).find("tr");
			var group = trs.eq(0).text();
			var ps = [];
			for(var i = 1;i<trs.length;i++){
				var tr = trs.eq(i);
				ps.push({
					"k" : $.trim(tr.find("td").eq(0).find("span").text()),
					"v" : $.trim(tr.find("input").val())
				});
			}
			paramJson.push({
				"group" : group,
				"params": ps
			});
		});
		paramJson = JSON.stringify(paramJson);
		
		$("#gatewayEditForm [name=gatewayParams]").val(paramJson);
		
		$.post("/rest/gateway/update",$("#gatewayEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改网关参数成功!','info',function(){
					$("#gatewayEditWindow").window('close');
					$("#gatewayList").datagrid("reload");
				});
			}
		});
	}
</script>

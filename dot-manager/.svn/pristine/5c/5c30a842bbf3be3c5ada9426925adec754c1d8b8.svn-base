<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" 	type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" 	src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" 	src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>



<div style="padding: 10px 10px 10px 10px">
	<a href="#" class="easyui-linkbutton" onclick="getChecked()">确认</a>
</div>

<div class="easyui-panel" style="padding: 5px">
	<ul id="sensorcatselect" class="easyui-tree"
		data-options ="url:'/sensor/cat/list',
		method:'get',
		animate:true,
		checkbox:true"></ul>
</div>



<script>

	function getChecked() {
		var nodes = $('#sensorcatselect').tree('getChecked',['checked','indeterminate']);
		var s = '';
		var paramJson = [];	
		var ps = [];
		var g ="";
		for (var i = 0; i < nodes.length; i++) {
	//		console.log( nodes[i].text);
			if($('#sensorcatselect').tree('isLeaf',nodes[i].target)){
				if (s != '')
					s += ',';
				if(ps !="")
					ps +=',';
				s += nodes[i].text;
				ps+=nodes[i].text;
			}
			else{
 					if(g !="" && ps != ""){
	//					console.log(g +"xxx"+ ps);
						paramJson.push({"group":g,
							"item":ps});						
					}
					if(ps !=""){
						ps ="";
					}					
					g= nodes[i].text;	
			}
		}
		paramJson.push({"group":g,"item":ps});			
		paramJson = JSON.stringify(paramJson);
		
	//	alert(paramJson);
		var e = parent.document.getElementById("output");
		e.innerHTML = s;
		parent.document.getElementById("sensor_Item").value =paramJson;

		$('#sensorCatSelectWindow').window('close');
	
	}
</script>
</body>

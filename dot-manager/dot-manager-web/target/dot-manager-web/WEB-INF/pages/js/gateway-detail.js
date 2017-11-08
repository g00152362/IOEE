var gw_detail = gw_detail || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		gw_detail.$form =  $('#deviceGroupAddForm');
		gw_detail.$esn = localStorage['esn'];
		gw_detail.$type ="";
		
		var s=["Home","Device","Detail"];
		setNavBar(s);

		initDeviceDetailPage();
	});
})(jQuery, window);

function initDeviceDetailPage(){
	var para = {esn:gw_detail.$esn};
	var type ="";
	//设置为同步，需要等待返回数据进行下一次查询
	$.ajaxSetup({
		async: false
		});
	
	// 加载基本信息	
	$.post("gateway/listid",para,
		function(data){
			if(data.status != 200){
				alert("get data fail!");
				return;
			}
			addDeviceInfo(data.data);
			changeStatue(data.data.status);
			type = data.data.type;

	});
	
	para ={id:type};
	$.post("deviceModel/listid",para,
			function(data){
				if(data.status != 200){
					alert("get data fail!");
					return;
				}
				addDeviceInfo(data.data);
		}); 
	
}
 


function addDeviceInfo(info)
{
	$.each(info, function(key, val) { 
		if(key == "bootTime" || key == "reportTime" || key == "updateTime"){
			$("#"+key).text(formatDateTime(val));
		}
		else if(key == "lastRebootType"){
			$("#"+key).text(formatRebootReason(val));
		}else if(key == "memory" || key == "storage"){
			var v = val;
			if(val >=1000){
				v =val/1000;
				$("#"+key).text(v +'G');
			}else{
				$("#"+key).text(v +'M');
			}
		}
		else{
			$("#"+key).text(val);
		}
		

	});

}
	
	function changeStatue(value)
	{
		$("#status").html(formatStatus(value));
	};
	
 /*
	loadDeviceScript =function (pos) {
		console.log(pos); 		
		var script = document.createElement("script");
		script.type = "text/javascript";
		script.src = "http://api.map.baidu.com/api?v=2.0&ak=pta3ObbcGPGMGfFTGAI97lbkzRhCaShc&callback=loadMap(\"" + pos +"\")";
		document.body.appendChild(script);

	loadMap = function(pos)
	{
		var map = new BMap.Map("allmap", {enableMapClick:false});           
		var myGeo = new BMap.Geocoder();		
		myGeo.getPoint(pos, function(point){
			if (point) {
				var address = new BMap.Point(point.lng, point.lat);
				map.centerAndZoom(address,15);                 
				map.enableScrollWheelZoom(true);							
				
				var myIcon = new BMap.Icon("images/map_anchor_blue.png",
						{anchor: new BMap.Size(100, 100), imageOffset: new BMap.Size(0,0)});

				var marker2 = new BMap.Marker(address,{icon:myIcon});  
				map.addOverlay(marker2);
				var label = new BMap.Label(pos,{offset:new BMap.Size(20,20),});
				 label.setStyle({
					 "color":"blue",                 
					 "fontSize":"14px", 
					 "border":"0", 
					 "backgroundColor" :"0.05",						 
					 });
				
					marker2.setLabel(label); 				
			}
		}, "南京市");		 
	};
}	;
	*/
	
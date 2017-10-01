var gw_detail = gw_detail || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		gw_detail.$form =  $('#deviceGroupAddForm');
		gw_detail.$esn = localStorage['esn'];
		
		var s=["Home","Device","Detail"];
		setNavBar(s);

		initDeviceDetailPage();
	});
})(jQuery, window);

function initDeviceDetailPage(){
	var para = {esn:gw_detail.$esn};
	
	$.post("gateway/listid",para,
		function(data){
			if(data.status != 200){
				alert("get data fail!");
				return;
			}
			addDeviceInfo(data.data);
			changeStatue(data.data.status);
		//	loadDeviceScript(data.data.position);

	}); 
}
 


function addDeviceInfo(info)
	{
		// fill position;
		$("#serialNumber").text(info.serialNumber);
		$("#position").text(info.position);
		$("#deviceName").text(info.deviceName);
		$("#groupName").text(info.groupName);	
		$("#type").text(info.type);	
		$("#vendor").text(info.vendor);	
		$("#mac").text(info.mac);	
		$("#ip").text(info.ip);	
		$("#bootTime").text(formatDateTime(info.bootTime));	
		$("#lastRebootType").text(formatRebootReason(info.lastRebootType));	
		$("#softwareVersion").text(info.softwareVersion);	
		$("#hardwareVersion").text(info.hardwareVersion);	
		$("#reportTime").text(formatDateTime(info.reportTime));	
		$("#updateTime").text(formatDateTime(info.updatedTime));
	};
	
	// TODO: the color need be changed!!!! how to do?
	function changeStatue(value)
	{
	 		if(value == 1)
	 		{
	 			$("#pic_status").attr("src","/images/circle_green");
				$("#status").text(" Online");
			

	 		}
	  		else if(value == 0){
	  			$("#pic_status").attr("src","/images/circle_gray.png");
				$("#status").text(" Offline");

	 		} else if(value ==2){
	  			$("#pic_status").attr("src","/images/circle_gray.png");  
	  			$("#status").text(" Unregiested");  			
	 		}

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
	
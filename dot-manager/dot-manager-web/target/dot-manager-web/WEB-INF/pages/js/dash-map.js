var dashMap = dashMap || {};


(function($, window, undefined){

	"use strict";
	$(document).ready(function()
	{
		var s=["Home","Map"];
		setNavBar(s);	
		
		loadJScript();
	});

})(jQuery, window);

//百度地图API功能
function loadJScript() {
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "http://api.map.baidu.com/api?v=2.0&ak=pta3ObbcGPGMGfFTGAI97lbkzRhCaShc&callback=init_map";
	document.body.appendChild(script);
}; 


//init mapping
function init_map() {
	var index = 0;
	var mapdata = new Array();    	

	var map = new BMap.Map("allmap", {enableMapClick:false});            // 创建Map实例
/* 		var point = new BMap.Point(116.404, 39.915); // 创建点坐标
	map.centerAndZoom(point,15);                 
	map.enableScrollWheelZoom();                 //启用滚轮放大缩小 */
	
	var myGeo = new BMap.Geocoder();
	
	//async can imporve the response
	$.ajaxSetup({async: true});	
	// loading data
	 $.get("gateway/list",function(data){
		 for(var i=0;i<data.rows.length;i++){
			 mapdata.push( data.rows[i]);
		//	 console.log(data.rows[i]);
		 }
		 // begin to add marker, each 300 us to display one marker
		 addMarker();			 
	  });			

		function addMarker(){
			var add = mapdata[index].position;
			geoMarkerSet(index,add);
			index++;
		};

	function geoMarkerSet(ii,add){
		myGeo.getPoint(add, function(point){
			if (point)
			{
				//if not finish, recall this function
				if(ii < mapdata.length-1)
				{
					setTimeout(addMarker,300);
				} 
			
					
				var address = new BMap.Point(point.lng, point.lat);
				
				if(ii == 0){
					//the first work as the center of map
					map.centerAndZoom(address,14);                 
					map.enableScrollWheelZoom();							
				}

				//创建蓝色图标						
					var myIcon = new BMap.Icon("images/map_anchor_blue.png",
						{anchor: new BMap.Size(100, 100), imageOffset: new BMap.Size(0,0)});
			
					
				var marker2 = new BMap.Marker(address,{icon:myIcon});  // 创建标注	
				map.addOverlay(marker2);
				var label = new BMap.Label(mapdata[ii].deviceName);
				 label.setStyle({
					 "color":"#00AAFF",                 
					 "fontSize":"6px", 
					 "border":"0", 
					 "backgroundColor" :"0.05",						 
					 });
				
					marker2.setLabel(label); 
			}
		}, "南京市"); 
	};
	

} ;





	
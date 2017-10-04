g_sendTimerId = -1;
g_navBarName=[];

Date.prototype.format = function(format){ 
    var o =  { 
    "M+" : this.getMonth()+1, //month 
    "d+" : this.getDate(), //day 
    "h+" : this.getHours(), //hour 
    "m+" : this.getMinutes(), //minute 
    "s+" : this.getSeconds(), //second 
    "q+" : Math.floor((this.getMonth()+3)/3), //quarter 
    "S" : this.getMilliseconds() //millisecond 
    };
    if(/(y+)/.test(format)){ 
    	format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
    }
    for(var k in o)  { 
	    if(new RegExp("("+ k +")").test(format)){ 
	    	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
	    } 
    } 
    return format; 
};

function serializeStrToObj(str){
	str = str.replace(/&/g, "','" );
	str = str.replace(/=/g, "':'" );
	str = "({'" +str + "'})" ;
	obj = eval(str);
	return obj;
	}

// 格式化时间
function formatDateTime(val){
	var now = new Date(val);
	return now.format("yyyy-MM-dd hh:mm:ss");
}
		
//FORMAT reboot reason
function formatRebootReason(val,row){
	switch(val){
	case 1:
		return "Cold Start";
	case 2:
		return "Command Reboot" ;
	case 3: 
		return "Reboot by Alige controller";
	case 4:
		return "Factory reset";
	case 5:
		return "Reboot from Usb Upgrade";
	case 6:
		return "reboot by 3rd APP";
	default:
		return "Unknow Reason";
	
	}
}
function initNavBar(){
	g_navBarName = new Array();
	g_navBarName['Home'] = "<a href='#' onClick=\"IotLoadFrame('dash-map.html','js/dash-map.js')\">监控</a>";
	g_navBarName['Device'] = "<a href='#' onClick=\"IotLoadFrame('gateway-list.html','js/gateway-list.js')\">设备</a>";
	g_navBarName['Model'] = "<a href='#' onClick=\"IotLoadFrame('devicemodel-list.html','js/devicemodel-list.js')\">设备类型</a>";
	g_navBarName['Group'] = "<a href='#' onClick=\"IotLoadFrame('devicegroup-list.html','js/devicegroup-list.js')\">设备组</a>";	
	g_navBarName['Add'] = "<strong>新增</strong>";
	g_navBarName['List'] = "<strong>列表</strong>";
	g_navBarName['Edit'] = "<strong>编辑</strong>";
}				

function setNavBar(s){
	var ul = document.getElementById("navbar_x");	
	if(ul == null){
		console.log('can find DOM navbar_x!');
		return;
	}
	
	var li =null;
	for(var i=0;i<4;i++){
		if(i>=s.length){
			// remove
			li = document.getElementById("sbar"+i);
			if(li != null){
				li.innerHTML = "";
			}
		}
		if(s[i] != null){
			li = document.getElementById("sbar"+i);
			if(li == null){
				li = document.createElement("li");
		 		li.setAttribute("id", "sbar"+i);
		 		ul.appendChild(li);				
			}

			li.innerHTML = g_navBarName[ s[i]];
	
				
			
		}		
	}
}

 function IotLoadFrame(url,script){
	// 	console.log(url +"__________"+ g_sendTimerId);
	 	if(url != 'data-chart.html' && g_sendTimerId >=0){
	 		clearInterval (g_sendTimerId);
	 		g_sendTimerId = -1;
	 	}
		//debug need notification!!!! after release to change true to improve the time	 	
		$.ajaxSetup({
			  cache: false
			});
		
		$( "#content" ).load(url,function(){
			if(script != null){

				$.getScript(script);
			}
			else{
				console.log("error, no js!")
			}			
		});

 };
 
 //ERROR CODE PROCESS, multi-lang modify here
 function IotErrorMsg(errorCode){
	 var errMsg="";
	 switch(errorCode){
	 case 200:
		 errMsg = "Success";
		 break; 
	 case 100:
		 errMsg = "The item is not exist";
		 break; 
	 case 101:
		 errMsg = "The item is existed";
		 break; 
	default:
		 errMsg = "Unknow code";
		 break; 			 
	 };
	 
	 alert(errMsg);
 }
 
 // 绑定数据到控件中去
 function BindSelect(ctrlName, url) {
	
	    var control = $('#' + ctrlName);
	    //设置Select2的处理
	    control.select2({
	        allowClear: true,
//	        formatResult: formatResult,
//	        formatSelection: formatSelection,
//	        escapeMarkup: function (m) {
//	            return m;
//	        }
	    });
	    //绑定Ajax的内容
	    $.getJSON(url, function (data) {
	        control.empty();//清空下拉框
	        //返回String数组
	        for(var i=0;i<data.length;i++){
	            control.append("<option value='" +  data[i] + "'>&nbsp;" + data[i]+ "</option>");
	        };
	    });
	}
 





var ds = ds || {};
Data = new Array() ;
currTime = 0; 
userSettingId = 0;

if(typeof valueType == "undefined"){
	valueType = {};
	valueType.light = 1;
	valueType.humidity = 2;
	valueType.accelerate = 3;
	valueType.gyroscope = 4;	
	
};


(function($, window, undefined){

	"use strict";
	$(document).ready(function()
	{
		 ds.$showchart = [];
		 ds.$chartObj=[];
		 ds.$form = document.forms['sensorChartSettingForm'];	



		// init the pick
        $('#reservationtime').daterangepicker({
            timePicker: true,
            timePickerIncrement: 30,
            format: 'YYYY/MM/DD HH:mm:ss'
          });
  
        for(var i=0;i<10; i++){
            ds.$chartObj[i] = echarts.init(document.getElementById('div'+i));
        }
        loadSetting();
	/*	
		var lightChart = echarts.init(document.getElementById('div11'));
        var humidityChart = echarts.init(document.getElementById('div12'));
        var accelerateChart = echarts.init(document.getElementById('div21'));
        var gyroscopeChart = echarts.init(document.getElementById('div22'));     
        
        
        
          

        // 指定图表的配置项和数据
        var lightOption = {
            title: {
                text: 'light',
            },
            yAxis: {
            	name : 'lux',
            },
        };
        
      var humidityOption = {
                title: {
                    text: 'Humidity',
                },
                yAxis: {
                	name : '%',
                },
            }; 
      var accelerateOption = {
              title: {
                  text: 'Accelerate',
              },
              yAxis: {
              	name : 'g/s',
              },
          };   
      
      var gyroscopeOption = {
              title: {
                  text: 'Gyroscope',
              },
              yAxis: {
              	name : 'dps',
              },
          };          
      
      	lightChart.setOption(commOption);      
        lightChart.setOption(lightOption);
        humidityChart.setOption(commOption); 
        humidityChart.setOption(humidityOption);
        accelerateChart.setOption(multiOption);      
        accelerateChart.setOption(accelerateOption);     
        gyroscopeChart.setOption(multiOption);      
        gyroscopeChart.setOption(gyroscopeOption);         
        // tmp data request, time is stamp since 1970:0

 
        
        var dd= new Date("2017-09-15 22:05:00");
        var start = dd.getTime()/1000;
        var dd1 = new  Date("2017-09-15 22:07:00");
        var end = dd1.getTime()/1000;
    	var param = {mac:"12:D2:08:2D:07:98",
    				 type:'light',
    				 startTimestamp:start,
    				 endTimestamp:0
    				 };
         postData(lightChart,param,Data,valueType.light);
         
         param.type = 'humidity';
      	 postData(humidityChart,param,valueType.humidity);
    	
      	 param.type = 'accelerate';
         postData(accelerateChart,param,valueType.accelerate);
        
         //gyroscope
     	 param.type = 'gyroscope';
         postData(gyroscopeChart,param,valueType.gyroscope);         
         
    	currTime = end;    
    	*/   
/*
    	g_sendTimerId = setInterval(function () {
			
    	//	console.log("g_sendTimerId"+g_sendTimerId);
		//WRONG!!!!!	
			var param12 = {mac:"12:D2:08:2D:07:98",
					 type:'light',
					 startTimestamp:currTime,
					 endTimestamp:0};
	    	currTime =currTime +10;


	    //	postNewData(lightChart,param12,true,valueType.light);	

	    	param12.type = 'humidity';
	   // 	postNewData(humidityChart,param12,false,valueType.humidity);	//		valueType.humidity
	    	
	    	param12.type = 'accelerate';
	    //	postNewData(accelerateChart,param12,false,valueType.accelerate);
	    	
	    	param12.type = 'gyroscope';
	  //  	postNewData(gyroscopeChart,param12,false,valueType.gyroscope);	    	
	    	
		}, 2500);*/
	});
	


})(jQuery, window);



function postData(obj,param,dataArray){

	$.post("sensorData/getdata",param, function(data){
		if(data.status == 200){

			obj.setOption({
	 	        xAxis: {
	 	            data: data.data.categories
	 	        }
	       	 });
			//save data
			if(Data.length == 0)
			{
				Data.push({categories:data.data.categories});
			}			

			var seriesArray=new Array();
			var i = 0;
			for(i=0;i <data.data.valueNumber;i++ ){
					var valuename = eval('data.data.value'+i);
					var obj1 = { data: valuename};
					seriesArray.push(obj1);
			}
			obj.setOption({ series: seriesArray });	
			// save the data
			Data.push(seriesArray);

		}

	});	

}

function postNewData(obj,param,bc,num){
	//console.log(param);

	$.post("sensorData/getdata",param, function(data){
		if(data.status == 200){
			//save data
			if(bc == true)
			{
			//console.log( Data[0]);
				Data[0] =  new Array();
				for (var i = 0 ;i <data.data.categories.length;i++){
				
					if(Data[0] != null && Data[0].length >20)
					{
						 Data[0].shift();
					}

			        Data[0].push(data.data.categories[i]);				
				}
				console.log(Data[0]);	
			}	
				
			obj.setOption({
	 	        xAxis: {
	 	            data:  Data[0],
	 	        }
	       	 });
			
		//	console.log(Data[type]);
			//console.log(type);
			// td: max the valueNumber is 4,reserver all data queue
			var seriesArray=new Array();
			var i = 0;
			for(i=0;i <data.data.valueNumber;i++ ){
					var valuename = eval('data.data.value'+i);
					var obj1 = { data: valuename};
		
					var quenum = 4*num+i+1;
					Data[quenum] =  new Array();
				//	console.log(obj1);
					for(var j = 0; j<obj1.data.length;j++){
						if(Data[quenum] !=null && Data[quenum].length > 20)
						{
							Data[quenum].shift();
						}
						Data[quenum].push(obj1.data[j]);
					}
					seriesArray.push({data:Data[quenum]});  //0 is categories;
			}
		//	console.log(Data[type]);
			obj.setOption({ series: seriesArray });	
				

		}

	});	

}	

function loadSetting(){
	var FormObject = document.forms['sensorChartSettingForm'];	
	var obj = {pageName:'data-chart'};	
	$.post("userSetting/get",obj, function(data){
		if(data.status == 200){
			// change the obj
			var ob= serializeStrToObj(data.data.settingJson);
			$.each(ob, function(key, val) { 
				if(FormObject.elements[key] != null){

					if(FormObject.elements[key].type == 'checkbox'){
						FormObject.elements[key].checked = true;
						ds.$showchart.push(key);
					}else{
						FormObject.elements[key].value=val;
						if(key=='historyTime'){
							var t=val.replace(/\+/g, " ");
							FormObject.elements[key].value = decodeURIComponent(t,true); 
						}						
					}
				}
			});
			
			userSettingId=data.data.id;
			showSetting();
	

		}
	});		

}




function showSetting(){
	var form = document.forms['sensorChartSettingForm'];	
	
    // 指定图表的配置项和数据
    var commOption = {
        title: {
        	
            x: 'center'
        },
        tooltip: {},
        legend: {
            data:['value0'],
            x: 'left',
            show: false
         },
        xAxis: {
        	type:'category',
            data: []
        },
        yAxis: {
        	type : 'value',
        },
        dataZoom: [{
            type: 'inside',
            start: 50,
            end: 100
        }, {
            start: 50,
            end: 100,
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        }],        
        series: [{
            type: 'line',            	
            name: 'value0',
            smooth:true, 	
            data: []
        }]
    };       
    
    // 指定图表的配置项和数据
    var multiOption = {
        title: {
            x: 'center'
        },
        tooltip: {},
        legend: {
            data:['X','Y','Z'],
            x: 'right',
            show:true,

         },
        xAxis: {
        	type:'category',
            data: []
        },
        yAxis: {
        	type : 'value',
        },
        dataZoom: [{
            type: 'inside',
            start: 50,
            end: 100
        }, {
            start: 50,
            end: 100,
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        }],        
        series: [
         {
            type: 'line',            	
            name: 'X',
            smooth:true, 	
            data: []
        },
        {
            type: 'line',            	
            name: 'Y',
            smooth:true, 	
            data: []
        },
        {
            type: 'line',            	
            name: 'Z',
            smooth:true, 	
            data: []
        },           
        ]
    };      
	
	var sType=form['showTypeRadios'].value;
	
    for(var i=0;i<ds.$showchart.length;i++){
        var typeName= ds.$showchart[i];   
     
		 var b=form[typeName];
		 var x = b.value.split('_')[0];
		 var unit= b.value.split('_')[1];
	//	 unit = decodeURIComponent(unit,true)
		 //temp process code???
		 console.log(unit); 
	//	 unit = unit.replace("%2525","%");
	//	 unit = unit.replace("%252F","/");

		 if(x=='s'){
			 ds.$chartObj[i].setOption(commOption); 
		 }else{
			 ds.$chartObj[i].setOption(multiOption); 
		 }
		 ds.$chartObj[i].setOption(
			{
				title: { text: typeName},
				yAxis: {name : unit},
			}
		 ); 
 
		// ds.$chartObj.push(temp);
	
		 if(sType == 'history'){
			 showHistorychart(ds.$chartObj[i],typeName);
		 }
    }
    //real time show all data in timer
	 if(sType == 'realTime'){
		 showRealchart();
	 }
}

function showRealchart(){
	//if old timer is exist, stop it first!
	 if(g_sendTimerId >0){
			clearInterval (g_sendTimerId);
			g_sendTimerId = -1;
		}			 
	
	var b=ds.$form['interval'];
	
	var dd= new Date();
	var start =parseInt( dd.getTime()/1000);	
	
	g_sendTimerId = setInterval(function () {

		var param = {mac:"12:D2:08:2D:07:98",
				 type:"",
				 startTimestamp:start,
				 endTimestamp:0
				 };
		for(var i=0;i<ds.$showchart.length;i++){	
			param.type = ds.$showchart[i]
			if(i == 0){
				// the first should save x data
				postNewData(ds.$chartObj[i],param,true,i);	
			}else{
				postNewData(ds.$chartObj[i],param,false,i);
			}
		}
		
	},b.value*1000);

}
function showHistorychart(chartObj,name){
	// TBD: mac is fixed!!!! need change
	 var b=ds.$form['historyTime'];
	 // notice ,must has a space to cut the string!
	 var beginTime = b.value.split(' -')[0];
	 var endTime = b.value.split(' -')[1];

    var dd= new Date(beginTime);
    var start = parseInt(dd.getTime()/1000);
    var dd1 = new  Date(endTime);
    var end = parseInt(dd1.getTime()/1000);
	var param = {mac:"12:D2:08:2D:07:98",
				 type:name,
				 startTimestamp:start,
				 endTimestamp:end
				 };

     postData(chartObj,param,Data);
 
}

function onSettingChart(){
	
	var form =  $('#sensorChartSettingForm');
	
	
	var obj = { id:userSettingId,
				pageName:'data-chart',
			   settingJson:form.serialize() 
	};
	$.post("userSetting/update",obj, function(data){
		if(data.status == 200){
			$('#pl_setting').collapse('toggle');
			window.location.reload();
		}
	});		
}

function cancelChartSetting(){
	$('#pl_setting').collapse('toggle');
}
	
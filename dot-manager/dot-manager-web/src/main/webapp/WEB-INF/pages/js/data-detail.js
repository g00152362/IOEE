var d_dtl = d_dtl || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		d_dtl.$table =  $('#dataDetalList');
		d_dtl.$selctions=[];
		d_dtl.userSettingId = 0;
		d_dtl.$form = document.forms['sensorDetailSettingForm'];
		d_dtl.$mac = "";
		d_dtl.$start = "";
		d_dtl.$end = "";
		d_dtl.t = new Array();
		
		var s=["Home","Data","Detail"];
		setNavBar(s);	

		// init the pick
        $('#reservationtime').daterangepicker({
            timePicker: true,
            timePickerIncrement: 30,
            format: 'YYYY/MM/DD HH:mm:ss'
          });
		
		$.extend(d_dtl, {

			getIdSelections:function () {
				return $.map(d_dtl.$table.bootstrapTable('getSelections'), function (row) {
				    return row.id;
					});
			},	
			loadDataDetailSetting:function(){
				var FormObject = document.forms['sensorDetailSettingForm'];	
				var obj = {pageName:'data-detail'};	
		    	$.ajaxSetup({
		    		async: false	
		    		});				
				$.post("userSetting/get",obj, function(data){
					if(data.status == 200){
						// change the obj
						var ob= serializeStrToObj(data.data.settingJson);
						$.each(ob, function(key, val) { 
					
							if(FormObject.elements[key] != null){

								if(FormObject.elements[key].type == 'checkbox'){
									//FormObject.elements[key].checked = true;
									//ds.$showchart.push(key);
								}else{
									FormObject.elements[key].value=val;
									
									if(key=='historyTime'){
										var t=val.replace(/\+/g, " ");
										FormObject.elements[key].value = decodeURIComponent(t,true);
										
									}		
									if(key=='mac'){
										FormObject.elements[key].value = decodeURIComponent(val,true);
									}
								}
							}
						});
						d_dtl.userSettingId=data.data.id;

						//show?
					}
				});
			},
			
			
		});
		
  		
        d_dtl.loadDataDetailSetting();
		
		initTable();

	});
	
})(jQuery, window);


	function initTable() {
		d_dtl.$table.bootstrapTable({
			dataField: "rows",
			striped: true,
			sidePagination: "server",
			queryParams: queryParams, 
			method: 'post',  
			contentType : "application/x-www-form-urlencoded",			
			url:"sensorData/getdataDetail",
		//	height: getHeight(),
	
			columns: [
			 {
			     title: "id",
			     field: "id", 
			     visible:false,
			 },        
			 {
			     title: "Mac",
			     field: "mac",
			     width: 180,
			     valign: 'middle',   
			 },
			
			 {
				 title: "X ",
				 field: "value",
			     width: 50,
			     valign: 'middle',             
			   },         
			 {
				 title: "Y",
			     field: "value1",
			     width: 50,
			     valign: 'middle',             
			    
			  },
				 {
					 title: "Z",
				     field: "value2",
				     width: 50,
				     valign: 'middle',             
				    
				  },
				  {
				 	 title: "上报时间",
				      field: "timeseq",
				      width: 50,
				      valign: 'middle',           
					     sortable: true,				      
				      formatter: timeStampFormat,
				}, 				  
			]
			});
	
		$(window).resize(function () {
			d_dtl.$table.bootstrapTable('resetView', {
		        height: getHeight()
		    });
		});
		//need  to be changed fixed!!!
		/*	    var dd= new Date();
	    var end = parseInt(dd.getTime()/1000);		
		var para = {mac:"60:01:94:43:3f:e5",
				 type:"magnetic",
				 startTimestamp:0,
				 endTimestamp:end
				 };		

		$.post("sensorData/getdataDetail",para,function(data){
				d_dtl.$table.bootstrapTable('load',data );
			});
			*/
			 		
	}
	function queryParams(params) {
		 var start = 0;
		 var end = 0;
		 var dd= new Date();
		// end = parseInt(dd.getTime()/1000);
		 var sType=d_dtl.$form['showTypeRadios'].value;
		 if(sType == 'history'){
			 var b=d_dtl.$form['historyTime'];
			 // notice ,must has a space to cut the string!
			 start = b.value.split(' -')[0];
			 end = b.value.split(' -')[1];
		 }
		 
		 if(sType == 'realTime'){

			 start = 0;
			 end = parseInt(dd.getTime()/1000);
			 //start a timer
		 }
		 
		 var sDataType=d_dtl.$form['datatype'].value;
		 console.log(sDataType);
		 var smac=d_dtl.$form['mac'].value;
	//	 var sDataType = "magnetic";
	//	 var mac=d_dtl.$mac;
		
		var temp = {
			 pageNumber: params.offset+1,
             pageSize: params.limit,			 
			 mac:smac,
			 type:sDataType,
			 startTimestamp:start,
			 endTimestamp:end
		 };
		return temp; 
	} 
	
	function responseHandler(res) {
		$.each(res.rows, function (i, row) {
		    row.state = $.inArray(row.id, d_dtl.selections) !== -1;
		});
		return res;
	}
		
	function detailFormatter(index, row) {
		var html = [];
		$.each(row, function (key, value) {
		    html.push('<p><b>' + key + ':</b> ' + value + '</p>');
		});
		return html.join('');
	}
	
	function operateFormatter(value, row, index) {
		return [
		    '<a class="like" href="javascript:void(0)" title="Like">',
		    '<i class="glyphicon glyphicon-heart"></i>',
		    '</a>  ',
		    '<a class="remove" href="javascript:void(0)" title="Remove">',
		    '<i class="glyphicon glyphicon-remove"></i>',
		    '</a>'
		].join('');
	}

	function getHeight() {
		return $(window).height() - $('h1').outerHeight(true);
	}

	
	/*format timestamp */
	function timeStampFormat(value,row,index){
		return formatDateTime(value*1000);

	} 
	
	
	function onSettingDetailData(){
		
		var form =  $('#sensorDetailSettingForm');
		// not support user now,need tbd!!
		var obj = {id:d_dtl.userSettingId,
					pageName:'data-detail',
				   settingJson:form.serialize() 
		};
		$.post("userSetting/update",obj, function(data){
			if(data.status == 200){
				$('#dd_setting').collapse('toggle');
		var para = {
				
		};
		d_dtl.$table.bootstrapTable('refresh');
/*
 		$.post("sensorData/getdataDetail",para,function(data){
				d_dtl.$table.bootstrapTable('load',data );
			});
 */				
			}
		});		
	}	
	

	function cancelSettingDetailData(){
		$('#dd_setting').collapse('toggle');
	}

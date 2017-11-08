var dm_list = dm_list || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		dm_list.$table =  $('#deviceModelList');
		dm_list.$selctions=[];
		
		var s=["Home","Device","Model","List"];
		setNavBar(s);		

		
		$.extend(dm_list, {

			getIdSelections:function () {
				return $.map(dm_list.$table.bootstrapTable('getSelections'), function (row) {
				    return row.id;
					});
			},	
			
			/*
			 *  delete device model process
			 */
			deleteDeviceModel : function (){
				var ids = dm_list.getIdSelections();
				if(ids.length == 0){
					alert("please select items to delete");
					return ;
				}
				var param = {"ids":ids};
					$.post("deviceModel/delete",param, function(data){
					if(data.status == 200){
						dm_list.$table.bootstrapTable('remove', {
				            field: 'id',
				            values: ids
				        });
					}
				});
				return;
			},	
			
			/*
			 *  edit model process
			 */
			editModel:function(){
				var ids = dm_list.getIdSelections();
				if( ids.length != 1 ){
					alert("please select an item to edit");
					return ;
				}
				
				localStorage['Model-id'] = ids[0];
				IotLoadFrame("devicemodel-edit.html?id="+ids[0],'js/devicemodel-edit.js');
				return;
			},
		
		});
		
		

		
		initTable();

	});
	
})(jQuery, window);


	function initTable() {
		dm_list.$table.bootstrapTable({
			dataField: "rows",
		//	height: getHeight(),
	
			columns: [
			 {
			     field: "ck",            	 
			     checkbox: true,
			     valign: 'middle' ,  
			     align:'center',             
			
			 },
      
			 {
			     title: "名称",
			     field: "id",
			     width: 180,
			     valign: 'middle',   
			     formatter: ModelnameFormat,             
			//            editable: true
			 },
			
			 {
				 title: "操作系统 ",
				 field: "os",
			     width: 50,
			     sortable: true,
			     valign: 'middle',             
			   },         
			 {
				 title: "处理器",
			     field: "processor",
			     width: 50,
			     valign: 'middle',             
			    
			  },
				 {
					 title: "核数",
				     field: "coreNumber",
				     width: 50,
				     valign: 'middle',             
				    
				  },
				  {
					 	 title: "内存",
					      field: "memory",
					      width: 50,
					      valign: 'middle',             
					      formatter: byteUnitFormat,
					   }, 				  
			  {
			 	 title: "存储",
			      field: "storage",
			      width: 50,
			      valign: 'middle', 
			      formatter: byteUnitFormat,			      
			     
			   },          
			
			    {
			        field: "备注",
			        valign: 'middle',                
			        title: "Description",
			        width: 200
			     }      
			]
			});
	
		$(window).resize(function () {
			dm_list.$table.bootstrapTable('resetView', {
		        height: getHeight()
		    });
		});
	}


	
	function responseHandler(res) {
		$.each(res.rows, function (i, row) {
		    row.state = $.inArray(row.id, dm_list.selections) !== -1;
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
	
	window.operateEvents = {
		'click .like': function (e, value, row, index) {
		    alert('You click like action, row: ' + JSON.stringify(row));
		},
		'click .remove': function (e, value, row, index) {
		    $table.bootstrapTable('remove', {
		        field: 'id',
		        values: [row.id]
		    });
		}
	};
	
	function getHeight() {
		return $(window).height() - $('h1').outerHeight(true);
	}





	
	/*format Byte Unit */
	function byteUnitFormat(value,row,index){
		if(value >=1000)
		{
			return value/1000+"G";
		}
		else{
			return value+"M";
		}
	
	} 
	
	/*format Model */
	function ModelnameFormat(value,row,index){
		if(value != "")
		{
			return "<a  style='color:#009AE7'  href=\"#\" onClick=\" ModelNameDetailClick('"+value+"')\">"+value+"</a>";
		}
		else{
			return "<span >Unsigned </span>";
		}
	
	} 
	function ModelNameDetailClick(name){
		localStorage['Model_name'] = name;
		IotLoadFrame("deviceModel-detail.html",'js/devicemodel-detail.js');
	}

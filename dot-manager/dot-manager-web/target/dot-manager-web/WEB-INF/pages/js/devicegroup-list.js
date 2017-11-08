var dg_list = dg_list || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		dg_list.$table =  $('#deviceGroupList');
		dg_list.$selctions=[];
		
		var s=["Home","Device","Group","List"];
		setNavBar(s);
		
		initTable();

	});
	
})(jQuery, window);


	function initTable() {
		dg_list.$table.bootstrapTable({
			dataField: "rows",
	//		height: getHeight(),
	
			columns: [
			 {
			     field: "ck",            	 
			     checkbox: true,
			     valign: 'middle' ,  
			     align:'center',             
			
			 },
			 {
			     title: "id",
			     field: "id", 
			     visible:false,
			 },         
			 {
			     title: "Name",
			     field: "name",
			     width: 180,
			     valign: 'middle',   
			     formatter: groupnameFormat,             
			//            editable: true
			 },
			
			 {
				 title: "Online device ",
				 field: "onlinenumber",
			     width: 50,
			     sortable: true,
			     valign: 'middle',             
			   },         
			 {
				 title: "Offline device",
			     field: "offlinenumber",
			     width: 50,
			     valign: 'middle',             
			    
			  },
			  {
			 	 title: "Unregistered",
			      field: "unregeisterednumber",
			      width: 50,
			      valign: 'middle',             
			     
			   },          
			
			    {
			        field: "description",
			        valign: 'middle',                
			        title: "Description",
			        width: 200
			     }      
			]
			});
	
		$(window).resize(function () {
			dg_list.$table.bootstrapTable('resetView', {
		        height: getHeight()
		    });
		});
	}

	function getIdSelections() {
		return $.map(dg_list.$table.bootstrapTable('getSelections'), function (row) {
		    return row.id;
			});
	}
	
	function responseHandler(res) {
		$.each(res.rows, function (i, row) {
		    row.state = $.inArray(row.id, dg_list.selections) !== -1;
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

	function deleteDeviceGroup(){
		var ids = getIdSelections();
		if(ids.length == 0){
			alert("please select items to delete");
			return ;
		}
		var param = {"ids":ids};
			$.post("deviceGroup/delete",param, function(data){
			if(data.status == 200){
				dg_list.$table.bootstrapTable('remove', {
		            field: 'id',
		            values: ids
		        });
			}
		});
		return;
	}

	function editGroup(){
		var ids = getIdSelections();
		if( ids.length != 1 ){
			alert("please select an item to edit");
			return ;
		}
		
		localStorage['group-id'] = ids[0];
		IotLoadFrame("devicegroup-edit.html?id="+ids[0],'js/devicegroup-edit.js');
		return;
	}

	
	/*format group */
	function groupnameFormat(value,row,index){
		if(value != "")
		{
			return "<a  style='color:#009AE7'  href=\"#\" onClick=\" groupNameDetailClick('"+value+"')\">"+value+"</a>";
		}
		else{
			return "<span >Unsigned </span>";
		}
	
	} 
	function groupNameDetailClick(name){
		localStorage['group_name'] = name;
		IotLoadFrame("devicegroup-detail.html",'js/devicegroup-detail.js');
	}

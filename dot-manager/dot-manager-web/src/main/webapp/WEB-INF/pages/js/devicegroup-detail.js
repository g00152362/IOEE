var dg_detail = dg_detail || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		dg_detail.$table =  $('#gatewayListBygroupName');
		
		dg_detail.$name= localStorage['group_name'];
		
		var s=["Home","Device","Group","Detail"];
		setNavBar(s);		

		initGroupInfo();
		initTable();

	});
	
	
})(jQuery, window);


function initGroupInfo(){
	var para= {'name':  dg_detail.$name};
	$.post("deviceGroup/listName",para,
			function(data){
				if(data.status != 200){
					alert("get data fail!");
					return;
				}
	       		$('#onlinenumber').text(data.data.onlinenumber);
	       		$('#offlinenumber').text(data.data.offlinenumber);
	       		$('#unregeisterednumber').text(data.data.unregeisterednumber);
	       		$('#description').text(data.data.description);
		}); 	
}

function responseHandler(res) {
    $.each(res.rows, function (i, row) {
        row.state = $.inArray(row.id, gw_list.selections) !== -1;
    });
    return res;
}   

function initTable() {
	dg_detail.$table.bootstrapTable({
	     dataField: "rows",
     height: getHeight(),

	 columns: [
	     {
	         title: "Name",
	         field: "deviceName",
	         width: 180,
	         valign: 'middle',             
	//     editable: true
	     },
	     {
	         field: "serialNumber",
	         title: "Series Number",
	         width: 180,
	         formatter:'deviceEsnFormat',
	         sortable: true,
	         valign: 'middle',
	         titleTooltip: "Serial Number"
	     },
	     {
	         field: "status",
	         title: "Status",
	         width: 150,
	         sortable: true,
	         valign: 'middle',             
	         formatter:'formatStatus'
	       },         
	     {
	         field: "groupName",
	         title: "Group",
	         width: 180,
	         valign: 'middle',             
	         formatter:'groupFormat'               
	      },
	      {
	          field: "type",
	          title: "Model",
	          valign: 'middle',              
	          sortable: true,
	//          editable: true,              
	           width: 100
	       },     
	       {
	           field: "position",
	           title: "Location",
	           valign: 'middle',               
	//           editable: true,                   
	              width:300
	        },                    
	        {
	            field: "softwareVersion",
	            valign: 'middle',                
	            title: "Software",
	            width: 60
	         }      
	 ]
	});
	
	var para= {'name':  dg_detail.$name};
	$.post("/gateway/listBygroupName",para,
			function(data){
			dg_detail.$table.bootstrapTable('load',data );
		}); 	


}


	
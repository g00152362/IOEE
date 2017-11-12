var sft_select = sft_select || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		sft_select.$table =  $('#softwareSelectList');
		sft_select.$selctions=[];
	  	initselectgrouptable();

	});
	
})(jQuery, window);


   
function initselectgrouptable() {
	sft_select.$table.bootstrapTable({
   	     dataField: "rows",
    //     height: getHeight(),
         singleSelect:true,
     columns: [
            {
               field: "ck",            	 
               checkbox: true,
               valign: 'middle' ,  
               align:'center',             

           }, 
			 {
			     title: "版本名",
			     field: "name",
			     width: 100,
			     valign: 'middle',   
			 },
			
			 {
				 title: "版本号",
				 field: "version",
			     width: 50,
			     sortable: true,
			     valign: 'middle',             
			   },    
				 {
					 title: "文件名",
				     field: "filename",
				     width: 150,
				     valign: 'middle',             
				    
				  },			   
				 {
					 title: "上传时间",
				     field: "uploadTime",
				     width: 150,
				     valign: 'middle',   
				     formatter: formatDateTime,
				    
				  } 
     ]
   });
	
	$(window).resize(function () {
		sft_select.$table.bootstrapTable('resetView', {
	        height: getHeight()
	    });
	});  	
}



	function responseHandler(res) {
       $.each(res.rows, function (i, row) {
           row.state = $.inArray(row.id, sft_select.selections) !== -1;
       });
       return res;
   }   
    
   
    
    function getHeight() {
        return $(window).height()-200;
    }
    	

    
    

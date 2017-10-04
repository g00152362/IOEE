var dg_select = dg_select || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		dg_select.$table =  $('#deviceGroupSelectList');
		dg_select.$selctions=[];
	  	initselectgrouptable();

	});
	
})(jQuery, window);


   
function initselectgrouptable() {
	dg_select.$table.bootstrapTable({
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
             title: "组名",
             field: "name",
             width: 180,
             valign: 'middle',   
         },
    
            {
                field: "description",
                title: "描述",
                width: 200
             }      
     ]
   });
	
	$(window).resize(function () {
		dg_select.$table.bootstrapTable('resetView', {
	        height: getHeight()
	    });
	});  	
}



	function responseHandler(res) {
       $.each(res.rows, function (i, row) {
           row.state = $.inArray(row.id, dg_select.selections) !== -1;
       });
       return res;
   }   
    
   
    
    function getHeight() {
        return $(window).height()-200;
    }
    	

    
    

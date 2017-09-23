var gw_add = gw_add || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		gw_add.$form =  $('#gatewayAddForm');
    	// use modal_select to know who use select_group;
    	localStorage['modal_select']="new_device";
		validateForm();
	});
	
})(jQuery, window);




function validateForm() {
	 
	gw_add.$form.validate({
	        rules : {
	        	serialNumber: "required",
	            deviceName: "required",
	        },		 
	        messages: {
	        	serialNumber: "ESN required",
	            deviceName: "Name required",
	        },
	        errorPlacement : function(error, element) {
	            element.next().remove();//
	            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
	            element.closest('.form-group').append(error);     	
	        },

	        highlight : function(element) {
	            $(element).closest('.form-group').addClass('has-error has-feedback');
	        },	        
	        submitHandler :function(form){
	   	        	$.post("gateway/add",gw_add.$form.serialize(), function(data){
        			if(data.status == 200){
        				 IotLoadFrame('gateway-list.html','js/gateway-list.js');
        			}
        		});
	        }    
	    });
	}



	
	function cancelAddDevice(){
		IotLoadFrame('gateway-list.html','js/gateway-list.js');
	}
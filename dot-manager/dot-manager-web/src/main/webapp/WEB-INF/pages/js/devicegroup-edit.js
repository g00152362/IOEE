var dg_edit = dg_edit || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		dg_edit.$form =  $('#deviceGroupEditForm');
		dg_edit.$id = localStorage['group-id'];
		
		var s=["Home","Device","Group","Edit"];
		setNavBar(s);
		
	  	initeditdgform();

	});
	
})(jQuery, window);



function initeditdgform() {
	// init validate
	dg_edit.$form.validate({
	        rules : {
	        	name: "required",
	        },		 
	        messages: {
	            name: "Name required",
	        },
	        errorPlacement : function(error, element) {
	            element.next().remove();
	            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
	            element.closest('.form-group').append(error);	        	
	        },
	        highlight : function(element) {
	            $(element).closest('.form-group').addClass('has-error has-feedback');
	        },	        
	        submitHandler :function(form){
		        	$.post("deviceGroup/update",dg_edit.$form.serialize(), function(data){
        			if(data.status == 200){
        				 IotLoadFrame('devicegroup-list.html','js/devicegroup-list.js');
        			}
        			else{
        				console.log("error update the device group data!");
        			}
        		});
	        }
	    });
	
		// load date form db
		var param = {id:dg_edit.$id };
		$.post("deviceGroup/listid",param,
				function(data){
					if(data.status != 200){
						alert("get data fail!");
						return;
					}
					dge_loadDate(data.data);
			}); 	 
	}


function dge_loadDate(data){
	var FormObject = document.forms['deviceGroupEditForm'];
	$.each(data, function(key, val) { 
		if(FormObject.elements[key] != null){
			FormObject.elements[key].value=val;
		}
	});
	FormObject.elements["id"]=dg_edit.$id;
}


function dge_cancelEdit(){
	IotLoadFrame('devicegroup-list.html','js/devicegroup-list.js');
}
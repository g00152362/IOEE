var gw_edit = gw_edit || {};

;(function($, window, undefined){
	
	$(document).ready(function()
	{
		gw_edit.$form =  $('#gatewayEditForm');
		gw_edit.$esn = localStorage['esn'];
		//set nav bar
		var s=["Home","Device","Edit"];
		setNavBar(s);
		
		initEdit();
	
	});
	
})(jQuery, window);



function initEdit(){
	
	
	gw_edit.$form.validate({
	        rules : {
	        	serialNumber: "required",
	            deviceName: "required",
	        },		 
	        messages: {
	        	serialNumber: "ESN required",
	            deviceName: "Name required",
	        },
	        errorPlacement : function(error, element) {
	            element.next().remove();//删除显示图标
	            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
	            element.closest('.form-group').append(error);//显示错误消息提示	        	
	        },
	        //给未通过验证的元素进行处理
	        highlight : function(element) {
	            $(element).closest('.form-group').addClass('has-error has-feedback');
	        },	        
	        submitHandler :function(form){
	        	console.log($("#gatewayEditForm").serialize());
		        	$.post("gateway/update",gw_edit.$form.serialize(), function(data){
        			if(data.status == 200){
        				 IotLoadFrame('gateway-list.html','js/gateway-list.js');
        			}
        		});
	        }    
	    });

	 
		var param = {esn:gw_edit.$esn};
		$.post("gateway/listid",param,
				function(data){
					if(data.status != 200){
						alert("get data fail!");
						return;
					}
					loadData(data.data);
			}); 
  

	};
	
	function loadData(data){
		$.each(data, function(key, val) { 
			var obj = gw_edit.$form.find('input[id='+key+']');
			if(obj != null){
				obj.val(val) ;
			}
		});
		
		
	}
	
	function cancelEdit(){
		IotLoadFrame('gateway-list.html','js/gateway-list.js');
	}
	


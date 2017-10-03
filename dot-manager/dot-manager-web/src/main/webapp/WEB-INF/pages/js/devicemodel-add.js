var dm_add = dm_add || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		dm_add.$form =  $('#deviceModelAddForm');
		
		var s=["Home","Device","Model","Add"];
		setNavBar(s);		
			
		$.extend(dm_add, {
			/*
			 * upload the picture 
			 */
			uploadFile:function(){
				var fileName = $('#photofile').val();
				if(fileName == null ||fileName =="" ){
					alert('please select a picture!');
					return;
				}
				$.ajaxFileUpload({
					url: 'deviceModel/fileupload',
					secureuri:false,
					fileElementId : 'photofile',
					dataType :  'text',
					success : function(data){
						if(data != null){
							var dataset = $.parseJSON(data);
							var urls='http://'+window.location.host+'/'+dataset.fileName;
							var obj = document.getElementById("pic_device");
							obj.src =urls;
							obj = document.getElementById("photo");
							obj.value=dataset.fileName;
						}
					},
				
					
				});
				
				
			},

			/*
			 *  submit the edit form process
			 */
			SubmitForm:function () {
				var unit = $('input:radio[name="s_unit"]:checked').val();
				var v = $('#storage').val();
				$('#storage').val(unit * v );
				
				unit = $('input:radio[name="m_unit"]:checked').val();
				v = $('#memory').val();
				$('#memory').val(unit * v );
				//$('#photo').val(dm_add.$filedir);
			
	        	$.post("deviceModel/add",dm_add.$form.serialize(), function(data){
        			if(data.status == 200){
        				IotLoadFrame('devicemodel-list.html','js/devicemodel-list.js');
        			}
        			else{
        				IotErrorMsg(data.msg);
        				IotLoadFrame('devicemodel-list.html','js/devicemodel-list.js');
        			}
        				
				});
			},	
			
			/*
			 * validate the form
			 */
			validateForm:function(form,submitFunction)	{
			 	form.validate({
				        rules : {
				        	id: {required:true,
				        		 remote: {          //远程ajax验证
				                     url: "deviceModel/checkname", //检查是否存在账号，存在则返回true
				                     type: "POST",
				                     dataType: "json",
				                     data: {
				                         id: function () {
				                             return $("#id").val(); //这个是取要验证的用户名
				                         }
				                     },
				                     dataFilter: function (data) {  //判断控制器返回的内容
				                         var result = JSON.parse(data);
				                         if( result.status != 200 ){
				                             return false;
				                         }else{
				                             return true;
				                         }
				                     }	
				        		 }
				        	},
				        	os:"required",
				        	processor:"required",
				        	coreNumber: {
				        		required:true,
				        		digits:true,
			        		   },	
			        		memory: {
				        		required:true,
				        		digits:true,
			        		   },	
			        		storage: {
				        		required:true,
				        		digits:true,
			        		   },		        		   
				        },		 
				        messages: {
				        	id: { required:"Model Name required",
				        			remote:"The model name is existed"
   					        	   },
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
				        submitHandler :submitFunction,
				          
				    });
				},	
				cancelAdd:function (){
					IotLoadFrame('devicemodel-list.html','js/devicemodel-list.js');
				},				
		});


		dm_add.validateForm(dm_add.$form,dm_add.SubmitForm);	  	

	});

	


})(jQuery, window);


	
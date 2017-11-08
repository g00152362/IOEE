var dm_edit = dm_edit || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		dm_edit.$form =  $('#deviceModelEditForm');
		dm_edit.$modelId =localStorage['Model-id'];
		
		var s=["Home","Device","Model","Edit"];
		setNavBar(s);		
		
		
		$.extend(dm_edit, {

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
				
				$.post("deviceModel/update",dm_edit.$form.serialize(), function(data){
					if(data.status == 200){
						 IotLoadFrame('devicemodel-list.html','js/devicemodel-list.js');
					}
					else{
						console.log("error update the device group data!");
					}
				});
			},	
			
			/*
			 *  cancel the edit form process
			 */
			cancelEdit:function(){
				IotLoadFrame('devicemodel-list.html','js/devicemodel-list.js');
			},
			
			loadData:function(){
				// load date form db
				var param = {'id':dm_edit.$modelId };
				$.post("deviceModel/listid",param,
						function(data){
							if(data.status != 200){
								alert("get data fail!");
								return;
							}
							// refresh the DOM
							var FormObject = document.forms['deviceModelEditForm'];
							$.each(data.data, function(key, val) { 
								if(FormObject.elements[key] != null){
									FormObject.elements[key].value=val;
								}
							});
							// if memory and storage > 1000, select the G unit
							var v= FormObject.elements['memory'].value;
							if(v >=1000){
								FormObject.elements['memory'].value = v/1000;
								$('input[name="m_unit"][value=1000]').prop("checked", "checked");
							}
							v= FormObject.elements['storage'].value;
							if(v >=1000){
								FormObject.elements['storage'].value = v/1000;
								$('input[name="s_unit"][value=1000]').prop("checked", "checked");
							}
								
					}); 				
			},
			

			validateForm:function (form,submitFunction)	{
			 	form.validate({
				        rules : {
				        	id: "required",
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
				        	name: "Model Name required",
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

			
		});
	

		dm_edit.validateForm(dm_edit.$form,dm_edit.SubmitForm);
		dm_edit.loadData();
		

	});
	
})(jQuery, window);


	
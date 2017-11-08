var lu_list = lu_list || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		lu_list.$table =  $('#loginUserList');
		lu_list.$selctions=[];
		
		var s=["Home","System","Loginuser","List"];
		setNavBar(s);		

		
		$.extend(lu_list, {

			getIdSelections:function () {
				return $.map(lu_list.$table.bootstrapTable('getSelections'), function (row) {
				    return row.id;
					});
			},	
			
			/*
			 *  delete device model process
			 */
			deleteDeviceModel : function (){
				var ids = lu_list.getIdSelections();
				if(ids.length == 0){
					alert("please select items to delete");
					return ;
				}
				var param = {"ids":ids};
					$.post("loginuser/delete",param, function(data){
					if(data.status == 200){
						lu_list.$table.bootstrapTable('remove', {
				            field: 'username',
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
				
				localStorage['loginuser-id'] = ids[0];
				IotLoadFrame("loginuser-edit.html?id="+ids[0],'js/loginuser-edit.js');
				return;
			},
		
		});
		
		

		
		initTable();

	});
	
})(jQuery, window);


	function initTable() {
		lu_list.$table.bootstrapTable({
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
			     title: "用户名",
			     field: "username",
			     width: 100,
			     valign: 'middle',   
			 },
			
			 {
				 title: "邮箱",
				 field: "email",
			     width: 50,
			     sortable: true,
			     valign: 'middle',             
			   },         
			 {
				 title: "电话",
			     field: "telephone",
			     width: 50,
			     valign: 'middle',             
			    
			  },
				 {
					 title: "权限",
				     field: "privilege",
				     width: 50,
				     valign: 'middle',             
				    
				  },
				  {
					 	 title: "创建时间",
					      field: "createdTime",
					      width: 50,
					      valign: 'middle',             
				   }, 				  
			]
			});
	
		$(window).resize(function () {
			lu_list.$table.bootstrapTable('resetView', {
		        height: getHeight()
		    });
		});
	}


	
	function responseHandler(res) {
		$.each(res.rows, function (i, row) {
		    row.state = $.inArray(row.id, lu_list.selections) !== -1;
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




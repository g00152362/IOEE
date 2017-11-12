var sft_list = sft_list || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		sft_list.$table =  $('#softwareList');
		sft_list.$selctions=[];
		
		var s=["Home","System","Software","List"];
		setNavBar(s);		

		
		$.extend(sft_list, {

			getIdSelections:function () {
				return $.map(sft_list.$table.bootstrapTable('getSelections'), function (row) {
				    return row.id;
					});
			},	
			
			/*
			 *  delete device model process
			 */
			deleteSoftware : function (){
				var ids = sft_list.getIdSelections();
				if(ids.length == 0){
					alert("please select items to delete");
					return ;
				}
				var param = {"ids":ids};
					$.post("softwareRelease/delete",param, function(data){
					if(data.status == 200){
						sft_list.$table.bootstrapTable('remove', {
				            field: 'id',
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
				var ids = sft_list.getIdSelections();
				if( ids.length != 1 ){
					alert("please select an item to edit");
					return ;
				}
				
				localStorage['software-id'] = ids[0];
				IotLoadFrame("software-edit.html?id="+ids[0],'js/software-edit.js');
				return;
			},
		
		});

		
		initTable();

	});
	
})(jQuery, window);


	function initTable() {
		sft_list.$table.bootstrapTable({
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
			     title: "id",
			     field: "id", 
			     visible:false,
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
				 title: "上传时间",
			     field: "uploadTime",
			     width: 150,
			     valign: 'middle',   
			     formatter: formatDateTime,
			    
			  },
				 {
					 title: "文件名",
				     field: "filename",
				     width: 150,
				     valign: 'middle',             
				    
				  },
				  
			]
			});
	
		$(window).resize(function () {
			sft_list.$table.bootstrapTable('resetView', {
		        height: getHeight()
		    });
		});
	}


	
	function responseHandler(res) {
		$.each(res.rows, function (i, row) {
		    row.state = $.inArray(row.id, sft_list.selections) !== -1;
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




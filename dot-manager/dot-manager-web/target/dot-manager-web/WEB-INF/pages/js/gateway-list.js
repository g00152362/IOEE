var gw_list = gw_list || {};

;(function($, window, undefined){
	
	"use strict";
	
	$(document).ready(function()
	{
		//gw_list.$table = document.getElementById("gatewayList");
		gw_list.$table =  $('#gatewayList');
		gw_list.selections =[];
		gw_list.ids=[];
		gw_list.$selectModal =  $('#selectModal');
		localStorage['modal_select'] = "group_tag";
		localStorage['esn'] = "";
		localStorage['group_name'] = "";
		
		//set nav bar
		var s=["Home","Device","List"];
		setNavBar(s);
		
		// init group tag
		//http://blog.csdn.net/qq_15096707/article/details/51746968 
		gw_list.tagsinput = $('#grouptags').tagsinput({
	         trimValue: false,
	         itemValue: 'value',
	         itemText: 'text',
	         tagClass: 'label-info ',
	         freeInput: false
	     })[0];
		
    	$.ajaxSetup({
    		async: true	
    		});
    	
		//init list table;
        initTable();
	});
	

})(jQuery, window);

   
    function initTable() {
    	gw_list.$table.bootstrapTable({
   	     dataField: "rows",
    //     height: getHeight(),

     columns: [
         {
             field: "ck",            	 
             checkbox: true,
        

         },
         {
             title: "设备名",
             field: "deviceName",
             width: 180,
           
 //            editable: true
         },
         {
             field: "serialNumber",
             title: "序列号",
             width: 180,
             formatter:'deviceEsnFormat',
             sortable: true,

             titleTooltip: "esn"
         },
         {
             field: "status",
             title: "状态",
             width: 150,
             sortable: true,
            
             formatter:'formatStatus'
           },         
         {
             field: "groupName",
             title: "设备组",
             width: 180,
             valign: 'middle',             
             formatter:'groupFormat'               
          },
          {
              field: "type",
              title: "类型",
            
              sortable: true,
//              editable: true,              
               width: 100
           },     
           {
               field: "position",
               title: "位置",
        
//               editable: true,                   
                  width:300
            },                    
            {
                field: "softwareVersion",
             
                title: "软件版本",
                width: 60
             }      
     ]
   });
    	
      $(window).resize(function () {
      	gw_list.$table.bootstrapTable('resetView', {
              height: getHeight()
          });
      });
    }
    
    function responseHandler(res) {
        $.each(res.rows, function (i, row) {
            row.state = $.inArray(row.id, gw_list.selections) !== -1;
        });
        return res;
    }   
    
    /*
     *  get selection serialNumber array
     */
    function getIdSelections() {
        return $.map(gw_list.$table.bootstrapTable('getSelections'), function (row) {
            return row.serialNumber;
        });
    }

   
    function getHeight() {
        return $(window).height() - $('h1').outerHeight(true);
    }


		
	/*format group */
	function groupFormat(value,row,index){
		if(value != "")
		{
			return "<a  style='color:#009AE7'  href=\"#\" onClick=\" groupDetailClick('"+value+"')\">"+value+"</a>";
		}
		else{
			return "<span >Unsigned </span>";
		}
	
	} 
	function groupDetailClick(name){
		localStorage['group_name'] = name;
		IotLoadFrame("devicegroup-detail.html","js/devicegroup-detail.js");
	}
	

	/*format esn */
	function deviceEsnFormat(value,row,index){

		return "<a  style='color:#009AE7' href=\"#\" onClick=\"deviceDetailClick('"+value+"')\">"+value+"</a>";	
		
	} 
	
	function deviceDetailClick(esn){
		localStorage['esn'] = esn;
		IotLoadFrame("gateway-detail.html","js/gateway-detail.js");
	}
	
/*
 * delete the device 
 */
	function deleteGateway(){
       	var ids = getIdSelections();
    	if(ids.length == 0){
    		alert("must select a item");
    		return ;
    	}
    	var param = {"ids":ids};
    	$.ajaxSetup({
    		async: false	
    		});
       	$.post("gateway/delete",param, function(data){
			if(data.status == 200){
				gw_list.$table.bootstrapTable('remove', {
	                field: 'serialNumber',
	                values: ids
	            });
			}
		});
      	return;
    }
	
	/*
	 *  edit gateway
	 */
	
	function editGateway(){

       	var idss = getIdSelections();

    	if( idss.length != 1 ){
    		alert("must select a item");
    		return ;
    	}
    	localStorage['esn'] = idss[0];

       	IotLoadFrame("gateway-edit.html?esn="+idss[0],"js/gateway-edit.js");
       	return;
    }
		
	
	function addToGroup(){
		gw_list.ids = getIdSelections();
    	if(gw_list.ids.length == 0){
    		alert("No item selected!");
    		return ;
    	}
    	// use modal_select to know who use select_group;
    	localStorage['modal_select']="move_group";
    	var obj = {'ids':gw_list.ids};
    	localStorage['device_select']= JSON.stringify(obj);
    	gw_list.$selectModal.modal();

	}
	
	// USELESS TO BE CONFIRM TO DELETE
	// initial statistic box,get data from db
	function initStat(){
		$.post("gateway/statistic",null, function(data){
			if(data.status == 200){
	       		//disp date
	       		var tt=0;
	       		$('#online').text(data.data.online);
	       		$('#offline').text(data.data.offline);
	       		$('#unregistered').text(data.data.unregistered);
	       		$('#total').text(data.data.unregistered+data.data.offline+data.data.online);
			}
		});		
	}
	
	
	/*    **************************
	 *   modal select group process 
	 ******************************** */	
	// when move to group modal select finished
	function updateGroupData(name){

		var paramJson=JSON.parse(localStorage['device_select']);
		var c = $.extend({},paramJson,{"group":name});
	       	$.post("gateway/updateGroup",c, function(data){
				if(data.status == 200){
					IotLoadFrame('gateway-list.html','js/gateway-list.js');
					return;
				}
			});    
	       	return;
	}
	
/*    **************************
 *   group tag 
 ******************************** */
	

	function addGroupTag(tagsinput,value,text,addfun,delfun){
		
	   var input = tagsinput.$element;
	   var tagsArray = tagsinput.itemsArray;
	   var tagsContainer = tagsinput.$container; // tag 的容器
	   
	   input.tagsinput('add', {'value' :value, 'text' :text});
   
	   // 当前新创建的标签
	   var cur = $(tagsContainer.children('span.tag:last'));
	
	   cur.on('click', function(e) {
	       e.stopPropagation();
	       tagsContainer.find('input').blur();
	       if(addfun != null){
	    	   addfun(value,text);
	       }
	   });
   
	   // 当前新建的标签的删除事件
	   cur.children('span[data-role=remove]').on('click', function(e) {
	       e.stopPropagation();
	       if (tagsinput.$element.attr('disabled')) {
	           return;
	       }
	       tagsinput.remove($(e.target).closest('.tag').data('item'));
	       if(delfun != null){
	    	   delfun(value,text);
	       }
	   });   
	}
	
	// tag click process
	function showListBygroup(v,t){

    	var param = {"name":v};
       	$.post("gateway/listBygroupName",param, function(data){
   		
				gw_list.$table.bootstrapTable('load',data);

		});
	}


var prefix = "/system/details"
	var orderJson=null;
$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'orderId',
            title: '订单编号',
           // sortable:true
           
        },
        
        {
            field: 'userId',
            title: '下单用户',
            formatter: function(value, row, index) {
            	var id=row.id; 
            	var colorsId=row.colorsId; 
            	var sizesId=row.sizesId; 
            	var buttonsId=row.buttonsId; 
            	var threadsId=row.threadsId; 
            	var partsId=row.partsId; 
            	var washId=row.washId; 
            	var userId=row.userId;
            	var jeansId=row.jeansId;
            	var orderType=row.orderType
            	var orderId=row.orderId;
            	
            	var formData = new FormData();  
                formData.append("partsId", partsId);
                formData.append("colorsId", colorsId);  
                formData.append("threadsId", threadsId);  
                formData.append("sizesId", sizesId);   
                formData.append("buttonsId", buttonsId);
                formData.append("washId", washId);
                formData.append("id", id);   
                formData.append("userId", userId);
                formData.append("jeansId", jeansId);
                formData.append("orderType", orderType);
                formData.append("orderId", orderId);
            	
            	$.ajax({
        			dataType: "json",
        			cache : true,
        			type : "POST",
        			url : "/system/details/orderDetails",
        			contentType: false,
        			processData: false,
        			data : formData ,
        			async : false,
        			error : function(request) {
        				$.modalAlert("系统错误", "error");
        			},
        			success : function(data) {
        				
        				if (data != null || data!="") {									
        					orderJson=data;        					
        					
        				} else {
        					
        					return "找不到对应的信息";
        				}

        			}
        		});
            	
            	
            	if(orderJson.user!="" && orderJson.user!=null){
            		return orderJson.user.nickname
            	}else{
            		return "";
            	}
            }
        },
        
        /*{
            field: 'jeansId',
            title: '成品库名称',
            formatter: function(value, row, index) {
            	//alert(orderJson.webDesigner)
            	if(orderJson.webDesigner!=null && orderJson.webDesigner!=""  ){
            		return orderJson.webDesigner.name;
            	}else{
            		return "";
            	}
            	
            }
        },*/
        
        /*{
            field: 'type',
            title: '衣服类型',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == 0) {
                    return '<span class="label label-primary">裤子</span>';
                }else if(value==1)  {
                    return '<span class="label label-info">上衣</span>';
                }else{
                    return '<span class="label label-default">预售款</span>';
                }
            }
        },*/

        {
            field: 'orderType',
            title: '衣服类型',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == 0) {
                    return '<span class="label label-primary">定制款</span>';
                }else if(value==1)  {
                    return '<span class="label label-info">预售款</span>';
                }
            }
        },
        {
            field: 'num',
            title: '数量',
            /*formatter: function(value, row, index) {
            	if(orderJson.order!=null && orderJson.order!=""  ){
            		return orderJson.order.num;
            	}else{
            		return 0;
            	}
            	
            }*/
        },
        
        {
            field: 'price',
            title: '金额',
           /* formatter: function(value, row, index) {
            	if(orderJson.order!=null && orderJson.order!=""  ){
            		return '￥'+orderJson.order.money;
            	}else{
            		return 0;
            	}
            	
            }*/
        },
        
        {
            field: 'payType',
            title: '支付方式',
            formatter: function(value, row, index) {            	
            	if(orderJson.order!=null && orderJson.order!=""  ){
            		var num=orderJson.order.payType
            		 if (num == 2) {
                         return '<span class="label label-primary">支付宝支付</span>';
                     }else if(num==1)  {
                         return '<span class="label label-info">微信支付</span>';
                     }
            	}else{
            		return "其他支付方式";
            	}
            	
            }
        },
        
       /* {
            field: 'colorsId',
            title: '颜色名称',
            formatter: function(value, row, index) {
            	
            	var index=orderJson.webColorList.length;;           	               
            	var actions = [];
            	if(orderJson.webColorList!="" && orderJson.webColorList!=null ){           		
            		for(var  i=0;i<index;i++){
            			actions.push(""+orderJson.webColorList[i].name);
            		}
            		
            		return actions.join('<br/>');
            	}else{
            		return "";
            	}
            		
    			
            }
        },
        
        {
            field: 'sizesId',
            title: '尺码',
            formatter: function(value, row, index) {
            	var index=orderJson.webSizesList.length; 
            	var actions = [];
            	if(orderJson.webSizesList!="" && orderJson.webSizesList!=null ){
            		
            		for(var  i=0;i<index;i++){
            			actions.push(''+orderJson.webSizesList[i].size);
            		}
            		return actions.join('<br/>');
            	}else{
            		if(orderJson.webDesigner!="" && orderJson.webDesigner!=null){
                		return orderJson.webDesigner.sizesId;
                	}else{
                		return "";
                	}
            	}
            	
    			
            }
        },
        
        {
            field: 'buttonsId',
            title: '纽扣名称',
            formatter: function(value, row, index) {
            	var index=orderJson.webButtonList.length; 
            	var actions = [];
            	//alert(orderJson.webButtonList=="");
            	if(orderJson.webButtonList!="" && orderJson.webButtonList!=null){           		
            		for(var  i=0;i<index;i++){
            			actions.push(""+orderJson.webButtonList[i].name);
            			return actions.join('<br/>');
            		}
            		
            	}else{
            		if(orderJson.webDesigner!="" && orderJson.webDesigner!=null){
                		return orderJson.webDesigner.buttonsId;
                	}else{
                		return "";
                	}
            	}
            	
    			
            }
        },
        
        {
            field: 'washId',
            title: '破洞名称',
            formatter: function(value, row, index) {
            	var index=orderJson.webWashList.length; 
            	var actions = [];
            	if(orderJson.webWashList!="" && orderJson.webWashList!=null){
            		
            		for(var  i=0;i<index;i++){
            			actions.push(""+orderJson.webWashList[i].name);
            		}
            		return actions.join('<br/>');
            	}else{
            		return "";
            	}
            	
    			
            }
        },
        
        {
            field: 'threadsId',
            title: '边线名称',
            formatter: function(value, row, index) {
            	var index=orderJson.webThreadList.length; 
            	var actions = [];
            	if(orderJson.webThreadList!="" && orderJson.webThreadList!=null){
            		
            		for(var  i=0;i<index;i++){
            			actions.push(""+orderJson.webThreadList[i].name);
            		}
            		return actions.join('<br/>');
            	}else{
            		return "";
            	}
            	
    			
            }
        },
        
        {
            field: 'partsId',
            title: '区域位置',
            formatter: function(value, row, index) {
            	var index=orderJson.webPartsList.length; 
            	var actions = [];
            	if(orderJson.webPartsList!="" && orderJson.webPartsList!=null){
            		
            		for(var  i=0;i<index;i++){
            			actions.push(""+orderJson.webPartsList[i].name);
            		}
            		return actions.join('<br/>');
            	}else{
            		return "";
            	}
            	
    			
            }
        },
        */
       
       
        {
            field: 'status',
            title: '状态',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == 1) {
                    return '<span class="label label-success">正常</span>';
                }else if(value==2)  {
                    return '<span class="label label-danger">禁用</span>';
                }
            }
        },
        
        {
            field: 'remark',
            title: '用户留言',
            formatter: function(value, row, index) {
            	if(orderJson.order!=null && orderJson.order!=""  ){
            		var num=orderJson.order.remark            	
               if(num!=null && num!=""){
            	   return num;
            	   }else{
            		   return "";
            	   }
            		
            	}else{
            		 return "";
            	}
               }
        },
        
       
        {
            field: 'ctime',
            title: '下单时间',
            //sortable:true
        },
       
       
        {
            field: 'payStatus',
            title: '支付状态',
            align: 'center',
            formatter: function(value, row, index) {            	
            	if(orderJson.order!=null && orderJson.order!=""  ){
            		var num=orderJson.order.payStatus
            		 if (num == 1) {
                         return '<span class="label label-primary" ><label onclick="edit(\'' + row.orderId + '\')">已支付（待生产）</label></span>';
                     }else if(num==2)  {
                         return '<span class="label label-primary"><label onclick="edit(\'' + row.orderId + '\')">已支付（已生产）</label></span>';
                     }
            		 
            		 else if(num==3)  {
                         return '<span class="label label-primary" ><label onclick="edit(\'' + row.orderId + '\')">待支付</label></span>';
                     }else if(num==4)  {
                         return '<span class="label label-info"><label onclick="edit(\'' + row.orderId + '\')">已发货</label></span>';
                     }else if(num==5)  {
                         return '<span class="label label-success"><label onclick="edit(\'' + row.orderId + '\')">已收货</label></span>';
                     }else if(num==0)  {
                         return '<span class="label label-danger"><label onclick="edit(\'' + row.orderId + '\')">订单失败</label></span>';
                     }
            	}else{
            		return '<span class="label label-danger">订单交易失败</span>';
            	}
            	
            }
        },
        
       
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
            	if(row.orderType==0){
            	    //定制预览
                    actions.push('<a class="btn btn-success btn-sm" href="#"  title="预览"  mce_href="#"  onclick="yulan(\'' + row.id + '\')">预览效果图</a>');
                }

            	actions.push('<a class="btn btn-primary btn-large " href="#" title="查看" mce_href="#" onclick="check(\'' + row.id + '\')"><i class="fa fa-search-plus"></i></a> ');	
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" onclick="remove(\'' + row.id + '\')"><i class="fa fa-remove"></i></a>');
				 actions.push('  ');
				/*actions.push('<a class="btn btn-danger btn-sm" href="#"  title="更改支付状态"  mce_href="#"  onclick="edit(\'' + row.orderId + '\')">更改支付状态</a>');*/
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*订单明细管理-新增*/
function add() {
    var url = prefix + '/add';
    window.open(url,'_self');
}

/*订单明细管理-查看*/
function check(id) {
    var url = prefix + '/check/' + id;
    window.open(url,'_self');
}

/*订单明细管理-预览*/
function yulan(id) {
    var url = prefix + '/yulan/' + id;
   // window.open(url,'_self');
    layer_show("预览定制款效果图", url, '1000', '800');
}

/*订单明细管理-修改*/
function edit(orderId) {
    var url = prefix + '/edit/' + orderId;
    layer_show("修改订单支付状态", url, '500', '500');
}



// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中该样式订单明细吗？", function(r) {
		_ajax(prefix + "/remove/" + id, "", "post", r);
    })
}

// 批量删除
function batchRemove() {
	var rows = $.getSelections("id");
	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", "warning");
		return;
	}
	$.modalConfirm("确认要删除选中的" + rows.length + "条数据吗?", function(r) {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
	});
}



/*
$("#payStatus").change(function () {
    $.refreshTable();

});

$("#payType").change(function () {
    $.refreshTable();

});*/

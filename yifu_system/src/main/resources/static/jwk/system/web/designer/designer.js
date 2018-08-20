var prefix = "/system/designer"
var jeansJson=null;
$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'name',
            title: '素裤名称'
        },
        
        {
            field: 'code',
            title: '商家编码'
        },
        
        
        {
            field: 'type',
            title: '衣服类型',
            formatter: function(value, row, index) {
                if (value == 0) {
                    return '<span class="label label-primary">裤子</span>';
                } else if (value == 1) {
                    return '<span class="label label-primary">上衣</span>';
                }
            }
            
        },
        
        
       /* {
            field: 'wordFont',
            title: '文字字体'
        },
        
        {
            field: 'wordContent',
            title: '文字内容'
        },
        
        
        {
            field: 'wordColor',
            title: '字体颜色'
        },
        
        {
            field: 'wordSize',
            title: '字体大小'
        },
        
        
        {
            field: 'isTobuy',
            title: '是否可以直接购买',
            formatter: function(value, row, index) {
                if (value == 0) {
                    return '<span class="label label-primary">不可以</span>';
                } else if (value == 1) {
                    return '<span class="label label-primary">可以</span>';
                }
            }
            
        },*/
        
        {
            field: 'colorsId',
            title: '颜色名称',
            formatter: function(value, row, index) {
            	var colorsId=row.colorsId; 
            	var sizesId=row.sizesId; 
            	var buttonsId=row.buttonsId; 
            	var threadsId=row.threadsId; 
            	var partsId=row.partsId; 
            	var washId=row.washId; 
            
            	var formData = new FormData();  
                formData.append("partsId", partsId);
                formData.append("colorsId", colorsId);  
                formData.append("threadsId", threadsId);  
                formData.append("sizesId", sizesId);   
                formData.append("buttonsId", buttonsId);
                formData.append("washId", washId);
            	
            	$.ajax({
        			dataType: "json",
        			cache : true,
        			type : "POST",
        			url : "/system/designer/designerModel",
        			contentType: false,
        			processData: false,
        			data : formData ,
        			async : false,
        			error : function(request) {
        				$.modalAlert("系统错误", "error");
        			},
        			success : function(data) {
        				
        				if (data != null || data!="") {									
        					jeansJson=data;
        					
        					
        				} else {
        					
        					return "*****系统问题";
        				}

        			}
        		});
            	
            	var index=jeansJson.webColorList.length; 
            	var actions = [];
            	if(jeansJson.webColorList!=null && jeansJson.webColorList!=undefined && jeansJson.webColorList!=""){
            		
            		for(var  i=0;i<index;i++){
            			actions.push(''+jeansJson.webColorList[i].name);
            		}
            	}
            	
    			return actions.join('<br/>');
            }
        },
        
        {
            field: 'sizesId',
            title: '尺码',
            formatter: function(value, row, index) {
            	var index=jeansJson.webSizesList.length; 
            	var actions = [];
            	if(jeansJson.webSizesList!=null && jeansJson.webSizesList!=undefined && jeansJson.webSizesList!=""){
            		
            		for(var  i=0;i<index;i++){
            			actions.push(''+jeansJson.webSizesList[i].size);
            		}
            	}
            	
    			return actions.join('<br/>');
            }
        },
        
        {
            field: 'buttonsId',
            title: '纽扣',
            formatter: function(value, row, index) {
            	var index=jeansJson.webButtonList.length; 
            	var actions = [];
            	if(jeansJson.webButtonList!=null && jeansJson.webButtonList!=undefined && jeansJson.webButtonList!=""){
            		
            		for(var  i=0;i<index;i++){
            			actions.push(''+jeansJson.webButtonList[i].name);
            		}
            	}
            	
    			return actions.join('<br/>');
            }
        },
        
        {
            field: 'threadsId',
            title: '边线',
            formatter: function(value, row, index) {
            	var index=jeansJson.webThreadList.length; 
            	var actions = [];
            	if(jeansJson.webThreadList!=null && jeansJson.webThreadList!=undefined && jeansJson.webThreadList!=""){
            		
            		for(var  i=0;i<index;i++){
            			actions.push(''+jeansJson.webThreadList[i].name);
            		}
            	}
            	
    			return actions.join('<br/>');
            }
        },
        
        /*{
            field: 'washId',
            title: '破洞',
            formatter: function(value, row, index) {
            	var index=jeansJson.webWashList.length; 
            	var actions = [];
            	if(jeansJson.webWashList!=null && jeansJson.webWashList!=undefined && jeansJson.webWashList!=""){
            		
            		for(var  i=0;i<index;i++){
            			actions.push(''+jeansJson.webWashList[i].name);
            		}
            	}
            	
    			return actions.join('<br/>');
            }
        },*/
        
        
        {
            field: 'partsId',
            title: '位置',
            formatter: function(value, row, index) {
            	var index=jeansJson.webPartsList.length; 
            	var actions = [];
            	if(jeansJson.webPartsList!=null && jeansJson.webPartsList!=undefined && jeansJson.webPartsList!=""){
            		
            		for(var  i=0;i<index;i++){
            			actions.push(''+jeansJson.webPartsList[i].name);
            		}
            	}
            	
    			return actions.join('<br/>');
            }
        },
        
       
        {
            field: 'status',
            title: '状态',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == 2) {
                    return '<span class="label label-danger">删除</span>';
                } else if (value == 1) {
                    return '<span class="label label-success">正常</span>';
                }else if(value==0)  {
                    return '<span class="label label-warning">未上架</span>';
                }
            }
        },
        
        {
            field: 'remark',
            title: '备注'
        },
        
        {
            field: 'ctime',
            title: '上架时间'
        },
       
        {
            field: 'updateTime',
            title: '更新时间'
        },
       
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="查看" mce_href="#" onclick="check(\'' + row.id + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" onclick="remove(\'' + row.id + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});


/*设计版成品管理-查看*/
function check(id) {
    var url = prefix + '/check/' + id;
    window.open(url,'_self');
}

/*设计版成品管理-新增*/
function add() {
    var url = prefix + '/add';
    window.open(url,'_self');
}

// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中该样式设计版成品吗？", function(r) {
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
var prefix = "/system/order"

$(function() {
	var columns = [
        {
            field: 'orderId',
            title: '订单编号'
        },
        {
            field: 'userId',
            title: '下单用户',
            formatter: function(value, row, index) {
            	var phone="暂无用户手机号码";var name="暂无昵称";
            	$.ajax({
        			dataType: "json",
        			cache : true,
        			type : "POST",
        			url : "/system/order/userinfo",
        			data : {
        				"userId": value					
        			},
        			async : false,
        			error : function(request) {
        				$.modalAlert("系统错误", "error");
        			},
        			success : function(data) {
        				
        				if (data != null || data!="") {
        					
        					phone=data.mobile;
        					name=data.nickname;
        				
        				} 

        			}
        		});
            	var actions = [];
				actions.push('昵称:'+name);
				actions.push('手机号:'+phone);
				return actions.join('<br/>');
            }
        },
        {
            field: 'remark',
            title: '用户留言'
        },
        {
            field: 'num',
            title: '数量'
        },
        {
            field: 'money',
            title: '总价(排序)',
            sortable:true
        },
        
        {
            field: 'payStatus',
            title: '状态',
            formatter: function(value, row, index) {
            	var strHtml = "";
                if (value == 1) {
                    return '已支付(待生产)';              
                } else if (value == 0) {
                    return '失败';
                }else if (value == 2) {
                    return '已支付(已发货)';
                }              
                else if (value == 3) {
                    return '待支付';
                }else if (value == 4) {
                    return '已发货';
                }
                else if (value == 5) {
                    return '完成';
                }
            }
        },
        {
            field: 'payType',
            title: '支付方式',
            formatter: function(value, row, index) {
            	if (value == 1) {
                    return '微信支付';
                } else if (value == 2) {
                    return '支付宝支付';
                }
            }
        },
        {
            field: 'create_time',
            title: '创建时间(排序)',
            sortable:true,
            formatter: function(value, row, index) {
            	
                    return row.createTime;
               
            }
        },
        
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
            	actions.push('<a class="btn btn-warning btn-sm" href="#" title="导出excel"  onclick="derive(\'' + row.id + '\')"><i class="fa fa-folder-open-o"></i></a>');
				actions.push('<a class="btn btn-primary btn-large " href="#" title="查看" mce_href="#" onclick="check(\'' + row.id + '\')"><i class="fa fa-search-plus"></i></a> ');				
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});





// 导出execle
/*function derive(id) {
	$.modalConfirm("确定要导出该订单表吗？", function(r) {	
		_ajax(prefix + "/derive/" + id, "", "post", r); 
    });
}*/

function derive(id) {
	 window.location.href=prefix + "/exportfeedback/" + id;
}


/*潮裤社区-查看*/
function check(id) {
    var url = prefix + '/check/' + id;
   // layer_show("查看信息", url, '1000', '800');
    window.open(url,'_self');
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

	$("#payStatus").change(function () {
      $.refreshTable();
  
    });
	
	$("#payType").change(function () {
	      $.refreshTable();
	  
	    });

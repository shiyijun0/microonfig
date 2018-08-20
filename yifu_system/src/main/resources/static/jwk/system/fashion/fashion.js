var prefix = "/system/fashion"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'id',
            title: 'id'
        },
        {
            field: 'goodsName',
            title: '标题'
        },
        {
            field: 'uid',
            title: '用户',
            formatter: function(value, row, index) {
           	 var mobile="暂无用户手机号码";
            	$.ajax({
        			dataType: "text",
        			cache : true,
        			type : "POST",
        			url : "/system/fashion/checkByuid",
        			data : {
        				"id": value					
        			},
        			async : false,
        			error : function(request) {
        				$.modalAlert("系统错误", "error");
        			},
        			success : function(data) {
        				
        				if (data != null || data!="") {
        					
        					mobile=data;
        				
        				} 

        			}
        		});
				return mobile;
           }
            	
        },
        {
            field: 'selectSize',
            title: '尺码',
            formatter: function(value, row, index) {
            	//var size=row.selectSize.split(",")         	
            	//var obj=JSON.stringify(row.selectSize)
            	var obj = $.parseJSON(row.selectSize);
            	
            	if(obj!=null || obj!=""){
            		return obj.sizeIdx;
            	}else{
            		return "暂无数据";
            	}
				
            }
        },
        
        {
            field: 'goodsIntro',
            title: '描述'
        },
        {
            field: 'buliao',
            title: '布料',
            formatter: function(value, row, index) {
            	var obj = $.parseJSON(row.buliao);       	
            	if(obj!=null || obj!=""){
            		return obj.name;
            	}else{
            		return "暂无数据";
            	}
				
            }
        },
        {
            field: 'buliaoList',
            title: '布料编号',
            formatter: function(value, row, index) {
            	var obj = $.parseJSON(row.buliaoList);       	
            	if(obj!=null || obj!=""){
            		return obj[0].num;
            	}else{
            		return "暂无数据";
            	}
				
            }
        },
        {
            field: 'ctime',
            title: '创建时间'
        },
       
       
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
				actions.push('<a class="btn btn-primary btn-sm " href="#" title="查看" mce_href="#" onclick="check(\'' + row.id + '\')"><i class="fa fa-search-plus"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" onclick="remove(\'' + row.id + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});


/*潮裤社区-查看*/
function check(id) {
    var url = prefix + '/check/' + id;
    //layer_show("查看信息", url, '800', '550');
    window.open(url,'_self');
}

// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中该样式布料吗？", function(r) {
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

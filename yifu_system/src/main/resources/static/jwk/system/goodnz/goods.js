var prefix = "/system/goodnz"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'name',
            title: '商品名称'
        },
        {
            field: 'cover',
            title: '图片',
            formatter: function(value, row, index) {
            	
            	 var id=row.id;var coverUrl=null;
             	$.ajax({
         			dataType: "text",
         			cache : true,
         			type : "POST",
         			url : "/system/goodnz/cover",
         			data : {
         				"id": id					
         			},
         			async : false,
         			error : function(request) {
         				$.modalAlert("系统错误", "error");
         			},
         			success : function(data) {
         				if(data!="0"){
         				if (data != null || data!="") {									
         					coverUrl=data;       					
         				
         				} else {
         					
         					return "*****系统问题";
         				}
         				}else{
         					coverUrl=null;
         				}
         			}
         		});
             	
                if (coverUrl != null) {
                	return '<img src="' + coverUrl + '" alt="图片" width="62" height="52">'
                }else{
                	return "无图片显示";
                }
            }
        },
        {
            field: 'sex',
            title: '男/女',
            	formatter: function(value, row, index) {
                    if (value == 1) {
                        return '男';
                    } else if (value == 2) {
                        return '女';
                    }
                }
        },
        {
            field: 'number',
            title: '商家编码'
        },
        
        {
            field: 'fid',
            title: 'ID'
        },
        {
            field: 'price',
            title: '价格'
        },
        {
            field: 'inventory',
            title: '库存'
        },
        {
            field: 'createTime',
            title: '上架时间'
        },
        {
            field: 'status',
            title: '状态',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == 0) {
                    return '<span class="label label-danger">未上架</span>';
                } else if (value == 1) {
                    return '<span class="label label-success">已上架</span>';
                }else if (value == 2) {
                    return '<span class="label label-warning">删除</span>';
                }
            }
        },
       
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="编辑" mce_href="#" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" onclick="remove(\'' + row.id + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*商品管理-新增*/
function add() {
    var url = prefix + '/add';
   // layer_show("添加牛仔裤", url, '800', '550');
    //if(confirm('添加成功，返回首页？')==true) {window.location.href('/system/goodnz/addinfo.html') } else {window.location.href('/system/goodnz/addinfo.html') } ;
    window.open('/system/goodnz/addinfo.html','_self');
}

/*商品管理-修改*/
function edit(id) {
    var url = prefix + '/editinfo/' + id;
    /* layer_show("修改牛仔裤", url, '800', '550');*/
	window.open(url,'_self');
}

// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中该样式牛仔裤吗？", function(r) {
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

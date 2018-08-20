var prefix = "/system/thread"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'name',
            title: '边线名称'
        },
        
        {
            field: 'type',
            title: '边线编码'
        },
        {
            field: 'cover',
            title: '边线图片',
            formatter: function(value, row, index) {
                if (value != "") {
                	return '<img src="' + qiniuUrl + value + '" alt="边线图片" width="62" height="52">'
                }
            }
        },
        
        {
            field: 'coverTitle',
            title: '边线小图片',
            formatter: function(value, row, index) {
        		if (value != "") {
                	return '<img src="' + qiniuUrl + value + '" alt="边线小图片" width="62" height="52">'
                }
            }
        },
        
        {
            field: 'price',
            title: '价格'
        },
        
        {
            field: 'fixe',
            title: '边线位置',
            formatter: function(value, row, index) {
            	var actions = [];
    			actions.push('X:'+row.fixeX);
    			actions.push('Y:'+row.fixeY);
    			return actions.join('<br/>');
            }
        },
        
       
      /*  {
            field: 'status',
            title: '状态',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == 0) {
                    return '<span class="label label-danger">删除</span>';
                } else if (value == 1) {
                    return '<span class="label label-success">正常</span>';
                }else  {
                    return '<span class="label label-warning">禁用</span>';
                }
            }
        },
        {
            field: 'isDefault',
            title: '默认',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == 0) {
                    return '<span class="label label-default">默认</span>';
                } else if (value == 1) {
                    return '<span class="label label-primary">非默认</span>';
                }else  {
                    return '<span class="label label-warning">禁用</span>';
                }
            }
        },*/
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
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="编辑" mce_href="#" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" onclick="remove(\'' + row.id + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*边线管理-新增*/
function add() {
    var url = prefix + '/add';
    window.open(url,'_self');
}

/*边线管理-修改*/
function edit(id) {
    var url = prefix + '/edit/' + id;
    window.open(url,'_self');
}

// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中该样式边线吗？", function(r) {
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

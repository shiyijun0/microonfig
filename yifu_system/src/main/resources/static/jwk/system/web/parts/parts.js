var prefix = "/system/parts"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'name',
            title: '零件图片名称'
        },
        
        {
            field: 'code',
            title: '零件编码'
        },
        {
            field: 'cover',
            title: '零件图片',
            formatter: function(value, row, index) {
                if (value != "") {
                	return '<img src="' + qiniuUrl + value + '" alt="零件图片" width="62" height="52">'
                }
            }
        },
        
        {
            field: 'coverTitle',
            title: '零件小图片',
            formatter: function(value, row, index) {
        		if (value != "") {
                	return '<img src="' + qiniuUrl + value + '" alt="零件小图片" width="62" height="52">'
                }
            }
        },
        
        /*{
            field: 'type',
            title: '衣服类型',
            formatter: function(value, row, index) {
                if (value == 0) {
                    return '<span class="label label-primary">裤子</span>';
                } else if (value == 1) {
                    return '<span class="label label-primary">上衣</span>';
                }
            }
            
        },*/
        
        
        {
            field: 'region',
            title: '位置',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == 1) {
                    return '<span class="label label-primary">左上</span>';
                } else if (value == 2) {
                    return '<span class="label label-primary">右上</span>';
                }else if (value == 3) {
                    return '<span class="label label-primary">左下</span>';
                }else if (value == 4) {
                    return '<span class="label label-primary">右下</span>';
                }else if (value == 5) {
                    return '<span class="label label-primary">左口袋</span>';
                }else if (value == 6) {
                    return '<span class="label label-primary">右口袋</span>';
                }else if (value == 7) {
                    return '<span class="label label-primary">反面左上</span>';
                }else if (value == 8) {
                    return '<span class="label label-primary">反面右上</span>';
                }else if (value == 9) {
                    return '<span class="label label-primary">反面左下</span>';
                }else if (value == 10) {
                    return '<span class="label label-primary">反面右下</span>';
                }
                else {
                    return '<span class="label label-warning">禁用</span>';
                }
            }
        },
        
        {
            field: 'price',
            title: '价格'
        },
        
        {
            field: 'fixe',
            title: '配件位置',
            formatter: function(value, row, index) {
            	var actions = [];
    			actions.push('X:'+row.fixeX);
    			actions.push('Y:'+row.fixeY);
    			return actions.join('<br/>');
            }
        },
        
       
       /* {
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

/*零件图片管理-新增*/
function add() {
    var url = prefix + '/add';
    window.open(url,'_self');
}

/*零件图片管理-修改*/
function edit(id) {
    var url = prefix + '/edit/' + id;
    window.open(url,'_self');
}

// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中该样式零件图片吗？", function(r) {
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

var prefix = "/system/goodbl"

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
                if (value != "") {
                	return '<img src="' + value + '" alt="图片" width="62" height="52">'
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
                    }else if (value == 3) {
                        return '通用';
                    }
                }
        },
        {
            field: 'number',
            title: '面料'
        },
        
        {
            field: 'fnumber',
            title: '厂家编码'
        },
        {
            field: 'shrinkX',
            title: '缩水率',
            formatter: function(value, row, index) {
            	var actions = [];
				actions.push('X:'+row.shrinkX);
				actions.push('Y:'+row.shrinkX);
				return actions.join('<br/>');
            }
        },
        {
            field: 'price',
            title: '价格'
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
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="编辑" mce_href="#" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" onclick="remove(\'' + row.id + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*布料管理-新增*/
function add() {
    var url = prefix + '/add';
   // layer_show("添加布料", url, '800', '550');
    window.open(url,'_self');
}

/*布料管理-修改*/
function edit(id) {
    var url = prefix + '/edit/' + id;
   // layer_show("修改布料", url, '800', '550');
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

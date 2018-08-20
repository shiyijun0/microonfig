var prefix = "/system/push"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'pushId',
            title: 'ID'
        },
        {
            field: 'title',
            title: '标题'
        },
        {
            field: 'content',
            title: '内容'
        },

        {
            field: 'status',
            title: '状态',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == '0') {
                    return '成功';
                } else if (value == '1') {
                    return '失败';
                }
            }
        },
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="编辑" mce_href="#" onclick="edit(\'' + row.pushId + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" onclick="remove(\'' + row.pushId + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*APP消息推送管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_show("新增推送", url, '800', '550');
}

/*APP消息推送管理-修改*/
function edit(pushId) {
    var url = prefix + '/edit/' + pushId;
    layer_show("修改推送", url, '800', '550');
}

// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中的APP消息推送吗？", function(r) {
		_ajax(prefix + "/remove/" + id, "", "post", r);
    })
}

// 批量删除
function batchRemove() {
	var rows = $.getSelections("pushId");
	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", "warning");
		return;
	}
	$.modalConfirm("确认要删除选中的" + rows.length + "条数据吗?", function(r) {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
	});
}

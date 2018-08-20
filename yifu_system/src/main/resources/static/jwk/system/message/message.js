var prefix = "/system/message"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'messageId',
            title: 'ID'
        },
        {
            field: 'title',
            title: '标题'
        },
        {
            field: 'content',
            title: '内容',
        },
        {
            field: 'type',
            title: '消息类型',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == '1') {
                    return '系统消息';
                } else if (value == '2') {
                    return '通知消息';
                }else if (value == '3') {
                    return '互动消息';
                }
            }
        },
        {
            field: 'ctime',
            title: '创建时间',
            align: 'center',
            //获取日期列的值进行转换
            formatter: function (value, row, index) {
                var date=new Date(value);
                return date.format("yyyy-MM-dd");
            }
        },
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="编辑" mce_href="#" onclick="edit(\'' + row.messageId + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" mce_href="#" onclick="remove(\'' + row.messageId + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*系统消息管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_show("新增消息", url, '800', '550');
}

/*系统消息管理-修改*/
function edit(messageId) {
    var url = prefix + '/edit/' + messageId;
    layer_show("修改消息", url, '800', '550');
}

// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中的消息吗？", function(r) {
		_ajax(prefix + "/remove/" + id, "", "post", r);
    })
}

// 批量删除
function batchRemove() {
	var rows = $.getSelections("messageId");
	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", "warning");
		return;
	}
	$.modalConfirm("确认要删除选中的" + rows.length + "条数据吗?", function(r) {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
	});
}



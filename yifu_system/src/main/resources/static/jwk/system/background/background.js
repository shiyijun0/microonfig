var prefix = "/system/background"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'backgroundId',
            title: 'ID',
            align: 'center',
        },
        {
            field: 'question',
            title: '问题列表',
            align: 'center',
        },
        {
            field: 'url',
            title: '推荐背景图片',
            align: 'center',
            formatter: function(value, row, index) {
                    return '<a href="'+value+'" target="_blank>"><img  src="'+value+'"  width="80px" height="80px" ></a> ';
            }
        },

        {
            field: 'sex',
            title: '性别',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == '1') {
                    return '男';
                } else if (value == '2') {
                    return '女';
                }
            }
        },
        {
            field: 'ctime',
            title: '创建时间',
            align: 'center',
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
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="编辑" mce_href="#" onclick="edit(\'' + row.backgroundId + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" mce_href="#" onclick="remove(\'' + row.backgroundId + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*推荐背景图管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_show("新增背景图", url, '800', '550');
}

/*推荐背景图管理-修改*/
function edit(backgroundId) {
    var url = prefix + '/edit/' + backgroundId;
    layer_show("修改背景图", url, '800', '550');
}

// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中的背景图吗？", function(r) {
		_ajax(prefix + "/remove/" + id, "", "post", r);
    })
}

// 批量删除
function batchRemove() {
	var rows = $.getSelections("backgroundId");
	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", "warning");
		return;
	}
	$.modalConfirm("确认要删除选中的" + rows.length + "条数据吗?", function(r) {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
	});
}

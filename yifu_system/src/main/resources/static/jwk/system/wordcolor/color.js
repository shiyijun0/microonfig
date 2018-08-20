var prefix = "/system/wordcolor"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'colorId',
            title: '颜色编号'
        },
        {
            field: 'colorName',
            title: '颜色名称'
        },
        {
            field: 'colorPrice',
            title: '颜色价格'
        },

        {
            field: 'createTime',
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
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="编辑" mce_href="#" onclick="edit(\'' + row.colorId + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" onclick="remove(\'' + row.colorId + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*文字颜色管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_show("新增文字颜色", url, '800', '550');
}

/*文字颜色管理-修改*/
function edit(colorId) {
    var url = prefix + '/edit/' + colorId;
    layer_show("修改文字颜色", url, '800', '550');
}

// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中的文字颜色吗？", function(r) {
		_ajax(prefix + "/remove/" + id, "", "post", r);
    })
}

// 批量删除
function batchRemove() {
	var rows = $.getSelections("colorId");
	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", "warning");
		return;
	}
	$.modalConfirm("确认要删除选中的" + rows.length + "条数据吗?", function(r) {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
	});
}

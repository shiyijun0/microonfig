var prefix = "/system/wordsize"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'sizeId',
            title: '字号编号'
        },
        {
            field: 'sizeName',
            title: '字号范围'
        },
        {
            field: 'sizePrice',
            title: '字号价格'
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
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="编辑" mce_href="#" onclick="edit(\'' + row.sizeId + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" onclick="remove(\'' + row.sizeId + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*文字字号管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_show("新增文字字号", url, '800', '550');
}

/*文字字号管理-修改*/
function edit(sizeId) {
    var url = prefix + '/edit/' + sizeId;
    layer_show("修改文字字号", url, '800', '550');
}

// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中的文字字号吗？", function(r) {
		_ajax(prefix + "/remove/" + id, "", "post", r);
    })
}

// 批量删除
function batchRemove() {
	var rows = $.getSelections("sizeId");
	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", "warning");
		return;
	}
	$.modalConfirm("确认要删除选中的" + rows.length + "条数据吗?", function(r) {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
	});
}

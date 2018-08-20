var prefix = "/system/wordfont"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'fontId',
            title: '字体编号'
        },
        {
            field: 'fontName',
            title: '字体名称'
        },
        {
            field: 'fontPrice',
            title: '字体价格'
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
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="编辑" mce_href="#" onclick="edit(\'' + row.fontId + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" onclick="remove(\'' + row.fontId + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*文字字体管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_show("新增文字字体", url, '800', '550');
}

/*文字字体管理-修改*/
function edit(fontId) {
    var url = prefix + '/edit/' + fontId;
    layer_show("修改文字字体", url, '800', '550');
}

// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中的文字字体吗？", function(r) {
		_ajax(prefix + "/remove/" + id, "", "post", r);
    })
}

// 批量删除
function batchRemove() {
	var rows = $.getSelections("fontId");
	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", "warning");
		return;
	}
	$.modalConfirm("确认要删除选中的" + rows.length + "条数据吗?", function(r) {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
	});
}

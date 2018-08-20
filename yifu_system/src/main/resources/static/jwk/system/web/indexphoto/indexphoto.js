var prefix = "/system/indexphoto"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'id',
            title: 'ID'
        },
        {
            field: 'img',
            title: '首页图片',
            formatter: function(value, row, index) {
                if (value != "") {
                    return '<img src="' +value+  '" alt="正面图片" width="62" height="52">'
                }
            }
        },
        {
            field: 'ordernum',
            title: '排序'
        },
        {
            field: 'createtime',
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
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="编辑" mce_href="#" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" onclick="remove(\'' + row.id + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*首页图片管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_show("新增首页图片", url, '800', '550');
}

/*首页图片管理-修改*/
function edit(id) {
    var url = prefix + '/edit/' + id;
    layer_show("编辑首页图片", url, '800', '550');
}

// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中该首页图片吗？", function(r) {
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

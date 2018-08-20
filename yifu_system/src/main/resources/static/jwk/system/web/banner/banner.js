var prefix = "/system/banner"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'id',
            title: 'ID'
        },
        {
            field: 'bannerImgs',
            title: '轮播图',
            formatter: function(value, row, index) {
                if (value != "") {
                	return '<img src="' + qiniuUrl + value + '" alt="正面图片" width="62" height="52">'
                }
            }
        },
        {
            field: 'orderNum',
            title: '排序'
        },
        {
            field: 'content',
            title: '内容'
        },
        {
            field: 'designerId',
            title: '成品库ID'
        },
        {
            field: 'name',
            title: '成品库名称'
        },
        {
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
            field: 'createTime',
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

/*轮播图管理-新增*/
function add() {
    var url = prefix + '/add';
    window.open(url,'_self');
}

/*轮播图管理-修改*/
function edit(id) {
    var url = prefix + '/edit/' + id;
    window.open(url,'_self');
}

// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中该样式轮播图吗？", function(r) {
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

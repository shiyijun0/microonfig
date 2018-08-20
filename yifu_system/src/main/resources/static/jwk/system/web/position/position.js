var prefix = "/system/position"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'position',
            title: '区',
            	formatter: function(value, row, index) {
                    if (value != "" ) {
                        return value+'区';
                    } 
                }
        },
        
       /* {
            field: 'type',
            title: '衣服类型',
            formatter: function(value, row, index) {
                if (value == 0 ) {
                    return '<span class="label label-primary">裤子 </span>';;
                } else if(value == 1){
                	return '<span class="label label-success">上衣</span>';;
                }
            }
        },*/
        {
            field: 'des',
            title: '描述'
        },
       
       
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];				
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" onclick="remove(\'' + row.id + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*衣服位置管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_show("添加衣服位置", url, '800', '550');
}


// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中该样式衣服位置吗？", function(r) {
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

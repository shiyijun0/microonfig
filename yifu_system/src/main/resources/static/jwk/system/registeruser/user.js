var prefix = "/system/registeruser"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'id',
            title: 'ID',
        },
        {
            field: 'nickname',
            title: '用户昵称'
        },
        {
            field: 'mobile',
            title: '手机号'
        },

        {
            field: 'sex',
            title: '性别',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == '1') {
                    return '女';
                } else if (value == '2') {
                    return '男';
                }else {
                    return '男';
                }
            }
        },
        {
            field: 'type',
            title: '类型',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == '1') {
                    return '普通用户';
                } else if (value == '2') {
                    return '设计师';
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
            },
        },
        {
            field: 'status',
            title: '状态',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == '1') {
                    return '<a href="#" class="btn btn-info btn-sm "  onclick="status(\'' + row.id + '\',\''+ value + '\')">正常</a>';
                } else if (value == '2') {
                    return '<a href="#" class="btn btn-danger btn-sm "  onclick="status(\'' + row.id + '\',\''+ value + '\')">禁用</a>';
                }
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

/*用户管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_show("新增用户", url, '800', '550');
}

/*用户管理-修改*/
function edit(id) {
    var url = prefix + '/edit/' + id;
    layer_show("修改用户", url, '800', '550');
}

// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中的用户吗？", function(r) {
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


// 状态
function status(id,value) {

    if(value==1){
        value=2;
        $.modalConfirm("确定要改变用户的状态吗？", function(r) {
            _ajax(prefix + '/save', {"id": id, "status": value}, "post", r);
        })
    }else if(value==2){
        value=1
        $.modalConfirm("确定要改变用户的状态吗？", function(r) {
            _ajax(prefix + '/save', {"id": id, "status": value}, "post", r);
        })

    }
    /*$.ajax({
        cache : true,
        type : "POST",
        url : "/system/registeruser/save",
        data : {
            "userId":userId,
            "status":value
        },
        async : false,
        error : function(request) {
            $.modalAlert("系统错误", "error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("修改状态成功,正在刷新数据请稍后……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
                    window.parent.location.reload();
                });
            } else {
                $.modalAlert(data.msg, "error");
            }

        }
    });*/
}



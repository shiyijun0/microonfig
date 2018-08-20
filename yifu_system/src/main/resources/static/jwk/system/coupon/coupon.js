var prefix = "/system/coupon"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'couponId',
            title: 'ID'
        },
        {
            field: 'rank',
            title: '类型级别'
        },
        {
            field: 'full',
            title: '金额'
        },
        {
            field: 'rates',
            title: '优惠金额'
        },
        {
            field: 'number',
            title: '数量'
        },
        {
            field: 'ctime',
            title: '创建时间',
            align: 'center',
            formatter: function (value, row, index) {
                var date=new Date(value);
                return date.format("yyyy-MM-dd hh:mm:ss");
            }
        },
        {
            field: 'endtime',
            title: '截止日期',
            align: 'center',
            formatter: function (value, row, index) {
                var date=new Date(value);
                return date.format("yyyy-MM-dd hh:mm:ss");
            }
        },
        {
            field: 'people',
            title: '分享对象',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == '1') {
                    return '普通优惠券';
                } else if (value == '2') {
                    return 'vip';
                }else  if(value == '3'){
                    return '免单';
                }
            }
        },
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="编辑" mce_href="#" onclick="edit(\'' + row.couponId + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" mce_href="#" onclick="remove(\'' + row.couponId + '\')"><i class="fa fa-remove"></i></a>');
                actions.push('  ');
				actions.push('<a class="btn btn-danger btn-sm" href="#"  title="导出excel"  mce_href="#"  onclick="excel(\'' + row.couponId + '\')">导出excel</a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*优惠券管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_show("新增优惠券", url, '800', '550');
}

/*优惠券管理-修改*/
function edit(couponId) {
    var url = prefix + '/edit/' + couponId;
    layer_show("修改优惠券", url, '800', '550');
}

/*导出excel*/
function excel(couponId) {
    window.location.href=prefix+'/export/' +couponId;
}


// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中的优惠券吗？", function(r) {
		_ajax(prefix + "/remove/" + id, "", "post", r);
    })
}

// 批量删除
function batchRemove() {
	var rows = $.getSelections("couponId");
	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", "warning");
		return;
	}
	$.modalConfirm("确认要删除选中的" + rows.length + "条数据吗?", function(r) {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
	});
}

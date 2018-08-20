var prefix = "/system/contact"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'id',
            title: 'ID'
        },
        {
            field: 'content',
            title: '内容'
        },

        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="编辑" mce_href="#" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i></a> ');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});



/*联系我们-修改*/
function edit(id) {
    var url = prefix + '/edit/' + id;
    layer_show("修改联系我们", url, '800', '550');
}




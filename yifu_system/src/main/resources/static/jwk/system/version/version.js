var prefix = "/system/version"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'versionId',
            title: 'ID'
        },
        {
            field: 'android',
            title: 'android'
        },
        {
            field: 'ios',
            title: 'ios'
        },

        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="编辑" mce_href="#" onclick="edit(\'' + row.versionId + '\')"><i class="fa fa-edit"></i></a> ');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});



/*版本管理-修改*/
function edit(versionId) {
    var url = prefix + '/edit/' + versionId;
    layer_show("修改版本号", url, '800', '550');
}




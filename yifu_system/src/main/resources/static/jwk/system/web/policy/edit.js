$("#form-policy-edit").validate({
	rules:{
        content:{
            required:true,
        },
	},
	submitHandler:function(form){
		update();
	}
});


function update() {
    var content = $("#content").val();
    $.ajax({
		type : "POST",
		url : "/system/policy/save",
		data : {
            "content":content
		},
		async : false,
		error : function(request) {
			$.modalAlert("系统错误", "error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("修改成功,正在刷新数据请稍后……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
					window.parent.location.reload();
				});
			} else {
				$.modalAlert(data.msg, "error");
			}

		}
	});
}

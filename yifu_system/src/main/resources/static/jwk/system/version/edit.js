$("#form-version-edit").validate({
	rules:{
        android:{
            required:true,
        },
        ios:{
            required:true,
        },
	},
	submitHandler:function(form){
		update();
	}
});


function update() {
    var android = $("input[name='android']").val();
    var ios = $("input[name='ios']").val();
	$.ajax({
		type : "POST",
		url : "/system/version/save",
		data : {
            "android":android,
			"ios":ios,
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

$("#form-registeruser-edit").validate({
	rules:{
		nickname:{
			required:true,
		},
		mobile:{
			required:true,
		},
	},
	submitHandler:function(form){
		update();
	}
});


function update() {
	var id=$("#id").val();
	var nickname = $("input[name='nickname']").val();
	var mobile = $("input[name='mobile']").val();
	var type = $("#type option:selected").val();
    var status = $("input[name='status']").is(':checked') == true ? 1 : 2;
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/registeruser/save",
		data : {
			"id":id,
            "nickname": nickname,
            "mobile": mobile,
            "type":type,
            "status":status
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

$("#form-wordfont-edit").validate({
	rules:{
		fontName:{
			required:true,
		},
		fontPrice:{
			required:true,
            number:true,
		},
	},
	submitHandler:function(form){
		update();
	}
});


function update() {
	var fontId = $("input[name='fontId']").val();
	var fontName = $("input[name='fontName']").val();
	var fontPrice = $("input[name='fontPrice']").val();

	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/wordfont/save",
		data : {
			"fontId": fontId,
			"fontName": fontName,
			"fontPrice": fontPrice,
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
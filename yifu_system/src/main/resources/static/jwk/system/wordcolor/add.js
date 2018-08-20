$("#form-wordcolor-add").validate({
	rules:{
		colorName:{
			required:true,
		},
		colorPrice:{
			required:true,
            number:true,
		},
	},
	submitHandler:function(form){
		add();
	}
});


function add() {
	var colorName = $("input[name='colorName']").val();
	var colorPrice = $("input[name='colorPrice']").val();
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/wordcolor/save",
		data : {
			"colorName": colorName,
			"colorPrice": colorPrice,
		},
		async : false,
		error : function(request) {
			$.modalAlert("系统错误", "error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("新增成功,正在刷新数据请稍后……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
					window.parent.location.reload();
				});
			} else {
				$.modalAlert(data.msg, "error");
			}

		}
	});
}

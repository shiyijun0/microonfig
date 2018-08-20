$("#form-wordsize-add").validate({
	rules:{
		sizeName:{
			required:true,
		},
		sizePrice:{
			required:true,
            number:true,
		},
	},
	submitHandler:function(form){
		add();
	}
});


function add() {
	var sizeName = $("input[name='sizeName']").val();
	var sizePrice = $("input[name='sizePrice']").val();
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/wordsize/save",
		data : {
			"sizeName": sizeName,
			"sizePrice": sizePrice,
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

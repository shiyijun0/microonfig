$("#form-label-add").validate({
	rules:{
		label:{
			required:true,

		},
		des:{
			required:true,
		},		
	},
	submitHandler:function(form){
		add();
	}
});

function add() {
	var label = $("input[name='label']").val();
	var des = $("input[name='des']").val();
	$.ajax({
		cache : true,
		type : "POST",
		dataType: "json",
		url : "/system/label/save",
		data : {
			"label": label,
			"des": des
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

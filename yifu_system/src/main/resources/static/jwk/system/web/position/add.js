$("#form-position-add").validate({
	rules:{
		position:{
			required:true,
			digits:true
		},
		/*type:{
			required:true,
		},*/
		des:{
			required:true,
		},		
	},
	messages: {
        "position": {
            digits: "只能输入整数"
        }
    },
	submitHandler:function(form){
		add();
	}
});

function add() {
	var position = $("input[name='position']").val();
	var des = $("input[name='des']").val();
	//var type = $("input[name='type']:checked").val();
	$.ajax({
		cache : true,
		type : "POST",
		dataType: "json",
		url : "/system/position/save",
		data : {
			"position": position,
			//"type": type,
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

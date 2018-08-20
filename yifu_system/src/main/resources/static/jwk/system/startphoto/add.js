$("#form-startphoto-add").validate({
	rules:{
        title:{
			required:true,
		},
        imageFile:{
            required:true,
		},
	},
	submitHandler:function(form){
		add();
	}
});


function add() {
    var data = new FormData($('#form-startphoto-add')[0]);
    $.ajax({
		cache : false,
		type : "POST",
		url : "/system/startphoto/save",
		data :data ,
        processData: false,  //不处理发送的数据，因为data值是FormData对象，不需要对数据做处理
        contentType: false,   //不设置Content-type请求头
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
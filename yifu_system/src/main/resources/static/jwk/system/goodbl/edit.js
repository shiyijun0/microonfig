$("#form-bl-edit").validate({
	rules:{
		name:{
			required:true,
		},
		file:{
			required:true,
		},
		sex:{
			required:true,
		},
		price:{ 
			required:true,
		},
		number:{
			required:true,
		},
		fNumber:{
			required:true,
		},
		shrinkX:{ 
			required:true,
		},
		shrinkY:{
			required:true,
		},
		des:{ 
			required:true,
		},
	},
	submitHandler:function(form){
		edit();
	}
});



function edit() {
	
	//多文件处理
	var num = [];
        var tx = $("#form-bl-edit").find("input:file"); //获取所有上传附件框
        for (var i = 0; i < tx.length; i++) { 
            num.push(tx.eq(i).attr('id'));       //将附件框的ID添加到数组中
        	
        }
        var id=$("#blId").val();
        var name = $("input[name='name']").val();
    	var number = $("input[name='number']").val();
    	var fNumber = $("input[name='fNumber']").val();
    	var sex=$("input[name='sex']:checked").val()
    	var price = $("input[name='price']").val();
    	var shrinkX = $("input[name='shrinkX']").val();
    	var shrinkY = $("input[name='shrinkY']").val();
    	var des = $("input[name='des']").val();	
	
	$.ajaxFileUpload({
		   url : "/system/goodbl/saveUpload",
		   secureuri : false,//是否需要安全协议
		   fileElementId : num,
		   type : 'POST', //文件提交的方式
		   dataType : 'string',
		   cache : false, //是否进行页面缓存
		   async : true, // 是否同步提交
		   success : function(data) {
			   $.ajax({
					dataType: "json",
					cache : true,
					type : "POST",
					url : "/system/goodbl/save",
					data : {
						"name": name,
						"number": number,
						"fNumber": fNumber,
						"sex": sex,
						"price": price,
						"shrinkX": shrinkX,
						"shrinkY": shrinkY,
						"fileUrl" : data,
						"id": id,
						"des": des						
					},
					async : false,
					error : function(request) {
						$.modalAlert("系统错误", "error");
					},
					success : function(data) {
						if (data.code == 0) {
							/*parent.layer.msg("新增成功,正在刷新数据请稍后……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
								window.parent.location.reload();
							});*/
							window.history.back(-1);
						} else {
							$.modalAlert(data.msg, "error");
						}

					}
				});
		   }
	   });
	
}
	
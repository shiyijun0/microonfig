$("#form-pj-edit").validate({
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
		number:{
			required:true,
		},
		fid:{
			required:true,
		},
		price:{ 
			required:true,
		},
		inventory:{
			required:true,
		},
		type:{
			required:true,
		},
		limited:{ 
			required:true,
		},
		category:{
			required:true,
		},
		rotate:{
			required:true,
		},
		fixed:{ 
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
	
	//多类别
	var numArr = [];
        var txt = $("#form-pj-edit").find("input:checkbox"); //获取所有上传附件框
        for (var i = 0; i < txt.length; i++) { 
        	 if(txt[i].checked){
    	          numArr.push(txt[i].value);
    	        }          	
        }
	//alert("****"+numArr)
        
	var idStr = numArr.join(); 
	if(idStr == ''){ 
	alert('请部件选择类别！'); 
	return false; 
	} 
	
	
	//多文件处理
	var num = [];
        var tx = $("#form-pj-edit").find("input:file"); //获取所有上传附件框
        for (var i = 0; i < tx.length; i++) { 
            num.push(tx.eq(i).attr('id'));       //将附件框的ID添加到数组中
        	
        }
        var id=$("#pjId").val();
	var name = $("input[name='name']").val();
	var number = $("input[name='number']").val();
	var inventory = $("input[name='inventory']").val();
	var sex=$("input[name='sex']:checked").val()
	var price = $("input[name='price']").val();
	var fid = $("input[name='fid']").val();
	var type = $("input[name='type']").val();
	var limited = $("input[name='limited']").val();
	var des = $("input[name='des']").val();	
	var rotate = $("input[name='rotate']:checked").val();
	var fixed = $("input[name='fixed']:checked").val();
	
	$.ajaxFileUpload({
		   url : "/system/goodpj/saveUpload",
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
					url : "/system/goodpj/save",
					data : {
						"name": name,
						"number": number,
						"inventory": inventory,
						"sex": sex,
						"price": price,
						"fId": fid,
						"type": type,
						"limited": limited,
						"numArr" : idStr,
						"fileUrl" : data,
						"rotate": rotate,
						"fixed": fixed,
						"id": id,
						"des": des
						
					},
					async : false,
					error : function(request) {
						$.modalAlert("系统错误", "error");
					},
					success : function(data) {
						if (data.code == 0) {
							window.history.back(-1);
							/*parent.layer.msg("新增成功,正在刷新数据请稍后……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
								window.parent.location.reload();
							});*/
						} else {
							$.modalAlert(data.msg, "error");
						}

					}
				});
		   }
	   });
	
}
	
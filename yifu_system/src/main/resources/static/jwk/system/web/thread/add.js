$("#form-thread-add").validate({
	rules:{
		name:{
			required:true,
		},
		file1:{
			required:true,
		},
		type:{
			required:true,
		},
		file2:{
			required:true,
		},
		price:{
			required:true,
			number:true,
		},
		fixeX:{
			required:true,
			number:true,
		},
		fixeY:{
			required:true,
			number:true,
		},
		/*status:{ 
			required:true,
		},
		isDefault:{
			required:true,
		}*/
	},
	submitHandler:function(form){
		add();
	}
});


function add() {
	var name = $("input[name='name']").val();
	var file1=$('#file1')[0].files[0];
	var file2=$('#file2')[0].files[0];
	var type = $("input[name='type']").val();
	var price = $("input[name='price']").val();
	var fixeX = $("input[name='fixeX']").val();
	var fixeY = $("input[name='fixeY']").val();	
	//var status = $("input[name='status']:checked").val();
	//var isDefault = $("input[name='isDefault']:checked").val();
	
	
	var formData = new FormData();

    var  label=$("#labelId").val();
    formData.append("label", label);

    formData.append("file1", file1);  
    formData.append("file2", file2);     
    formData.append("type", type);
    formData.append("name", name);  
    formData.append("price", price);  
    formData.append("fixeX", fixeX);
    formData.append("fixeY", fixeY);  
   // formData.append("status", status);  
   // formData.append("isDefault", isDefault);
		
	$.ajax({
		cache : true,
		type : "POST",
		dataType: "json",
		url : "/system/thread/save",
		contentType: false,
		processData: false,
		data : formData ,
		async : false,
		error : function(request) {
			$.modalAlert("系统错误", "error");
		},
		success : function(data) {
			if (data.code == 0) {
				window.history.back(-1);
			} else {
				$.modalAlert(data.msg, "error");
			}

		}
	});
}


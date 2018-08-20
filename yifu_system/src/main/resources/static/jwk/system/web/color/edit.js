var label=null;
$("#labelId").change(function(){
    label=$("#labelId").val();

});

$("#form-color-edit").validate({
	rules:{
		name:{
			required:true,
		},
		/*file1:{
			required:true,
		},
		file2:{
			required:true,
		},
		file3:{
			required:true,
		},*/
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
		},*/
		
		part1:{ 
			required:true,
		},
		
		part2:{ 
			required:true,
		},
		part3:{ 
			required:true,
		},
		
		part4:{ 
			required:true,
		},
		part5:{ 
			required:true,
		},
		
		part6:{ 
			required:true,
		},
		part7:{ 
			required:true,
		},
		
		part8:{ 
			required:true,
		},
		
		depth:{ 
			required:true,
		},
		/*isDefault:{
			required:true,
		}*/
	},
	submitHandler:function(form){
		edit();
	}
});



function edit() {
	var id=$("#colorId").val();
	var name = $("input[name='name']").val();
	var file1=$('#file1')[0].files[0];	
	var file2=$('#file2')[0].files[0];
	var file3=$('#file3')[0].files[0];
	var price = $("input[name='price']").val();
	var fixeX = $("input[name='fixeX']").val();
	var fixeY = $("input[name='fixeY']").val();	
	var status = $("input[name='status']:checked").val();
	//var isDefault = $("input[name='isDefault']:checked").val();
	var depth = $("input[name='depth']:checked").val();
	
	var part1 = $("input[name='part1']:checked").val();
	var part2 = $("input[name='part2']:checked").val();
	var part3 = $("input[name='part3']:checked").val();
	var part4 = $("input[name='part4']:checked").val();
	var part5 = $("input[name='part5']:checked").val();
	var part6 = $("input[name='part6']:checked").val();
	var part7 = $("input[name='part7']:checked").val();
	var part8 = $("input[name='part8']:checked").val();

     label=$("#labelId").val();

	var formData = new FormData();
    formData.append("file1", file1);  
    formData.append("file2", file2);  
    formData.append("file3", file3);
    
    formData.append("part1", part1);
    formData.append("part2", part2);
    formData.append("part3", part3);
    formData.append("part4", part4);
    formData.append("part5", part5);
    formData.append("part6", part6);
    formData.append("part7", part7);
    formData.append("part8", part8);
    
    formData.append("id", id);
    formData.append("name", name);  
    formData.append("price", price);  
    formData.append("fixeX", fixeX);
    formData.append("fixeY", fixeY);  
    formData.append("status", status);  
    //formData.append("isDefault", isDefault);
    formData.append("depth", depth);

    formData.append("label", label);

	$.ajax({
		cache : true,
		type : "POST",
		dataType: "json",
		url : "/system/color/save",
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
	
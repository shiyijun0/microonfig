$("#form-presell-add").validate({
	rules:{
		presellCode:{
			required:true,
		},
		file1:{
			required:true,
		},
		file2:{
			required:true,
		},
		price:{
			required:true,
			number:true,
		},
		priceName:{
			required:true,
		},
		originalPrice:{
			required:true,
		},
		inventory:{
			required:true,
			number:true,
		},
		sizes:{
			required:true,
		},
		colors:{
			required:true,
		},
		orderNum:{
			required:true,
			number:true,
		},
		status:{ 
			required:true,
		},
		
	},
	submitHandler:function(form){
		add();
	}
});

function add() {
	var file1=$('#file1')[0].files[0];
	var file2=$('#file2')[0].files[0];
	var file3=$('#file3')[0].files[0];
	var file4=$('#file4')[0].files[0];
	
	var orderNum = $("input[name='orderNum']").val();
	var status = $("input[name='status']:checked").val();		
	var name = $("#name").val();
	var presellCode = $("#presellCode").val();
	var price = $("#price").val();
	var priceName = $("#priceName").val();
	var originalPrice = $("#originalPrice").val();
	var inventory = $("#inventory").val();
	
	var sizesId = "";
	$('input:checkbox[name="sizes"]:checked').each(function(){  
        if(sizesId==""){
        	sizesId = $(this).val();
        }else{
        	sizesId = sizesId+","+$(this).val();
        }
    }); 
	
	var colorsId = "";
	$('input:checkbox[name="colors"]:checked').each(function(){  
		if(colorsId==""){
			colorsId = $(this).val();
		}else{
			colorsId = colorsId+","+$(this).val();
		}
	}); 
	
	var formData = new FormData();
    formData.append("file1", file1);  
    formData.append("file2", file2);  
    formData.append("file3", file3);  
    formData.append("file4", file4);  
    formData.append("name", name);  
    formData.append("presellCode", presellCode);
    formData.append("price", price);  
    formData.append("priceName", priceName);  
    formData.append("originalPrice", originalPrice);  
    formData.append("inventory", inventory);
    formData.append("status", status);  
    formData.append("orderNum", orderNum);  
    formData.append("colorsId", colorsId);  
    formData.append("sizesId", sizesId);  
    
	$.ajax({
		cache : true,
		type : "POST",
		dataType: "json",
		url : "/system/presell/save",
		contentType: false,
		processData: false,
		data : formData,
		async : false,
		error : function(request) {
			$.modalAlert("系统错误", "error");
		},
		beforeSend:function(){
			$("#savebtn").text("提交中");
		},complete:function(){
			$("#savebtn").text("提交");
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


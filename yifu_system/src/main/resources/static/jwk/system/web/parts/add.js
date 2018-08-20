var form=$("#form-parts-add");
/*form.find('select[name="type"]').change(function() {
				var thisVal = $(this).val();
				if (thisVal == 0) {  
					document.getElementById('iDBody1').style.display = "";
				    document.getElementById('iDBody2').style.display = "none";
				    $("#region2").attr("checked",false);
					
				}
				if (thisVal == 1) {
					document.getElementById('iDBody1').style.display = "none";
					$("#region1").attr("checked",false);
				    document.getElementById('iDBody2').style.display = "";
				}
				
			});*/



$("#form-parts-add").validate({
	rules:{
		name:{
			required:true,
		},
		file1:{
			required:true,
		},
		code:{
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
		},*/
		region:{
			required:true,
		},
		/*type:{
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
	var code = $("input[name='code']").val();
	var price = $("input[name='price']").val();
	var fixeX = $("input[name='fixeX']").val();
	var fixeY = $("input[name='fixeY']").val();	
	//var status = $("input[name='status']:checked").val();
	var region = $("input[name='region']:checked").val();
	
	//var type = $("select[name='type']").val();
	
	var formData = new FormData();

    var  label=$("#labelId").val();
    formData.append("label", label);

    formData.append("file1", file1);  
    formData.append("file2", file2);     
    //formData.append("type", type);
    formData.append("name", name);  
    formData.append("price", price);  
    formData.append("fixeX", fixeX);
    formData.append("fixeY", fixeY);  
   // formData.append("status", status);  
    formData.append("region", region);
    formData.append("code", code);
		
	$.ajax({
		cache : true,
		type : "POST",
		dataType: "json",
		url : "/system/parts/save",
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


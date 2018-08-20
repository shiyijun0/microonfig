var designerId=null;

$("#designerId").change(function(){
    designerId=$("#designerId").val();


});

$("#form-banner-edit").validate({
	rules:{
		
		/*file1:{
			required:true,
		},*/
		
		orderNum:{
			required:true,
			number:true,
		},
        content:{
            required:true,
        },
		designerId:{
			required:true,
			
		},
		
		/*status:{ 
			required:true,
		},*/
		
	},
	submitHandler:function(form){
		edit();
	}
});



function edit() {
	var id=$("#bannerId").val();
	var orderNum = $("input[name='orderNum']").val();
	var file1=$('#file1')[0].files[0];
	//var designerId = $("input[name='designerId']").val();
   designerId=$("#designerId").val();
    var name = $("select[name='designerId']").find("option:selected").text();
	//var status = $("input[name='status']:checked").val();
    var content=$("input[name='content']").val();
	
	
	var formData = new FormData();
    formData.append("file1", file1);
    formData.append("name", name);
    formData.append("designerId", designerId);
    formData.append("id", id);
    formData.append("orderNum", orderNum);
    formData.append("content", content);
    //formData.append("status", status);  
  
		
	$.ajax({
		cache : true,
		type : "POST",
		dataType: "json",
		url : "/system/banner/save",
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
	
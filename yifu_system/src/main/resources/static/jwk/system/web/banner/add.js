var designerId=null;

$("#designerId").change(function(){
	     designerId=$("#designerId").val();
	     // alert("*********"+designerId)
	    
	});

$("#form-banner-add").validate({
	rules:{
		
		file1:{
			required:true,
		},
		
		orderNum:{
			required:true,
			number:true,
		},
        content:{
            required:true,
        },

		designerId:{
			required:true,
			number:true,
		},
		
		/*status:{ 
			required:true,
		},*/
		
	},
	submitHandler:function(form){
		add();
	}
});


function add() {
	
	var file1=$('#file1')[0].files[0];
	
	var orderNum = $("input[name='orderNum']").val();
	var content=$("input[name='content']").val();
	//var designerId = $("input[name='designerId']").val();
	
	var name = $("select[name='designerId']").find("option:selected").text();
    // alert(name);
	  designerId=$("#designerId").val();
	//var status = $("input[name='status']:checked").val();		
	
	var formData = new FormData();
    formData.append("file1", file1);  
    
    formData.append("name", name); 
    formData.append("designerId", designerId);
    formData.append("orderNum", orderNum);
    formData.append("content", content);
   // formData.append("status", status);  
    
		
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


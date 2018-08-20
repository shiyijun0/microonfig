

var webJeansId=null;
var webPartsId=null;

$("#webJeansId").change(function(){
	webJeansId=$("#webJeansId").val();
	    
	    
	});

$("#webPartsId").change(function(){
	webPartsId=$("#webPartsId").val();
	    
	    
	});

$("#form-location-add").validate({
	rules:{
		
		webPartsId:{
			required:true,
		},
		
		webJeansId:{
			required:true,
			
		},
		
		
	},
	submitHandler:function(form){
		add();
	}
});


function add() {
	
	/*var file1=$('#file1')[0].files[0];
	
	var orderNum = $("input[name='orderNum']").val();*/
	
	
	
	var jeansName = $("select[name='webJeansId']").find("option:selected").text();    
	webJeansId=$("#webJeansId").val();
	
	var partsName = $("select[name='webPartsId']").find("option:selected").text();    
	webPartsId=$("#webPartsId").val();
	
	 
	// var status = $("input[name='status']:checked").val();		
	
	var formData = new FormData();
    formData.append("jeansName", jeansName);  
    
    formData.append("jeansId", webJeansId); 
    formData.append("partsName", partsName);
    formData.append("partsId", webPartsId);  
     
   // formData.append("status", status);  
    
		
	$.ajax({
		cache : true,
		type : "POST",
		dataType: "json",
		url : "/system/location/save",
		contentType: false,
		processData: false,
		data : formData ,
		async : false,
		error : function(request) {
			$.modalAlert("系统错误", "error");
		},
		success : function(data) {
			if (data.code == 0) {
				//window.history.back(-1);
				window.location.href="/system/location/index_location?model="+data.model+"&color="+data.color+"&thread="+data.thread+"&partsId="+data.partsId;
			} else {
				$.modalAlert(data.msg, "error");
			}

		}
	});
}


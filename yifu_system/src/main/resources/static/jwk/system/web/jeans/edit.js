var form=$("#form-jeans-edit"); 
$("#form-jeans-edit").validate({
	rules:{
		name:{
			required:true,
		},
		title:{
			required:true,
		},
		
		code:{
			required:true,
		},
		
		/*file3:{
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
		},
		isTobuy:{ 
			required:true,
		},*/
		type:{
			required:true,
		},
		color:{
			required:true,
		},
		thread:{
			required:true,
		},
		/*wash:{
			required:true,
		},*/
		buttons:{
			required:true,
		},
		size:{
			required:true,
		} 
	},
	submitHandler:function(form){
		edit();
	}
});


function edit() {
	var name = $("input[name='name']").val();
	var file3=$('#file3')[0].files[0];
	var code = $("input[name='code']").val();
	var price = $("input[name='price']").val();
	var fixeX = $("input[name='fixeX']").val();
	var fixeY = $("input[name='fixeY']").val();	
	//var status = $("input[name='status']:checked").val();
	//var isTobuy = $("input[name='isTobuy']:checked").val();	
	var title = $("input[name='title']").val();
	var id = $("input[name='washId']").val();
    var label=$("#webJeansId").val();
	var numArrRegion= [];var numArrColor = [];var numArrSize = [];
	var numArrThread = [];var numArrButtons = []; var numArrWash = [];    
	//选择图片位置
	var txt=document.getElementsByName("region");
	  for (var i = 0; i < txt.length; i++) { 
        	 if(txt[i].checked){
        		 numArrRegion.push(txt[i].value);
    	        }          	
        } 
	
	var numArrRegionId = numArrRegion.join();
	
	
	if(numArrRegionId == ''){ 
	alert('请部件选择图片区域！'); 
	return false; 
	} 
	//选择颜色
	var txt3 = $("#iDBody3").find("input:checkbox"); 
    for (var i = 0; i < txt3.length; i++) { 
    	 if(txt3[i].checked){
    		 numArrColor.push(txt3[i].value);
	        }          	
    }       
	var numArrColorId = numArrColor.join(); 
	
	//选择边线
	var txt4 = $("#iDBody4").find("input:checkbox"); 
    for (var i = 0; i < txt4.length; i++) { 
    	 if(txt4[i].checked){
    		 numArrThread.push(txt4[i].value);
	        }          	
    }       
	var numArrThreadId = numArrThread.join();
	
	//选择尺寸
	var txt5 = $("#iDBody5").find("input:checkbox"); 
    for (var i = 0; i < txt5.length; i++) { 
    	 if(txt5[i].checked){
    		 numArrSize.push(txt5[i].value);
	        }          	
    }       
	var numArrSizeId = numArrSize.join();
	
	
	//选择纽扣
	var txt6 = $("#iDBody6").find("input:checkbox"); 
    for (var i = 0; i < txt6.length; i++) { 
    	 if(txt6[i].checked){
    		 numArrButtons.push(txt6[i].value);
	        }          	
    }       
	var numArrButtonsId = numArrButtons.join();
	
	//选择破洞
	/*var txt7 = $("#iDBody7").find("input:checkbox");
    for (var i = 0; i < txt7.length; i++) { 
    	 if(txt7[i].checked){
    		 numArrWash.push(txt7[i].value);
	        }          	
    }       
	var numArrWashId = numArrWash.join();*/
	//alert("*********"+numArrWashId)
	var formData = new FormData();
    formData.append("file3", file3);  
    formData.append("name", name);  
    formData.append("price", price);  
    formData.append("fixeX", fixeX);
    formData.append("fixeY", fixeY);  
   // formData.append("status", status);  
    formData.append("code", code);   
    formData.append("title", title);
   // formData.append("isTobuy", isTobuy);  
    formData.append("partsId", numArrRegionId);
    formData.append("colorsId", numArrColorId);  
    formData.append("threadsId", numArrThreadId);
  //  formData.append("washId", numArrWashId);
    formData.append("sizesId", numArrSizeId);   
    formData.append("buttonsId", numArrButtonsId);
    formData.append("id", id);
    formData.append("label", label);
	$.ajax({
		cache : true,
		type : "POST",
		dataType: "json",
		url : "/system/jeans/save",
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

	
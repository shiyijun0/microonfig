/*
var form=$("#form-jeans-add"); var thisVal=null;
form.find('select[name="type"]').change(function() {
				 thisVal = $(this).val();
				if (thisVal == 0) {  
					document.getElementById('iDBody1').style.display = "";
				    document.getElementById('iDBody2').style.display = "none";				  
				    var checkedbrowser=document.getElementsByName("region2");  
		             
		            var len = checkedbrowser.length;  
		            var i = 0;  
		            for(; i < len; i++){  
		                // 必须先赋值为false,再移除属性  
		                checkedbrowser[i].checked = false;  
		                // 不移除属性也可以  
		                //checkedbrowser[i].removeAttribute("checked");  
		            }  
				  
					
				}
				if (thisVal == 1) {
					document.getElementById('iDBody1').style.display = "none";					
				    document.getElementById('iDBody2').style.display = "";
				    var checkedbrowser=document.getElementsByName("region1");  
		             
		            var len = checkedbrowser.length;  
		            var i = 0;  
		            for(; i < len; i++){  
		                // 必须先赋值为false,再移除属性  
		                checkedbrowser[i].checked = false;  
		                // 不移除属性也可以  
		                //checkedbrowser[i].removeAttribute("checked");  
		            }  
				}
				
			});
*/



$("#form-jeans-add").validate({
	rules:{
		name:{
			required:true,
		},
		title:{
			required:true,
		},
		/*file1:{
			required:true,
		},*/
		code:{
			required:true,
		},
		/*file2:{
			required:true,
		},*/
		file3:{
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
		add();
	}
});


function add() {
	var name = $("input[name='name']").val();
	/*var file1=$('#file1')[0].files[0];
	var file2=$('#file2')[0].files[0];*/
	var file3=$('#file3')[0].files[0];
	var code = $("input[name='code']").val();
	var price = $("input[name='price']").val();
	var fixeX = $("input[name='fixeX']").val();
	var fixeY = $("input[name='fixeY']").val();	
	//var status = $("input[name='status']:checked").val();
	//var isTobuy = $("input[name='isTobuy']:checked").val();
	//var type = $("select[name='type']").val();
    var type = $("input[name='type']:checked").val();
	var title = $("input[name='title']").val();
	var label=$("#webJeansId").val();
	//选择图片位置
	var numArrRegion= [];var numArrColor = [];var numArrSize = [];
	var numArrThread = [];var numArrButtons = []; var numArrWash = [];    
	
	/*if(thisVal==0){
		  var txt=document.getElementsByName("region1");
		  
		  for (var i = 0; i < txt.length; i++) { 
	        	 if(txt[i].checked){
	        		 numArrRegion.push(txt[i].value);
	    	        }          	
	        }  
		
	}else if(thisVal==1){
		var txt=document.getElementsByName("region2");
		  
		  for (var i = 0; i < txt.length; i++) { 
	        	 if(txt[i].checked){
	        		 numArrRegion.push(txt[i].value);
	    	        }          	
	        } 
	}else{
		var txt=document.getElementsByName("region1");
		thisVal=0;
		  for (var i = 0; i < txt.length; i++) { 
	        	 if(txt[i].checked){
	        		 numArrRegion.push(txt[i].value);
	    	        }          	
	        }  
	}
	*/

    var txt=document.getElementsByName("region1");
    for (var i = 0; i < txt.length; i++) {
        if(txt[i].checked){
            numArrRegion.push(txt[i].value);
        }
    }
    var numArrRegionId = numArrRegion.join();
	
	//alert("****numArrRegionId****"+numArrRegionId+"***thisVal****"+thisVal)
	
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
	
	var formData = new FormData();
   /* formData.append("file1", file1);  
    formData.append("file2", file2);*/
    formData.append("file3", file3);
    formData.append("type", type);
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
   // formData.append("washId", numArrWashId);
    formData.append("sizesId", numArrSizeId);   
    formData.append("buttonsId", numArrButtonsId);

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


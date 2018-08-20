/*var form=$("#form-jeans-add"); var thisVal=null;
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
				
			});*/

		$("#webJeansId").change(function(){
			webJeansId=$("#webJeansId").val();
			//var jeansId = $("select[name='webJeansId']").find("option:selected").attr("id");   			 
			//window.location.href="/system/designer/adds?id="+webJeansId;
			var url="/system/designer/adds?id="+webJeansId;
			
			
			 window.open(url,'_self');
			
			/*var formData = new FormData();
			   			   
			    formData.append("id", webJeansId);
			    
				$.ajax({
					cache : true,
					type : "POST",
					dataType: "json",
					url : "/system/designer/jeansId",
					contentType: false,
					processData: false,
					data : formData ,
					async : false,
					error : function(request) {
						$.modalAlert("系统错误", "error");
					},
					success : function(data) {
						if (data) {
							//window.history.back(-1);
							//alert(data.partList1[0].name+"*******")
							jeansList=data;
						
						} else {
							$.modalAlert(data.msg, "error");
						}

					}
				});
				
				if(jeansList){
					alert("******"+jeansList)
				}*/
				
			});

		


$("#form-designer-add").validate({
	rules:{
		name:{
			required:true,
		},
		
		code:{
			required:true,
		},
			
		
		type:{
			required:true,
		},
		colorsId:{
			required:true,
		},
		threadsId:{
			required:true,
		},
		/*washsId:{
			required:true,
		},*/
		buttonsId:{
			required:true,
		},
		sizesId:{
			required:true,
		} 
	},
	submitHandler:function(form){
		add();
	}
});


function add() {
	//var name = $("input[name='name']").val();
	//var jeansId=$("input[name='name']").attr("id");
	var name = $("select[name='webJeansId']").find("option:selected").text();
	var jeansId = $("select[name='webJeansId']").find("option:selected").attr("id");
	var code = $("input[name='code']").val();	
	var type = $("input[name='type']:checked").val();
	var colorsId = $("input[name='colorsId']:checked").val();
	var threadsId = $("input[name='threadsId']:checked").val();
	//var washsId = $("input[name='washsId']:checked").val();
	var buttonsId = $("input[name='buttonsId']:checked").val();
	var sizesId = $("input[name='sizesId']:checked").val();
	
	var remark = $("input[name='remark']").val();
	//选择图片位置
	var numArrRegion= [];  
	
	//选择区域位置
	/*var txt7 = $("#iDBody1").find("input:checkbox"); 
    for (var i = 0; i < txt7.length; i++) { 
    	 if(txt7[i].checked){
    		 numArrRegion.push(txt7[i].value);
	        }          	
    }  */     
	
	var partsId1 = $("input[name='partsId1']:checked").val();
	var partsId2 = $("input[name='partsId2']:checked").val();
	var partsId3 = $("input[name='partsId3']:checked").val();
	var partsId4 = $("input[name='partsId4']:checked").val();
	var partsId5 = $("input[name='partsId5']:checked").val();
	var partsId6 = $("input[name='partsId6']:checked").val();
	var partsId7 = $("input[name='partsId7']:checked").val();
	var partsId8 = $("input[name='partsId8']:checked").val();
	
	
	//alert(partsId1==undefined);
	if(partsId1==undefined && partsId2==undefined && partsId3==undefined && partsId4==undefined && 
			partsId5==undefined && partsId6==undefined && partsId7==undefined && partsId8==undefined){
		
		alert("至少选中一个区域位置");
		return;
	}
	
	if(partsId1!=undefined){
		numArrRegion.push(partsId1);
	}
	
	if(partsId2!=undefined){
		numArrRegion.push(partsId2);
	}
	
	if(partsId3!=undefined){
		numArrRegion.push(partsId3);
	}
	
	if(partsId4!=undefined){
		numArrRegion.push(partsId4);
	}
	
	if(partsId5!=undefined){
		numArrRegion.push(partsId5);
	}
	
	
	if(partsId6!=undefined){
		numArrRegion.push(partsId6);
	}
	
	if(partsId7!=undefined){
		numArrRegion.push(partsId7);
	}
	

	if(partsId8!=undefined){
		numArrRegion.push(partsId8);
	}
	
	var numArrpartsId = numArrRegion.join();
	
	
	var formData = new FormData();
   
   
    formData.append("type", type);
    formData.append("name", name);  
    formData.append("jeansId", jeansId);    
    formData.append("code", code);   
    formData.append("remark", remark); 
    formData.append("partsId", numArrpartsId);
    formData.append("colorsId", colorsId);  
    formData.append("threadsId", threadsId);
   // formData.append("washId", washsId);
    formData.append("sizesId", sizesId);   
    formData.append("buttonsId", buttonsId);
		
	$.ajax({
		cache : true,
		type : "POST",
		dataType: "json",
		url : "/system/designer/save",
		contentType: false,
		processData: false,
		data : formData ,
		async : false,
		error : function(request) {
			$.modalAlert("系统错误", "error");
		},
		success : function(data) {
			if (data.code == 0) {
				var jeansList = $("#selectno").val();
				console.log(jeansList)
				if(jeansList==1){
					window.location.href="/system/designer";
					//window.history.back(-2);
				}else{
					window.history.back(-1);
				}
			} else {
				$.modalAlert(data.msg, "error");
			}

		}
	});
}


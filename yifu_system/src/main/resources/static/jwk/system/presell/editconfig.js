$("#form-config-edit").validate({
	rules:{
		limited:{
			required:true,
			digits:true,
			number:true,
		},
		price:{
			required:true,
			number:true,
		},		
		status:{
			required:true,
		},
		startTime:{
            required:true,
        },
        endTime:{
            required:true,
        },
		
	},
	messages: {
        "limited": {
            digits: "只能输入整数"
        }
    },
	submitHandler:function(form){
		edit();
	}
});

//时间格式化
Date.prototype.Format = function (fmt) {
    var o = {  
        "M+": this.getMonth() + 1, //月份   
        "d+": this.getDate(), //日   
        "H+": this.getHours(), //小时   
        "m+": this.getMinutes(), //分   
        "s+": this.getSeconds(), //秒   
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
        "S": this.getMilliseconds() //毫秒   
    };  
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    for (var k in o)  
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
    return fmt;  
}  

function edit() {
	var id = $("input[name='id']").val();
	var limited = $("input[name='limited']").val();
	var price = $("input[name='price']").val();
	var status = $("input[name='status']:checked").val();
	var sTime = $("input[name='ctime']").val();
	var eTime = $("input[name='endtime']").val();
	var selectpreId = $("#selectpreId").val();
	
	var time = new Date().Format("yyyy-MM-dd HH:mm:ss");
	if(eTime<sTime){
		$.modalAlert("限时结束时间不能小于限时开始时间");
		return;
	}
	if(sTime<=time){
		$.modalAlert("限时开始时间不能小于当前时间");
		return;
	}
	if( selectpreId =="" || selectpreId == null){
		$.modalAlert("获取不到当前选中的预售款信息");
		return;
	}
	
	$.ajax({
		cache : true,
		type : "POST",
		dataType: "json",
		url : "/system/presell/saveconfig",
		data : {
			"id": id,
			"limited": limited,
			"presellId": selectpreId,
			"price": price,
			"status": status,
			"startTime": sTime,
			"endTime": eTime
		},
		async : false,
		error : function(request) {
			$.modalAlert("系统错误", "error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("修改成功,正在刷新数据请稍后……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
					window.parent.location.reload();
				});
			} else {
				$.modalAlert(data.msg, "error");
			}

		}
	});
}

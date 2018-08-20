$("#form-recommend-add").validate({
	rules:{
		recommendName:{
			required:true,
		},
		limited:{
			required:true,
			digits:true,
		},
		inventory:{
			required:true,
			digits:true,
		},
		price:{
			required:true,
			number:true,
		},		
		status:{
			required:true,
		},
		ctime:{
            required:true,
        },
        endtime:{
            required:true,
        }
	},
	messages: {
        "inventory": {
            digits: "只能输入整数"
        },
        "limited": {
            digits: "只能输入整数"
        }
    },
	submitHandler:function(form){
		add();
	}
});

function add() {
	var recommendId = $("select[name='recommendName']").val();
	var limited = $("input[name='limited']").val();
	var inventory = $("input[name='inventory']").val();
	var price = $("input[name='price']").val();
	
	//var status = $("input[name='status']").is(':checked') == true ? 1 : 0;
	var status = $("input[name='status']:checked").val();
	
	/*var sDate = $('input[name="sDate"]').val().trim();
	var sHour = $('input[name="sHour"]').val().trim();
	var eDate = $('input[name="eDate"]').val().trim();
	var eHour = $('input[name="eHour"]').val().trim();
	var sTime = moment(sDate + ' ' + sHour + ':00').format('YYYY-MM-DD HH:mm:ss');
	var eTime = moment(eDate + ' ' + eHour + ':00').format('YYYY-MM-DD HH:mm:ss');*/
	
	var sTime = $("input[name='ctime']").val();
	var eTime = $("input[name='endtime']").val();
	//var people = $("#people option:selected").val();
	
	$.ajax({
		cache : true,
		type : "POST",
		dataType: "json",
		url : "/system/recommend/save",
		data : {
			"recommendId": recommendId,
			"limited": limited,
			"inventory": inventory,
			"price": price,
			"status": status,
			"sTime": sTime,
			"eTime": eTime
		},
		async : false,
		error : function(request) {
			$.modalAlert("系统错误", "error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("新增成功,正在刷新数据请稍后……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
					window.parent.location.reload();
				});
			} else {
				$.modalAlert(data.msg, "error");
			}

		}
	});
}

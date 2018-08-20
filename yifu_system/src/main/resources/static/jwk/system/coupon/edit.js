$("#form-coupon-edit").validate({
	rules:{
        rank:{
            required:true,
        },
        full:{
            required:true,
            number:true,
        },
        rates:{
            required:true,
            number:true,
        },
        number:{
            required:true,
            digits:true,
        },
        ctime:{
            required:true,
        },
        endtime:{
            required:true,
        },
	},
    messages: {
        "number": {
            digits: "只能输入整数"
        }
    },
	submitHandler:function(form){
		update();
	}
});


function update() {
	var couponId =$("#couponId").val();
    var rank = $("input[name='rank']").val();
    var full = $("input[name='full']").val();
    var rates = $("input[name='rates']").val();
    var number = $("input[name='number']").val();
    var ctime = $("input[name='ctime']").val();
    var endtime = $("input[name='endtime']").val();
    var people = $("#people option:selected").val();
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/coupon/save",
		data : {
			"couponId":couponId,
            "rank": rank,
            "full": full,
            "rates":rates,
            "number":number,
            "ctime":ctime,
            "endtime":endtime,
            "people":people
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

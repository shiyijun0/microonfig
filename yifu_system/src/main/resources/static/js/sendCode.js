$(function() {
	$("#getPhoneCode").bind("click", sendValidateCode);//发送验证码

});

var timer;
var timer_running = false;
var remain_second = 60;
var conflict=true;
function sendValidateCode() {
	var mobile = $.trim($("#phone").val());
	if (mobile==null || mobile=="") {
		$.modalMsg("获取不到手机号");
		return;
	}else{
		var regu = /^\d{11}$/;
		var re = new RegExp(regu);
		if(!(re.test(mobile) || new RegExp(/^\d{3}\*\*\*\*\d{4}$/).test(mobile))){
			$.modalMsg("手机号格式不正确");
			return;
		}
	}
	$.ajax({
        cache: true,
        type: "POST",
        url: "/custom/sendcode",
        data: {
            "mobile": mobile,
        },
        async: false,
        dataType: "json",
        error: function (request) {
            $.modalMsg("系统出问题了");
        },
        success: function (data) {
            if (data.code == 0) {
            	$.modalMsg("短信发送成功");
            	$(".verification_news_again").css({"opacity": "0.5"})
				if(!timer_running){
					timer_running = true;
                    conflict=false;
					timer = setInterval("remainTime()", 1000);
			    }
				remainTime();
				$("#getPhoneCode").unbind("click");
            } else {
                $.modalMsg("验证码发送失败");
            }
        }
    });
}

function remainTime() {
	remain_second--;
	$("#getPhoneCode").html(remain_second + "秒后重发");
	if (remain_second <= 0) {
		clearTimeout(timer);
		timer_running = false;
		$("#getPhoneCode").html("获取验证码");
		$(".verification_news_again").css({"opacity": "1"});
		remain_second = 60;
		$("#getPhoneCode").bind("click", sendValidateCode);
	}
}
$(function() {
  //头部三个圆点点击事件,判断用户是否已经登录
    $(".back_right").click(function() {
    	$.ajax({
            cache: true,
            type: "POST",
            url: "/custom/islogin",
            data:{},
            dataType: "json",
            error: function (request) {
                $.modalMsg("系统出问题了");
            },
            success: function (data) {
            	if(data.msg=="未登录"){
            		$(".login_switch").fadeOut();
                    $(".login").fadeIn();
            	}else{
            		$("body").animate({"left": "55%"},500).css("overflow","hidden");
           	        $(".nav_mid").animate({"left": "0"},500);
           	        $(".nav_bg").fadeIn();
            	}
            }
    	});
    });
});

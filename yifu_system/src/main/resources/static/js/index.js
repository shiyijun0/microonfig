$(function(){

    $.ajax({
        cache: true,
        type: "POST",
        url: "/custom/user2",
        dataType: "json",
        error: function (request) {
            alert("系统出问题了");
        },
        success: function (data) {
            if (data.user!=null) {
                if(data.user.type==1){
                    $(".type").text("普通用户");
                }else if(data.user.type==2){
                    $(".type").text("设计师");
                }
                $(".big").text(data.user.nickname);
                $(".integral").text("积分 "+data.user.integral);
                $("#uimg").attr("src",data.user.portraiturl);
            }
        }
    });


});

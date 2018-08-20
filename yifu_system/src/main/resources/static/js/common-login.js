$(function () {
    $(".nav_bg").click(function () {
        if (indexType == 1) {
            $("body").animate({"left": "0"}, 500).css("overflow", "auto");
            $(".nav_mid").animate({"left": "-55%"}, 500);
            $(".nav_bg").fadeOut();
        } else {
            $("body").animate({"left": "0"}, 50).css("overflow", "auto");
            $(".nav_mid").animate({"left": "-55%"}, 500);
            $(".nav_bg").fadeOut();
        }

    });

    $("#verification_back").click(function () {
        $(".verification").fadeOut();
        $(".login").fadeIn();
    });

    $(".tit_fr").click(function () {
        $(".lo_bg").fadeOut()
    });

    //点击密码登录，切换到验证码登录
    $("#login").click(function (event) {
        $(".login_switch").fadeOut();
        $(".login").fadeIn();
    });

    //点击验证码登录，切换到密码登录
    $("#login_switch").click(function (event) {
        $(".login_switch").fadeIn();
        $(".login").fadeOut();
    });

    //头部三个圆点点击事件,判断用户是否已经登录
    $(".back_right").click(function () {
    	var reqLeftUrl = window.location.href;
        $.ajax({
            cache: true,
            type: "POST",
            url: "/custom/islogin",
            data: {reqLeftUrl:reqLeftUrl},
            dataType: "json",
            error: function (request) {
                $.modalMsg("系统出问题了");
            },
            success: function (data) {
                if (data.msg == "未登录") {
                    $(".login_switch").fadeOut();
                    $(".login").fadeIn();
                } else {
                    if (data.user.type == 1) {
                        $(".type").text("普通用户");
                    } else if (data.user.type == 2) {
                        $(".type").text("设计师");
                    }
                    $(".big").text(data.user.nickname);
                    $(".integral").text(" 积分 " + data.user.integral);
                    $("#uimg").attr("src", data.user.portraiturl);

                    if (indexType == 1) {//针对定制
                        $("body").animate({"left": "55%"}, 500).css("overflow", "hidden");
                        // $(".nav_mid").animate({"left": "0"},500);
                        $(".nav_bg").fadeIn();
                    } else {
                        $("body").animate({"left": "0"}, 150).css("overflow-x", "hidden");
                        $(".nav_mid").animate({"left": "0"}, 500);
                        $(".nav_bg").fadeIn();
                    }

                }
            }
        });
    });

    /*验证手机号是否正确*/
    $(".login_next").click(function () {//验证码登录
        var phLeg = $(".login_ph").find("input").val();
        var regex = /^1[3|5|7|8]\d{9}$/;
        var result = regex.test(phLeg);
        if (!result) {
            $.modalMsg("请输入正确手机号");
            return;
        } else {
            $.ajax({
                cache: true,
                type: "POST",
                url: "/custom/codetime",
                data: {
                    "mobile": phLeg,
                },
                dataType: "json",
                error: function (request) {
                    $.modalMsg("系统出问题了");
                },
                success: function (data) {
                    if (data > 0) {
                        $("#getPhoneCode").unbind("click");
                        if (conflict) {
                            $("#getPhoneCode").html(data + "秒后重发");
                            $(".verification_news_again").css({"opacity": "0.5"})
                            data--;
                            var timer = setInterval(function () {
                                $("#getPhoneCode").html(data + "秒后重发");
                                if (data > 0) {
                                    $("#getPhoneCode").unbind("click");
                                    data--;
                                } else {
                                    clearInterval(timer);
                                    $("#getPhoneCode").html("获取验证码");
                                    $(".verification_news_again").css({"opacity": "1"});
                                    $("#getPhoneCode").bind("click", sendValidateCode);
                                }
                            }, 1000);
                        }
                    }
                }
            });


            $(".login").fadeOut();
            $(".verification_news").empty();
            $(".verification_news").append("验证码发送至" + phLeg);
            $(".verification").fadeIn();
        }
    });

    /*验证码登录点击下一步*/
    $(".verification_num").on("input", function () {
        $(this).next("input").focus();
        var str = $(".verification_num input").val();
        var mobile = $(".login_ph").find("input").val();
        if (str.length == 4) {
            $.ajax({
                cache: true,
                type: "POST",
                url: "/custom/Iscode",
                data: {
                    "mobile": mobile,
                    "code": str,
                },
                dataType: "json",
                error: function (request) {
                    $.modalMsg("系统出问题了");
                },
                success: function (data) {
                    if (data.code == 0) {
                        if (data.user.type == 1) {
                            $(".type").text("普通用户");
                        } else if (data.user.type == 2) {
                            $(".type").text("设计师");
                        }
                        $(".big").text(data.user.nickname);
                        $(".integral").text(" 积分 " + data.user.integral);
                        $("#uimg").attr("src", data.user.portraiturl);
                        $(".verification,.login_bg,.set_password,.password").fadeOut();
                    } else if (data.code == 1) {
                        $(".verification").fadeOut();
                        $(".set_password").fadeIn();
                    } else if (data.code == 2) {
                        $.modalMsg("你的验证码有误");
                    }
                }
            });

        }

        if (str.length > 4) {
            $.modalMsg("你的验证码有误");
        }


    });

    /*验证码登录 密码确认*/
    $("#sub").click(function () {
        var phLeg = $(".login_ph").find("input").val();
        var pass = $("#password1").val();
        var regex = /(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^.{8,16}$/;
        var result = regex.test(pass);
        if (!result) {
            $.modalMsg("密码设置格式有误！");
            return;
        } else {
            $.ajax({
                cache: true,
                type: "POST",
                url: "/custom/save",
                data: {
                    "password": pass,
                    "mobile": phLeg,
                    "nickname": phLeg,
                },
                dataType: "json",
                async: false,
                error: function (request) {
                    $.modalMsg("系统出问题了");
                },
                success: function (data) {
                    if (data.code == 0) {
                        if (data.user.type == 1) {
                            $(".type").text("普通用户");
                        } else if (data.user.type == 2) {
                            $(".type").text("设计师");
                        }
                        $(".big").text(data.user.nickname);
                        $(".integral").text(" 积分 " + data.user.integral);
                        $("#uimg").attr("src", data.user.portraiturl);
                        $(".verification,.login_bg,.set_password,.password").fadeOut();
                        $.modalMsg("登录成功");
                    }
                    else {
                        $.modalMsg("登录失败");
                    }
                }
            });
        }
    });


    /*密码登录点击下一步*/
    $(".login_switch_next").click(function () {
        var mobile = $("#mobile").val();
        if (mobile == "" || mobile == null) {
            $.modalMsg("请输入手机号");
            return;
        }
        var regex = /^1[3|5|7|8]\d{9}$/;
        var result = regex.test(mobile);
        if (!result) {
            $.modalMsg("手机号格式不正确");
            return;
        } else {
            $.ajax({
                cache: true,
                type: "POST",
                url: "/custom/checkMobileUnique",
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
                        $(".login_switch").fadeOut();
                        $("#mobile1").val("");
                        $("#mobile1").val(mobile);
                        $(".entering_password").fadeIn();
                    }
                    else {
                        $.modalMsg("该用户不存在");
                    }
                }
            });
        }
    });


    /*密码登录点击确认*/
    $("#sub2").click(function () {
        var phLeg = $("#mobile1").val();
        var pass = $("#pass2").val();
        if (pass == "" || pass == null) {
            $.modalMsg("请输入密码");
            return;
        }
        var regex = /^1[3|5|7|8]\d{9}$/;
        var result = regex.test(phLeg);
        if (!result) {
            $.modalMsg("手机号格式不正确");
            return;
        } else {
            $.ajax({
                cache: true,
                type: "POST",
                url: "/custom/checkPassword",
                data: {
                    "password": pass,
                    "mobile": phLeg,
                },
                async: false,
                dataType: "json",
                error: function (request) {
                    $.modalMsg("系统出问题了");
                },
                success: function (data) {
                    if (data.code == 0) {
                        if (data.user.type == 1) {
                            $(".type").text("普通用户");
                        } else if (data.user.type == 2) {
                            $(".type").text("设计师");
                        }
                        $(".big").text(data.user.nickname);
                        $(".integral").text(" 积分 " + data.user.integral);
                        $("#uimg").attr("src", data.user.portraiturl);
                        $(".entering_password").fadeOut();
                        $(".verification,.login_bg,.set_password,.password").fadeOut();
                        $("#mobile").val('');
                        $.modalMsg("登录成功");
                    }
                    else {
                        $.modalMsg("输入的密码有误");
                    }
                }
            });
        }
    });


    /**
     * 点击忘记密码
     */
    $(".entering_password_news").click(function(){
        var mobile = $("#mobile1").val();
        $.ajax({
            cache: true,
            type: "POST",
            url: "/custom/checkMobileUnique",
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
                    $("#forgetmobile").val(mobile);
                    $(".login,.entering_password").fadeOut();
                    $(".password").fadeIn();
                }
                else {
                    $.modalMsg("该用户不存在");
                }
            }
        });

    });


    /**
     * 点击提交新密码
     */
    $("#forgetpass").click(function(){
        var phLeg=$("#forgetmobile").val();
        var pass = $("#newpassword").val();
        var regex = /(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^.{8,16}$/;
        var result = regex.test(pass);
        if (!result) {
            $.modalMsg("密码设置格式有误！");
            return;
        } else {
            $.ajax({
                cache: true,
                type: "POST",
                url: "/custom/resetPassword",
                data: {
                    "password": pass,
                    "mobile": phLeg,
                },
                dataType: "json",
                async: false,
                error: function (request) {
                    $.modalMsg("系统出问题了");
                },
                success: function (data) {
                    if (data.code == 0) {
                        $(".verification,.login_bg,.set_password,.password").fadeOut();
                        $.modalMsg("密码重置成功");
                    }
                    else {
                        $.modalMsg("密码重置失败");
                    }
                }
            });
        }



    });


})

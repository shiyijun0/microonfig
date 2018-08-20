// 手机号码验证
$.validator.addMethod("isMobile", function(value, element) {
	var length = value.length;
	var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
	return this.optional(element) || (length == 11 && mobile.test(value));
   }, "请正确填写您的手机号码");

$("#form-registeruser-add").validate({
	rules:{
        nickname:{
			required:true,
		},
        mobile:{
			required:true,
            isMobile:true,
            remote: {
                url: "/system/registeruser/checkMobileUnique",
                type: "post",
                dataType: "text",
                data: {
                    mobile : function() {
                        return $.trim($("#mobile").val());
                    }
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            },
		},
	},
    messages: {
        "mobile": {
            isMobile: "手机号格式不正确",
            remote: "手机号已经存在"
        }
    },
	submitHandler:function(form){
		add();
	}
});


function add() {
	var nickname = $("input[name='nickname']").val();
	var mobile = $("input[name='mobile']").val();
	var type = $("#type option:selected").val();
    var status = $("input[name='status']").is(':checked') == true ? 1 : 2;
    $.ajax({
		cache : true,
		type : "POST",
		url : "/system/registeruser/save",
		data : {
			"nickname": nickname,
			"mobile": mobile,
			"type":type,
			"status":status
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

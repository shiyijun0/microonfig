$("#form-user-add").validate({
	rules:{
		loginName:{
			required:true,
			minlength: 5,
			remote: {
                url: "/system/user/checkNameUnique",
                type: "post",
                dataType: "text",
                data: {
                	name : function() {
                        return $.trim($("#loginName").val());
                    }
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }
		},
		userName:{
			required:true,
		},
		password:{
			required:true,
			minlength: 6
		},
		email:{
			required:true,
			email:true
		},
		phonenumber:{
			required:true,
		},
	},
	messages: {
        "loginName": {
            remote: "该用户已经存在"
        }
    },
	submitHandler:function(form){
		add();
	}
});

function getCheckeds(_name) {
	var adIds = "";
	$('input:checkbox[name="'+_name+'"]:checked').each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}

function add() {
	var userId = $("input[name='userId']").val();
	var deptId = $("input[name='deptId']").val();
	var loginName = $("input[name='loginName']").val();
	var userName = $("input[name='userName']").val();
	var password = $("input[name='password']").val();
	var email = $("input[name='email']").val();
	var phonenumber = $("input[name='phonenumber']").val();
	var status = $("input[name='status']").is(':checked') == true ? 0 : 1;
	var roleIds = $.getCheckeds("role");
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/user/save",
		data : {
			"userId": userId,
			"deptId": deptId,
			"loginName": loginName,
			"userName": userName,
			"password": password,
			"email": email,
			"phonenumber": phonenumber,
			"status": status,
			"roleIds": roleIds
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

/*用户管理-修改-选择部门树*/
function selectDeptTree() {
    var url = "/system/user/selectDeptTree";
    layer_show("选择部门", url, '400', '410');
}

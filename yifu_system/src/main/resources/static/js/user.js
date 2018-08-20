$(function(){

    $("#file0").change(function(){
         var data = new FormData($('#form1')[0]);
        $.ajax({
            cache: true,
            type: "POST",
            url: "/custom/userphoto",
            data:data,
            dataType: "json",
            processData: false,  //不处理发送的数据，因为data值是FormData对象，不需要对数据做处理
            contentType: false,   //不设置Content-type请求头
            error: function (request) {
                $.modalMsg("系统出问题了");
            },
            success: function (data) {
                if (data.user!=null) {
                    $("#img0").attr("src",data.user.portraiturl);
                    $("#uimg").attr("src",data.user.portraiturl);
                }else{
                    $.modalMsg("更改用户头像失败");
                }
            }
        });
    });


});




function sub(){
    var nickname=$("#nickname").val();
    if(nickname.length>11){
        $.modalMsg("昵称长度不能超过11位字符");
        return;
    }
    $.ajax({
        cache: true,
        type: "POST",
        url: "/custom/usersave",
        data:$('#form1').serialize(),
        dataType: "json",
        error: function (request) {
            $.modalMsg("系统出问题了");
        },
        success: function (data) {
            if (data.user!=null) {
                $("#nick").text(data.user.nickname);
                if(data.user.type==1){
                    $(".type").text("普通用户");
                }else if(data.user.type==2){
                    $(".type").text("设计师");
                }
                $(".big").text(data.user.nickname);
                $(".integral").text("积分 "+data.user.integral);
                $("#nickname").val(data.user.nickname);
                if(data.user.sex==1){
                    $("#sex option[value='1']").attr("selected","selected");
                }else{
                    $("#sex option[value='2']").attr("selected","selected");
                }
                if(data.user.birthday!=0) {
                    $("#birthday").val(data.user.birthday);
                }else{
                    $("#birthday").val("");
                }
                $("#mobile").val(data.user.mobile);
                $("#wechat").val("");
                $("#weibo").val("");
                $("#id").val(data.user.id);
                $("#img0").attr("src",data.user.portraiturl);
                $("#uimg").attr("src",data.user.portraiturl);
                $.modalMsg("修改成功");
            }else{
                $.modalMsg("修改失败");
            }
        }
    });

}

// 图片上传
var uploadPreview = function(setting) {
    var _self = this;
    _self.IsNull = function(value) {
        if (typeof (value) == "function") { return false; }
        if (value == undefined || value == null || value == "" || value.length == 0) {
            return true;
        }
        return false;
    }
    _self.DefautlSetting = {
        UpBtn: "",
        DivShow: "",
        ImgShow: "",
        ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
        ErrMsg: "选择文件错误,图片类型必须是(gif,jpeg,jpg,bmp,png)中的一种",
        callback: function() { }
    };
    _self.Setting = {
        UpBtn: _self.IsNull(setting.UpBtn) ? _self.DefautlSetting.UpBtn : setting.UpBtn,
        DivShow: _self.IsNull(setting.DivShow) ? _self.DefautlSetting.DivShow : setting.DivShow,
        ImgShow: _self.IsNull(setting.ImgShow) ? _self.DefautlSetting.ImgShow : setting.ImgShow,
        Width: _self.IsNull(setting.Width) ? _self.DefautlSetting.Width : setting.Width,
        Height: _self.IsNull(setting.Height) ? _self.DefautlSetting.Height : setting.Height,
        ImgType: _self.IsNull(setting.ImgType) ? _self.DefautlSetting.ImgType : setting.ImgType,
        ErrMsg: _self.IsNull(setting.ErrMsg) ? _self.DefautlSetting.ErrMsg : setting.ErrMsg,
        callback: _self.IsNull(setting.callback) ? _self.DefautlSetting.callback : setting.callback
    };
    _self.getObjectURL = function(file) {
        var url = null;
        if (window.createObjectURL != undefined) {
            url = window.createObjectURL(file);
        } else if (window.URL != undefined) {
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) {
            url = window.webkitURL.createObjectURL(file);
        }
        return url;
    }
    _self.Bind = function() {
        document.getElementById(_self.Setting.UpBtn).onchange = function() {
            if (this.value) {
                if (!RegExp("\.(" + _self.Setting.ImgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {
                    alert(_self.Setting.ErrMsg);
                    this.value = "";
                    return false;
                }
                if (navigator.userAgent.indexOf("MSIE") > -1) {
                    try {
                        document.getElementById(_self.Setting.ImgShow).src = _self.getObjectURL(this.files[0]);
                    } catch (e) {
                        var div = document.getElementById(_self.Setting.DivShow);
                        this.select();
                        top.parent.document.body.focus();
                        var src = document.selection.createRange().text;
                        document.selection.empty();
                        document.getElementById(_self.Setting.ImgShow).style.display = "none";
                        div.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                        div.style.width = _self.Setting.Width + "px";
                        div.style.height = _self.Setting.Height + "px";
                        div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src;
                    }
                }
                else {
                    document.getElementById(_self.Setting.ImgShow).src = _self.getObjectURL(this.files[0]);
                }
                _self.Setting.callback();
            }
        }
    }
    _self.Bind();
}

function file_click(){

    new uploadPreview({ UpBtn: "file0", ImgShow: "img0"});
}
window.onload = file_click;


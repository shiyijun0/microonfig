/*Date.prototype.format = function(format) {
    var date = {
        "M+" : this.getMonth() + 1,
        "d+" : this.getDate(),
        "h+" : this.getHours(),
        "m+" : this.getMinutes(),
        "s+" : this.getSeconds(),
        "q+" : Math.floor((this.getMonth() + 3) / 3),
        "S+" : this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '')
            .substr(4 - RegExp.$1.length));
    }
    for ( var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k]
                : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
}



$(function(){

    $.ajax({
        cache: true,
        type: "POST",
        url: "/custom/selectcoupon",
        dataType: "json",
        error: function (request) {
            $.modalMsg("系统出问题了");
        },
        success: function (data) {
            if (data.code == 0) {
                $(".packet_box").remove();
                for (var i = 0; i < data.size; i++) {
                    $(".red_packet").append("<div class=\"packet_box\">\n" +
                        "\t\t<div class=\"packet_fl\">\n" +
                        "\t\t\t<p class=\"packet_big\">优惠卷</p>\n" +
                        "\t\t\t<p class=\"packet_sm\" >有效期为 " + new Date(data.couponUser[i].ctime).format("yyyy.M.d") + "-" + new Date(data.couponUser[i].endtime).format("yyyy.M.d") +
                        "\t\t</div>\n" +
                        "\t\t<div class=\"packet_fr\" >" + data.couponUser[i].rank + "</div>\n" +
                        "\t</div>");
                }
            }
        }
    });


});*/


function sub(){
       var code=$("#couponcode").val();
       if(code==""||code==null){
           $.modalMsg("优惠码不能为空");
       }else {
           $.ajax({
               cache: true,
               type: "POST",
               url: "/custom/couponcode",
               data: {
                   "code": code,
               },
               dataType: "json",
               error: function (request) {
                   $.modalMsg("系统出问题了");
               },
               success: function (data) {
                   if (data.code == 0) {
                       window.location.href="/custom/volume";
                   } else if (data.code == 1) {
                       $.modalMsg(data.msg);
                   } else if (data.code == 2) {
                       $.modalMsg(data.msg);
                   }else if(data.code==3){
                       $.modalMsg(data.msg);
                   }
               }
           });
       }
}

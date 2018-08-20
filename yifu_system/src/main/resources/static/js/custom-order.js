$(function() {
	//下单 判断是否已经登录
    $("#xiadan").click(function () {
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
                    $(".login,.login_bg").fadeIn();
            	}else{
            		$(".login,.login_bg").fadeOut();
            		//去订单页
            		goOrder();
            	}
            }
    	});
    });
   


})
//去订单页
function goOrder(){
	console.info(2222);
    var type = moduleInfo.moduleType == null ? null : moduleInfo.moduleType;
    var colorsId = moduleInfo.buliao == null ? null : moduleInfo.buliao.id;
    var buttonsId = null;
    var threadsId = moduleInfo.bianxian == null ? null : moduleInfo.bianxian.id;
    var washId = moduleInfo.podong == null ? null : moduleInfo.podong.id;
    var parts1 = moduleInfo.tuFRU == null ? null : moduleInfo.tuFRU.id;
    var parts2 = moduleInfo.tuFRD == null ? null : moduleInfo.tuFRD.id;
    var parts3 = moduleInfo.tuFLU == null ? null : moduleInfo.tuFLU.id;
    var parts4 = moduleInfo.tuFLD == null ? null : moduleInfo.tuFLD.id;
    var parts5 = moduleInfo.tuDRU == null ? null : moduleInfo.tuDRU.id;
    var parts6 = moduleInfo.tuDLU == null ? null : moduleInfo.tuDLU.id;
    var parts7 = moduleInfo.tuDRD == null ? null : moduleInfo.tuDRD.id;
    var parts8 = moduleInfo.tuDLD == null ? null : moduleInfo.tuDLD.id;

    var numArrParts = [];

    if (parts1) {
        numArrParts.push(parts1)
    }
    if (parts2) {
        numArrParts.push(parts2)
    }
    if (parts3) {
        numArrParts.push(parts3)
    }
    if (parts4) {
        numArrParts.push(parts4)
    }
    if (parts5) {
        numArrParts.push(parts5)
    }
    if (parts6) {
        numArrParts.push(parts6)
    }
    if (parts7) {
        numArrParts.push(parts7)
    }
    if (parts8) {
        numArrParts.push(parts8)
    }

    var partsId = numArrParts.join();
    var wordColor = moduleInfo.wenZi == null ? null : moduleInfo.wenZi.color;
    var wordFont = moduleInfo.wenZi == null ? null : moduleInfo.wenZi.font;
    var wordContent = moduleInfo.wenZi == null ? null : moduleInfo.wenZi.content;
    var wordPrice = moduleInfo.wenZi == null ? null : moduleInfo.wenZi.sprice;

    var price = allSprice;//总价格
    var status = 1;
    if(designerId == "" || designerId == null){
    	$.modalMsg("找不到商品id");
    	return;
    }
    if(jeansId == "" || jeansId == null){
    	$.modalMsg("找不到商品id");
    	return;
    }
    if(price == "" || price == null){
    	$.modalMsg("价格为空");
    	return;
    }
    
    $.ajax({
        cache: true,
        type: "POST",
        url: "/custom/saveorderGoods",
        data: {

            "jeansId": designerId,//成品裤Id
            "type": type,
            "colorsId": colorsId,
            "buttonsId": buttonsId,
            "threadsId": threadsId,
            "washId": washId,
            "partsId": partsId,
            "wordColor": wordColor,
            "wordFont": wordFont,
            "wordContent": wordContent,
            "wordPrice": wordPrice,
            "status": status,
            "price": price,
            "orderType":0
        },
        async: false,
        error: function (request) {
            $.modalMsg("系统出问题了");
        },
        success: function (data) {
        	console.info(data);
            if (data.code == 0) {
            	var userId = data.userId;
                var info = data.webOrderGoods;
                if( info == null || userId == null){
                	$.modalMsg("下单出错");
                	return;
                }
                var requrl = window.location.href;
                window.location.href = "/custom/goOrder?jeansId="+jeansId+"&orderType=0&requrl="+requrl;
                //$(".login,.login_bg").fadeOut();
            }
            else {
            	if("请重新登录"==data.msg){
                	$(".login,.login_bg").fadeIn();
                	return;
            	}else{
            		$.modalMsg(data.msg);
            	}
            }
        }
    });
}
/*$(function(){
	
    $.ajax({
        cache: true,
        type: "POST",
        url: "/custom/addrmanage",
        dataType: "json",
        error: function (request) {
            alert("系统出问题了");
        },
        success: function (data) {
            console.info(data);
            if (data.code==0) {
                $("#sub").prevAll().remove();
                for(var i=0;i<data.size;i++){
                    var add="<div class='address_box'>" +
                       "<a href='javascript:close("+data.address[i].addrId+")' >"+
                       "<div class='address_box_close' >×</div>" +
                        "</a>"+
                        "<a href='javascript:saveaddr("+data.address[i].addrId+")' >"+
                       "<div class='address_box_tit'>" +
                       "<span>" + data.address[i].linkman + "  "+data.address[i].mobile+" </span>" +
                       "</div>" +
                       "<div class='address_box_p'>" +
                       "<p class='P_fl'>" +
                       "<span>详细地址</span>" +
                       "</p>" +
                       "<p class='P_fr'>"+data.address[i].addr+"</p>" +
                       "</div>" +
                        "</a>"+
                       "<div class='address_box_tit' style='position: relative;'>"+
                       "<label for='name1'>"+
                       "<span>默认地址 </span>"+
                       "<span>";
                        if(data.address[i].defaultFlag==0) {
                          add+= "<input type='radio' name='defaultid' onclick='setdefault("+data.address[i].addrId+")'>"
                        }
                        if(data.address[i].defaultFlag==1){
                          add+= "<input type='radio' name='defaultid' checked='checked' onclick='setdefault("+data.address[i].addrId+")'>";
                        }
                        add+="</span>"+
                            "<a href='javascript:updateaddr("+data.address[i].addrId+")'>修改 </a>"+
                            "</label>"+
                            "</div>"+
                            "</div>";
                    $("#sub").before(add);
                }
            }
        }
    });
});*/

/*删除地址*/
function adddelete(addrId){
    layer.confirm('您确定要删除此条记录吗？', {
        btn: ['确定','取消'], //按钮
        icon: 3,
        title: "系统提示",
        btnclass: ['btn btn-danger']
    }, function(){
        $.ajax({
            cache: true,
            type: "POST",
            url: "/custom/addrclose",
            data:{
                "id":addrId,
            },
            dataType: "json",
            error: function (request) {
                alert("系统出问题了");
            },
            success: function (data) {
                if(data.code==0){
                    window.location.href="/custom/address?jeansId="+jeansId+"&orderType="+orderType+"&cartId="+cartId+"&cartOrder="+cartOrder;
                }else{
                    alert(data.msg);
                }
            }
        });
    });
}


/*更改默认地址*/
function setdefault(id){
        $.ajax({
            cache: true,
            type: "POST",
            url: "/custom/updatedefault",
            data:{
                "id":id
               
            },
            dataType: "json",
            error: function (request) {
                alert("系统出问题了");
            },
            success: function (data) {
                if(data.code==0){
                    alert("设置默认地址成功");
                }else{
                    alert("设置默认地址错误");
                }
            }
        });
}


function updateaddr(id){
    window.location.href="/custom/addredit?addrId="+id+"&jeansId="+jeansId+"&orderType="+orderType+"&cartOrder="+cartOrder+"&cartId="+cartId;
}


function saveaddr(id){
	if(jeansId!=0 && jeansId!=null ){
		if(cartOrder == 1){
			window.location.href="/custom/cartOrder?jeansId="+jeansId+"&cartId="+cartId+"&addrId="+id;
		}
		if(cartOrder == 0 || cartOrder == "" || cartOrder == null || cartOrder == "null"){
			window.location.href="/custom/goOrder?userId="+userId+"&jeansId="+jeansId+"&addrId="+id+"&orderType="+orderType;
		}
		if(cartOrder == 2){
			window.location.href="/custom/address";
			//window.history.back(-1);
		}
	}
}
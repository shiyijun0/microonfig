$("#form-jeans-add").validate({
	rules:{
		jeansName:{
			required:true,
		},
		file:{
			required:true,
		},
		number:{
			required:true,
		},
		sex:{
			required:true,
		},
		fid:{
			required:true,
		},
		price:{
			required:true,
		},
		inventory:{
			required:true,
		},
		limited:{
			required:true,
		},
	},
	submitHandler:function(form){
		add();
	}
});

function add() {
	
	var jeansName = $("input[name='jeansName']").val();
	var number = $("input[name='number']").val();
	var inventory = $("input[name='inventory']").val();
	var sex=$("input[name='sex']:checked").val()
	var price = $("input[name='price']").val();
	var fid = $("input[name='fid']").val();
	var type = $("input[name='type']").val();
	var limited = $("input[name='limited']").val();
	var remark = $("input[name='remark']").val();
	 //alert("**price******"+price+"***sex**"+sex+"*****");
	var extraRules = getExtraTable();
	var jsonValue = JSON.stringify({
		extraRules: extraRules
	});
	//alert("**jsonValue***"+jsonValue)
	
	//多文件处理
	var numArr = [];
        var txt = $("#form-jeans-add").find("input:file"); //获取所有上传附件框
      
        for (var i = 0; i < txt.length; i++) { 
        	//alert("*****"+txt.eq(i).attr('id'))
            numArr.push(txt.eq(i).attr('id'));       //将附件框的ID添加到数组中
        	
        }
	
       /* var formData = new FormData();
        formData.append('file', $('#file')[0].files[0]);
        formData.append('file', $('#filePc')[0].files[0]);
        formData.append('file', $('#fileIos')[0].files[0]);
        formData.append('file', $('#fileAndroid')[0].files[0]);
        formData.append('file', $('#fileSize')[0].files[0]);*/
        
	$.ajaxFileUpload({
		   url : "/system/goodnz/saveUpload",
		   secureuri : false,//是否需要安全协议
		   fileElementId : numArr,
		   type : 'POST', //文件提交的方式
		   dataType : 'string',
		   cache : false, //是否进行页面缓存
		   async : true, // 是否同步提交
		   //processData: false,
		  // data: formData,
		   success : function(data) {
	
			   $.ajax({
					//contentType: false, //必须
					dataType: "json",
					cache : true,
					type : "POST",
					url : "/system/goodnz/save",
					data : {
						"name": jeansName,
						"number": number,
						"inventory": inventory,
						"sex": sex,
						"price": price,
						"fId": fid,
						"type": type,
						"limited": limited,
						"extraRules" : jsonValue,
						"fileUrl" : data,
						"des": remark
						
					},
					async : false,
					error : function(request) {
						$.modalAlert("系统错误", "error");
					},
					success : function(data) {
						if (data.code == 0) {							
							window.history.back(-1);				
							//parent.layer.msg("新增成功,正在刷新数据请稍后……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
								//window.parent.location.reload();
								
							//});
						} else {
							$.modalAlert(data.msg, "error");
						}

					}
				});
		   }
	   });
}
var modal = $('#form-jeans-add');
var extraTable = modal.find('[data-table="extra"]');

var getRowTpl = function() {
	var tpl = 
		'<tr>'+
			'<td class="text-center"><label><input name="sizeImg" type="checkbox" value="" />尺码 </label> </td>'+
			'<td class="text-center"><input name="count" type="text" class="form-control input-sm input-small"></td>'+
			'<td class="text-center"><input name="content" type="text" class="form-control input-sm input-small"></td>'+
			'<td class="text-center" ><button data-command="delete" type="button" class="btn btn-danger btn-xs btn-mini"><i class="fa fa-times"></i> 删除</button></td>'+
		'</tr>';
	var $thisRow = $(tpl);
	$thisRow.find('[data-command="delete"]').click(function() {
		$(this).parents('tr').remove();
	});
	return $thisRow;
}

modal.find('[data-command="add"]').click(function() {

	var $thisRow = getRowTpl();
	
	modal.find('tbody').append($thisRow);
});

var getExtraTable = function() {
	var list = [];
	extraTable.find('tbody > tr').each(function() {
		var count = parseFloat($(this).find('input[name="count"]').val());
		if (isNaN(count) || count < 0) {
			count = 0;
		}
		var sizeImg =$(this).find("input[name='sizeImg']").is(':checked') == true ? 1 : 0;
		var content = $(this).find('input[name="content"]').val();
		if ( count> 0 ) {
			var data = { count: count, sizeImg: sizeImg, content: content };
			list.push(data);
		}
	});
	return list;
}



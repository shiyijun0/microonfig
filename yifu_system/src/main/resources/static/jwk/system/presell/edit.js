$(function() {
	var existSize = $("#existSize").val();
	var existColor = $("#existColor").val();
	var s = existSize.split(",");
	var c = existColor.split(",");
	$("input[name='colors']").each(function() {
		var col = $(this).val();
		for (var i = 0; i < c.length; i++) {
			if (col == c[i]){
				$(this).attr("checked","true");
			}
		}
	});

	$("input[name='sizes']").each(function() {
		for (var i = 0; i < s.length; i++) {
			if ($(this).val() == s[i]){
				$(this).attr("checked","true");
			}
		}
	});
});
$("#form-presell-edit").validate({
	rules : {
		presellCode : {
			required : true,
		},
		price : {
			required : true,
			number : true,
		},
		priceName : {
			required : true,
		},
		originalPrice : {
			required : true,
		},
		inventory : {
			required : true,
			number : true,
		},
		sizes : {
			required : true,
		},
		colors : {
			required : true,
		},
		orderNum : {
			required : true,
			number : true,
		},
		status : {
			required : true,
		},

	},
	submitHandler : function(form) {
		update();
	}
});

function update() {

	var file1 = $('#file1')[0].files[0];
	var file2 = $('#file2')[0].files[0];
	var file3 = $('#file3')[0].files[0];
	var file4 = $('#file4')[0].files[0];
	var imgs = $("#imgs").val();
	var imgs2 = $("#imgs2").val();
	if (imgs == null || imgs == "") {
		$('#file1').rules("add", {
			required : true,
		});
	}
	if (imgs2 == null || imgs2 == "") {
		$('#file2').rules("add", {
			required : true,
		});
	}
	if (file1 != null && file1 != "") {
		imgs = "";
	}
	if (file2 != null && file2 != "") {
		imgs2 = "";
	}
	var id = $("input[name='id']").val();
	var orderNum = $("input[name='orderNum']").val();
	var status = $("input[name='status']:checked").val();
	var name = $("#name").val();
	var presellCode = $("#presellCode").val();
	var price = $("#price").val();
	var priceName = $("#priceName").val();
	var originalPrice = $("#originalPrice").val();
	var inventory = $("#inventory").val();

	var sizesId = "";
	$('input:checkbox[name="sizes"]:checked').each(function() {
		if (sizesId == "") {
			sizesId = $(this).val();
		} else {
			sizesId = sizesId + "," + $(this).val();
		}
	});

	var colorsId = "";
	$('input:checkbox[name="colors"]:checked').each(function() {
		if (colorsId == "") {
			colorsId = $(this).val();
		} else {
			colorsId = colorsId + "," + $(this).val();
		}
	});

	var formData = new FormData();
	formData.append("file1", file1);
	formData.append("file2", file2);
	formData.append("file3", file3);
	formData.append("file4", file4);
	formData.append("name", name);
	formData.append("presellCode", presellCode);
	formData.append("price", price);
	formData.append("priceName", priceName);
	formData.append("originalPrice", originalPrice);
	formData.append("inventory", inventory);
	formData.append("status", status);
	formData.append("orderNum", orderNum);
	formData.append("id", id);
	formData.append("imgs", imgs);
	formData.append("imgs2", imgs2);
	formData.append("colorsId", colorsId);
	formData.append("sizesId", sizesId);

	$.ajax({
		cache : true,
		type : "POST",
		dataType : "json",
		url : "/system/presell/save",
		contentType : false,
		processData : false,
		data : formData,
		async : false,
		error : function(request) {
			$.modalAlert("系统错误", "error");
		},
		success : function(data) {
			if (data.code == 0) {
				window.history.back(-1);
			} else {
				$.modalAlert(data.msg, "error");
			}

		}
	});
}

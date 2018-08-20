var prefix = "/system/recommend"
var ques=null;var config=null;
$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'id',
            title: 'id',
            formatter: function(value, row, index) {
            var recommendId=row.id;
    	$.ajax({
			dataType: "json",
			cache : true,
			type : "POST",
			url : "/system/recommend/question",
			data : {
				"recommendId": recommendId					
			},
			async : false,
			error : function(request) {
				$.modalAlert("系统错误", "error");
			},
			success : function(data) {
				
				if (data != null || data!="") {									
					ques=data;
					//alert("*******"+ques[0].question);
				
				} else {
					//$.modalAlert(data.msg, "error");
					return "*****系统问题";
				}

			}
		});
    	
    return 	recommendId;
            }
        },
        {
            field: 'goodsName',
            title: '标题'
        },
        {
            field: 'gender',
            title: '性别',
            formatter: function(value, row, index) {
                if (value == 1) {
                    return '男';
                } else if (value == 2) {
                    return '女';
                }else if (value == 3) {
                    return '通用';
                }
            }
            	
        },
        {
            field: 'question',
            title: '问题1',
            formatter: function(value, row, index) {
            	if(ques[0]!=null || ques[0]!=""){
            		return ques[0].question;
            	}else{
            		return "暂无问题";
            	}
				
            }
        },
        
        {
        	 field: 'question',
             title: '问题2',
             formatter: function(value, row, index) {
            	 if(ques[1]!=null || ques[1]!=""){
             		return ques[1].question;
             	}else{
             		return "暂无问题";
             	}
             }
        },
        {
        	 field: 'question',
             title: '问题3',
             formatter: function(value, row, index) {
            	 if(ques[2] !=null || ques[2]!=""){
             		return ques[2].question;
             	}else{
             		return "暂无问题";
             	}
 				
             }
        },
        {
        	 field: 'question',
             title: '问题4',
             formatter: function(value, row, index) {
            	 if(ques[3]!=null || ques[3]!=""){
              		return ques[3].question;
              	}else{
              		return "暂无问题";
              	}
             }
        },
        {
        	 field: 'question',
             title: '问题5',
             formatter: function(value, row, index) {
            	 if(ques[4] !=null || ques[4]!=""){
              		return ques[4].question;
              	}else{
              		return "暂无问题";
              	}
 				
             }
        },
       
        {
       	 field: 'goodsId',
           title: '状态',
            formatter: function(value, row, index) {
            	 var gstatus=null;
             	$.ajax({
         			dataType: "text",
         			cache : true,
         			type : "POST",
         			url : "/system/recommend/checkByGoodId",
         			data : {
         				"id": value					
         			},
         			async : false,
         			error : function(request) {
         				$.modalAlert("系统错误", "error");
         			},
         			success : function(data) {
         				
         				if (data != null || data!="") {
         					if (data== 0) {
         						gstatus= '<span class="label label-danger">未上架</span>';
         	                } else if (data == 1) {
         	                	gstatus= '<span class="label label-success">已上架</span>';
         	                }else if (data == 2) {
         	                	gstatus= '<span class="label label-warning">删除</span>';
         	                }
         					
         				
         				} else {
         					
         					gstatus= '<span class="label label-success">未上架</span>';
         				}

         			}
         		});
				return gstatus;
            }
       },
       
       {
           field: 'limited',
           title: '限量',
           formatter: function(value, row, index) {
           var recommendId=row.id;
   	$.ajax({
			dataType: "json",
			cache : true,
			type : "POST",
			url : "/system/recommend/recommendConfig",
			data : {
				"recommendId": recommendId					
			},
			async : false,
			error : function(request) {
				$.modalAlert("系统错误", "error");
			},
			success : function(data) {
				
				if (data != null || data!="") {									
					config=data;
				} else {
					return "*****系统问题";
				}

			}
		});
   	var recommendConfig="暂无设置";
   	if(config!="0"){
   	if(config.limited != null || config.limited!="" ){
   		recommendConfig=config.limited
   	}
   	}
   return recommendConfig	;
           }
       },
       {
      	 field: 'inventory',
           title: '库存',
           formatter: function(value, row, index) {
        	   var recommendConfig="暂无设置";
        	   if(config!="0"){
        	   	if(config.inventory != null || config.inventory!=""){
        	   		recommendConfig=config.inventory
        	   	}
        	   }
        	    return recommendConfig	;
           }
      },
       
      {
       	 field: 'price',
            title: '价格',
            formatter: function(value, row, index) {
         	   var recommendConfig="暂无设置";
         	  if(config!="0"){
         	   	if(config.price != null || config.price!="" || config!="0"){
         	   		recommendConfig=config.price
         	   	}
         	  }
         	    return recommendConfig	;
            }
       },
       
       {
         	 field: 'startTime',
              title: '限时开始时间',
              formatter: function(value, row, index) {
           	   var recommendConfig="暂无设置";
           	if(config!="0"){
           	   	if(config.startTime != null || config.startTime!="" || config!= "0"){
           	   		recommendConfig=moment(config.startTime).format('YYYY-MM-DD hh:mm:ss');
           	   	}
           	   	}
           	    return recommendConfig	;
              }
         },
         
         {
           	 field: 'endTime',
                title: '限时结束时间',
                formatter: function(value, row, index) {
             	   var recommendConfig="暂无设置";
             	  if(config!="0"){
             	   	if(config.endTime != null || config.endTime!="" || config!="0"){
             	   		recommendConfig=moment(config.endTime).format('YYYY-MM-DD hh:mm:ss')
             	   	}
             	  }
             	    return recommendConfig	;
                }
           },
       
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
            	actions.push('<a class="btn btn-primary btn-sm " href="#" title="编辑" mce_href="#" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-primary btn-sm " href="#" title="查看" mce_href="#" onclick="check(\'' + row.id + '\')"><i class="fa fa-search-plus"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" onclick="remove(\'' + row.id + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*设计师推荐-编辑*/
function edit(id) {
    var url = prefix + '/edit/' + id;
    layer_show("查看信息", url, '1000', '650');
   // window.open(url,'_self');
}

/*分区管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_show("增加设计款限量限时限价", url, '1000', '650');
}

/*设计师推荐-查看*/
function check(id) {
    var url = prefix + '/check/' + id;
    //layer_show("查看信息", url, '800', '550');
    window.open(url,'_self');
}

// 单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中该样式布料吗？", function(r) {
		_ajax(prefix + "/remove/" + id, "", "post", r);
    })
}

// 批量删除
function batchRemove() {
	var rows = $.getSelections("id");
	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", "warning");
		return;
	}
	$.modalConfirm("确认要删除选中的" + rows.length + "条数据吗?", function(r) {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
	});
}

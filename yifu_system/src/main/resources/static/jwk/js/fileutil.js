//上传图片
//初始化fileinput
var FileInput = function () {
    var oFile = new Object();

    oFile.Init = function(ctrlName) {
        var control = $('#' + ctrlName);

        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
            showUpload: false, //是否显示上传按钮
            showCaption: false,//是否显示标题
            showRemove: false,//是否显示删除按钮
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: false,//是否显示拖拽区域
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            maxFileSize:1024*10,
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            fileActionSettings:{//设置图片删除和上传按钮隐藏
                showRemove: false,
                showUpload: false,
            },
        });

    }
    return oFile;
};


//上传图片或视频
//初始化fileinput
var FileInput2 = function () {
    var oFile = new Object();

    oFile.Init = function(ctrlName) {
        var control = $('#' + ctrlName);

        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            allowedFileExtensions: ['jpg', 'gif', 'png','mkv','3gp','wmv','mp4','flv','avi','rmvb','mov','rm','ram'],//接收的文件后缀
            showUpload: false, //是否显示上传按钮
            showCaption: false,//是否显示标题
            showRemove: false,//是否显示删除按钮
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: false,//是否显示拖拽区域
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            maxFileSize:1024*1024,
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            fileActionSettings:{//设置图片删除和上传按钮隐藏
                showRemove: false,
                showUpload: false,
            },
        });

    }
    return oFile;
};

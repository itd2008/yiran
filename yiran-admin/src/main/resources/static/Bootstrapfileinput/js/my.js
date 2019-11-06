$(function() {
	//初始化fileinput
	var fileInput = new FileInput();
	fileInput.Init("uploadFile", "http://127.0.0.1/testDemo/fileupload/upload.action");
});

//初始化fileinput
var FileInput = function() {
	var oFile = new Object();

	//初始化fileinput控件（第一次初始化）
	oFile.Init = function(ctrlName, uploadUrl) {
		var control = $('#' + ctrlName);

		//初始化上传控件的样式
		control.fileinput({
			language: 'zh', //设置语言
			uploadUrl: 'http://127.0.0.1/testDemo/fileupload/upload.action', //上传的地址
			allowedFileExtensions: ['jpg', 'png', 'gif'], //接收的文件后缀
			uploadAsync: true, //默认异步上传

			showUpload: false, //是否显示上传按钮
			showRemove: true, //显示移除按钮
			showCaption: true, //是否显示标题

			dropZoneEnabled: true, //是否显示拖拽区域

			//minImageWidth: 50, //图片的最小宽度
			//minImageHeight: 50,//图片的最小高度
			//maxImageWidth: 1000,//图片的最大宽度
			//maxImageHeight: 1000,//图片的最大高度
			//maxFileSize:0,//单位为kb，如果为0表示不限制文件大小
			//minFileCount: 0,
			maxFileCount: 10, //表示允许同时上传的最大文件个数
			enctype: 'multipart/form-data',

			browseClass: "btn btn-primary", //按钮样式: btn-default、btn-primary、btn-danger、btn-info、btn-warning        
			previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",

		});

		//文件上传完成之后发生的事件
		$("#uploadFile").on("fileuploaded", function(event, data, previewId, index) {

		});
	}
	return oFile;	//这里必须返回oFile对象，否则FileInput组件初始化不成功
};

/*
$(function() {
	$("#uploadFile").fileinput({
		language: 'zh', //设置语言
		uploadUrl: "http://127.0.0.1/testDemo/fileupload/upload.action", //上传地址
		allowedFileExtensions: ['jpg', 'gif', 'png'], //接收的文件后缀
		//uploadExtraData:{"id": 1, "fileName":'123.mp3'},
		uploadAsync: true, //默认异步上传
		showUpload: true, //是否显示上传按钮
		showRemove: true, //显示移除按钮
		showPreview: true, //是否显示预览
		showCaption: true, //是否显示标题
		browseClass: "btn btn-primary", //按钮样式  
		dropZoneEnabled: true, //是否显示拖拽区域
		//minImageWidth: 50, //图片的最小宽度
		//minImageHeight: 50,//图片的最小高度
		//maxImageWidth: 1000,//图片的最大宽度
		//maxImageHeight: 1000,//图片的最大高度
		//maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
		maxFileCount: 10, //表示允许同时上传的最大文件个数
		enctype: 'multipart/form-data',
		validateInitialCount: true,
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
		msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
	});
	
	//异步上传返回错误结果处理
	$('#uploadFile').on('fileerror', function(event, data, msg) {
		console.log(data.id);
		console.log(data.index);
		console.log(data.file);
		console.log(data.reader);
		console.log(data.files);
		// get message
		alert(msg);
	});
	
	//异步上传返回结果处理
	$("#uploadFile").on("fileuploaded", function(event, data, previewId, index) {
		console.log(data.id);
		console.log(data.index);
		console.log(data.file);
		console.log(data.reader);
		console.log(data.files);
		var obj = data.response;
		alert(JSON.stringify(data.success));
	});
	
	//批量同步上传错误处理
	$('#uploadFile').on('filebatchuploaderror', function(event, data, msg) {
		console.log(data.id);
		console.log(data.index);
		console.log(data.file);
		console.log(data.reader);
		console.log(data.files);
		// get message
		alert(msg);
	});
	
	//批量同步上传成功结果处理
	$("#uploadFile").on("filebatchuploadsuccess", function(event, data, previewId, index) {
		console.log(data.id);
		console.log(data.index);
		console.log(data.file);
		console.log(data.reader);
		console.log(data.files);
		var obj = data.response;
		alert(JSON.stringify(data.success));
	});
	
	//上传前
	$('#uploadFile').on('filepreupload', function(event, data, previewId, index) {
		var form = data.form,
			files = data.files,
			extra = data.extra,
			response = data.response,
			reader = data.reader;
		console.log('File pre upload triggered');
	});
})*/
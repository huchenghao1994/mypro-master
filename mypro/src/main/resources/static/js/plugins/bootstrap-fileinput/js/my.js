$(function() {
	$("#fileUpload").fileinput({
		language: 'zh', //设置语言
		allowedFileExtensions: ['xlsx', 'gif', 'png'], //接收的文件后缀
		showUpload:false, //是否显示上传按钮
		showRemove: true, //显示移除按钮
		showCaption: true, //是否显示标题
	    showPreview :false, //是否显示预览
	    dropZoneEnabled: false, //是否显示拖拽区域
		browseClass: "btn btn-primary", //按钮样式  
		browseClass: "btn btn-primary", //按钮样式: btn-default、btn-primary、btn-danger、btn-info、btn-warning        
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
	});
	
})
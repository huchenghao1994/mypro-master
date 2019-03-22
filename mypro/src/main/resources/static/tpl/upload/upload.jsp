<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="baseurl" value="${pageContext.request.contextPath}"></c:set>
<link href="/css/bootstrap.css" rel="stylesheet" />
<link href="/js/plugins/bootstrapValidator/css/bootstrapValidator.css" rel="stylesheet" />
<link rel="stylesheet" href="/css/style.css" />
<link rel="stylesheet" href="/fonts/iconfont.css" />
<script type="text/javascript" src="/js/jquery.min.js"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">                   
<html>
<head>
<script language="javascript">  
	function upload(){
	    	   $.ajax({
	   			url : '/upload/uploadFile',
	   			type: 'POST',
	   		    cache: false,
	   		    data: new FormData($("#uploadForm")[0]),
	   			async : true,//true为异步，false为同步
	   			contentType:false,
	            processData: false,  
	   			success : function(result) {
	   				if (result.code == "0") {
	   					parent.$("#${id}").val(result.data);
	   					parent.toastr.success('选择成功!');
	   				} else {
	   					parent.toastr.error('选择失败!');
	   				}
	   			},
				error:function(){
					parent.toastr.success("选择失败!");
				}
	   		})
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<form method="post" enctype="multipart/form-data" name="uploadForm" id="uploadForm"> 
  <input type="file" title="请点击选择文件" name="file" style="width:80px; height:50px;cursor:pointer;position:absolute; right:0;opacity:0;" onChange="upload()"> 
  <input type="button" value="选择" class="btn btn-default" style="width:60px;height:36px;">
  </div>
</form>


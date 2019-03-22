<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="baseurl" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript">
function changePassword() {
	$('#updateForm').data('bootstrapValidator').validate();
	if (!($('#updateForm').data('bootstrapValidator').isValid())) {
		return;
	}
	$.ajax({
		url : "/sys_user/updatePassword",
		type : "POST",
		data :$("#updateForm").serialize(),
		success : function(result) {
			if(result.code=='0'){
				toastr.success("修改密码成功，系统将于3秒后返回登陆页面");
				self.setInterval("logout()",1000);
			}else if(result.code=='2'){
				toastr.warning(result.msg);
			}else{
				toastr.error("修改密码失败！");
			}
		},
		error : function() {
			toastr.error("修改密码失败！");
			$.mask_close_all();
		}
	});
}

var res=0;
function logout(){
	res=res+1;
	if(res==3){
		clearInterval();
		window.location.href = "/logout"
	}
}

	$(function() {
		//初始化表单验证
		formValidator();
	})
	//初始化表单验证
	function formValidator() {
		$('#updateForm').bootstrapValidator({
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				//密码
				paswdOld : {
					message : '密码验证失败',
					validators : {
						notEmpty : {
							message : '密码不能为空'
						}
					}
				},
				paswd : {
					message : '密码验证失败',
					validators : {
						notEmpty : {
							message : '密码不能为空'
						},
	                    stringLength: {
	                        min: 6,
	                        max: 18,
	                        message: '密码长度在6到18之间'
	                    },
                        callback : {
							callback : function(value,validator,$field) {
								$("#updateForm").data("bootstrapValidator").updateStatus("paswdNew",  "NOT_VALIDATED",  null );
						        return true;
							}
						}
	                    
					}
				},
				paswdNew : {//密码确认
					message : '密码确认验证失败',
					validators : {
						notEmpty : {
							message : '密码确认不能为空'
						},
						identical : {
							field : 'paswd',
							message : '两次密码不相同'
						}
					}
				}
			}
		});
	}
</script>

<form name="updateForm" id="updateForm" method="post" class="form-horizontal">
<input type="hidden" name="tellerId" value=${tellerId}>
<input type="hidden" name="token" value="${token}">
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title">修改密码</h4>
	</div>
	<div class="modal-body">
		<div class="panel-body wrapper-sm">
	    <div class="form-group row">
				<label class="col-sm-6 control-label text-right"><b
					class="form-text-black">原密码</b><b class="form-error">*</b>:</label>
				<div class="col-sm-18">
					<input type="password" name="paswdOld" placeholder="请输入原密码"
						class="form-control">
				</div>
			</div>
			  <div class="form-group row">
				<label class="col-sm-6 control-label text-right"><b
					class="form-text-black">新密码</b><b class="form-error">*</b>:</label>
				<div class="col-sm-18">
					<input type="password" name="paswd" placeholder="请输入新密码"
						class="form-control m-b-xs">
				</div>
			</div>
			  <div class="form-group row">
				<label class="col-sm-6 control-label text-right"><b
					class="form-text-black">确认新密码</b><b class="form-error">*</b>:</label>
				<div class="col-sm-18">
					<input type="password" name="paswdNew" placeholder="请确认新密码"
						class="form-control m-b-xs">
				</div>
			</div>
	</div>
	</div>
     <div class="modal-footer">
		<div class="col-sm-25 m-b text-center">
		    <button type="button" class="btn btn-primary ng-scope"
				onclick="changePassword()"><span class="glyphicon glyphicon-save" aria-hidden="true"></span>保存</button>
			<button class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
		</div>
	</div>
</form>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/validate/bootstrapValidator.js"></script>
<title>添加用户</title>
</head>
<body>
	<div class="header lighter smaller blue">
		<h3><i class="icon-plus"></i>&nbsp;添加用户 </h3>
		<jsp:include page="nav.jsp"/>
	</div>
	
	<sf:form method="POST" modelAttribute="user" id="dataForm">
		<div class="form-group form-group-lg">
			<label for="nickname">用户昵称</label>
			<sf:input path="nickname" class="form-control" id="nickname" placeholder="请输入用户昵称"/>
		</div>
		<div class="form-group form-group-lg">
			<label for="username">登陆用户名</label>
			<sf:input path="username" class="form-control" id="username" placeholder="请输入登陆用户名"/>
		</div>
		<div class="form-group form-group-lg">
			<label for="password">登陆密码</label>
			<sf:password path="password" class="form-control" id="password" placeholder="请输入登陆密码"/>
		</div>
		<div class="form-group form-group-lg">
			<label for="confirmPassword">重复密码</label>
			<input type="password" name="confirmPassword" class="form-control" id="confirmPassword" placeholder="请再次输入登陆密码"/>
		</div>
		<div class="form-group form-group-lg">
			<label for="status">用户状态</label>
			<br/>
			是否启用：
			<label>
				<input name="statusBox" class="ace ace-switch ace-switch-5" type="checkbox" />
				<span class="lbl"></span>
			</label>
		</div>
		<input type="hidden" name="token" value="${token}"/>
		<button type="submit" class="btn btn-primary">确定提交</button>
	</sf:form>
	<script type="text/javascript">
		jQuery(function($) {
			$('#dataForm').bootstrapValidator({
//		        live: 'disabled',
		        message: '验证不通过',
		        feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		        	nickname: {
		                validators: {
		                    notEmpty: {
		                        message: '请输入用户昵称'
		                    }
		                }
		            },
		            username: {
		                validators: {
		                    notEmpty: {
		                        message: '请输入用户名'
		                    },
		                    stringLength: {
		                        min: 3,
		                        max: 30,
		                        message: '用户名长度必须在3~10之间'
		                    }
		                }
		            },
		            password: {
		                validators: {
		                    notEmpty: {
		                        message: '密码不能为空'
		                    },
		                    stringLength: {
		                        min: 6,
		                        max: 30,
		                        message: '密码长度必须在6~30之间'
		                    },
		                    identical: {
		                        field: 'confirmPassword',
		                        message: '两次密码输入不一致'
		                    },
		                    different: {
		                        field: 'username',
		                        message: '密码不能和用户名一样'
		                    }
		                }
		            },
		            confirmPassword: {
		                validators: {
		                	notEmpty: {
		                        message: '密码不能为空'
		                    },
		                    identical: {
		                        field: 'password',
		                        message: '两次密码输入不一致'
		                    },
		                    different: {
		                        field: 'username',
		                        message: '密码不能和用户名一样'
		                    }
		                }
		            }
		        }
		    });
		});
	</script>
</body>
</html>
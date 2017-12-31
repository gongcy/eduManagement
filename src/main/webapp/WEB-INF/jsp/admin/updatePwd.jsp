<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/validate/bootstrapValidator.js"></script>
<title>修改密码</title>
</head>
<body>
	<div class="header lighter smaller blue">
		<h3><i class="icon-edit"></i>&nbsp;修改个人信息 &nbsp;&nbsp;<c:if test="${flag=='2'}"><span class="red icon-exclamation-sign">修改信息失败</span></c:if><c:if test="${flag=='1'}"><span class="green icon-ok">修改信息成功</span></c:if>
			<c:if test="${errorMsg!=null && errorMsg!=''}"><span class="red icon-exclamation-sign">${errorMsg}</span></c:if>	
		</h3>
	</div>
	
	<form method="POST" id="dataForm">
		<div class="form-group form-group-lg">
			<label for="nickname">用户昵称</label>
			<input name="nickname" class="form-control" id="nickname" placeholder="请输入用户昵称" value="${LOGIN_USER.user.nickname}"/>
		</div>
		<div class="form-group form-group-lg">
			<label for="username">登陆用户名</label>
			<input name="username" class="form-control" readonly="true" id="username" placeholder="请输入登陆用户名" value="${LOGIN_USER.user.username}"/>
		</div>
		<div class="form-group form-group-lg">
			<label for="password">原始密码<small>（不修改密码可以不输入）</small></label>
			<input type="password" name="oldPwd" class="form-control" id="password" placeholder="请输入原始密码"/>
		</div>
		<div class="form-group form-group-lg">
			<label for="password">登陆密码<small>（不修改密码可以不输入）</small></label>
			<input type="password" name="password" class="form-control" id="password" placeholder="请输入登陆密码"/>
		</div>
		<div class="form-group form-group-lg">
			<label for="confirmPassword">重复密码<small>（不修改密码可以不输入）</small></label>
			<input type="password" name="confirmPassword" class="form-control" id="confirmPassword" placeholder="请再次输入登陆密码"/>
		</div>
		<input type="hidden" name="token" value="${token}"/>
		<button type="submit" class="btn btn-primary">确定提交</button>
	</form>
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
		            oldPwd: {
		                validators: {
		                    stringLength: {
		                        min: 6,
		                        max: 30,
		                        message: '密码长度必须在6~30之间'
		                    }
		                }
		            },
		            password: {
		                validators: {
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
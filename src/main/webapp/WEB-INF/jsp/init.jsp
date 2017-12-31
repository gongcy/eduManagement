<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap3/css/bootstrap.min.css" type="text/css"/>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="<%=request.getContextPath()%>/resources/js-lib/jquery-1.12.3.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=request.getContextPath()%>/resources/bootstrap3/js/bootstrap.min.js"></script>
<title>系统初始化</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/admin/css/main.css"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/validate/bootstrapValidator.js"></script>

</head>
<body>
	<c:if test="${initFlag}">
		<div class="header my-top-header" style="border-top: 3px solid #ddeb00;">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h1 style="color:#FFF" ><span class="glyphicon glyphicon-log-in"></span><strong>&nbsp;系统初始化！</strong></h1>
					</div>
				</div>
			</div>
		</div>
		<div class="container" style="background: #FFF; padding-bottom:100px;">
			<h1 class="text-center" style="font-size:180px; color:#ddd;">
				<span class="glyphicon glyphicon-exclamation-sign"></span>
				<br/>
				<span style="font-size:28px;">请先初始化系统，再使用哦！</span>
			</h1>
			<div class="panel panel-info">
				<div class="panel-heading">
					<span class="glyphicon glyphicon-question-sign"></span>
					系统初始化
				</div>
				<div class="panel-body">
					<sf:form modelAttribute="user" id="dataForm">
						<div class="form-group">
							<div class="input-group input-group-lg">
								<div class="input-group-addon">用户昵称：</div>
								<sf:input path="nickname" class="form-control"  placeholder="请输入昵称，用于显示"/>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group input-group-lg">
								<div class="input-group-addon">　用户名：</div>
								<sf:input path="username" class="form-control"  placeholder="请输入用户名，用于最高权限的管理员"/>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group input-group-lg">
								<div class="input-group-addon">密　　码：</div>
								<sf:password path="password" class="form-control"  placeholder="请输入登陆密码"/>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group input-group-lg">
								<div class="input-group-addon">重复密码：</div>
								<input type="password" name="confirmPassword" class="form-control"  placeholder="请再输一次密码"/>
							</div>
						</div>
						<input type="hidden" name="token" value="${token}"/>
						<button type="submit" class="btn btn-primary btn-lg">确定初始化</button>
					</sf:form>
				</div>
			</div>
		</div>
		
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
	</c:if>
	<c:if test="${!initFlag}">
		<div class="header my-top-header" style="border-top: 3px solid #ddeb00;">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h1 style="color:#FFF" ><span class="glyphicon glyphicon-log-in"></span><strong>&nbsp;系统初始化！</strong></h1>
					</div>
				</div>
			</div>
		</div>
		<div class="container" style="background: #FFF">
			<h1 class="text-center" style="font-size:180px; color:#00b600;">
				<span class="glyphicon glyphicon-ok"></span>
				<br/>
				<span style="font-size:28px; font-weight: bold;">恭喜您！系统初始化完成！</span>
			</h1>
			
			<div class="text-center" style="padding-bottom:90px; padding-top:50px;">
			<a href="<%=request.getContextPath()%>/admin" class="btn btn-lg btn-danger">进入管理后台</a>
			<a href="<%=request.getContextPath()%>/" class="btn btn-lg btn-info">进入前台页面</a>
			</div>
		</div>
	</c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
	<head>
		<title>后台首页</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="author" content="zslin.com" />
	</head>
	<body>
		<div class="alert alert-block alert-success">
			<i class="icon-ok green"></i>
			欢迎登陆
			<strong class="green">
				${appConfig.appName}
				<small>(${appConfig.appVersion})</small>
			</strong>
			，登陆IP：${LOGIN_USER.login_ip}，登陆时间：<fmt:formatDate value="${LOGIN_USER.login_time}" pattern="MM-dd HH:mm:ss"/>	
		</div>
		
	</body>
</html>
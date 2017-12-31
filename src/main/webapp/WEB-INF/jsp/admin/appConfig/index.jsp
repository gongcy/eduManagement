<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站配置管理</title>
<script type="text/javascript">
	$(function() {
	});
</script>
</head>
<body>
	<div class="header lighter smaller blue">
		<h3><span class="glyphicon glyphicon-th-list"></span><i class="icon-setting"></i>&nbsp;网站配置管理<small>&nbsp;</small></h3>
	</div>
	
	<sf:form method="POST" modelAttribute="appConfig">
		<div class="form-group form-group-lg">
			<div class="input-group">
		      <div class="input-group-addon">网站名称：</div>
		      <sf:input path="appName" type="text" class="form-control" placeholder="请输入网站名称" />
		    </div>
		</div>
		<div class="form-group form-group-lg">
			<div class="input-group">
		      <div class="input-group-addon">当前版本：</div>
		      <sf:input path="appVersion" type="text" class="form-control" placeholder="请输入当前版本" />
		    </div>
		</div>
		<div class="form-group form-group-lg">
			<div class="input-group">
		      <div class="input-group-addon">网站首页：</div>
		      <sf:input path="indexPage" type="text" class="form-control" placeholder="请输入网站首页" />
		    </div>
		</div>
		<button type="submit" class="btn btn-primary submit-update-btn">保存设置</button>
	</sf:form>	
</body>
</html>
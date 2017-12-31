<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>出现错误</title>
<style type="text/css">
	body, html {
		background:#f8f8f8;
	}
</style>
</head>
<body>
	<div class="container" style="background: #FFF; padding-top:100px; padding-bottom:100px;">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<span class="glyphicon glyphicon-remove"></span>
				出现错误！
			</div>
			<div class="panel-body">
				<h3>您访问的页面出现错误啦！赶紧绕道行走吧！</h3>
				<p style="padding-top:10px;">
					<a href="<%=request.getContextPath()%>/" class="btn btn-primary btn-lg">返回首页</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
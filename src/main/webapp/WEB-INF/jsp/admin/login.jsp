<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
	<head>
		<title>用户登陆</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="author" content="zslin.com" />
	
		<!-- 新 Bootstrap 核心 CSS 文件 -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap3/css/bootstrap.min.css" type="text/css"/>
		
		<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
		<script src="<%=request.getContextPath()%>/resources/js-lib/jquery-1.12.3.min.js"></script>
		
		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap3/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/login/account/style.css">

		<script type="text/javascript">
			$(function() {
				var errorHtml = $.trim($("#showErrors").html());
				//alert(errorHtml);
				if(errorHtml!=''){
					$("#showErrors").css("display", "block");
				}
			});
		</script>
		 <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
	</head>
	<body>
        <div class="header my-top-header" style="border-top: 3px solid #ddeb00;">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h1 style="color:#FFF" ><span class="glyphicon glyphicon-lock"></span><strong>&nbsp;用户登陆</strong></h1>
					</div>
				</div>
			</div>
		</div>
		<div class="register-container container">
            <div class="row">
            	<div class="col-md-5"></div>
                <div class="register col-md-7">
                    <form method="post" id="addForm">
                    	<div class="form-group form-group-lg text-left">
                    	<input name="targetUrl" type="hidden" value="${targetUrl}"/>
                    	<div class="alert alert-danger" id="showErrors" style="display: none;" role="alert">${errMsg}</div>
                    	<input name="token" type="hidden" value="${token}"/>
                        <h2><span class="red"><strong>用户登陆</strong></span></h2>
                        <label for="email">用户名</label>
                        <input type="text" name="username" id="email" class="form-control input-xlarge" placeholder="请输入用户名" required="required"/>
                        <label for="pwd">登陆密码</label>
                        <input type="password" name="password" id="pwd" class="form-control input-xlarge" placeholder="请输入登陆密码" required="required"/>
                        <p class="text-center">
                        <button type="submit">提交登陆</button>
                        </p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <input id="ctx" value="<%=request.getContextPath()%>" type="hidden"/>
        <script src="<%=request.getContextPath()%>/resources/login/account/jquery.backstretch.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/login/account/scripts.js"></script>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
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
<title><decorator:title default="${appConfig.appName}"/></title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/admin/css/main.css"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/validate/bootstrapValidator.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/myself/cms.admin.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/myself/delete.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/public.js"></script>
<script type="text/javascript">
	$(function() {
		var height = $(window).height();
		//alert(height);
		$("#web-main-container").css("min-height",(height-75)+"px");
		
		$("#beginFilter").click(function() {
			var location = window.location.href;
			
			location = $.getBaseHref(location);
			//alert(location);
			var params = "?";
			$(".filter_element").each(function(){
				var val = $(this).val();
				var n = $(this).attr("name");
				if(val!=""&&val!=-1) {
					params+=n+"="+val+"&";
				}
			});
			window.location.href=location+params;
		});
		
		$(".filter_value").each(function() {
			var n = $(this).attr("filter_name");
			$(":input[name='"+n+"']").val($(this).val());
		});
	});
</script>
<decorator:head/>
</head>
<body>
	<div class="header my-top-header" style="border-top: 3px solid #ddeb00;">
		<div class="container">
			<div class="row">
				<div class="col-md-5">
					<h1 style="color:#FFF" ><span class="glyphicon glyphicon-book"></span><strong>&nbsp;${appConfig.appName}！</strong></h1>
				</div>
				<div class="col-md-7 text-right">
					<div class="btn-group btn-group-lg" role="group" aria-label="..." style="margin-top:22px;">
						<a href="<%=request.getContextPath()%>/web/book/index" class="btn btn-info" type="button">图书查询</a>
						<a href="<%=request.getContextPath()%>/web/reader/index" class="btn btn-info" type="button">阅读者查询</a>
						<a href="<%=request.getContextPath()%>/web/borrow/index" class="btn btn-info" type="button">外借查询</a>
						<a href="<%=request.getContextPath()%>/admin" target="_blank" class="btn btn-danger" type="button">后台管理</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container" style="background: #FFF;" id="web-main-container">
		<decorator:body/>
	</div>
</body>
</html>
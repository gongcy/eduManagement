<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
	<head>
		<!-- 使用了sitemesh的特有标题标签，如果实体页面未设置标题，则使用default中的标题 -->
		<title><decorator:title default="CMS管理系统"/></title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- 新 Bootstrap 核心 CSS 文件 -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap3/css/bootstrap.min.css" type="text/css"/>
		
		<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
		<script src="<%=request.getContextPath()%>/resources/js-lib/jquery-1.12.3.min.js"></script>
		
		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap3/js/bootstrap.min.js"></script>
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/admin/assets/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/admin/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/admin/assets/css/jquery-ui-1.10.3.custom.min.css" /> --%>
		<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/admin/assets/css/jquery.gritter.css" /> --%>

		<!-- fonts,字体，需要下载到本地 -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/admin/assets/css/family.css" />

		<!-- ace styles -->

		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/admin/assets/css/ace.min.css" />
		<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/admin/assets/css/ace-rtl.min.css" /> --%>
		<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/admin/assets/css/ace-skins.min.css" /> --%>

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/admin/assets/css/ace-ie.min.css" />
		<![endif]-->
		
		<!-- 折叠js -->
		<script src="<%=request.getContextPath()%>/resources/admin/assets/js/ace-extra.min.js"></script>

		<!--[if lt IE 9]>
		<script src="<%=request.getContextPath()%>/resources/admin/assets/js/html5shiv.js"></script>
		<script src="<%=request.getContextPath()%>/resources/admin/assets/js/respond.min.js"></script>
		<![endif]-->
		
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/myself/delete.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/myself/auth.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/myself/cms.admin.js"></script>
		<script type="text/javascript">
			$(function() {
				$(".logout-href").deleteFun({title:'退出提示',msg:'确定退出系统吗？'});
				
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
		<!-- 这里引入sitemesh的头部标题，只有这样子页面中head部份里的代码才会被引用 -->
		<decorator:head/>
	</head>
	<body>
		<div class="container-fluid" style="padding:0px; ">
			<div class="navbar navbar-default1" id="navbar">
				<div class="navbar-container" id="navbar-container">
					<div class="navbar-header pull-left">
						<a href="<%=request.getContextPath()%>/admin/" class="navbar-brand">
							<i class="icon-globe bigger-140"></i>
							<span class="bigger-100" style="font-weight: bold;">${appConfig.appName}<small>&nbsp;&nbsp;${appConfig.appVersion}</small></span>
						</a><!-- /.brand -->
					</div><!-- /.navbar-header -->
					
					<div class="navbar-header pull-right" role="navigation">
						<ul class="nav ace-nav">
							<li class="light-blue">
								<a data-toggle="dropdown" href="javascript:" class="dropdown-toggle">
									<i class="icon-user nav-user-photo"></i>
									<span class="user-info">
										<small style="padding-bottom:5px;">欢迎回来,</small>
										${LOGIN_USER.user.nickname}
									</span>
									<i class="icon-caret-down"></i>
								</a>
	
								<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
									<li>
										<a href="<%=request.getContextPath()%>/" target="_blank">
											<i class="glyphicon glyphicon-home"></i>前台首页
										</a>
									</li>
									<li>
										<a href="<%=request.getContextPath()%>/admin">
											<i class="icon-home"></i>后台首页
										</a>
									</li>
	
									<li>
										<a href="<%=request.getContextPath()%>/admin/updatePwd">
											<i class="icon-edit"></i>修改个人信息
										</a>
									</li>
	
									<li class="divider"></li>
	
									<li>
										<a href="<%=request.getContextPath()%>/logout" title="退出系统" class="logout-href">
											<i class="icon-off"></i>
											退出系统
										</a>
									</li>
								</ul>
							</li>
						</ul><!-- /.ace-nav -->
					</div><!-- /.navbar-header -->
					
					<%-- <div class="navbar-header pull-right">
						<a href="<%=request.getContextPath()%>/logout" title="退出系统" class="logout-href btn btn-default btn-app radius-4 btn-xs">
							<i class="icon-cog bigger-160"></i>
							退出
						</a>
					</div> --%><!-- /.navbar-header -->
				</div><!-- /.container -->
			</div>
		</div>
		
		<div class="main-container" id="main-container">

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<!-- 
					<div class="sidebar-shortcuts" id="sidebar-shortcuts">
						<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
							<button class="btn btn-success">
								<i class="icon-signal"></i>
							</button>

							<button class="btn btn-info">
								<i class="icon-pencil"></i>
							</button>

							<button class="btn btn-warning">
								<i class="icon-group"></i>
							</button>

							<button class="btn btn-danger">
								<i class="icon-cogs"></i>
							</button>
						</div>

						<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
							<span class="btn btn-success"></span>

							<span class="btn btn-info"></span>

							<span class="btn btn-warning"></span>

							<span class="btn btn-danger"></span>
						</div>
					</div>
					 -->
					<!-- #sidebar-shortcuts -->

					<jsp:include page="nav.jsp"/>

					<!-- <div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div> -->
				</div>

				<div class="main-content">
					<!-- <div class="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">Home1</a>
							</li>

							<li>
								<a href="#">UI Elements</a>
							</li>
							<li class="active">Elements</li>
						</ul>.breadcrumb
					</div> -->
					<div class="container-fluid" style="padding-top:10px; padding-bottom:15px;">
						<!-- 这里引用子页面的body内容 -->
						<decorator:body/>
					</div>
					
				</div><!-- /.main-content -->

				
			</div><!-- /.main-container-inner -->

			<a href="javascript:" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
		
		
		<script src="<%=request.getContextPath()%>/resources/admin/assets/js/typeahead-bs2.min.js"></script>
		<!-- page specific plugin scripts -->
		<!--[if lte IE 8]>
		  <script src="<%=request.getContextPath()%>/resources/admin/assets/js/excanvas.min.js"></script>
		<![endif]-->

		<%-- <script src="<%=request.getContextPath()%>/resources/admin/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/admin/assets/js/jquery.ui.touch-punch.min.js"></script> --%>
		<%-- <script src="<%=request.getContextPath()%>/resources/admin/assets/js/bootbox.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/admin/assets/js/jquery.easy-pie-chart.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/admin/assets/js/jquery.gritter.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/admin/assets/js/spin.min.js"></script> --%>

		<!-- ace scripts -->
		<%-- <script src="<%=request.getContextPath()%>/resources/admin/assets/js/ace-elements.min.js"></script> --%>
		<script src="<%=request.getContextPath()%>/resources/admin/assets/js/ace.min.js"></script>
		
		<input type="hidden" id="ctx" value="<%=request.getContextPath()%>"/>
		
		<div id="userAuthHref" style="display: none;">
			<c:forEach items="${LOGIN_USER.authList}" var="authTmp">
				(${authTmp})
			</c:forEach>
		</div>
	</body>
</html>
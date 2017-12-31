<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
	<head>
		<title>${appConfig.appName}-借阅</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="author" content="zslin.com" />
		<script type="text/javascript">
			$(function() {
			});
		</script>
	</head>
	<body>
		<div class="page-header lighter blue">
			<h3><span class="glyphicon glyphicon-eye-open"></span>&nbsp;借阅详情<small>（${datas.total}）</small></h3>
		</div>
		
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title"><i class="glyphicon glyphicon-education"></i> 阅读者信息：</h3>
					</div>
					<div class="panel-body">
						<p>姓名：${reader.name}</p>
						<p>证件号：${reader.cardNo}</p>
						<p>身份证号：********</p>
						<p>电话：******</p>
						<p>地址：********</p>
						
					</div>
				</div>
			</div>
			
			<div class="col-md-6">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h3 class="panel-title"><i class="glyphicon glyphicon-book"></i> 已借书籍（<b>${datas.total}</b> 本）：</h3>
					</div>
					<div class="panel-body">
						<c:forEach items="${datas.datas}" var="b">
						
						<div class="panel panel-warning">
							<!-- <div class="panel-heading">
								<h3 class="panel-title"><i class="glyphicon glyphicon-education"></i> 阅读者信息：</h3>
							</div> -->
							<div class="panel-body">
								<p>书名：${b.bookName}</p>
								<p>借阅日期：<fmt:formatDate value="${b.createDate}" pattern="yyyy-MM-dd HH:mm"/></p>
								<p>状态：<c:if test="${b.status=='1'}"><b class="red">借阅中</b></c:if><c:if test="${b.status=='2'}"><b class="blue">已归还</b></c:if></p>
							</div>
						</div>
						</c:forEach>
						
						<jsp:include page="/common_jsp/pager.jsp" >
							<jsp:param value="${datas.total}" name="totalRecord"/>
							<jsp:param value="readerId" name="params"/>
							<jsp:param value="show" name="url"/>
						</jsp:include>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
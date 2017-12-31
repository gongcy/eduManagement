<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
	<head>
		<title>${appConfig.appName}-首页</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="author" content="zslin.com" />
	</head>
	<body>
		<div class="header lighter smaller blue">
			<h3><span class="glyphicon glyphicon-th-list"></span>&nbsp;图书列表<small>（${datas.total}）</small></h3>
		</div>
		
		<div>
			<jsp:include page="/filter/Book_hql.jsp"/>
		</div>
		<div class="table-responsive">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>分类</th>
						<th>名称</th>
						<th>作者</th>
						<th>出版社</th>
						<th>书价</th>
						<th>数量</th>
						<th>租金</th>
					</tr>
				</thead>
	
				<tbody>
					<c:forEach items="${datas.datas}" var="obj">
						<tr>
							<td><a href="<%=request.getContextPath()%>/web/book/index?cateId=${obj.cateId}">${obj.cateName}</a></td>
							<td>${obj.name}</td>
							<td>${obj.author}</td>
							<td>${obj.pub}</td>
							<td>${obj.money}元</td>
							<td>${obj.amount}册</td>
							<td>${obj.price}元/天</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="10">
							<jsp:include page="/common_jsp/pager.jsp" >
								<jsp:param value="${datas.total}" name="totalRecord"/>
								<jsp:param value="index" name="url"/>
							</jsp:include>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</body>
</html>
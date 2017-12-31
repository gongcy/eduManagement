<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>借阅列表</title>
<script type="text/javascript">
	$(function() {
		$(".delete-obj-href").deleteFun();
	});
</script>
</head>
<body>
	<div class="header lighter smaller blue">
		<h3><span class="glyphicon glyphicon-th-list"></span>&nbsp;借阅列表<small>（${datas.total}）</small></h3>
	</div>
	
	<div>
		<jsp:include page="/filter/Borrow_hql.jsp"/>
	</div>
	<div class="table-responsive">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>状态</th>
					<th>读者</th>
					<th>书名</th>
					<th>条码</th>
					<th>日期</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${datas.datas}" var="obj">
					<tr>
						<td><c:if test="${obj.status=='1'}"><span class="red">外借中</span></c:if><c:if test="${obj.status=='2'}"><span class="blue">已归还</span></c:if></td>
						<td>${obj.readerName}</td>
						<td>${obj.bookName}</td>
						<td>${obj.bookNameNo}</td>
						<td><fmt:formatDate value="${obj.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
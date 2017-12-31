<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<script type="text/javascript">
	$(function() {
		$(".delete-obj-href").deleteFun();
	});
</script>
</head>
<body>
	<div class="header lighter smaller blue">
		<h3><span class="glyphicon glyphicon-th-list"></span>&nbsp;用户列表<small>（${datas.total}）</small></h3>
		<jsp:include page="nav.jsp"/>
	</div>
	
	<!-- <div class="table-header">
		Results for "Latest Registered Domains"
	</div> -->
	<div class="table-responsive">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>昵称</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${datas.datas}" var="obj">
					<tr>
						<td>${obj.id}</td>
						<td>${obj.nickname}(${obj.username})</td>
						<td><c:if test="${obj.status=='1'}"><span class="green glyphicon glyphicon-ok-sign">正常</span></c:if><c:if test="${obj.status!=1}"><span class="red glyphicon glyphicon-remove-sign">停用</span></c:if>
							
						</td>
						<td>
							<div class="action-buttons">
								<a class="blue auth" sn="UserController.roles" title="分配权限" href="<%=request.getContextPath()%>/admin/user/roles/${obj.id}"><i class="icon-key bigger-130"></i></a>
								<a class="green auth" title="修改" sn="UserController.update" href="<%=request.getContextPath()%>/admin/user/update/${obj.id}">
									<i class="icon-pencil bigger-130"></i>
								</a>
								<c:if test="${obj.username!='admin' && obj.username!='sadmin'}">
								<a class="delete-obj-href red auth"  sn="UserController.delete" title="此操作不可逆，确定删除【${obj.nickname}】吗？" href="<%=request.getContextPath()%>/admin/user/delete/${obj.id}">
									<i class="icon-trash bigger-130"></i>
								</a>
								</c:if>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						<jsp:include page="/common_jsp/pager.jsp" >
							<jsp:param value="${datas.total}" name="totalRecord"/>
							<jsp:param value="list" name="url"/>
						</jsp:include>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>
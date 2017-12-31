<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色列表</title>
<script type="text/javascript">
	$(function() {
		$(".delete-obj-href").deleteFun();
	});
</script>
</head>
<body>
	<div class="header lighter smaller blue">
		<h3><span class="glyphicon glyphicon-th-list"></span>&nbsp;角色列表<small>（${datas.total}）</small></h3>
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
					<th>名称</th>
					<th>SN</th>
					<th>操作</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${datas.datas}" var="obj">
					<tr>
						<td>${obj.id}</td>
						<td>${obj.name}</td>
						<td>${obj.sn}</td>
						<td>
							<div class="action-buttons">
								<a class="blue auth" sn="RoleController.menus" title="分配权限" href="<%=request.getContextPath()%>/admin/role/menus/${obj.id}"><i class="icon-key bigger-130"></i></a>
								<a class="green auth" sn="RoleController.update" title="修改" href="<%=request.getContextPath()%>/admin/role/update/${obj.id}">
									<i class="icon-pencil bigger-130"></i>
								</a>

								<a class="delete-obj-href red auth" sn="RoleController.delete" title="此操作不可逆，确定删除【${obj.name}】吗？" href="<%=request.getContextPath()%>/admin/role/delete/${obj.id}">
									<i class="icon-trash bigger-130"></i>
								</a>
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
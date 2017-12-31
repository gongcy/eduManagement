<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="ace-settings-container" id="ace-settings-container">
	<ul class="pagination">
		<li><a class="auth" sn="UserController.list" href="<%=request.getContextPath()%>/admin/user/list"><span class="glyphicon glyphicon-th-list"></span>用户列表</a></li>
		<li><a class="auth" sn="UserController.add" href="<%=request.getContextPath()%>/admin/user/add"><span class="icon-plus"></span>添加用户</a></li>
	</ul>
</div>
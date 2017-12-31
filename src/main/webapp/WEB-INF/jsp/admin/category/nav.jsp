<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="ace-settings-container" id="ace-settings-container">
	<ul class="pagination">
		<li><a class="auth" sn="CategoryController.list" href="<%=request.getContextPath()%>/admin/category/list"><span class="glyphicon glyphicon-th-list"></span>分类列表</a></li>
		<li><a class="auth" sn="CategoryController.add" href="<%=request.getContextPath()%>/admin/category/add"><span class="icon-plus"></span>添加分类</a></li>
	</ul>
</div>
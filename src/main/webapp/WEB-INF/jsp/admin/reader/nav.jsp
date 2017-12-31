<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="ace-settings-container" id="ace-settings-container">
	<ul class="pagination">
		<li><a class="auth" sn="ReaderController.list" href="<%=request.getContextPath()%>/admin/reader/list"><span class="glyphicon glyphicon-th-list"></span>读者列表</a></li>
		<li><a class="auth" sn="ReaderController.add" href="<%=request.getContextPath()%>/admin/reader/add"><span class="icon-plus"></span>添加读者</a></li>
	</ul>
</div>
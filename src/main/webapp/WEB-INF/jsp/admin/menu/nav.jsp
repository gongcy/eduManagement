<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="ace-settings-container" id="ace-settings-container">
	<ul class="pagination">
		<li><a href="<%=request.getContextPath()%>/admin/menu/list"><span class="glyphicon glyphicon-th-list auth" sn="MenuController.list"></span>菜单列表</a></li>
		<li><a href="<%=request.getContextPath()%>/admin/menu/rebuildMenus" class="rebuildMenu auth" sn="MenuController.rebuildMenus"><span class="icon-refresh"></span>重构菜单</a></li>
	</ul>
</div>
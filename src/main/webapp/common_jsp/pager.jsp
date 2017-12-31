<%@page import="java.util.Map"%>
<%@page import="com.zslin.basic.threadlocal.SystemRequestHolder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<pg:pager export="curPage=pageNumber" items="${param.totalRecord }"
	maxPageItems="<%=SystemRequestHolder.getSystemRequest().getPageSize() %>" url="${param.url}">
		<c:forEach items="${param.params }" var="p">
			<pg:param name="${p }" />
		</c:forEach> 
		<%
			Map<String,String> params = (Map<String,String>)request.getAttribute("filter_params");
			for(String key:params.keySet()) {
				String v = params.get(key);
		%>
			<pg:param name="<%=key %>"/>
		<%		
			}
		%>
		
	<nav class="text-right">
		<ul class="pagination">
			
			<pg:first>
				<li><a href="${pageUrl}" id="is_first_href">首页</a></li>
			</pg:first>
			<pg:prev>
			<li><a href="${pageUrl}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
			</pg:prev>
			<pg:pages>
			<c:if test="${curPage eq pageNumber }">
				<li class="active"><span class="current_page line_height">${pageNumber}</span></li>
			</c:if>
			<c:if test="${curPage != pageNumber }">
				<li><a href="${pageUrl }" class="pager_link">${pageNumber }</a></li>
			</c:if>
			</pg:pages>
			
			<pg:next>
				<li><a href="${pageUrl}" title="下一页"><span aria-hidden="true">&raquo;</span></a></li>
			</pg:next> 
			<pg:last>
				<li><a href="${pageUrl}">尾页</a></li>
			</pg:last>
		</ul>
	</nav>
	
</pg:pager>

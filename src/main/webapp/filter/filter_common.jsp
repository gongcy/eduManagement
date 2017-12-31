<%@page import="java.util.Map"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
	Map<String,String> params = (Map<String,String>)request.getAttribute("filter_params");
	for(String key:params.keySet()) {
		String v = params.get(key);
%>
	<input type="hidden" class="filter_value" filter_name="<%=key %>" value="<%=v%>"/>
<%		
	}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js-lib/bootstrap-treeview.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/dwrService.js"></script>
<title>为用户授权</title>
</head>
<body>
	<div class="header lighter smaller blue">
		<h3><i class="icon-key"></i>&nbsp;为用户授权 <small>${user.nickname}[${user.username}]</small></h3>
		<jsp:include page="nav.jsp"/>
	</div>
	<div class="widget-box">
		<div class="widget-header header-color-blue2">
			<h4 class="lighter smaller">在角色名前面打钩则表示授权（共有【${fn:length(roleList)}】个角色）</h4>
		</div>

		<div class="list-group">
			<c:forEach items="${roleList}" var="obj">
				<a href="javascript:" class="list-group-item">
					<label>
						<input class="ace ace-checkbox-2 single-role-box" id="role_${obj.id}" objId="${obj.id}" type="checkbox" />
						<span class="lbl">&nbsp;${obj.name}</span>
					</label>
				</a>
			</c:forEach>
		</div>
		<input type="hidden" id="curRoles" value="${roleIds}"/>
		<input type="hidden" id="curUserId" value="${user.id}"/>
	</div>
	<script type="text/javascript">
		$(function() {
			$(".single-role-box").click(function() {
				var objId = $(this).attr("objId");
				dwrService.addOrDeleteUserRole($("#curUserId").val(), objId, function(res){
					if(res!='1') {alert("操作失败");}
				});
			});
			var curAuth_array = $("#curRoles").val().split(",");
			for(var i=0; i<curAuth_array.length; i++) {
				$(("#role_"+curAuth_array[i])).attr("checked", true);
			}
		});
		
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/validate/bootstrapValidator.js"></script>
<title>添加角色</title>
</head>
<body>
	<div class="header lighter smaller blue">
		<h3><i class="icon-plus"></i>&nbsp;添加角色 </h3>
		<jsp:include page="nav.jsp"/>
	</div>
	
	<sf:form method="POST" modelAttribute="role" id="dataForm">
		<div class="form-group form-group-lg">
			<label for="name">角色名称</label>
			<sf:input path="name" class="form-control" id="name" placeholder="请输入角色名称"/>
		</div>
		<input type="hidden" name="token" value="${token}"/>
		<button type="submit" class="btn btn-primary">确定提交</button>
	</sf:form>
	<script type="text/javascript">
		jQuery(function($) {
			$('#dataForm').bootstrapValidator({
//		        live: 'disabled',
		        message: '验证不通过',
		        feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		            name: {
		                validators: {
		                    notEmpty: {
		                        message: '请输入角色名称'
		                    }
		                }
		            }
		        }
		    });
		});
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/validate/bootstrapValidator.js"></script>
<title>修改读者</title>
</head>
<body>
	<div class="header lighter smaller blue">
		<h3><i class="icon-plus"></i>&nbsp;修改读者信息<small>[${reader.name}]</small></h3>
		<jsp:include page="nav.jsp"/>
	</div>
	
	<sf:form method="POST" modelAttribute="reader" id="dataForm">
		<div class="form-group">
			<div class="input-group input-group-lg">
				<div class="input-group-addon">证件号</div>
				<sf:input path="cardNo" class="form-control" placeholder="请输入证件号"/>
			</div>
		</div>
		
		<div class="form-group">
			<div class="input-group input-group-lg">
				<div class="input-group-addon">姓名</div>
				<sf:input path="name" class="form-control" placeholder="请输入姓名"/>
			</div>
		</div>
		
		<div class="form-group">
			<div class="input-group input-group-lg">
				<div class="input-group-addon">身份证号</div>
				<sf:input path="identity" class="form-control" placeholder="请输入身份证号"/>
			</div>
		</div>
		
		<div class="form-group">
			<div class="input-group input-group-lg">
				<div class="input-group-addon">联系电话</div>
				<sf:input path="phone" class="form-control" placeholder="请输入联系电话"/>
			</div>
		</div>
		
		<div class="form-group">
			<div class="input-group input-group-lg">
				<div class="input-group-addon">联系地址</div>
				<sf:input path="address" class="form-control" placeholder="请输入联系地址"/>
			</div>
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
		                        message: '请输入读者姓名'
		                    }
		                }
		            },
		            cardNo: {
		                validators: {
		                    notEmpty: {
		                        message: '请输入证件号'
		                    }
		                }
		            }
		        }
		    });
		});
	</script>
</body>
</html>
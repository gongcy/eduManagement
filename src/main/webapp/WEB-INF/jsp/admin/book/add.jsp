<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/validate/bootstrapValidator.js"></script>
<title>添加图书</title>
</head>
<body>
	<div class="header lighter smaller blue">
		<h3><i class="icon-plus"></i>&nbsp;添加图书 </h3>
		<jsp:include page="nav.jsp"/>
	</div>
	
	<sf:form method="POST" modelAttribute="book" id="dataForm">
		<div class="form-group">
			<div class="input-group input-group-lg">
				<div class="input-group-addon">图书分类</div>
				<%-- <sf:input path="name" class="form-control" placeholder="请输入图书名称"/> --%>
				<sf:select path="cateId" items="${cateList}" itemLabel="name" itemValue="id" class="form-control cate-id"/>
				<sf:hidden path="cateName" id="cateName"/>
			</div>
		</div>
		
		<div class="form-group">
			<div class="input-group input-group-lg">
				<div class="input-group-addon">图书条码</div>
				<sf:input path="no" class="form-control" placeholder="请输入图书条码，即唯一编号"/>
			</div>
		</div>
		
		<div class="form-group">
			<div class="input-group input-group-lg">
				<div class="input-group-addon">图书名称</div>
				<sf:input path="name" class="form-control" placeholder="请输入图书名称"/>
			</div>
		</div>
		
		<div class="form-group">
			<div class="input-group input-group-lg">
				<div class="input-group-addon">书架位置</div>
				<sf:input path="pos" class="form-control" placeholder="请输入图书所在位置，如：2-1-3-6"/>
			</div>
		</div>
		
		<div class="form-group">
			<div class="input-group input-group-lg">
				<div class="input-group-addon">图书作者</div>
				<sf:input path="author" class="form-control" placeholder="请输入作者"/>
			</div>
		</div>
		<div class="form-group">
			<div class="input-group input-group-lg">
				<div class="input-group-addon">出版社</div>
				<sf:input path="pub" class="form-control" placeholder="请输入出版社"/>
			</div>
		</div>
		<div class="form-group">
			<div class="input-group input-group-lg">
				<div class="input-group-addon">图书价格</div>
				<sf:input path="money" class="form-control" placeholder="请输入图书价格"/>
			</div>
		</div>
		<div class="form-group">
			<div class="input-group input-group-lg">
				<div class="input-group-addon">租金价格</div>
				<sf:input path="price" class="form-control" placeholder="请输入租金，多少元/天"/>
			</div>
		</div>
		<div class="form-group">
			<div class="input-group input-group-lg">
				<div class="input-group-addon">图书数量</div>
				<sf:input path="amount" class="form-control" placeholder="请输入图书数量"/>
			</div>
		</div>
		<div class="form-group">
			<div class="input-group input-group-lg">
				<div class="input-group-addon">图书备注</div>
				<sf:textarea path="remark" cssStyle="height:80px; resize:none;" class="form-control" placeholder="请输入图书备注"/>
			</div>
		</div>
		<input type="hidden" name="token" value="${token}"/>
		<button type="submit" class="btn btn-primary">确定提交</button>
	</sf:form>
	<script type="text/javascript">
		jQuery(function($) {
			$("#cateName").val($(".cate-id").find("option:first").html());
			$(".cate-id").change(function() {
				var val = $(this).val();
				var text = $(this).find("option[value='"+val+"']").html();
				//alert(text);
				$("#cateName").val(text);
			});
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
		                        message: '请输入图书名称'
		                    }
		                }
		            },
		            no: {
		                validators: {
		                    notEmpty: {
		                        message: '请输入图书条码'
		                    }
		                }
		            },
		            author: {
		                validators: {
		                    notEmpty: {
		                        message: '请输入图书作者'
		                    }
		                }
		            },
		            pos: {
		                validators: {
		                    notEmpty: {
		                        message: '请输入所在位置'
		                    }
		                }
		            },
		            pub: {
		                validators: {
		                    notEmpty: {
		                        message: '请输入图书出版社'
		                    }
		                }
		            },
		            amount: {
		                validators: {
		                    notEmpty: {
		                        message: '请输入数量'
		                    },
		                    regexp: {
		                        regexp: /^[0-9]+$/,
		                        message: '只能输入正整数'
		                    },
		                }
		            },
		            money: {
		                validators: {
		                    notEmpty: {
		                        message: '请输入书价'
		                    },
		                    regexp: {
		                        regexp: /^[0-9]+$/,
		                        message: '只能输入正整数'
		                    },
		                }
		            },
		            price: {
		                validators: {
		                    notEmpty: {
		                        message: '请输入租金'
		                    },
		                    regexp: {
		                        regexp: /^[0-9]+$/,
		                        message: '只能输入正整数'
		                    },
		                }
		            }
		        }
		    });
		});
	</script>
</body>
</html>
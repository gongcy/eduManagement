<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
	<head>
		<title>${appConfig.appName}-借阅</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="author" content="zslin.com" />
		<script type="text/javascript">
			$(function() {
				$(".delete-obj-href").deleteFun();
				$("#searchBookBtn").click(function() {
					searchBook();
				});
				$("input[name='nameNo']").keyup(function(event) {
					if(event.keyCode==13) {searchBook();}
				});
			});
			
			function searchBook() {
				var bookNo = $("input[name='nameNo']").val();
				if($.trim(bookNo)=='') {showDialog("请输入图书条码");}
				else {
					var thisUrl = $("#thisUrl").val() + "&bookNo="+bookNo;
					//alert(thisUrl);
					window.location.href = thisUrl;
				}
			}
		</script>
	</head>
	<body>
		<input id="thisUrl" value="<%=request.getContextPath()%>/admin/borrow/add?readerId=${param.readerId}" type="hidden"/>
		<div class="page-header lighter blue">
			<h3><span class="glyphicon glyphicon-plus"></span>&nbsp;借书<small>（${reader.name}）</small></h3>
		</div>
		
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title"><i class="glyphicon glyphicon-education"></i> 阅读者信息：</h3>
					</div>
					<div class="panel-body">
						<p>姓名：${reader.name}</p>
						<p>证件号：${reader.cardNo}</p>
						<p>身份证号：${reader.identity}</p>
						<p>电话：${reader.phone}</p>
						<p>地址：${reader.address}</p>
						
					</div>
				</div>
				
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h3 class="panel-title"><i class="glyphicon glyphicon-book"></i> 已借书籍（<b>${datas.total}</b> 本）：</h3>
					</div>
					<div class="panel-body">
						<c:forEach items="${datas.datas}" var="b">
						
						<div class="panel panel-warning">
							<!-- <div class="panel-heading">
								<h3 class="panel-title"><i class="glyphicon glyphicon-education"></i> 阅读者信息：</h3>
							</div> -->
							<div class="panel-body">
								<p>书名：${b.bookName}</p>
								<p>借阅日期：<fmt:formatDate value="${b.createDate}" pattern="yyyy-MM-dd HH:mm"/></p>
							</div>
							<div class="panel-footer"><a class="delete-obj-href btn btn-primary" title="确定现在归还[${b.bookName}]吗？" href="<%=request.getContextPath()%>/admin/borrow/back?readerId=${reader.id}&bookNo=${b.bookNameNo}">现在归还</a></div>
						</div>
						</c:forEach>
						<jsp:include page="/common_jsp/pager.jsp" >
							<jsp:param value="${datas.total}" name="totalRecord"/>
							<jsp:param value="readerId,bookNo" name="params"/>
							<jsp:param value="add" name="url"/>
						</jsp:include>
					</div>
				</div>
			</div>
			
			<div class="col-md-6">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title"><i class="glyphicon glyphicon-plus"></i> 新增外借书（<small>先查询到查，再外借</small>）：</h3>
					</div>
					<div class="panel-body">
						
						<div class="form-group">
							<div class="input-group input-group-lg">
								<!-- <div class="input-group-addon">图书条码</div> -->
								<input name="nameNo" class="form-control" placeholder="请输入图书条码，即唯一编号" value="${param.bookNo}"/>
								<span class="input-group-addon"><a href="javascritp:;" id="searchBookBtn">提交查询</a></span>
							</div>
						</div>
					
						<c:if test="${book!=null}">
						<div class="panel panel-warning">
							<div class="panel-heading">
								<h3 class="panel-title"><i class="glyphicon glyphicon-book"></i> 查到书籍（<b>${book.name}</b>）：</h3>
							</div>
							<div class="panel-body">
								<p>书名：${book.name}</p>
								<p>作者：${book.author}</p>
								<p>出版社：${book.pub}</p>
								<p>租金：${book.price} /天</p>
								<p>总数量：${book.amount} 册</p>
								<p>剩余数量：${book.amount-borrowCount}册</p>
								<c:if test="${!hasBorrow && (book.amount-borrowCount)>0}">
									<p class="text-center"><a class="btn btn-success delete-obj-href" title="确定将[${book.name}]外借给[${reader.name}]吗？" href="<%=request.getContextPath()%>/admin/borrow/add?readerId=${reader.id}&bookNo=${book.no}"><i class="glyphicon glyphicon-plus"></i> 外借给[${reader.name}]</a></p>
								</c:if>
								<c:if test="${hasBorrow}">
									<p class="text-center"><a class="btn btn-danger" disabled="disabled" href="javascript:;"><i class="glyphicon glyphicon-remove"></i> 不可重复借给[${reader.name}]</a></p>
								</c:if>
								<c:if test="${(book.amount-borrowCount)<=0}">
									<p class="text-center"><a class="btn btn-danger" disabled="disabled" href="javascript:;"><i class="glyphicon glyphicon-remove"></i> 此书库存为零</a></p>
								</c:if>
							</div>
						</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
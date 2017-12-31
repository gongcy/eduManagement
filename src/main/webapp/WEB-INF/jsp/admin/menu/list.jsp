<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js-lib/bootstrap-treeview.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js-lib/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js-lib/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js-lib/ui/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js-lib/ui/jquery.ui.sortable.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/myself/my.sortable.js"></script>

<title>菜单列表</title>
<script type="text/javascript">
	$(function() {
		$(".rebuildMenu").deleteFun({title:'重构提示', msg:'确定要重新生成所有菜单吗？请谨慎操作'});
		
		var defaultData = $("#treeJson").val();
		//alert(defaultData);
		defaultData = eval('(' + defaultData + ')'); //JSON.parse(defaultData);
		
		//alert(defaultData);
		var myMenuTree = $('#tree1').treeview({
			data: defaultData,
			showIcon: true,
			showCheckbox: false,
			showTags: true,
			state:'',
			multiSelect: false,
			//levels: 99, //展开99级，基本也就是全部展开了
			onNodeSelected: function(event, node) {
				operate(node.text, "1");
			}
		});
		
		myMenuTree.treeview("checkNode", myMenuTree.treeview("search", "="+$("#curPid").val()+">"));
		
		//添加或删除数据
		function operate(name, flag) {
			var objId = $(name).attr("title"); //获取菜单Id
			//alert(objId);
			if(objId!=$("#curPid").val()) {
				window.location.href = $("#thisUrl").val()+objId;
			}
		}
		
		$("table").sorttable({
			url:"<%=request.getContextPath()%>/admin/menu/updateSort",
			authSn:"MenuController.updateSort"
		});
	});
</script>
</head>
<body>
	
	<input type="hidden" id="thisUrl" value="<%=request.getContextPath()%>/admin/menu/list?pid="/>
	<input type="hidden" id="treeJson" value="${treeJson}"/>
	<input type="hidden" id="curPid" value="${param.pid}"/>
	<div class="header lighter smaller blue">
		<h3><span class="glyphicon glyphicon-th-list"></span>&nbsp;菜单列表<small>（${datas.total}）</small></h3>
		<jsp:include page="nav.jsp"/>
	</div>
	
	<!-- <div class="table-header">
		Results for "Latest Registered Domains"
	</div> -->
	<div class="row">
		<div class="col-md-3">
			<div id="tree1" class="tree"></div>
		</div>
		<div class="col-md-9">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>名称</th>
							<th>SN</th>
							<th>类型</th>
							<th>链接地址</th>
							<th>操作</th>
						</tr>
					</thead>
		
					<tbody>
						<c:forEach items="${datas.datas}" var="obj">
							<tr>
								<td>${obj.id}</td>
								<td>${obj.name}</td>
								<td>${obj.sn}</td>
								<td><c:if test="${obj.type=='1'}">导航菜单</c:if><c:if test="${obj.type!='1'}">权限菜单</c:if></td>
								<td>${obj.href}</td>
								<td>
									-
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="7">
								<jsp:include page="/common_jsp/pager.jsp" >
									<jsp:param value="${datas.total}" name="totalRecord"/>
									<jsp:param value="pid" name="params"/>
									<jsp:param value="list" name="url"/>
								</jsp:include>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
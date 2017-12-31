<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js-lib/bootstrap-treeview.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/dwrService.js"></script>
<title>为角色授权</title>
</head>
<body>
	<div class="header lighter smaller blue">
		<h3><i class="icon-key"></i>&nbsp;为角色授权 <small>${role.name}</small></h3>
		<jsp:include page="nav.jsp"/>
	</div>
	<div class="widget-box">
		<div class="widget-header header-color-blue2">
			<h4 class="lighter smaller">在菜单名前面打钩则表示授权</h4>
		</div>

		<div class="widget-body">
			<div class="widget-main padding-8">
				<div id="tree1" class="tree"></div>
			</div>
		</div>
		<input type="hidden" id="treeJson" value="${treeJson}"/>
		<input type="hidden" id="objId" value="${role.id}"/>
		<input type="hidden" id="curAuth" value="${curAuth}"/>
	</div>
	<script type="text/javascript">
		var curAuth_array;
		$(function() {
			var defaultData = $("#treeJson").val();
			//alert(defaultData);
			defaultData = eval('(' + defaultData + ')'); //JSON.parse(defaultData);
			
			//alert(defaultData);
			var myMenuTree = $('#tree1').treeview({
				data: defaultData,
				showIcon: true,
				showCheckbox: true,
				showTags: true,
				state:'',
				multiSelect: false,
				//levels: 99, //展开99级，基本也就是全部展开了
				onNodeChecked: function(event, node) {
					operate(node.text, "1");
				},
				onNodeUnchecked: function (event, node) {
					operate(node.text, "2");
				}
			});
			
			curAuth_array = $("#curAuth").val().split(",");
			for(var i=0; i<curAuth_array.length; i++) {
				//alert(curAuth_array[i]);
				myMenuTree.treeview("checkNode", myMenuTree.treeview("search", "="+curAuth_array[i]+">"));
			}
		});
		
		//alert(curAuth_array.length);
		
		//添加或删除数据
		function operate(name, flag) {
			var objId = $(name).attr("title"); //获取菜单Id
			//alert(objId+"=="+$.inArray(objId, curAuth_array)>=0);
			if(flag=='1' && $.inArray(objId, curAuth_array)>=0) {} //初始化时不作任何操作
			else {
				dwrService.addOrDeleteRoleMenu($("#objId").val(), objId, function(res) {
					
				});
			}
		}
		
	</script>
</body>
</html>
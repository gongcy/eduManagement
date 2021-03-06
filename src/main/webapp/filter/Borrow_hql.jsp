<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div id="sys_filter" class="form-inline" style="padding-bottom:10px;">
	
		<div class="input-group">
		<span class="input-group-addon">状态</span><select class='filter_element form-control' 
		id='FILTERTYPE_PRE:fborrow_status_STR_EQ'
		name='FILTERTYPE_PRE:fborrow_status_STR_EQ'><option value='-1'>=全部=</option>
			<option value="1">外借中</option>
			<option value="2">已归还</option>
			</select>
		</div>
		
		<div class="input-group">
		<span class="input-group-addon">书名</span><input type='text' style="width:240px;"
		class='filter_element form-control' id='FILTERTYPE_PRE:fborrow_bookName_STR_LIKEBE'
		name='FILTERTYPE_PRE:fborrow_bookName_STR_LIKEBE' />
		</div>
		
		<div class="input-group">
		<span class="input-group-addon">条码</span><input type='text' style="width:240px;"
		class='filter_element form-control' id='FILTERTYPE_PRE:fborrow_bookNameNo_STR_LIKEBE'
		name='FILTERTYPE_PRE:fborrow_bookNameNo_STR_LIKEBE' />
		</div>
		
		<span
		class="filter_container"><button type="button" class="btn btn-primary btn-sm" id="beginFilter">查询</button></span>
	<jsp:include page="/filter/filter_common.jsp" />
</div>
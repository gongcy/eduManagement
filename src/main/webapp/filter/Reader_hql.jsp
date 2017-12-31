<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div id="sys_filter" class="form-inline" style="padding-bottom:10px;">
		
		<div class="input-group">
		<span class="input-group-addon">证件号</span><input type='text' style="width:140px;"
		class='filter_element form-control' id='FILTERTYPE_PRE:freader_cardNo_STR_LIKEBE'
		name='FILTERTYPE_PRE:freader_cardNo_STR_LIKEBE' />
		</div>
		
		<div class="input-group">
		<span class="input-group-addon">身份证号</span><input type='text' style="width:200px;"
		class='filter_element form-control' id='FILTERTYPE_PRE:freader_identity_STR_LIKEBE'
		name='FILTERTYPE_PRE:freader_identity_STR_LIKEBE' />
		</div>
		
		<div class="input-group">
		<span class="input-group-addon">姓名</span><input type='text' style="width:100px;"
		class='filter_element form-control' id='FILTERTYPE_PRE:freader_name_STR_LIKEBE'
		name='FILTERTYPE_PRE:freader_name_STR_LIKEBE' />
		</div>
		
		<span
		class="filter_container"><button type="button" class="btn btn-primary btn-sm" id="beginFilter">查询</button></span>
	<jsp:include page="/filter/filter_common.jsp" />
</div>
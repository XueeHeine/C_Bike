<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/easyui/include.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/list2.js"></script>
</head>
<body>
	<table id="emp-list"></table>
	
	<div id="emp-list-toolbar">
		<table border="0" cellpadding="0" cellspacing="0" style="width: 100%;">
			<tr>

				<td style="text-align: right;">
			时间区间：<input id="ftime"  name="ftime" style="float: right;" class="easyui-datebox" data-options="width:180"/>
					到<input id="ltime"  name="ltime" style="float: right;" class="easyui-datebox" data-options="width:180"/>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="
			iconCls:'icon-search'" onclick="eventObj.query()">查询</a>
				</td>
			<tr>
		</table>
	</div>
	<div id="emp-dialog"></div>
	<div id="emp-add1"></div>
	
</body>
</html>
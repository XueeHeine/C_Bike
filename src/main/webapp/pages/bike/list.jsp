<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@include file="/easyui/include.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/list11.js"></script>
</head>
<body>
	<table id="emp-list"></table>
	
	<div id="emp-list-toolbar">
		<table border="0" cellpadding="0" cellspacing="0" style="width: 100%;">
			<tr>
				<td style="width: 700px;">
			<a href="javascript:void(0)" onclick="eventObj.add()" class="easyui-linkbutton" data-options="
			iconCls:'icon-add'">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="
			iconCls:'icon-remove'" onclick="eventObj.remove()">删除选中</a>
				</td>
				<td style="text-align: right;">
			单车型号：<input id="model"  name="model" style="float: right;" class="easyui-textbox" data-options="width:180"/>
			供应商名称：<input id="supidd"  name="supidd"  class="easyui-combotree" data-options="width:180,
					url:'${pageContext.request.contextPath }/bicycle/getSup?index=1',method:'get',animate:true,
					lines:true"/>
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
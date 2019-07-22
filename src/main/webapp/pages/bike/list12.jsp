<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@include file="/easyui/include.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/list12.js"></script>
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
			供应商名称：<input id="sname"  name="sname" style="float: right;" class="easyui-textbox" data-options="width:180"/>
			优先级：<input id="priority"  name="priority" class="easyui-combobox" data-options="editable:false,width:180,
					valueField: 'label',
					textField: 'value',
					data: [{
						label: '0',
						value: 'all'
					},{
						label: '2',
						value: '2'
					},{
						label: '4',
						value: '4'
					},{
						label: '6',
						value: '6'
					},{
						label: '8',
						value: '8'
					},{
						label: '10',
						value: '10'
					}]"/>
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
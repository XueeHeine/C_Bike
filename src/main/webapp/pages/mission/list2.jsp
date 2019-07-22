<%@ page language="java" contentType="text/html;charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@include file="/easyui/include.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/list14.js"></script>
</head>
<body>
<table id="mis-list"></table>


<div id="mis-list-toolbar">
	<table border="0" cellpadding="0" cellspacing="0" style="width: 100%;">
		<tr>
<%--				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="
			iconCls:'icon-remove'" onclick="eventObj.remove()">删除选中</a>--%>
			<td style="width: 700px;">
				任务名称：<input id="mname"  name="mname" style="float: right;" class="easyui-textbox" data-options="width:180"/>
				紧急情况：<input id="qzhj"  name="qzhj" class="easyui-combobox" data-options="editable:false,width:180,
					valueField: 'label',
					textField: 'value',
					data: [{
						label: '1',
						value: '普通'
					},{
						label: '2',
						value: '紧急'
					}]"/>
				状态：<input id="mstatus"  name="mstatus" class="easyui-combobox" data-options="editable:false,width:180,
					valueField: 'label',
					textField: 'value',
					data: [{
						label: '0',
						value: '未领取'
					},{
						label: '1',
						value: '工作中'
					},{
						label: '2',
						value: '等待完成检验'
					},{
						label: '3',
						value: '关闭'
					}]"/>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="
			iconCls:'icon-search'" onclick="eventObj.query()">查询</a>
			</td>
		<tr>
	</table>
</div>
<div id="mis-dialog"></div>
<div id="mis-add1"></div>

</body>
</html>
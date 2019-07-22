<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/easyui/include.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/list4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		html{
			height: 100%;
			width: 100%;
		}
	</style>
</head>
<body style="width: 99%;height: 97%">
	<input id="id" value="${param.id}" style="display: none">
	<input id="time" value="${param.time}" style="display: none">
	<div id="all" style="width: 100%;height: 100%;background-color: #ffffff;border:0px solid #d1d1d1;">
		<div id="alldiv" style="width: 99%;height: 100%;background-color: #ffffff;border:1px solid #d1d1d1;margin-left: 1px;margin-right: 1px">
			<div id="top" style="height: 100%;border: 1px solid #d1d1d1;width: 100%">


			</div>


		</div>

	</div>
	
</body>
</html>
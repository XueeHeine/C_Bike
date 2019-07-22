<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/easyui/include.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/list3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		html{
			height: 100%;
			width: 100%;
		}
	</style>
</head>
<body style="width: 99%;height: 97%">
	<div id="all" style="width: 100%;height: 100%;background-color: #ffffff;border:0px solid #d1d1d1;">
		<div id="alldiv" style="width: 99%;height: 100%;background-color: #ffffff;border:1px solid #d1d1d1;margin-left: 1px;margin-right: 1px">
			<div id="top" style="height: 40px;border: 1px solid #d1d1d1;width: 100%">
 				<table style="width: 100%;height: 100%">
					<tr>
						<td width="30%" align="center" nowrap="nowrap"></td>
						<td width="5%" align="center" nowrap="nowrap">时间：</td>
						<td width="5%" align="center" nowrap="nowrap">
							<input id="startyeartime" class="Wdate" type="text" style="line-height: 31px;height: 32px;width: 80px;text-align: left" onFocus="WdatePicker({dateFmt:'yyyy',isShowClear:false,readOnly:true})" value="2019">
						</td>
						<td width="5%" align="center" nowrap="nowrap">单车型号：</td>
						<td width="5%" align="center" nowrap="nowrap">
							<input id="xh"  name="gno" class="easyui-combotree" data-options="width:180,
							url:'${pageContext.request.contextPath }/bicycle/getBicycle',method:'post',animate:true,
							lines:true"/>
						</td>
						<td width="8%" align="center" nowrap="nowrap">
							<div>
								<a class="easyui-linkbutton c100" onclick="querydata()" style="margin-top: 1px;margin-bottom: 1px"><span id="aaa">查询</span></a>
							</div>
						</td>
						<td width="40%" align="center" nowrap="nowrap"></td>

					</tr>

				</table>

			</div>
			<div id="center" style="height: 41%;width:100%;margin-top: 2px;margin-left:1px;border: 1px solid #d1d1d1;">
				<div id="mycharts1" style="background-color: #f8f8f8;border:0px solid #ccc;width: 100%;height: 100%">

				</div>
			</div>
			<div id="center1" style="height: 40px;border: 1px solid #d1d1d1;width: 100%;margin-top: 2px">
				<table style="width: 100%;height: 100%">
					<tr>
						<td width="30%" align="center" nowrap="nowrap"></td>
						<td width="5%" align="center" nowrap="nowrap">单车比较：</td>
						<td width="5%" align="center" nowrap="nowrap">
							<input id="bj1"  name="gno" class="easyui-combotree" data-options="width:180,
							url:'${pageContext.request.contextPath }/bicycle/getBicycle',method:'post',animate:true,
							lines:true"/>
						</td>
						<td width="2%" align="center" nowrap="nowrap">~</td>
						<td width="5%" align="center" nowrap="nowrap">
							<input id="bj2"  name="gno" class="easyui-combotree" data-options="width:180,
							url:'${pageContext.request.contextPath }/bicycle/getBicycle',method:'post',animate:true,
							lines:true"/>
						</td>
						<td width="8%" align="center" nowrap="nowrap">
							<div>
								<a class="easyui-linkbutton c100" onclick="compareTo()" style="margin-top: 1px;margin-bottom: 1px"><span id="aaaa">查询</span></a>
							</div>
						</td>
						<td width="43%" align="center" nowrap="nowrap"></td>

					</tr>

				</table>

			</div>
			<div id="bottom" style="height: 41%;width:100%;margin-top:2px;;margin-left:1px;border: 1px solid #d1d1d1;">
				<div id="mycharts2" style="background-color: #f8f8f8;border:0px solid #ccc;width: 100%;height: 100%">

				</div>
			</div>

		</div>

	</div>
	
</body>
</html>
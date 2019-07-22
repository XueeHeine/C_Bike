<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<body>
	<form id="emp-form" method="post">
		<input type="hidden" name="id" value="${b.id }" />
		<table style="margin:70px auto" cellpadding="5px" cellspacing="5px">
			<tr>
				<td>单车型号</td>
				
				<td>

					<input value="${b.model }" name="model" class="easyui-textbox" data-options="required:true,width:180"/>

				</td>
				
			</tr>
			<tr>
				<td>供应商</td>
				<td>
					<input id="supid" value="${b.sup.id }" name="sup.id" class="easyui-combotree" data-options="required:true,width:180,
					url:'${pageContext.request.contextPath }/bicycle/getSup',method:'post',animate:true,
					lines:true"/>
				</td>
			</tr>
			<tr>
				<td>剩余数量</td>
				<td>
					<c:choose>
						<c:when test="${empty b }">
					<input id="salary" value="${b.nnumber }" name="nnumber" class="easyui-numberbox" data-options="required:true,width:180"/>
					</c:when>
					<c:otherwise>
						${b.nnumber }
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<td>单车类别</td>
				<td>
					<input value="${b.category }" name="category" class="easyui-combobox" data-options="required:true,editable:false,width:180,
					valueField: 'label',
					textField: 'value',
					data: [{
						label: '1',
						value: '公路自行车'
					},{
						label: '2',
						value: '山地自行车'
					},{
						label: '3',
						value: '折叠自行车'
					},{
						label: '4',
						value: '旅行自行车'
					}]"/>
				</td>
			</tr>
			
			
		</table>
	</form>
</body>

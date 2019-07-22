<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<body>
	<form id="emp-form" method="post">
		<input type="hidden" name="id" value="${b.id }" />
		<table style="margin:70px auto" cellpadding="5px" cellspacing="5px">
			<tr>
				<td>供应商名称</td>
				
				<td>


					<c:choose>
						<c:when test="${empty b }">
							<input value="${b.sname }" name="sname" class="easyui-textbox" data-options="required:true,width:180"/>
						</c:when>
						<c:otherwise>
							${b.sname }
						</c:otherwise>
					</c:choose>
				</td>
				
			</tr>
			<tr>
				<td>供应商地址</td>
				<td>
					<input value="${b.address }" name="address" class="easyui-textbox" data-options="required:true,width:180"/>
				</td>
			</tr>

			<tr>
				<td>供应商优先级</td>
				<td>
					<input value="${b.priority }" name="priority" class="easyui-combobox" data-options="required:true,editable:false,width:180,
					valueField: 'label',
					textField: 'value',
					data: [{
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
				</td>
			</tr>
			
			
		</table>
	</form>
</body>

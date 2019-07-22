<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<body>
	<form id="emp-form" method="post">
		<input type="hidden" name="id" value="${b.id }" />
		<table style="margin:70px auto" cellpadding="5px" cellspacing="5px">
			<tr>
				<td>租聘号</td>
				<td>
					<input value="${idd }" name="rno" class="easyui-textbox" data-options="required:true,width:180"/>
				</td>
			</tr>
			<tr>
				<td>单车型号</td>
				<td>
					<input id="cc"  name="gno" class="easyui-combotree" data-options="required:true,width:180,
					url:'${pageContext.request.contextPath }/bicycle/getBicycle',method:'post',animate:true,
					lines:true,
					onLoadSuccess:function(node,data){
						 $('#cc').combotree('setValue',${b.gno });
						$('#cc').combotree('setText','${b.model }');	 
					}"/>
				</td>
			</tr>
			<tr>
				<td>租赁人</td>
				<td>
					<input value="${b.supname }" name="getname" class="easyui-textbox" data-options="required:true,width:180"/>
				</td>
			</tr>
			<tr>
				<td>联系电话</td>
				<td>
					
					<input id="salary" value="${b.nnumber }" name="phone" class="easyui-numberbox" data-options="required:true,width:180"/>
					
				</td>
			</tr>

			
			
			
		</table>
	</form>
</body>

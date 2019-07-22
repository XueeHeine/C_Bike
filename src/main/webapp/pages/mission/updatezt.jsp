<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/easyui-1.5/themes/material/easyui.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/easyui-1.5/themes/icon.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.5/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.5/datagrid-detailview.js"></script>
    <script type="text/javascript">var contextPath = '${pageContext.request.contextPath}';</script>
   <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/list11.js"></script>--%>
    <style type="text/css">
        #mis-form{
            margin:32px auto;
            width:480px;
        }
        .input_container{
            margin-bottom:32px;
            margin-top: 30px;
        }
    </style>
</head>
<body>
<form id="misman-form" method="post" action="/C_Bike/mission/addRM">
    <input type="hidden" name="mid" value="${mid}"/>
    <table style="margin:70px auto" cellpadding="5px" cellspacing="5px">
        <tr>
            <td>状态</td>
            <td>
            <input id="mstatus"  name="mstatus" class="easyui-combobox" data-options="editable:false,width:180,
					valueField: 'label',
					textField: 'value',
					data: [{
						label: '1',
						value: '工作中'
					},{
						label: '2',
						value: '待检验'
					},{
						label: '3',
						value: '关闭'
					}]"/>
            <td>
        </tr>
    </table>
</form>
</body>

</html>
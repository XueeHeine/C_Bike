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
            <td>维修员：</td>
            <td>
                <input id="man" value="${b.sup.id }" name="userName" class="easyui-combotree" data-options="required:true,width:180,
					url:'${pageContext.request.contextPath }/mission/getManList',method:'post',animate:true,
					lines:true"/>
            </td>
        </tr>
    </table>
</form>
</body>

</html>
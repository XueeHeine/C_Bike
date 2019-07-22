<%--
  Created by IntelliJ IDEA.
  User: Alpha-LGC
  Date: 2018/11/8
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>通知管理</title>
        <%@include file="/easyui/include.jsp"%>
        <script src="${pageContext.request.contextPath}/js/user.js"></script>
    </head>
    <body>
        <div>发布群通知：<input id="text" type="text" /><button onclick="sendmsg1()">确认发布</button>
        </div>

        <script>
            function sendmsg1() {
                var msg = $("#text").val();
                parent.window.sendmsg(msg);
            }

        </script>
    </body>

</html>

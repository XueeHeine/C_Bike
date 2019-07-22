<%--
  Created by IntelliJ IDEA.
  User: Alpha-LGC
  Date: 2018/11/7
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>登录</title>
        <%@include file="/easyui/include.jsp"%>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
        <style>
            .code{
                background-image: url(${pageContext.request.contextPath}/js/timg.jpg);/* 验证码的背景图 */
                font-family: Arial;
                font-style: italic;
                color: black;
                border: 0;
                padding:2px 3px;
                letter-spacing: 3px;
                font-weight: bolder;
                text-align: center;
            }

        </style>
    </head>
    <body>
        <div id="loginin-dialog">
            <form  id="login-form" method="post">

                <table style="margin: 10px auto;" cellpadding="5px" cellspacing="10px">
                    <tr>
                        <td><small>用户名:</small></td>
                        <td><input type="text" id="username" name="username"/></td>
                    </tr>
                    <tr>
                        <td><small>密码:</small></td>
                        <td><input type="text" id="password" name="password"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="checkbox" id="remember" name="remember"/>    <small>记住密码</small></td>
                    </tr>
                    <tr>
                        <td><small>验证码:</small></td>
                        <td>
                            <span>
                                <input type="text" class="easyui-textbox" id="input1" data-options="required:false,validType:'yanzhengma',width:180" />
                                <input type="text" id="checkNode" class="code" style="width: 55px" disabled="disabled" />
                                <small><a href="#" onclick="createCode()"><small>看不清楚,换一张</small></a></small>
                                <input type="button" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" name="button1" onclick="validate()" value="验证一下" />
                            </span>
                        </td>
                    </tr>
                </table>

            </form>

        </div>

    <%--注册 div--%>
        <div id="register-dialog"></div>


    </body>
</html>

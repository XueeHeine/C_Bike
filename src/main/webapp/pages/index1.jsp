<%--
  Created by IntelliJ IDEA.
  User: Alpha-LGC
  Date: 2018/11/8
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
    <head>
        <title>首页</title>
        <%@include file="/easyui/include.jsp"%>
        <script src="${pageContext.request.contextPath}/js/index.js"></script>
        <style>
            li{
                height: 30px;
            }
        </style>
    </head>
    <body>

        <div id="cc" class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',height:100" style="overflow-x: hidden;overflow-y: auto">
                <div style="float: right;margin-right:30px;line-height: 46px">
                    <span>欢迎:<b>${USER_SESSION.username}</b></span><span>  <a href="javascript:void(0);" onclick="logout()">退出</a></span>
                    <%--<span><a href="javascript:void(0);" onclick="changepwd()">修改密码</a></span>--%>
                </div>
                <div style="float: left ;height:50px;margin-right:30px;line-height: 26px; clear: left" >
                    <div style="float: left"><marquee behavior="slide" direction="down">欢迎使用QDU单车管理系统</marquee></div>
                    <br><br>
                    <div style="float: left"><span id="ls"></span><span id="onlineCount"></span><marquee behavior="" direction="right" width="700px"><h4 style="display: inline" id="message">公告：</h4></marquee></div>

                </div>
                <%--<div style="float: left;margin-right:30px;line-height: 16px" id="message">--%>
            </div>


            <div data-options="region:'west',title:'导航栏',split:true,width:240,iconCls:'icon-large-smartart'">
                <%--导航的--%>
                        <shiro:hasPermission name="系统权限_查看">
                    <div id="aa" class="easyui-accordion" data-options="fit:true" style="width:300px;height:200px;">
                        <div title="系统权限" data-options="iconCls:'icon-tip'" style="overflow:auto;padding:10px;">
                            <ul class="easyui-tree" style="">
                                <shiro:hasPermission name="系统权限_查看">
                                <li data-options="iconCls:'icon-man'"><a  class="opts" url="/user/userinfo">用户权限</a></li>
                                <li data-options="iconCls:'icon-man'"><a  class="opts" url="/role/roleinfo">角色权限</a></li>
                                 <li data-options="iconCls:'icon-man'"><a  class="opts" url="/ad/ad">通知管理</a></li>
                                </shiro:hasPermission>
                            </ul>
                        </div>
                        </shiro:hasPermission>
                        <div title="员工管理" data-options="iconCls:'icon-reload'" style="padding:10px;">
                            <ul class="easyui-tree">
                                <li><a  class="opts" url="/emp/list">用户入职</a></li>
                                <li><a  class="opts" url="/emp/edlist">可编辑列表</a></li>
                                <li><a  class="opts">访问权限</a></li>
                            </ul>
                        </div>
                        <shiro:hasRole name="超级管理员">
                            <div title="Title3">
                                content3
                            </div>
                        </shiro:hasRole>
                        <div title="租车操作" data-options="iconCls:'icon-tip'" style="overflow:auto;padding:10px;">
                            <ul class="easyui-tree" style="">
               <shiro:hasPermission name="单车权限_查看">
                                <li data-options="iconCls:'icon-man'"><a  class="opts" url="/bicycle/list">车辆信息管理</a></li>
                                <li data-options="iconCls:'icon-man'"><a  class="opts" url="/sup/list">供应商信息管理</a></li>
                                <li data-options="iconCls:'icon-man'"><a  class="opts" url="/rent/list">单车租借</a></li>
                                <li data-options="iconCls:'icon-man'"><a  class="opts" url="/rent/list1">单车租借统计</a></li>
                                <li data-options="iconCls:'icon-man'"><a  class="opts" url="/rent/list3">租借信息展示</a></li>
             </shiro:hasPermission>
                            </ul>
                        </div>
                       <%-- &lt;%&ndash;<shiro:hasRole name="VIP1">
                        <div title="Title3">
                            content3
                        </div>
                        </shiro:hasRole>&ndash;%&gt;
                        <shiro:hasPermission name="查看">
                            <div title="Title4">
                                content3
                            </div>
                        </shiro:hasPermission>&ndash;%&gt;--%>
                    </div>



            </div>
            <div data-options="region:'center'">
               <div class="easyui-tabs" id="content" data-options="fit:true"></div>

                <div id="dd"></div>
            </div>



        </div>
    <script>
        var websocket = null;

             //判断当前浏览器是否支持WebSocket
             if('WebSocket' in window){
                     websocket = new WebSocket("ws://localhost:8080/C_Bike/websocket");
             }
             else{
                     alert('Not support websocket')
             }

           //连接发生错误的回调方法
             websocket.onerror = function(){
                     setMessageInnerHTML("error");
             };

             //连接成功建立的回调方法
             websocket.onopen = function(event){
                     //setMessageInnerHTML("open");
                 $("#ls").html("连接状态:<font color='green'>已连接</font>");

             }

             //接收到消息的回调方法
            websocket.onmessage = function(event){
                 var str = event.data;
                 if(str.startsWith("当前在线人数")){
                     $("#onlineCount").html("<font color='green'>"+str+"</font>");
                 }
                 if(str == "12138"){
                     $.messager.alert({
                         title:'警告',
                         msg:"您的账号在另一客户端登录！",
                         icon:'error',
                         showType:'slide',
                         fn:function () {
                             closeWebSocket();
                             //location.replace(contextPath+"/login/logout");
                             // var win =  window.open(contextPath+"/login/logout","_top");
                             // win.location.replace(contextPath+"/");
                         }
                     });
                     return;
                 }
                    setMessageInnerHTML(event.data);
            }

             //连接关闭的回调方法
             websocket.onclose = function(){
                    //setMessageInnerHTML("close");
                 $("#ls").html("连接状态:<font color='red'>连接已断开</font>");
             }

            //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
             window.onbeforeunload = function(){
                     websocket.close();
             }

             //将消息显示在网页上
             function setMessageInnerHTML(innerHTML){
                    document.getElementById('message').innerHTML = innerHTML + '<br/>';
             }

            //关闭连接
             function closeWebSocket(){
                 logout();
                     websocket.close();
             }

            //发送消息
             function send(){
                 var message = document.getElementById('text').value;
                 websocket.send(message);
             }
        function sendmsg(msg){

            websocket.send(msg);
        }



        function startRequest(){


            $.ajax({
                type: "POST",
                url: contextPath+"/haveSession",
                data: null,

                success: function(msg){
                   // alert( "Data Saved: " + msg );
                },
                error:function (msg) {
                    location.replace(contextPath+"/login/logout");
                }
            });

            /*create();
            request.open("post",contextPath+"/haveSession",true);
            request.setRequestHeader("Content-Type"
                ,"application/x-www-form-urlencoded");
            request.onreadystatechange = function(){
                if(request.readyState==4){
                    if (request.status==302){
                        let value = request.responseText;
                            console.log("!!!!!!!!");
                            location.replace(contextPath+"/login/logout");
                            return;

                    }
                }
            };
            request.send("");*/




        }
        $(function () {
            $.ajaxSetup({
                contentType : "application/x-www-form-urlencoded;charset=utf-8",
                complete : function(XMLHttpRequest, textStatus) {
                    var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
                    if (sessionstatus == "timeout") {
                        console.log("hello");
                        // 如果超时就处理 ，指定要跳转的页面
                        window.location.replace(contextPath+"/login/logout");
                    }
                }
            });

        });

    </script>
    </body>
</html>

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
        <title>用户信息</title>
        <%@include file="/easyui/include.jsp"%>
        <script src="${pageContext.request.contextPath}/js/user.js"></script>
    </head>
    <body>


        <table style="width: 100% ;height: 100%;text-align: center">
            <tr>
               <td>用户名:</td>
                <td>${param.uname}</td>
            </tr>
            <tr>
                <td>启用状态:</td>
                <td>${param.status == "1" ? "<span style='color:green'>启用中</span>":"<span style='color:red'>已停用</span>"}</td>
            </tr>
            <tr>
                <td>当前角色:</td>
                <td>
                    <input id="cc" class="easyui-combotree"/>

                </td>
            </tr>

        </table>




        <div id="user-dialog">

        </div>
    
    <script>
        $(function () {
            var flag = undefined;
            $('#cc').combotree({
                url:contextPath+'/user/editRoles?id=${param.id}',
                valueField:'id',
                textField:'text',
                multiple:true,
                animate:true,
                separator:" ",
                onCheck:function (node, checked) {
                    console.log(node.id);
                    console.log(checked);
                    if((checked == false && node.id ==0)||(checked == false && node.id !=0)){
                        console.log("cancel"+node.id);
                        /*flag = 1 删去角色  0 添加角色*/
                        flag = 1
                    }else{
                        flag = 0;
                    }
                    $.ajax({
                        url:contextPath+'/user/addOrSubRole',
                        data:{"userid":${param.id},"roleid":node.id,"flag":flag},
                        type:'post',
                        success:function (data) {
                            if(data.code == 500) {
                                $.messager.alert({
                                    title: '警告',
                                    msg: data.msg,
                                    timeout: 5000,
                                    showType: 'slide'
                                });
                            }else if(data.code == 200){
                                console.log("success!!!!!!!!!!");
                                $.messager.show({
                                    title: '提示',
                                    msg: data.msg,
                                    timeout: 2000,
                                    showType: 'slide'
                                });
                                //$('#cc').combotree('destroy');
                            }
                        }
                    });

                }
            });
        });
        
        
    </script>

    </body>

</html>

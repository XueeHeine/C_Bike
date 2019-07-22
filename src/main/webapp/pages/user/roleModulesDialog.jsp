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
        <title>角色信息</title>
        <%@include file="/easyui/include.jsp"%>
        <script src="${pageContext.request.contextPath}/js/user.js"></script>
    </head>
    <body>


        <table style="width: 100% ;height: 100%;text-align: center">
            <tr>
                <td><font size="3px"face="楷体">角色名:</font></td>
                <td><font size="3px"face="黑体" color="red">${param.rolename}</font></td>
            </tr>

            <tr>
                <td><font size="3px"face="楷体">当前权限:</font></td>
                <td>
                    <input id="cc" class="easyui-combotree"/>

                </td>
            </tr>

        </table>




        <div id="role-dialog">

        </div>
    
    <script>



        $(function () {
            var flag = undefined;
            $('#cc').combotree({
                url:contextPath+'/role/editModules?id=${param.id}&rolename=${param.rolename}',
                valueField:'id',
                textField:'text',
                multiple:true,
                animate:true,
                separator:" ",
                onCheck:function (node, checked) {
                    console.log(node.id);
                    console.log(checked);
                    if((checked == false && node.id < 0)||(checked == false && node.id >0)){
                        console.log("cancel"+node.id);
                        /*flag = 1 删去角色  0 添加角色*/
                        flag = 1
                    }else{
                        flag = 0;
                    }
                    $.ajax({
                        url:contextPath+'/role/addOrSubModules',
                        data:{"roleid":${param.id},"moduleid":node.id,"flag":flag},
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
                            }else if(data.code == 403){

                                $.messager.show({
                                    title: '提示',
                                    msg: data.msg,
                                    timeout: 2000,
                                    showType: 'slide'
                                });
                                //$('#cc').combotree('destroy');
                            }
                        },error:function (err) {
                            $.messager.alert({
                                title: '警告',
                                msg: "<strong>您没有<权限>!请联系管理员</strong>",
                                //timeout:5000,
                                icon: 'error',
                                showType: 'slide'
                            });
                           // $("#user-list").datagrid("reload");
                        }
                    });

                }
            });
        });
        
        
    </script>

    </body>

</html>

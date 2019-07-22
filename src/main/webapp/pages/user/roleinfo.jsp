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
        <title>角色权限</title>
        <%@include file="/easyui/include.jsp"%>
        <%--<script src="${pageContext.request.contextPath}/js/user.js"></script>--%>
    </head>
    <body>
        <%--员工列表--%>
        <table id="role-list"></table>

        <div id="role-dialog">

        </div>


        <div id="role-editroles-dialog">

        </div>


    <script>

        function editModules(id,name) {
            // alert(id+"  "+name);
            $('#role-editroles-dialog').dialog({
                title: '角色详情',
                width: 500,
                height: 300,
                closed: false,
                cache: false,
                href: contextPath+'/pages/user/roleModulesDialog.jsp?id='+id+"&rolename="+name,
                modal: true
            });

        }

        $(function () {

            let editRow = undefined;
            /*页面加载即初始化表格*/
            $("#role-list").datagrid({
                url:contextPath+"/role/query",
                method:'post',
                striped:true,
                loadMsg:'数据加载中请耐心等待 . . . . .',
                pagination:true,
                rownumbers:true,
                ctrlSelect:true,
                pageNumber:1,
                pageSize:10,
                pageList:[10,20,30],
                checkOnSelect:true,
                queryParams:{},
                fitColumns:true,
                fit:true,
                columns:[[
                    {
                        title:"主键ID",field:"id",checkbox:true
                    }, {
                        title:"角色名",field:"rolename",align:'center',width:80,
                        editor:{
                            type:'textbox',
                            options:{
                                required:true,
                                editable:false
                                // readonly:true
                            }
                        }
                    },{
                        title:"操作", field:'operate1',align:'center',width:180,
                        formatter:function (value,row,index) {
                            if (row.id==undefined)
                                return '<strong><暂不可操作></strong>';
                            return "<a class='editcls' href='javascript:editModules(\""+row.id+"\",\""+row.rolename+"\")'>配置权限</a>";
                        }

                    }
                ]],

                toolbar:[
                    {
                        text:'添加角色',
                        iconCls:'icon-add',
                        handler:function () {
                            //添加时先判断是否有开启编辑的行，如果有则把开户编辑的那行回滚编辑
                            if(editRow!=undefined){
                                $("#role-list").datagrid("rejectChanges");
                                editRow = undefined;

                            }
                            //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
                            if(editRow==undefined){
                                $("#role-list").datagrid("insertRow",{
                                    index:0,
                                    row:{

                                    }
                                });
                                //将新插入的那一行开户编辑状态
                                $("#role-list").datagrid("beginEdit",0);
                                //给当前编辑的行赋值
                                editRow = 0;
                                var dx = $('#role-list').datagrid('getEditor', { index: 0, field: 'rolename' });
                                $(dx.target).textbox({editable:true});


                            }
                        }
                    },{
                        text:'删除',
                        iconCls:'icon-remove',
                        handler:function () {
                            //删除时先获取选择行
                            var rows = $("#role-list").datagrid("getSelections");
                            if(rows.length==0){
                                $.messager.show({
                                    title:'提交',
                                    msg:'请至少选择一条记录！',
                                    timeout:5000,
                                    showType:'slide'
                                });
                                return;
                            }
                            //选择要删除的行
                            if(rows.length>0){
                                $.messager.confirm("提示","你确定要删除吗？",function (sure) {
                                    if(sure){
                                        var ids = $.map(rows,function (row) {
                                            return row.id;
                                        });


                                        //发送请求删除
                                        $.ajax({
                                            url:contextPath+'/role/remove',
                                            data:JSON.stringify(ids),
                                            contentType:'application/json',
                                            dataType:"json",
                                            type:'post',
                                            success:function (data) {
                                                $.messager.show({
                                                    title:'提示',
                                                    msg:data.msg,
                                                    timeout:5000,
                                                    showType:'slide'
                                                });
                                                $("#role-list").datagrid("reload");
                                                /*return;*/
                                            },
                                            error:function (err) {
                                                $.messager.alert({
                                                    title: '警告',
                                                    msg: "<strong>您没有<权限>!请联系管理员</strong>",
                                                    //timeout:5000,
                                                    icon: 'error',
                                                    showType: 'slide'
                                                });
                                                $("#role-list").datagrid("reload");
                                            }

                                        });
                                        /* $.messager.alert({
                                         title:'警告',
                                         msg:"您没有权限",
                                         timeout:5000,
                                         icon:'error',
                                         showType:'slide'
                                         });
                                         $("#user-list").datagrid("rejectChanges");
                                         editRow = undefined;*/
                                    }
                                })
                            }
                        }
                    },{
                        text:'修改',
                        iconCls:'icon-edit',
                        handler:function () {
                            //修改时要获取选择到的行
                            var rows = $("#role-list").datagrid("getSelections");
                            if(rows.length>1){
                                $.messager.alert({
                                    title:'提交',
                                    msg:'至多选择一条记录！',
                                    timeout:5000,
                                    showType:'slide'
                                });
                                return;
                            }
                            if(rows.length==0){
                                $.messager.alert({
                                    title:'提交',
                                    msg:'请选择选择一条记录进行修改！',
                                    timeout:5000,
                                    showType:'slide'
                                });
                                return;
                            }
                            if(editRow!=undefined){
                                $.messager.alert({
                                    title:'提示',
                                    msg:'编辑已取消！',


                                });
                                $("#role-list").datagrid("rejectChanges");
                                editRow = undefined;

                            }
                            let index = $("#user-list").datagrid("getRowIndex",rows[0]);
                            editRow = index;

                            $("#role-list").datagrid("beginEdit",index);




                        }



                    },{
                        text:'保存',
                        iconCls:'icon-save',
                        handler:function () {
                            if(editRow!=undefined)
                                $("#role-list").datagrid("endEdit",editRow);//会触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台

                        }

                    },{
                        text:'取消编辑',
                        iconCls:'icon-redo',
                        handler:function () {
                            if(editRow!=undefined)
                                $("#role-list").datagrid("rejectChanges");
                            editRow = undefined;
                        }
                    }
                ],
                onAfterEdit:function (index, row, changes) {

                    $.ajax({
                        url:contextPath + '/role/add',
                        data:{"id":'',"rolename":row.rolename},
                        dataType:"json",
                        type:'post',
                        success:function (msg) {
                            if(msg.code==200){
                                $.messager.alert({
                                    title:'提示',
                                    msg: msg.msg,
                                    timeout:5000,
                                    icon:'info',
                                    showType:'slide'
                                });
                                editRow = undefined;

                                $("#role-list").datagrid("reload");
                            }else{
                                $.messager.alert({
                                    title:'警告',
                                    msg:msg.msg,
                                    timeout:5000,
                                    icon:'error',
                                    showType:'slide'
                                });
                                editRow = undefined;

                                $("#role-list").datagrid("rejectChanges");

                            }
                            /* return;*/
                            editRow = undefined;
                            /*return;*/
                        },
                        error:function (err) {
                            $.messager.alert({
                                title: '警告',
                                msg: "<strong>您没有<权限>!请联系管理员</strong>",
                                //timeout:5000,
                                icon: 'error',
                                showType: 'slide'
                            });
                            $("#role-list").datagrid("rejectChanges");
                            editRow = undefined;
                            //$("#role-list").datagrid("reload");
                        }


                    });





                    //if(row.createtime==null||row.id==''){
                    /*$.post(contextPath + '/role/add',{"id":'',"rolename":row.rolename}, function (msg) {
                        //console.log(msg.msg);
                        if(msg.code==200){
                            $.messager.alert({
                                title:'提示',
                                msg: msg.msg,
                                timeout:5000,
                                icon:'info',
                                showType:'slide'
                            });
                            editRow = undefined;

                            $("#role-list").datagrid("reload");
                        }else{
                            $.messager.alert({
                                title:'警告',
                                msg:msg.msg,
                                timeout:5000,
                                icon:'error',
                                showType:'slide'
                            });
                            editRow = undefined;

                            $("#role-list").datagrid("rejectChanges");

                        }
                        /!* return;*!/
                        editRow = undefined;
                    }, "json");*/

                },
                onDblClickRow: function (rowIndex, rowData) {
                    // editRow = undefined;
                    //

                    if (editRow != undefined) {

                        $.messager.alert("提示！","修改数据未保存！");
                        return;
                    }
                    if (editRow == undefined) {

                        $('#role-list').datagrid('beginEdit', rowIndex);

                        editRow = rowIndex;
                        index = editRow;
                        console.log(editRow);



                    }
                },
                onLoadSuccess:function (data) {
                    console.log(data);
                    $('.editcls').linkbutton({plain:true,iconCls:'icon-add'});


                }
            });



        });



    </script>

    </body>

</html>

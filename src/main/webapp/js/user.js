
//setInterval("startRequest()",30000);

function startRequest(){
    $("#user-list").datagrid("reload");//需要定时重新加载的函数。
};

function editRoles(id,status,name) {
   // alert(id+"  "+name);
    $('#user-editroles-dialog').dialog({
        title: '用户详情',
        width: 500,
        height: 300,
        closed: false,
        cache: false,
        href: contextPath+'/pages/user/userRolesDialog.jsp?id='+id+"&status="+status+"&uname="+name,
        modal: true
    });

}



$(function () {
    $.extend($.fn.validatebox.defaults.rules, {
        et: {
            validator: function(value){
                let reg = /^A\d{3}$/;
                return reg.test(value);
            },
            message: '该用户名已被占用！'
        }
    });





    let editRow = undefined;

    /*页面加载即初始化表格*/
    $("#user-list").datagrid({
        url:contextPath+"/user/query",
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
                title:"用户名",field:"username",align:'center',width:80,
                editor:{
                    type:'textbox',
                    options:{
                        required:true,
                        editable:false
                         // readonly:true
                    }
                }
            }, {
                title:"用户密码",field:"password",align:'center',width:80,
                editor:{
                    type:'passwordbox',
                    options:{
                        required:true
                    }
                }
            }, {
                title:"使用状态",field:"status",align:'center',width:90,
                formatter:function (value,row,index) {
                        if(value =='1')
                            return "<span style='color: green'>启用</span>";
                        if(value =='0')
                            return "<span style='color: red'>已停用</span>";
                       /* var str = "<input class = 'easyui-switchbutton' data-options ='onText:\'启用\',offText:\'禁用\''/>";
                        var sid = "sbutton"+ row.id;
                        return '<input id="'+sid+'" '+'style="width:70px;height:25px">';*/

                },editor:{
                    type:'combobox',
                    options:{
                        valueField:'value',
                        textField:'text',
                        data:[{
                            value:'0',
                            text:'停用'
                        },{
                            value:'1',
                            text:'启用'
                        }]
                    }
                }

            }, {
                title:"创建时间",field:"createtime",align:'center',width:180,
                editor:{
                    type:'datetimebox',
                        options:{
                        editable:false,
                        readonly:true
                    }}
            }, {
                title:"操作", field:'operate',align:'center',width:180,
                formatter:function (value,row,index) {
                    if (row.id==undefined)
                        return '<strong>暂不可用</strong>';
                   // return '';
                    return "<a class='editcls' href='javascript:editRoles(\""+row.id+"\",\""+row.status+"\",\""+row.username+"\")'>配置角色</a>";
                }

            }
        ]],

        view: detailview,
        detailFormatter: function(rowIndex, rowData){
            return '<table><tr>' +
                '<td rowspan=2 style="border:0"><img src="'+contextPath +'/images/'+ rowData.id + '.png" style="height:50px;"></td>' +
                '<td style="border:0">' +
                '<p>用户名: ' + rowData.username + '</p>' +
                '<p>使用状态: ' + rowData.status + '</p>' +
                '</td>' +
                '</tr></table>';
        },


        toolbar:[
            {
                text:'添加',
                iconCls:'icon-add',
                handler:function () {
                    //添加时先判断是否有开启编辑的行，如果有则把开户编辑的那行回滚编辑
                    if(editRow!=undefined){
                        $("#user-list").datagrid("rejectChanges");
                        editRow = undefined;

                    }
                    //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
                    if(editRow==undefined){
                        $("#user-list").datagrid("insertRow",{
                            index:0,
                            row:{

                            }
                        });
                        //将新插入的那一行开户编辑状态
                        $("#user-list").datagrid("beginEdit",0);
                        //给当前编辑的行赋值
                        editRow = 0;
                        var dx = $('#user-list').datagrid('getEditor', { index: 0, field: 'username' });
                        $(dx.target).textbox({editable:true});


                    }
                }
            },{
                text:'删除',
                iconCls:'icon-remove',
                handler:function () {
                    //删除时先获取选择行
                    var rows = $("#user-list").datagrid("getSelections");
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
                                    url:contextPath+'/user/remove',
                                    data:JSON.stringify(ids),
                                    contentType:'application/json',
                                    dataType:"json",
                                    type:'post',
                                    success:function (data) {
                                        $.messager.show({
                                            title:'提交',
                                            msg:data.msg,
                                            timeout:5000,
                                            showType:'slide'
                                        });
                                        $("#user-list").datagrid("reload");
                                            /*return;*/
                                    },
                                    error:function (err) {
                                        $.messager.alert({
                                            title:'警告',
                                            msg:"<strong>您没有<权限>!请联系管理员</strong>",
                                            //timeout:5000,
                                            icon:'error',
                                            showType:'slide'
                                        });
                                        $("#user-list").datagrid("reload");
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
                    var rows = $("#user-list").datagrid("getSelections");
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
                        $("#user-list").datagrid("rejectChanges");
                        editRow = undefined;

                    }
                    let index = $("#user-list").datagrid("getRowIndex",rows[0]);
                    editRow = index;

                    $("#user-list").datagrid("beginEdit",index);




                }



            },{
                text:'保存',
                iconCls:'icon-save',
                handler:function () {
                    if(editRow!=undefined)
                        $("#user-list").datagrid("endEdit",editRow);//会触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台

                }

            },{
                text:'取消编辑',
                iconCls:'icon-redo',
                handler:function () {
                    if(editRow!=undefined)
                        $("#user-list").datagrid("rejectChanges");
                    editRow = undefined;
                }
            }
        ],
        onAfterEdit:function (index, row, changes) {
            if(row.password==null || row.password == undefined) {
                if (editRow != undefined) {

                $("#user-list").datagrid("rejectChanges");
                    editRow == undefined;
                }

                $.messager.alert({
                    title:'提示',
                    msg: '密码不能为空',
                    timeout:5000,
                    icon:'info',
                    showType:'slide'
                });
            }
            if(row.status == null ||row.status == undefined) {
                row.status = 1;
            }
            $.ajax({
                url: contextPath + '/user/addOrUpdate',
                data: {"id":row.id,"username":row.username,"password":row.password,"status":row.status,"createtime":row.createtime},
                type: 'post',
                dataType:"json",
                success: function (msg) {
                    if(msg.code==200){
                        $.messager.alert({
                            title:'提示',
                            msg: msg.msg,
                            timeout:5000,
                            icon:'info',
                            showType:'slide'
                        });
                        editRow = undefined;

                        $("#user-list").datagrid("reload");
                    }else if(msg.code==300){
                        $.messager.alert({
                            title:'警告',
                            msg:msg.msg,
                            icon:'error',
                            showType:'slide',
                            fn:function () {
                                parent.window.closeWebSocket();
                                //location.replace(contextPath+"/login/logout");
                               // var win =  window.open(contextPath+"/login/logout","_top");
                               // win.location.replace(contextPath+"/");
                            }
                        });
                        editRow = undefined;
                    }else{
                        $.messager.alert({
                            title:'警告',
                            msg:msg.msg,
                            timeout:5000,
                            icon:'error',
                            showType:'slide'
                        });
                        editRow = undefined;

                        $("#user-list").datagrid("rejectChanges");

                    }
                    /* return;*/
                    editRow = undefined;
                },
                error:function (err) {
                    $.messager.alert({
                        title: '警告',
                        msg: "<strong>您没有<权限>!请联系管理员</strong>",
                        //timeout:5000,
                        icon: 'error',
                        showType: 'slide'
                    });
                    $("#user-list").datagrid("reload");
                }
            });







        //if(row.createtime==null||row.id==''){
            /*$.post(contextPath + '/user/addOrUpdate',{"id":row.id,"username":row.username,"password":row.password,"status":row.status,"createtime":row.createtime}, function (msg) {
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

                    $("#user-list").datagrid("reload");
                }else if(msg.code==300){
                    $.messager.alert({
                        title:'警告',
                        msg:msg.msg,
                        icon:'error',
                        showType:'slide',
                        fn:function () {
                            //location.replace(contextPath+"/login/logout");
                           var win =  window.open(contextPath+"/login/logout","_top");
                            win.location.replace(contextPath+"/");
                        }
                    });
                    editRow = undefined;
                }else{
                    $.messager.alert({
                        title:'警告',
                        msg:msg.msg,
                        timeout:5000,
                        icon:'error',
                        showType:'slide'
                    });
                    editRow = undefined;

                        $("#user-list").datagrid("rejectChanges");

                }
               /!* return;*!/
                editRow = undefined;
            }, "json");*/
            /*$.messager.alert({
                title:'警告',
                msg:"您没有权限",
                timeout:5000,
                icon:'error',
                showType:'slide'
            });
            $("#user-list").datagrid("rejectChanges");
            editRow = undefined;*/
    },
    onDblClickRow: function (rowIndex, rowData) {
        // editRow = undefined;
        //

        if (editRow != undefined) {

            $.messager.alert("提示！","修改数据未保存！");
            return;
        }
        if (editRow == undefined) {

            $('#user-list').datagrid('beginEdit', rowIndex);

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



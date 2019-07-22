$(function () {
    /*页面加载即初始化表格*/
    $("#emp-list").datagrid({
        url:contextPath+"/emp/query",
        method:'post',
        striped:true,
        loadMsg:'数据加载中请不要等待',
        pagination:true,
        rownumbers:true,
        ctrlSelect:true,
        pageNumber:1,
        pageSize:3,
        pageList:[3,4,5],
        checkOnSelect:true,
        queryParams:{},
        fitColumns:true,
        fit:true,
        columns:[[
            {
                title:"主键ID",field:"id",checkbox:true
            }, {
                title:"员工姓名",field:"ename",align:'center',width:80
            }, {
                title:"员工编号",field:"eno",align:'center',width:80
            }, {
                title:"员工职位",field:"job",align:'center',width:90,
                formatter:function (value,row,index) {
                        if(value=='gcs')
                            return "<span style='color: orangered'>工程师</span>";
                        if(value=='jxzj')
                            return "教学总监";
                        if(value=='xzzl')
                            return "行政助理";
                }
            }, {
                title:"员工薪资",field:"salary",align:'center',width:80
            }, {
                title:"员工部门",field:"dname",align:'center',width:80
            }, {
                title:"入职时间",field:"hiredate",align:'center',width:180
            }
        ]],
        toolbar:'#emp-list-toolbar'
    });

});

var eventObj = {
    query:function () {
        $("#emp-dialog").dialog({

            title: '员工查询',
            width: 400,
            height: 320,
            closed: false,
            cache: false,
            href:  contextPath+'/pages/emp/query.jsp',
            modal: true,
            buttons:[{
                text:'查询',
                iconCls:'icon-search',
                handler:function () {
                    $("#emp-list").datagrid("load",{//load 重新出发datagrid
                        ename:$("#ename").textbox("getValue"),
                        did:$("#did").combobox("getValue"),
                        startTime:$("#startTime").datebox("getValue"),
                        endTime:$("#endTime").datebox("getValue")
                    });
                    $("#emp-dialog").dialog("close");
                    $("#emp-dialog").dialog("clear");
                }
            }
                
                
            ]

        });
    },
    add:function () {
        $("#emp-dialog").dialog({

            title: '新增员工',
            width: 560,
            height: 600,
            closed: false,
            cache: false,
            href:  contextPath+'/pages/emp/addAndUpdate.jsp',
            modal: true,
            buttons:[{
                text:'新增',
                iconCls:'icon-ok',
                handler:function () {
                    $("#emp-form").form('submit',{
                        url:contextPath+'/emp/add',
                        onSubmit:function (p) {
                            var isValid = $(this).form('validate');
                            if (!isValid){
                                $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                            }

                            p.etoak = 'et1807';//提交额外的参数
                            //$("#emp-form").form("validate")
                            return isValid;
                        },
                        success:function (result) {
                            let data = $.parseJSON(result);
                            if(data.code==200){

                                $.messager.progress('close');
                                $("#register-dialog").dialog("close");//关闭窗口
                                $("#register-dialog").dialog("clear");//清空信息
                                $("#emp-list").datagrid("reload");
                            }
                            $.messager.progress('close');
                            $.messager.show({
                                title:'提交',
                                msg:data.msg,
                                timeout:5000,
                                showType:'slide'
                            });

                        }
                    });
                    $("#emp-dialog").dialog("close");
                    $("#emp-dialog").dialog("clear");
                    /*$("#emp-list").datagrid("load",{//load 重新出发datagrid
                        ename:$("#ename").textbox("getValue"),
                        did:$("#did").combobox("getValue"),
                        startTime:$("#startTime").datebox("getValue"),
                        endTime:$("#endTime").datebox("getValue")
                    });*/
                    $("#emp-list").datagrid("reload");

                }
            }


            ]

        });
    },
    update:function () {
        let rows = $("#emp-list").datagrid("getSelections");
        if(rows.length != 1){
            $.messager.show({
                title:'提交',
                msg:'请选择一条记录！',
                timeout:5000,
                showType:'slide'
            });
            return;
        }
        var row = rows[0];
        $("#emp-dialog").dialog({

            title: '员工更新',
            width: 400,
            height: 380,
            closed: false,
            href: contextPath+'/emp/empAddAndUpdate?id='+row.id,
            modal: true,
            buttons:[{
                text:'保存',
                iconCls:'icon-save',
                handler:function () {
                    $("#emp-form").form('submit',{
                       url:contextPath+'/emp/update',
                        onSubmit:function (p) {
                            p.id = row.id;
                            return $(this).form("validate");
                        },
                        success:function (result) {
                            let data = $.parseJSON(result);
                            if(data.code==200){
                                $("#emp-dialog").dialog("close");
                                $("#emp-dialog").dialog("clear");
                                $("#emp-list").datagrid("reload");
                            }
                            $.messager.progress('close');
                            $.messager.show({
                                title:'提交',
                                msg:data.msg,
                                timeout:5000,
                                showType:'slide'});
                        }
                    });

                }
            }


            ]

        });


    },
    remove:function () {
        let rows = $("#emp-list").datagrid("getSelections");
        console.log(rows);
        if(rows.length == 0){
            $.messager.show({
                title:'提交',
                msg:'请至少选择一条记录！',
                timeout:5000,
                showType:'slide'
            });
            return;
        }



        $.messager.confirm('确认','您确认想要删除记录吗？',function(sure){
            if (sure){
               let ids = $.map(rows,function (row) {
                   return row.id;
               });
              /* $.post(contextPath+'/emp/remove',JSON.stringify(ids),function (msg) {

               },"json");*/
              $.ajax({
                  url:contextPath+'/emp/remove',
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
                      $("#emp-list").datagrid("reload");

                  }
              });
            }
        });
    }
}
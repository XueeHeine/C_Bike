function logout() {

    location.replace(contextPath+"/login/logout");

}

/*setInterval("startRequest()",2000);

function startRequest(){
    var ss = ${USER_SESSION.username};

    console.log(ss);
    if(ss == '')
        logout()
};*/


function changepwd() {
    $("#dd").dialog({
        title: '修改密码',
        width: 400,
        height: 330,
        closed: false,
        href: contextPath+'/pages/emp/changepwd.jsp',
        modal: true,
        buttons:[{
            text:'确认修改',
            handler:function(){
                $("#pwdform").form('submit',{
                    url:contextPath+'/emp/changepwd',
                    success:function(result){
                        let data = $.parseJSON(result);
                        if(data.code==200){

                            $.messager.progress('close');
                            $("#dd").dialog("close");//关闭窗口
                            $("#dd").dialog("clear");//清空信息
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
            }
        },{
            text:'关闭',
            handler:function(){
                $("#dd").dialog("close");//关闭窗口
                $("#dd").dialog("clear");//清空信息
            }
        }]
    });
}

function tabs(option) {
    let tabs = $("#content");
    var exists = tabs.tabs("exists",option.title);
    if(exists){
        tabs.tabs("select",option.title);
        var tab = $(tabs).tabs("getTab",option.title);
        tabs.tabs("update",{
           tab:tab,
            options:{
                title:option.title,
                content:option.content,
                closable:true
            }
        });
    }else{
        tabs.tabs("add",{
            title:option.title,
            content:option.content,
            closable:true
        })
    }
}

$(function () {
    $("a.opts").click(function () {
        let url = $(this).attr("url");
        if(null != url && url != undefined && url != ""){
           // let tabs = $("#content");
            var option={
                "content":'<iframe src="'+contextPath+url+'" frameborder="0" height="100%" width="100%" scrolling="auto"></iframe>',
                "title":$(this).text()
            }
            tabs(option);
        }
        $('#tt').tabs('add',{
            title:'New Tab',
            content:'Tab Body',
            closable:true,
            tools:[{
                iconCls:'icon-mini-refresh',
                handler:function(){
                    alert('refresh');
                }
            }]
        });
    });
});
$(function () {

    $.extend($.fn.validatebox.defaults.rules, {
        yanzhengma: {
            validator: function(value){

                //return value.toUpperCase() == code.toUpperCase();
                return true;
            },
            message: '验证码填写错误！'
        }
    });

    createCode();





    $("#loginin-dialog").dialog({
        title: '登录',
        width: 500,
        height:340,
        closed: false,
        closable:false,
        draggable:false,
        buttons:[{
            text:'注册',
            iconCls:'icon-add',
            handler:function(){
                /*点击进入注册窗口*/
                $("#register-dialog").dialog({
                    title: '注册',
                    width: 560,
                    height: 600,
                    closed: false,
                    cache: false,
                    href: contextPath+'/emp/empAddAndUpdate',
                    modal: true,
                    buttons:[{
                        text:'注册',
                        iconCls:'icon-ok',
                        handler:function () {
                            $.messager.progress();
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
                                    }
                                    $.messager.progress('close');
                                    $.messager.show({
                                        title:'提示',
                                        icon:'info',
                                        msg:data.msg,
                                        timeout:5000,
                                        showType:'slide'
                                    });

                                }
                            });
                        }

                    }]
                });
            }
        },{
            text:'登录',
            iconCls:'icon-ok',
            handler:function(){
               $("#login-form").form('submit',{
                   url:contextPath+'/login/login',
                   onSubmit:function (p) {
                       return $(this).form("validate");
                   },
                   success:function (result) {
                       let data = $.parseJSON(result);
                       if(data.code==200){
                           //alert("成功");


                           var uName =$('#username').textbox("getValue");
                           var psw = $('#password').textbox("getValue");
                           console.log(uName);
                           console.log(psw);
                           if($('#remember').prop('checked') == true){//保存密码
                               $.cookie('username',uName, {expires:7,path:'/'});
                               $.cookie('password',psw, {expires:7,path:'/'});
                           }else{//删除cookie
                               $.cookie('username', '', { expires: -1, path: '/' });
                               $.cookie('password', '', { expires: -1, path: '/' });
                           }



                           window.location.href= contextPath+'/login/index';
                           //window.open()打开新的选项卡window.close   全是get请求
                           //window.replace(url)//重定向 跳转后不能后退的  没有历史记录

                       }else {
                           $.messager.alert({
                               title: '警告',
                               icon:'warning',
                               msg: data.msg,
                               timeout: 5000,
                               showType: 'slide'
                           });
                       }
                   }
               });
            }
        }]

    });

    $("#username").textbox({
        width:180,
        required:true,
        iconCls:'icon-man',
        missingMessage:'请输入用户名'

    });
    $("#password").passwordbox({
        width:180,
        required:true,
        showEye: true,
        missingMessage:'请输入密码'

    });

    var username = $.cookie('username');
    var password = $.cookie('password');
    console.log(username);
    console.log(password);
    //将获取的值填充入输入框中
    $('#username').textbox("setValue",username);
    $('#password').textbox("setValue",password);
    if(username != null && username != '' && password != null && password != ''){//选中保存秘密的复选框
        $("#remember").attr('checked',true);
    }

});

var code;/*定义一个验证码的全局变量*/
function createCode(){/* 生成验证码的函数 */
    code="";
    var codeLength=4;
    var checkCode=document.getElementById("checkNode");
    checkCode.value="";
    var selectChar=new Array(1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');
    for(var i=0;i<codeLength;i++){
        var charIndex=Math.floor(Math.random()*61);
        code+= selectChar[charIndex];
    }
    if (code.length != codeLength) {
        createCode();
    }
    checkCode.value=code;
}

function validate() {/* 检验输入的验证码的正确性 */
    var inputCode = document.getElementById("input1").value.toUpperCase();
    var codeToUp=code.toUpperCase();
    if(inputCode.length<=0){
       // alert("请输入验证码！");
        $.messager.alert('提示','请填写验证码！');
        return false;
    }
    else if(inputCode != codeToUp){
       // alert("验证码输入错误！");
        $.messager.alert('警告','验证码输入错误！');
        createCode();
        return false;
    }
    else{
        //alert("输入正确，输入的验证码为："+inputCode);
        $.messager.alert('提示','验证码填写无误！');
        return true;
    }
}


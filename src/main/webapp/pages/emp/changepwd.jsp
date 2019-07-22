<%--
  Created by IntelliJ IDEA.
  User: Alpha-LGC
  Date: 2018/11/8
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body>
    <div>
        <form action="" method="post" id="pwdform">
            <table style="margin: 30px auto" cellpadding="10px" cellspacing="10px">
                <tr>
                    <td>原密码:</td>
                    <td><input  name="oldpwd" class="easyui-passwordbox" data-options="width:180,required:true"></td>
                </tr>
                <tr>
                    <td>新密码:</td>
                    <td><input  id="newpwd" name="newpwd" class="easyui-passwordbox"  data-options="required:true,width:180"/></td>
                </tr>
                <tr>
                    <td>确认密码:</td>
                    <td><input  id="rpwd"  class="easyui-passwordbox" validType="equals['#newpwd']"  data-options="required:true,width:180"/></td>
                </tr>
            </table>

        </form>

    </div>
    <script>
        $(function () {
            $.extend($.fn.validatebox.defaults.rules, {
                equals: {
                    validator: function(value,param){
                        return value == $(param[0]).val();
                    },
                    message: '密码前后输入不一致.'
                }
            });
        });
    </script>
</body>

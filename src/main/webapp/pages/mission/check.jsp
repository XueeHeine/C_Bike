<%--
  Created by IntelliJ IDEA.
  User: Alpha-LGC
  Date: 2018/11/7
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <body>
        <form id="emp-form" method="post">
            <%--<input type="hidden" name="id" value="${e.id}"/>--%>
            <table style="margin: 30px auto" cellspacing="10px" cellpadding="5px">
                <tr>
                    <td>员工姓名:</td>
                    <td>
                        <input type="text" class="easyui-textbox" name="ename" value="${e.ename}" data-options="required:true,width:180"/>
                    </td>
                </tr>
                <tr>
                    <td>员工编号:</td>
                    <td>
                        <c:choose>
                            <c:when test="${empty e}">
                                <input type="text" class="easyui-textbox" name="eno" value="${e.eno}" data-options="required:true,width:180,validType:'et'"/>
                            </c:when>
                            <c:otherwise>
                                <input type="text" class="easyui-textbox" name="eno" value="${e.eno}" data-options="required:true,width:180,validType:'et',disabled:true"/>
                            </c:otherwise>
                        </c:choose>

                    </td>
                </tr>
                <c:if test="${empty e}">
                    <tr>
                        <td>员工密码:</td>
                        <td>
                            <input type="password" class="easyui-textbox" name="password"  data-options =" required:true,width:180"/>
                        </td>
                    </tr>

                </c:if>

                <tr>
                    <td>员工职位:</td>
                    <td>
                        <input type="text" class="easyui-combobox" name="job" value="${e.job}" data-options="required:true,width:180,
                            valueField: 'label',
                            textField: 'value',
                            data: [{
                                label: 'gcs',
                                value: '工程师'
                            },{
                                label: 'xzzl',
                                value: '行政助理'
                            },{
                                label: 'jxzj',
                                value: '教学总监'
                            }]"/>
                    </td>
                </tr>
                <tr>
                    <td>员工薪资:</td>
                    <td>
                        <c:choose>
                            <c:when test="${empty e}">
                                <input type="text" class="easyui-numberspinner" name="salary" value="${e.salary}" data-options="min:0,precision:2,required:true,width:180,prefix:'￥'"/>
                            </c:when>
                            <c:otherwise>
                                <input type="text" id="salary" class="easyui-numberspinner" name="salary" value="${e.salary}" data-options="min:0,precision:2,required:true,width:180,prefix:'￥',increment:100"/>
                            </c:otherwise>
                        </c:choose>

                    </td>
                </tr>
                <tr>
                    <td>员工部门:</td>
                    <td>
                        <input type="text"  id="cc" class="easyui-combotree" name="did" value="${e.did}" data-options="required:true,width:180,
                            url:'${pageContext.request.contextPath}/dept/getDept',method:'post',animate:true,lines:true,onLoadSuccess:function(node,data){
                                $('#cc').combotree('setValue',${e.did});
                                $('#cc').combotree('setText','${e.dname}');
                            }"/>
                    </td>
                </tr>


            </table>
        
        
        </form>

    <script>
        $(function () {
            $.extend($.fn.validatebox.defaults.rules, {
                et: {
                    validator: function(value){
                        let reg = /^e\d{3}$/;
                        return reg.test(value);
                    },
                    message: '编号必须以e开头加并且包含3位有效数字！'
                }
            });
        });
        function editsal() {
           // console.log($("#salary").numberspinner("getValue"));
            console.log($("#salary").val());
        }


    </script>
    </body>


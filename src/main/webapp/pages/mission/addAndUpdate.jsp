<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/easyui-1.5/themes/material/easyui.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/easyui-1.5/themes/icon.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.5/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.5/datagrid-detailview.js"></script>
    <script type="text/javascript">var contextPath = '${pageContext.request.contextPath}';</script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/add.js"></script>
    <style type="text/css">
        #mis-form{
            margin:32px auto;
            width:480px;
        }
        .input_container{
            margin-bottom:32px;
        }
    </style>
</head>
<body>
<form id="mis-form" method="post" action="/C_Bike/mission/add">
    <input type="hidden" name="mid" value="${b.id }"/>
    <h1>新增任务：</h1>
    <div class="input_container">
        <div>任务名称</div>
        <div>
            <input value="${b.sname }" name="mname" class="easyui-textbox" data-options="width:180,required:true,validType:'length'"/>
        </div>
    </div>
    <div class="input_container">
        <div>任务描述</div>
        <div>
            <input value="${b.address }" name="mdetails" class="easyui-textbox" data-options="width:180" style="width:100%;height:120px"/>
        </div>
    </div>
        <div class="input_container">
            <div>任务紧急情况</div>
            <div>
                <input  name="qzhj" class="easyui-combobox" data-options="editable:false,width:180,
					valueField: 'label',
					textField: 'value',
					data: [{
						label: '1',
						value: '一般'
					},{
						label: '2',
						value: '紧急'
					}]"/>
            </div>
        </div>
        <div class="input_container">
            <div>任务状态</div>
            <div>
                <input value="未领取" name="mstatus" class="easyui-combobox" data-options="editable:false,width:180,
					valueField: 'label',
					textField: 'value',
					data: [{
						label: '0',
						value: '未领取'
					}]"/>
            </div>
        </div>
<%--    <div class="input_container">
        <div>联系电话</div>
        <div>
            <input value="${b.address }" name="mphone" class="easyui-textbox" data-options="width:180" style="width:100%;height:120px"/>
        </div>
    </div>--%>
        <div class="input_container">
            <div >
                <input type="submit" value="新增" name="address" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px"/>
            </div>
        </div>
</form>
</body>

</html>
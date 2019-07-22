<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<%@include file="/easyui/include.jsp" %>
	</head>
	<body>
		<input class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:300px">
		<input type="text" class="easyui-textbox" data-options="width:300,height:50,prompt:'请输入信息！',type:'password',editable:true,disable:false,readonly:false,iconCls:'icon-ok',
			iconAlign:'left',iconWidth:20,buttonText:'按钮啊',buttonIcon:'icon-edit',onChange:function(newValue, oldValue){console.log(newValue)},onClickButton:function(){ console.log($(this).textbox('getValue'))  }"/>
			<br />
		<input type="text" id="tb" style="width:300px"/>
				<br />
				
				
		<input id="pwd" name="pwd" type="password" class="easyui-textbox" data-options="required:true" />   
		<input id="rpwd" name="rpwd" type="password" class="easyui-textbox" required="required" validType="equals['#pwd']" />  
				<br />
		<div id="p"></div>
		<div id="tab" class="easyui-tabs">
			<div title="t1" ></div>
			<div title="t2"></div>
		</div>
		<select id="cc" class="easyui-combobox" name="dept" style="width:200px;">   
		    <option value="aa">aitem1</option>   
		    <option>bitem2</option>   
		    <option>bitem3</option>   
		    <option>ditem4</option>   
		    <option>eitem5</option>   
		</select>  
		<br />	
		<input type="text" id="dt" />
		
		<input type="text" id="nn"/>
		<script type="text/javascript">


		
			$(function(){

				$("#dt").datetimebox({
					required:true,
					showSecond:false,
					timeSeparator:':'
				});
				
				$('#nn').numberbox({    
				    min:0,    
				    precision:2    
				});  
								

				
				$("#tab").tabs('add',{
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
				
				$("#tab").tabs({
					border:false,
					onSelect:function(title){
						alert(title+'is selected');
					},
					tools:[{
						iconCls:'icon-add',
						handler:function(){
							alert('添加')
						}
					},{
						iconCls:'icon-save',
						handler:function(){
							alert('保存')
						}
					}]
									
				});


				
				$("#p").panel({
					width:500,
					height:150,
					iconCls:'icon-man',
					collapsible:true,
					minimizable:true,
					maximizable:true,
					closable:true,
					//fit:true,
					title:'我就试一下啊',
					openAnimation:'show',
					tools:[{
						iconCls:'icon-add',
						handler:function(){
							alert("aaaa");
						}},{
						iconCls:'icon-remove',
						handler:function(){
							alert("aaaa");
						}
						}],
					onBeforeClose:function(){
						return false;
						
					}

				});

				function confirm(){
					
				}

				
				//自定义验证！！必须在初始化的时候加载！！！！
				$.extend($.fn.validatebox.defaults.rules, {    
				    etoak: {   //验证名称相当于url email 这种 
				        validator: function(value, param){
					        let regphone = /^13[0-9]{9}$/;//js当中正则表达式前后必须出现/   
					         
				            return value.length >= param[0];    
				        },    
				        message: '请输入至少{0}个字符！！！'   
				    }
				   
				});
				
				$.extend($.fn.validatebox.defaults.rules, {    
					    equals: {    
					        validator: function(value,param){    
					            return value == $(param[0]).val();    
					        },    
					        message: '密码前后输入不一致.'   
					    }    
					}); 
				 
				
				
				 
				$("#tb").textbox({
					required:true,
					missingMessage:'你他妈的空了!!!',
					//validType:'etoak[3]',
					validType:'idCard',
					//invalidMessage:'输邮箱啊！！！',
					//iconCls:'icon-man',
					icons:[{iconCls:'icon-man'},{iconCls:'icon-add'}],
					buttonText:'按钮啊',
					buttonIcon:'icon-edit',
					onClickButton:function(){
						alert("@@@@");
					},
					onClickIcon:function(index){
						alert("！！！"+index);
					}
				});

				$("#tb").textbox('setValue','222');
				var options = $("#tb").textbox("options");
				console.log(options);
				console.log(options.readonly);
			});

		</script>
	</body>
</html>
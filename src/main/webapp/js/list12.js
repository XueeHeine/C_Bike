$(function(){
	$("#emp-list").datagrid({
		toolbar:'#emp-list-toolbar',
		url:contextPath + "/sup/query",
		method: 'post',
		striped:true,
		loadMsg:'正在加载~请稍等。。。',
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize:5,
		fitColumns:true,
		pageList:[5,10,15],
		fit:true,
		columns : [[
		            {title:"主键ID",field:"id",checkbox:true},
		            {title:"供应商名称",field:"sname",align:'center',width:80},
		            {title:"供应商地址",field:"address",align:'center',width:60},
		            {title:"供应商优先级",field:"priority",align:'center',width:60},
		            {title:"操作",field:"startTime",align:'center',width:100,
		            	formatter:function(value,row,index){
		            		return "<a href='javascript:void(0)' onclick='eventObj.update("+row.id+")'>修改</a>" +
		            				"|<a href='javascript:void(0)' onclick='eventObj.remove1("+row.id+")'>删除</a>";
		            	}}
		            ]]
		
		
	});
});

function jian(){
	var salary = $("#salary").textbox("getValue");
	if(salary!=null&&salary!=0){
		$("#salary").textbox("setValue",salary-100);
	}
}
function jia(){
	var salary = $("#salary").textbox("getValue");
	if(salary!=null&&salary!=0){
		$("#salary").textbox("setValue",parseInt(salary)+parseInt(100));
	}
}
var eventObj = {
		query : function(){
			
			$("#emp-list").datagrid("load",{
    			sname: $("#sname").textbox("getValue"),
                priority : $("#priority").combotree("getValue")
    		});
			
			
		},
		add : function(){
			$("#emp-add1").dialog({
				title: '新增',    
			    width: 550,    
			    height: 350,
			    closed: false,    
			    cache: false,    
			    href: contextPath+'/sup/empAddAndUpdate',
			   /* 遮罩层*/
			    modal: true,
			    buttons : [{
			    	text:'新增',
			    	iconCls:'icon-ok',
			    	handler:function(){
			    		$("#emp-form").form("submit",{
			    			
			    			url:contextPath+'/sup/add',
			    			onSubmit:function(p){

			    				return $("#emp-form").form('validate');
			    			},
			    			success:function(result){
			    				var data = $.parseJSON(result);
			    				if(data.code==200){
			    					$("#emp-list").datagrid("load");
			    					$("#emp-add1").dialog("close");
			    					$("#emp-add1").dialog("clear");
			    					

			    				}
			    				$.messager.show({
		    						title:'提示',
		    						msg:data.msg,
		    						timeout:5000,
		    						showType:'slide'
		    					});
			    			}
			    		});
			    	}
			    }]
			});
		},
		update :function(id){
			
			
			$("#emp-dialog").dialog({
				title: '单车修改',    
			    width: 400,    
			    height: 400,    
			    closed: false,    
			    cache: false,    
			    href: contextPath+'/sup/empAddAndUpdate?id='+id,
			    modal: true ,
			    onClose:function(){
			    	$("#emp-dialog").dialog("clear");
			    }
			    ,
			    buttons:[{
			    	text:'修改',
			    	iconCls:'icon-edit',
			    	handler:function(){
			    		$("#emp-form").form("submit",{
			    			url:contextPath + "/sup/update",
			    			onSubmit :function(p){
			    				//隐藏域传id
			    				return $(this).form("validate");
			    			},
			    			success:function(result){
			    				var data = $.parseJSON(result);
			    				if(data.code==200){
			    					$("#emp-dialog").dialog("close");
			    					$("#emp-dialog").dialog("clear");
			    					$("#emp-list").datagrid("reload");
			    				}
			    				$.messager.show({
			    					title:'提示',
			    					msg:data.msg,
			    					timeout:5000,
			    					showType:'slide'
			    				});
			    			}
			    		})
			    	}
			    }]
			});
		},
		remove : function(){
			var rows = $("#emp-list").datagrid("getSelections");
			if(rows.length == 0){
				$.messager.show({
					title:'提示',
					msg:'请选中至少一条记录',
					timeout:5000,
					showType:'slide'
				});
				return;
			}
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){    
			    	var ids = $.map(rows,function(row){
			    		return row.id;
			    	});
			    	$.ajax({
			    		url:contextPath+'/sup/remove',
			    		contentType: 'application/json',
			    		data:JSON.stringify(ids),
			    		type:'post',
			    		dataType:'json',
			    		success:function(data){
	    					$("#emp-list").datagrid("reload");
	    					$.messager.show({
	    						title:'提示',
	    						msg:data.msg,
	    						timeout:5000,
	    						showType:'slide'
	    					});
			    		}
			    	})
					}
			    });
			

		},
		remove1 : function(id){
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){    
			    	
			    	$.ajax({
			    		url:contextPath+'/sup/remove1?idd='+id,
			    		type:'post',
			    		dataType:'json',
			    		success:function(data){
	    					$("#emp-list").datagrid("reload");
	    					$.messager.show({
	    						title:'提示',
	    						msg:data.msg,
	    						timeout:5000,
	    						showType:'slide'
	    					});
			    		}
			    	})
					}
			    });
		}
}


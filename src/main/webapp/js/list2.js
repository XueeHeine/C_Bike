$(function(){
	$("#emp-list").datagrid({
		toolbar:'#emp-list-toolbar',
		url:contextPath + "/rent/count",
		method: 'post',
		striped:true,
		loadMsg:'正在加载~请稍等。。。',
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize:100,
		fitColumns:true,
		pageList:[100],
		fit:true,
		columns : [[
		            
		            {title:"公路自行车",field:"i1",align:'center',width:80},
		            {title:"山地自行车",field:"i2",align:'center',width:60},
		            {title:"折叠自行车",field:"i3",align:'center',width:60},
		            {title:"旅行自行车",field:"i4",align:'center',width:80},
		            {title:"租借时间",field:"gtime",align:'center',width:80}
		            
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
                ftime: $("#ftime").datebox("getValue"),
                ltime : $("#ltime").datebox("getValue")
            });
			
			
		},
		add : function(){
			$("#emp-add1").dialog({
				title: '新增',    
			    width: 550,    
			    height: 450,    
			    closed: false,    
			    cache: false,    
			    href: contextPath+'/rent/empAddAndUpdate',
			   /* 遮罩层*/
			    modal: true,
			    buttons : [{
			    	text:'新增',
			    	iconCls:'icon-ok',
			    	handler:function(){
			    		$("#emp-form").form("submit",{
			    			
			    			url:contextPath+'/rent/add',
			    			onSubmit:function(p){
			    				p.etoak='et1807';
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
			    href: contextPath+'/bicycle/empAddAndUpdate?id='+id,    
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
			    			url:contextPath + "/bicycle/update",
			    			onSubmit :function(p){
			    				//隐藏域传id
			    				p.eno = $("#eno").html();
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
			    					msg:'更新失败',
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
			    		url:contextPath+'/bicycle/remove',
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
			$.messager.confirm('确认','您确认要还书嘛？',function(r){    
			    if (r){    
			    	
			    	$.ajax({
			    		url:contextPath+'/rent/jia?idd='+id,
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

function myformatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}


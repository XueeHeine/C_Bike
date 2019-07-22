$(function(){
	$("#emp-list").datagrid({
		toolbar:'#emp-list-toolbar',
		url:contextPath + "/rent/query",
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
		            {title:"租赁号",field:"rno",align:'center',width:80},
		            {title:"单车编号",field:"gno",align:'center',width:60},
		            {title:"租赁人",field:"getname",align:'center',width:60},
		            {title:"联系电话",field:"phone",align:'center',width:80},
		            {title:"租借时间",field:"gtime",align:'center',width:80},
		            {title:"归还时间",field:"rtime",align:'center',width:80},
		            {title:"操作",field:"startTime",align:'center',width:100,
		            	formatter:function(value,row,index){
		            		return "<a href='javascript:void(0)' onclick='huanshu("+row.id+")'>归还</a>" ;
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
			$("#emp-dialog").dialog({
				title: '查询页面',    
			    width: 400,    
			    height: 400,    
			    closed: false,    
			    cache: false,    
			    href: contextPath+'/pages/emp/query.jsp',    
			    modal: true ,
			    buttons : [{
			    	text:'查询',
			    	iconCls:'icon-search',
			    	handler:function(){
			    			$("#aaa").form("submit",{
			    			
			    			url:contextPath+'/rent/count',
			    			onSubmit:function(p){
			    				p.etoak='et1807';
			    				return $("#emp-form").form('validate');
			    			},
			    			success:function(result){
			    				var data = $.parseJSON(result);
			    				$("#kuaile").textbox("setValue",data.number);
			    			}
			    		});
			    	}
			    	
			    		}]
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
function huanshu(id) {
    $.ajax({
        url:contextPath+'/rent/profit',
        data:'idd='+id,
        type:'post',
        dataType:'json',
        success:function(data){

            $.messager.confirm('确认','您确认要还车嘛？需支付'+data.money+'元（前两小时1元，之后每增加一小时加1元）',function(r){
                if (r){

                    huanshu1(id,data.money);
                }
            });
        }
    })
}
function huanshu1(id,money){
    $.ajax({
        url:contextPath+'/rent/jia',
        data:'idd='+id+'&money='+money,
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


$(function(){
	$("#mis-list").datagrid({
		toolbar:'#mis-list-toolbar',
		url:contextPath + "/mission/query",
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
		            {title:"主键ID",field:"mid",checkbox:true},
		            {title:"任务名称",field:"mname",align:'center',width:80},
		            {title:"任务详情",field:"mdetails",align:'center',width:60},
		            {title:"任务紧急程度",field:"qzhj",align:'center',width:60,
                        formatter:function(value,row,index){
                            if(value=='1'){
                                return '普通';
                            }else{
                                return '紧急';
                            }
                            return '';
                        }},
			        {title:"任务状态",field:"mstatus",align:'center',width:60,
                        formatter:function(value,row,index){
                            if(value=='0'){
                                return '未领取';
                            }else if(value=='1'){
                                return '修理中';
                            }else if(value=='2'){
                                return '待检验';
                            }else{
                                return '已关闭';
                            }
                            return '';
                        }},
            		{title:"修理员",field:"userName",align:'center',width:60},
            		{title:"创建时间",field:"stime",align:'center',width:60},
            		{title:"完成时间",field:"etime",align:'center',width:60},
		            {title:"操作",field:"startTime",align:'center',width:100,
		            	formatter:function(value,row,index){
		            		return "<a href='javascript:void(0)' onclick='eventObj.update("+row.mid+")'>更换维修员</a>" +
		            				"|<a href='javascript:void(0)' onclick='eventObj.remove1("+row.mid+")'>关闭</a>";
		            	}}
		            ]]
		
		
	});
});


var eventObj = {
		query : function(){
			
			$("#mis-list").datagrid("load",{
    			mname: $("#mname").textbox("getValue"),
                qzhj : $("#qzhj").combotree("getValue"),
                mstatus : $("#mstatus").combotree("getValue")
    		});
		},
		update :function(mid){
			$("#mis-dialog").dialog({
				title: '维修员修改',
			    width: 400,    
			    height: 400,    
			    closed: false,    
			    cache: false,    
			    href: contextPath+'/mission/updateMan?mid='+mid,
			    modal: true ,
			    onClose:function(){
			    	$("#mis-dialog").dialog("clear");
			    }
			    ,
			    buttons:[{
			    	text:'修改',
			    	iconCls:'icon-edit',
			    	handler:function(){
			    		$("#misman-form").form("submit",{
			    			url:contextPath + "/mission/update",
			    			onSubmit :function(p){
			    				//隐藏域传id
			    				return $(this).form("validate");
			    			},
			    			success:function(result){
			    				var data = $.parseJSON(result);
			    				if(data.code==200){
			    					$("#mis-dialog").dialog("close");
			    					$("#mis-dialog").dialog("clear");
			    					$("#mis-list").datagrid("reload");
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
		remove1 : function(mid){
			$.messager.confirm('确认','您确认想要关闭任务吗？',function(r){
			    if (r){    
			    	
			    	$.ajax({
			    		url:contextPath+'/mission/remove1?mid='+mid,
			    		type:'post',
			    		dataType:'json',
			    		success:function(data){
	    					$("#mis-list").datagrid("reload");
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


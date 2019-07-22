
var eventObj = {
		add : function(){
			$("#mis-form").dialog({
				title: '新增',    
			    width: 550,    
			    height: 450,    
			    closed: false,    
			    cache: false,    
			    href: contextPath+'/bicycle/empAddAndUpdate',
			   /* 遮罩层*/
			    modal: true,
			    buttons : [{
			    	text:'新增',
			    	iconCls:'icon-ok',
			    	handler:function(){
			    		$("#emp-form").form("submit",{
			    			
			    			url:contextPath+'/bicycle/add',
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

			    			/*
			    			*
			    			*
			    			*
			    			* */
                            /*,onLoadError:function () {
                                $.messager.show({
                                    title:'提示',
                                    msg:"你么得权限",
                                    timeout:5000,
                                    showType:'slide'
                                });
                            }*/


			    			/**/

			    		});
			    	}
			    }]
			});
		}
}


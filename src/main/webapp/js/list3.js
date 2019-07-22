$(function(){

});
var myChart;
var option;
function querydata() {
	var datatime = $("#startyeartime").val();
	var bike_id = $("#xh").combotree("getValue");
	if(bike_id==''){
        $.messager.show({
            title:'提示',
            msg:'必须选择单车类型',
            timeout:5000,
            showType:'slide'
        });
        return;
	}
	var queryParam = "time="+datatime+"&id="+bike_id;
	if(myChart){
		myChart.clear();
	}else{
		myChart = echarts.init(document.getElementById('mycharts1'));
	}
	var optionVal = getOptionMychart(queryParam);
	myChart.setOption(optionVal,true);

	myChart.on('click',function (params) {
		window.location.href = contextPath+'/pages/bike/list4.jsp?time='+params.name+'&id='+bike_id;
    });

}
var queryResult;
function getOptionMychart(queryParam) {

	queryAjaxChartData(queryParam);
	var optionArray={
		title:{
			text:'单车租借数量趋势图(月)',
			x:'center',
			y:'10'
		},
		tooltip:{
			trigger:'axis'
		},
		legend:{
			data:['租借数量'],
			padding:38
		},
		calculable:false,
		grid:{
			x:30,
			x2:30,
			y2:10,
			containLabel:true
		},
		xAxis:[{
			type:'category',
			boundaryGap:true,
			data : queryResult.time
		}],
		yAxis:[{
			type:'value',
			name:'车辆/辆',
		}],
		series:[{
			name:'租借数量',
			type:'bar',
			barMaxWidth:'30',
			data:queryResult.num
		}]
	}
	return optionArray;

}
var myChart2;
function compareTo() {

    var bike_id1 = $("#bj1").combotree("getValue");
    var bike_id2 = $("#bj2").combotree("getValue");
    if(bike_id1==''||bike_id2==''){
        $.messager.show({
            title:'提示',
            msg:'必须两个单车类型都选择',
            timeout:5000,
            showType:'slide'
        });
        return;
    }
    var queryParam = "bike1="+bike_id1+"&bike2="+bike_id2;
    if(myChart2){
        myChart2.clear();
    }else{
        myChart2 = echarts.init(document.getElementById('mycharts2'));
    }
    var optionVal = getOptionMychart2(queryParam);
    myChart2.setOption(optionVal,true);



}
function getOptionMychart2(queryParam) {

    queryAjaxChartData2(queryParam);
    var optionArray={
        title:[{
            text:'车辆收益比较',
            x:'25%',
            y:'10%'
        },{
            text:'车辆租借数量比较',
            x:'64%',
            y:'10%'
        }],
        tooltip:{
            trigger:'item',
			show:true,
			formatter:"{a} <br/>{b} : {c} （{d}%）"
        },
        series:[{
            name:'车辆收益比较',
            type:'pie',
            center:['30%','60%'],
            radius:['0','60%'],
            itemStyle:{
            	normal:{
            		label:{
            			position:'inner',
						formatter:function (params) {
							return params.value+"元"
                        }
					},
					labelLine:{
            			show:false
					}
				}
			},
			sort:'ascending',
			data:[
				{value:queryResult.profit1,name:queryResult.name1},
                {value:queryResult.profit2,name:queryResult.name2}
			]
        },
            {
                name:'车辆租借数量比较',
                type:'pie',
                center:['70%','60%'],
                radius:['0','60%'],
                itemStyle:{
                    normal:{
                        label:{
                            position:'inner',
                            formatter:function (params) {
                                return params.value+"个"
                            }
                        },
                        labelLine:{
                            show:false
                        }
                    }
                },
                sort:'ascending',
                data:[
                    {value:queryResult.count1,name:queryResult.name1},
                    {value:queryResult.count1,name:queryResult.name2}
                ]
            }]
    }
    return optionArray;

}
function queryAjaxChartData(queryParam) {
	$.ajax({
		type:'post',
		url:contextPath+'/rent/queryy',
		data:queryParam,
		dataType:'json',
		cache:false,
		async:false,
		success:function(result){
            queryResult = result;
		}
	})
}
function queryAjaxChartData2(queryParam) {
    $.ajax({
        type:'post',
        url:contextPath+'/rent/compare',
        data:queryParam,
        dataType:'json',
        cache:false,
        async:false,
        success:function(result){
            queryResult = result;

        }
    })
}
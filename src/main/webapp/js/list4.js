$(function(){

    querydata();

});
var myChart;
var option;
function querydata() {
	var time = $("#time").val();
	var id = $("#id").val();

	var queryParam = "time="+time+"&id="+id;
	if(myChart){
		myChart.clear();
	}else{
		myChart = echarts.init(document.getElementById('top'));
	}
	var optionVal = getOptionMychart(queryParam);
	myChart.setOption(optionVal,true);



}
var queryResult;
function getOptionMychart(queryParam) {

	queryAjaxChartData(queryParam);
	var optionArray={
		title:{
			text:'单车租借数量趋势图(日)',
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

function queryAjaxChartData(queryParam) {
	$.ajax({
		type:'post',
		url:contextPath+'/rent/queryyday',
		data:queryParam,
		dataType:'json',
		cache:false,
		async:false,
		success:function(result){
            queryResult = result;

		}
	})
}

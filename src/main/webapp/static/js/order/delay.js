require.config({
	paths : {
		echarts : base + '/static/echarts'
	}
});

require([
	'echarts',
	'echarts/chart/line'
], function(ec) {
	
	function load() {
		var mainChart = ec.init(document.getElementById('main'));
		var from = $('#from').val();
		var to = $('#to').val();
		var title = "订单处理时间("+from+"~"+to+")";
		mainChart.showLoading({
			text : title+"加载中...",
			effect : "spin",
			textStyle : {
				fontSize : 20
			}
		});

		$.ajax({
			url : base + "/order/delay",
			method : 'post',
			data: {from:$("#from").val(), to:$("#to").val()},
			success : function(data) {
				var option = {
					backgroundColor : '#f5f5f5',
					title : {
						x:'center',
						text : title
					},
					tooltip : {
						trigger: 'axis',
						show : true
					},
					legend : {
						x:'left',
						data : [ '处理时间' ]
					},
					toolbox : {
						show : true,
						feature : {
							mark : {show : false},
							dataZoom : {show : true},
							dataView : {show : true, readOnly : true},
							restore : {show : true},
							saveAsImage : {show : true}
						}
					},
					dataZoom : {
						show : true,
						realtime : true,
						start : 0,
						end : 100
					},
					xAxis : [ {
						type : 'category',
						data : data['series']
					} ],
					yAxis : [ {
						type : 'value',
						axisLabel : {
			                formatter: '{value} 秒'
			            }
					} ],
					series : [ {
						name : "处理时间",
						type : "line",
						barMaxWidth : 20,
						data : data['value'],
						markLine : {
			                data : [
			                    {type : 'average', name: '平均值'}
			                ]
			            }
					} ]
				};
				mainChart.hideLoading();
				mainChart.setOption(option);
				$(window).on('resize', function(){
					mainChart.resize();
				});
			}
		});
	}

	$('#from').datepicker({
		maxDate : new Date(),
		defaultDate : new Date(),
		dateFormat : "yy-mm-dd"
	});
	$("#from").datepicker("setDate", new Date());
	$('#to').datepicker({
		maxDate : new Date(),
		defaultDate : new Date(),
		dateFormat : "yy-mm-dd"
	});
	$("#to").datepicker("setDate", new Date());
	
	$("#refresh").bind("click", function() {
		load();
	});
	
	load();

});
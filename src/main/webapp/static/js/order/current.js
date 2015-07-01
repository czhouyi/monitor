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
		var title = "实时订单数量";
		mainChart.showLoading({
			text : title+"加载中...",
			effect : "spin",
			textStyle : {
				fontSize : 20
			}
		});

		$.ajax({
			url : base + "/order/current",
			method : 'post',
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
							data : ['外网','专线','互联网']
						},
						toolbox : {
							show : true,
							feature : {
								mark : {show : false},
								dataZoom : {show : true},
								dataView : {show : false,readOnly : false},
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
						}],
						yAxis : [ {
							type : 'log',
							min : 1
						} ],
						series : [ {
							name : "外网",
							type : "line",
							barMaxWidth : 20,
							data : data['value0'],
							markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}
				                ]
				            }
						}, {
							name : "专线",
							type : "line",
							barMaxWidth : 20,
							data : data['value1'],
							markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}
				                ]
				            }
						}, {
							name : "互联网",
							type : "line",
							barMaxWidth : 20,
							data : data['value2'],
							markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}
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
		
		var timeTicket;
		clearInterval(timeTicket);
		timeTicket = setInterval(function (){
			$.ajax({
				url : base + "/order/increase",
				success : function(data) {
					mainChart.addData([
						[
				            0,        	// 系列索引
				            data['series'], 	// 新增数据
				            false,     	// 新增数据是否从队列头部插入
				            false,     	// 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				            data['value0']
			            ],
			            [
				            1,        	// 系列索引
				            data['series'], 	// 新增数据
				            false,     	// 新增数据是否从队列头部插入
				            false,     	// 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				            data['value1']
				        ],
			            [
				            2,        	// 系列索引
				            data['series'], 	// 新增数据
				            false,     	// 新增数据是否从队列头部插入
				            false,     	// 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				            data['value2']
				        ]
					]);
				}
			});
		}, 1000*60);
	}
	
	$("#refresh").bind("click", function() {
		load();
	});
	
	load();

});
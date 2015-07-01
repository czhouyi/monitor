require.config({
	paths : {
		echarts : base + '/static/echarts'
	}
});

require([
	'echarts',
	'echarts/chart/map'
], function(ec) {

	function load() {
		var mainChart = ec.init(document.getElementById('main'));
		var from = $('#from').val();
		var to = $('#to').val();
		var title = "订单被查询对象分布("+from+"~"+to+")";
		mainChart.showLoading({
			text : title+"加载中...",
			effect : "spin",
			textStyle : {
				fontSize : 20
			}
		});
		
		$.ajax({
			url : base + "/order/distribution",
			method : 'POST',
			data: {from:$("#from").val(), to:$("#to").val()},
			success : function(data) {
				var option = {
						backgroundColor : '#f5f5f5',
						title : {
							text : title,
							x:'center'
						},
						tooltip : {
							show : true,
							trigger: 'item'
						},
						legend : {
							orient: 'vertical',
							x:'left',
							data : [ '查询对象' ]
						},
						dataRange: {
							min: 0,
							max: data['max'],
							x: 'left',
							y: 'bottom',
							text:['高','低'],
							calculable : true
						},
						toolbox : {
							show : true,
							orient : 'vertical',
							x: 'right',
							y: 'center',
							feature : {
								mark : { show : false },
								dataZoom : { show : false },
								dataView : { show : true, readOnly : true },
								restore : { show : true },
								saveAsImage : { show : true }
							}
						},
						roamController: {
							show: true,
							x: 'right',
							mapTypeControl: {
								'china': true
							}
						},
						series : [ {
							name : "查询对象",
							type : "map",
							mapType: 'china',
							roam: false,
							itemStyle:{
								normal:{label:{show:true}},
								emphasis:{label:{show:true}}
							},
							data : data['data']
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
	$("#from").datepicker("setDate", new Date(new Date()-7*3600*24*1000));
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
require.config({
	paths : {
		echarts : base + '/static/echarts'
	}
});

require([
	'echarts',
	'echarts/chart/pie'
], function(ec) {
	
	function load() {
		var mainChart = ec.init(document.getElementById('main'));
		var from = $('#from').val();
		var to = $('#to').val();
		var title = "订单按来源计数饼图("+from+"~"+to+")";
		mainChart.showLoading({
			text : title + "加载中...",
			effect : "spin",
			textStyle : {
				fontSize : 20
			}
		});

		$.ajax({
			url : base + "/order/from_cnt",
			method : 'POST',
			data: {from:$("#from").val(), to:$("#to").val()},
			success : function(data) {
				var option = {
						backgroundColor : '#f5f5f5',
						title : {
							x:'center',
							text : title
						},
						tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
					    legend: {
					        orient : 'vertical',
					        x : 'left',
					        data: data['legend']
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            mark : {show: false},
					            dataView : {show: true, readOnly: true},
					            magicType : {
					                show: true, 
					                type: ['pie'],
					                option: {
					                    funnel: {
					                        x: '25%',
					                        width: '50%',
					                        funnelAlign: 'left',
					                        max: 1548
					                    }
					                }
					            },
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : true,
					    series : [
					              {
					            	  name:'订单来源',
					            	  type:'pie',
					            	  radius : '55%',
					            	  center: ['50%', '60%'],
					            	  data: data['data']
					              }
					              ]
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
	$("#from").datepicker("setDate", new Date(new Date()-30*3600*24*1000));
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
// 路径配置
require.config({
	paths : {
		echarts : base + '/static/echarts'
	}
});

// 使用
require([
	'echarts',
	'echarts/chart/line'
], function(ec) {
	
	function load() {
		var mainChart = ec.init(document.getElementById('main'));
		var from = $('#from').val();
		var to = $('#to').val();
		var cid = $('#cid').val();
		var orderFrom = $('#orderFrom').val();
		var product = $('#product').val();
		var title = "客户";
		if(cid) {
			title += "["+$('#cid').find("option:selected").text()+"]";
		}
		title += "订单总数("+from+"~"+to+")";
		mainChart.showLoading({
			text : title + "加载中...",
			effect : "spin",
			textStyle : {
				fontSize : 20
			}
		});

		$.ajax({
			url : base + "/order/ts_static",
			method : 'POST',
			data: {from:$("#from").val(), to:$("#to").val(), cid:$("#cid").val(),
				orderFrom:$("#orderFrom").val(), product:$("#product").val(), scope:$("#scope").val()},
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
							data : [ '订单数' ]
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
						}],
						yAxis : [ {
							type : 'value'
						} ],
						series : [ {
							name : "订单数",
							type : "line",
							barMaxWidth : 20,
							data : data['value'],
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
		$('#error').hide();
		var s = new Date($("#from").val());
		var e = new Date($("#to").val());
		var period = e - s;
		var scope = $("#scope").val();
		if(scope == 'hour' && period > 30*24*3600*1000) {
			$('#errorMsg').text('提示：按小时统计时间间隔不宜取太大');
			$('#error').show();
			return;
		}
		load();
	});
	
	$.ajax({
		url : base + "/customer/all",
		method : 'post',
		success : function(data) {
			for (var i=0; i<data.length;i++) {
				$("#cid").append("<option value='"+data[i].ID+"'>"+data[i].NAME+"</option>");
			}
		}
	});
	
	load();

});
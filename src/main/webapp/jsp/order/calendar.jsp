<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/common/head.jsp"%>
<title>订单数量日历热力图-业务监控系统</title>
<style>

#chart {
	width: 800px;
	margin: 0 auto;
}

.background {
	fill: #eee;
}

line {
	stroke: #fff;
}

text.active {
	fill: red;
}

.day {
	fill: #fff;
	stroke: #ccc;
}

.month {
	fill: none;
	stroke: #fff;
	stroke-width: 4px;
}

.year-title {
	font-size: 1.5em;
}

/* color ranges */
.Oranges .q0-9 {
	fill: #FFF5EB
}

.Oranges .q1-9 {
	fill: #FEE6CE
}

.Oranges .q2-9 {
	fill: #FDD0A2
}

.Oranges .q3-9 {
	fill: #FDAE6B
}

.Oranges .q4-9 {
	fill: #FD8D3C
}

.Oranges .q5-9 {
	fill: #F16913
}

.Oranges .q6-9 {
	fill: #D94801
}

.Oranges .q7-9 {
	fill: #A63603
}

.Oranges .q8-9 {
	fill: #7F2704
}

/* hover info */
#tooltip {
	background-color: #fff;
	border: 2px solid #ccc;
	padding: 10px;
}
</style>
</head>
<body>
	<%@ include file="/jsp/common/nav.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<div class="main">
				<ol class="breadcrumb">
					<li><a href="${base}/">首页</a></li>
					<li><a href="${base}/order">订单监控</a></li>
					<li class="active">订单数量日历热力图</li>
				</ol>

				<div id="chart" class="clearfix"></div>
				
				<div class="col-sm-5">
					<div class="panel panel-default">
						<div class="panel-heading">参数设置</div>
						<div class="panel-body">
							<div class="form-group">
								<label for="year">年份</label>
								<input class="form-control" type="number" id="year" />
								<label for="cid">客户</label>
								<select id="cid" class="form-control">
									<option value=''>全部</option>
								</select>
							</div>
							<button id="refresh" class="btn btn-default">
								<span class="glyphicon glyphicon-refresh"></span> 刷新
							</button>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<%@ include file="/jsp/common/foot.jsp"%>
	<script src="${base}/static/d3/d3.v3.js"></script>
	<script src="${base}/static/js/order/calendar.js"></script>
</body>
</html>


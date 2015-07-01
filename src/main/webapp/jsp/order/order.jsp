<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/common/head.jsp"%>
<title>订单监控-业务监控系统</title>
</head>
<body>
	<%@ include file="/jsp/common/nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="main">
				<ol class="breadcrumb">
					<li><a href="${base}/">首页</a></li>
					<li class="active">订单监控</li>
				</ol>
				<ul class="list-group">
				    <li class="list-group-item"><a href="${base}/order/ts_static">历史订单时序图</a></li>
				    <li class="list-group-item"><a href="${base}/order/main_customer">主要客户</a></li>
				    <li class="list-group-item"><a href="${base}/order/distribution">被查询对象分布</a></li>
				    <li class="list-group-item"><a href="${base}/order/delay">订单处理时间</a></li>
				    <li class="list-group-item"><a href="${base}/order/current">实时订单数量</a></li>
				    <li class="list-group-item"><a href="${base}/order/calendar">订单数量日历热力图</a></li>
				    <li class="list-group-item"><a href="${base}/order/product_cnt">订单按产品计数</a></li>
				    <li class="list-group-item"><a href="${base}/order/from_cnt">订单按来源计数</a></li>
				</ul>
			</div>
		</div>
	</div>

	<%@ include file="/jsp/common/foot.jsp"%>
</body>
</html>


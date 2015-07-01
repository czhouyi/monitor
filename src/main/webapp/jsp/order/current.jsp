<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/common/head.jsp"%>
<title>实时订单数量-业务监控系统</title>
</head>
<body>
	<%@ include file="/jsp/common/nav.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<div class="main">
				<ol class="breadcrumb">
					<li><a href="${base}/">首页</a></li>
					<li><a href="${base}/order">订单监控</a></li>
					<li class="active">实时订单数量</li>
				</ol>

				<div id="main" style="height: 400px"></div>
				<div class="col-sm-5">
					<div class="panel panel-default">
						<div class="panel-heading">参数设置</div>
						<div class="panel-body">
							<button id="refresh" class="btn btn-default">
								<span class="glyphicon glyphicon-refresh"></span> 刷新
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/jsp/common/foot.jsp" %>
	<script src="${base}/static/js/order/current.js"></script>
</body>
</html>


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/common/head.jsp"%>
<title>主要客户-业务监控系统</title>
</head>
<body>
	<%@ include file="/jsp/common/nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="main">
				<ol class="breadcrumb">
					<li><a href="${base}/">首页</a></li>
					<li><a href="${base}/order">订单监控</a></li>
					<li class="active">主要客户</li>
				</ol>

				<div id="main" style="height: 400px"></div>
				<div class="col-sm-5">
					<div class="panel panel-default">
						<div class="panel-heading">参数设置</div>
						<div class="panel-body">
							<div class="form-group">
								<label for="from">起始日期</label>
								<input class="form-control" type="text" id="from" readOnly />
								<label for="to">结束日期</label>
								<input class="form-control" type="text" id="to" readOnly />
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
	<script src="${base}/static/js/order/main_customer.js"></script>
</body>
</html>


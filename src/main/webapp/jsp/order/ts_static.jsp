<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/common/head.jsp"%>
<title>历史订单时序图-业务监控系统</title>
</head>
<body>
	<%@ include file="/jsp/common/nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="main">
				<ol class="breadcrumb">
					<li><a href="${base}/">首页</a></li>
					<li><a href="${base}/order">订单监控</a></li>
					<li class="active">历史订单时序图</li>
				</ol>

				<div id="main" style="height: 400px"></div>
				<div class="col-sm-5">
					<div class="panel panel-default">
						<div class="panel-heading">参数设置</div>
						<div class="panel-body">
							<div id="error" class="alert alert-danger" hidden>
  								<span id="errorMsg">错误！请进行一些更改。</span>
							</div>
							<div class="form-group">
								<label for="from">起始日期</label>
								<input class="form-control" type="text" id="from" readOnly />
								<label for="to">结束日期</label>
								<input class="form-control" type="text" id="to" readOnly />
								<label for="cid">客户</label>
								<select id="cid" class="form-control">
									<option value=''>全部</option>
								</select>
								<label for="orderFrom">数据来源</label>
								<select id="orderFrom"
									class="form-control">
									<option value="">全部</option>
									<option value="0">外网</option>
									<option value="1">专线</option>
									<option value="2">互联网</option>
								</select>
								<label for="product">产品</label>
								<select id="product"
									class="form-control">
									<option value="">全部</option>
									<option value="1">按照企业查询</option>
									<option value="1">按照人员查询</option>
									<option value="3">企业监控</option>
									<option value="4">单节点关联关系</option>
									<option value="5">集团族谱关联关系</option>
									<option value="6">多节点关联关系</option>
									<option value="2">司法数据</option>
									<option value="7">权威数据</option>
								</select>
								<label for="scope">统计粒度</label>
								<select id="scope"
									class="form-control">
									<option value="hour">按小时统计</option>
									<option value="day">按天统计</option>
									<option value="month">按月统计</option>
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
	<script src="${base}/static/js/order/ts_static.js"></script>
</body>
</html>


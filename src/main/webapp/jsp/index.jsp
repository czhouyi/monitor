<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/common/head.jsp"%>
<title>首页-业务监控系统</title>
</head>
<body>
	<%@ include file="/jsp/common/nav.jsp"%>
	<div class="container-fluid">
		<div class="row">

			<div class="main">
				<div id="main" style="height: 400px"></div>
			</div>
		</div>
	</div>

	<%@ include file="/jsp/common/foot.jsp" %>
	<script src="${base}/static/js/order/current.js"></script>
</body>
</html>


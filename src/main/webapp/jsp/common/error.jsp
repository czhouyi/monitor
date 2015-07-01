<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/common/head.jsp"%>
<title>500-业务监控系统</title>
</head>
<body>
	<%@ include file="/jsp/common/nav.jsp"%>
	<div class="container">
		<div class="row">
			<div class="main">
				<div class="error-container">
					<div class="well">
						<h1 class="grey lighter smaller">
							500 服务器内部错误
						</h1>
						<hr>
						<h3 class="lighter smaller">
							<span>出错原因：</span><br>
						</h3>
						<span>${exception}</span>
						<hr>
						<div class="center">
							<a href="${base}/" class="btn btn-primary">返回首页</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/jsp/common/foot.jsp"%>
</body>
</html>


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/common/head.jsp"%>
<title>用户信息-业务监控系统</title>
</head>
<body>
	<%@ include file="/jsp/common/nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="main">
				<ol class="breadcrumb">
					<li><a href="${base}/">首页</a></li>
					<li class="active">用户信息</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">个人资料</h3>
					</div>
					<div class="panel-body">
						<form role="form" action="/auth/profile/" method="post" commandName="user">
						
							<div class="form-group">
								<label for="username">姓名</label>
								<input type="text" name="username" class="form-control" disabled="true" value="${user.username}">
							</div>
							<div class="form-group">
								<label for="email">邮箱</label>
								<input type="text" name="email" class="form-control" disabled="true" value="${user.email}">
							</div>
							<div class="form-group">
								<label for="mobile">手机号</label>
								<input type="text" name="mobile" class="form-control" disabled="true" value="${user.mobile}">
							</div>
							<div class="form-group">
								<label for="customer">组织</label>
								<input type="text" name="customer" class="form-control" disabled="true" value="${user.customerName}">
							</div>
							<button class="btn btn-default" type="submit" disabled>更新资料</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/jsp/common/foot.jsp"%>
</body>
</html>


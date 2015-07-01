<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/common/head.jsp"%>
<link href="${base}/static/css/login.css" rel="stylesheet">
<title>登录-业务监控系统</title>
</head>
<body>
	<%@ include file="/jsp/common/nav.jsp"%>
	
	<div class="container">
		<form:form class="form-signin" role="form" action="${base}/auth/login" method="post" commandName="user">
			<h2 class="form-signin-heading">登录</h2>
			<form:errors path="" cssClass="alert alert-danger alert-dismissable" element="div">
			</form:errors>
			<label for="email" class="sr-only">邮箱</label>
			<input type="text" name="email" class="form-control" placeholder="请输入邮箱" required autofocus>
			<form:errors path="email" cssClass="alert alert-danger alert-dismissable" element="div">
			</form:errors>
			<label for="password" class="sr-only">密码</label>
			<input type="password" name="password" class="form-control" placeholder="请输入密码" required>
			<form:errors path="password" cssClass="alert alert-danger alert-dismissable" element="div">
			</form:errors>
			<div class="checkbox">
	          	<label>
	            	<input type="checkbox" value="remember-me"> 记住我
	          	</label>
        	</div>
        	<input type="hidden" name="r" value="${r}">
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form:form>
	</div>

	<%@ include file="/jsp/common/foot.jsp"%>
</body>
</html>


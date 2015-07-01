<!-- 通用页面头部文件 -->
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${base}/">业务监控系统</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li id="site_nav_index">
					<a href="${base}/"><span class="glyphicon glyphicon-dashboard"></span> 主面板</a>
				</li>
				<li id="site_nav_order">
					<a href="${base}/order"><span class="glyphicon glyphicon-stats"></span> 订单监控</a>
				</li>
				<li id="site_nav_server">
					<a href="${base}/server"><span class="glyphicon glyphicon-cloud"></span> 服务监控</a>
				</li>
				<li id="site_nav_hardware">
					<a href="#"><span class="glyphicon glyphicon-hdd"></span> 硬件监控</a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">设置</a></li>
				<li><a href="#">帮助</a></li>
				<c:if test="${user!=null}">
					<li><a href="${base}/auth/profile">${user.username}</a></li>
					<li><a href="${base}/auth/logout">登出</a></li>
				</c:if>
				<c:if test="${user==null}">
					<li><a href="${base}/auth/login">登录</a></li>
				</c:if>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="搜索...">
			</form>
		</div>
	</div>
</nav>

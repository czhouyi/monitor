<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/common/head.jsp"%>
<title>服务列表-业务监控系统</title>
</head>
<body>
	<%@ include file="/jsp/common/nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="main">
				<ol class="breadcrumb">
					<li><a href="${base}/">首页</a></li>
					<li><a href="${base}/server">服务监控</a></li>
					<li class="active">服务列表</li>
				</ol>
				<a href="${base}/server/meta/profile" class="btn btn-primary" role="button">
					<span class="glyphicon glyphicon-plus"></span> 新增</a>
				<div class="table-responsive">
				<table class="table">
				   	<thead>
				      	<tr>
				         	<th>类型</th>
				         	<th>服务名称</th>
				         	<th>地址</th>
				         	<th>是否监控</th>
				         	<th>操作</th>
				      	</tr>
				   	</thead>
				   	<tbody>
				   		<c:forEach items="${metas}" var="meta">
				   		<tr ${meta.monitor?'class="success" style="color:green;"':''}>
				   			<td>${meta.type}</td>
				   			<td>${meta.name}</td>
				   			<td>${meta.url}</td>
				   			<td>${meta.monitor?'监控中':'未监控'}</td>
				   			<td>
				   			<a href="${base}/server/meta/${meta.id}" class="btn btn-primary btn-xs" role="button">
				   				<span class="glyphicon glyphicon-pencil"></span> 编辑
				   			</a></td>
				      	</tr>
				   		</c:forEach>
				   	</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/jsp/common/foot.jsp"%>
</body>
</html>


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/common/head.jsp"%>
<title>服务监控-业务监控系统</title>
</head>
<body>
	<%@ include file="/jsp/common/nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="main">
				<ol class="breadcrumb">
					<li><a href="${base}/">首页</a></li>
					<li class="active">服务监控</li>
				</ol>
				<div class="btn-group">
				  	<a href="${base}/server" type="button" class="btn btn-${f=='all' ?'primary':'default'}" role="button">全部</a>
				  	<a href="${base}/server?f=tomcat" type="button" class="btn btn-${f=='tomcat' ?'primary':'default'}" role="button">Tomcat</a>
				  	<a href="${base}/server?f=oracle" type="button" class="btn btn-${f=='oracle' ?'primary':'default'}" role="button">Oracle</a>
				  	<a href="${base}/server?f=hadoop" type="button" class="btn btn-${f=='hadoop' ?'primary':'default'}" role="button">Hadoop</a>
				  	<a href="${base}/server?f=solr" type="button" class="btn btn-${f=='solr' ?'primary':'default'}" role="button">Solr</a>
				</div>
				<div class="btn-group">
				    <a href="${base}/server/meta" type="button" class="btn btn-default" role="button">
				      	<span class="glyphicon glyphicon-cog"></span> 配置监控
				   	</a>
				</div>
				<div class="btn-group">
				    <a href="${base}/server/export" type="button" class="btn btn-default" role="button">
				      	<span class="glyphicon glyphicon-export"></span> 导出CSV文件
				   	</a>
				</div>
				<div class="table-responsive">
				<table class="table">
				   	<thead>
				      	<tr>
				         	<th>服务类型</th>
				         	<th>服务名称</th>
				         	<th>服务状态</th>
				         	<th>更新时间</th>
				         	<th width="30%">异常原因</th>
				      	</tr>
				   	</thead>
				   	<tbody>
				   		<c:forEach items="${statusList}" var="status">
				   		<tr class="${status.status}">
				   			<td>${status.type}</td>
				         	<td>${status.name}</td>
				         	<td>${status.status=='success'?'运行正常':status.status=='warning'?'轻微异常':'严重异常'}</td>
				         	<td><fmt:formatDate value="${status.mdate}" pattern="yyyy-M-d HH:mm:ss"/></td>
				         	<td>${status.info}</td>
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


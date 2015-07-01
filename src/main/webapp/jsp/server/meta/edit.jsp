<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/common/head.jsp"%>
<title>${meta.id==0?'新增':'编辑'}服务-业务监控系统</title>
</head>
<body>
	<%@ include file="/jsp/common/nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="main">
				<ol class="breadcrumb">
					<li><a href="${base}/">首页</a></li>
					<li><a href="${base}/server">服务监控</a></li>
					<li><a href="${base}/server/meta">服务列表</a></li>
					<li class="active">${meta.id==0?'新增':'编辑'}服务</li>
				</ol>
				<div class="col-sm-8">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">${meta.id==0?'新增':'编辑'}服务</h3>
						</div>
						<div class="panel-body">
							<form:form role="form" action="${base}/server/meta/profile" method="post" commandName="serverMeta">
  								<form:errors path="" cssClass="alert alert-danger alert-dismissable" element="div" >
  								</form:errors>
								<input type="hidden" name="id" value="${meta.id}">
								<div class="form-group">
									<label for="name">服务名称</label>
									<input type="text" name="name" class="form-control" value="${meta.name}" required autofocus placeholder="服务名称">
									<form:errors path="name" cssClass="alert alert-danger alert-dismissable" element="div" >
  									</form:errors>
								</div>
								<div class="form-group">
									<label for="type">服务类型</label>
									<label class="checkbox-inline">
								      	<input type="radio" name="type" id="tomcat" value="tomcat" ${meta.type=='tomcat' ? 'checked':''}> Tomcat
								   	</label>
								   	<label class="checkbox-inline">
								      	<input type="radio" name="type" id="oracle" value="oracle" ${meta.type=='oracle' ? 'checked':''}> Oracle
								   	</label>
								   	<label class="checkbox-inline">
								      	<input type="radio" name="type" id="hadoop" value="hadoop" ${meta.type=='hadoop' ? 'checked':''}> Hadoop
								   	</label>
								   	<label class="checkbox-inline">
								      	<input type="radio" name="type" id="solr" value="solr" ${meta.type=='solr' ? 'checked':''}> Solr
								   	</label>
								   	<form:errors path="type" cssClass="alert alert-danger alert-dismissable" element="div" >
  									</form:errors>
								</div>
								<div class="form-group">
									<label for="url">URL地址</label>
									<input type="text" id="url" name="url" class="form-control" value="${meta.url}" required placeholder="格式：http://121.14.23.177:8080/gsinfo">
									<form:errors path="url" cssClass="alert alert-danger alert-dismissable" element="div" >
  									</form:errors>
								</div>
								<div class="form-group">
									<label for="username">用户</label>
									<input type="text" name="username" class="form-control" value="${meta.username}" placeholder="无必要填请置空">
									<form:errors path="username" cssClass="alert alert-danger alert-dismissable" element="div" >
  									</form:errors>
								</div>
								<div class="form-group">
									<label for="password">密码</label>
									<input type="password" name="password" class="form-control" value="${meta.password}" placeholder="无必要填请置空">
									<form:errors path="password" cssClass="alert alert-danger alert-dismissable" element="div" >
  									</form:errors>
								</div>
								<div class="checkbox">
      								<label>
							    		<input type="checkbox" id="monitor" name="monitor" <c:if test="${meta.monitor}">checked</c:if>> 是否监控
							    	</label>
							   	</div>
								<button class="btn btn-primary" type="submit">保存配置</button>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/jsp/common/foot.jsp"%>
	<script src="${base}/static/js/server/server.js"></script>
</body>
</html>


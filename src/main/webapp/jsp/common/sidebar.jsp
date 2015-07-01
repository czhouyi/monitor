<!-- 通用页面头部文件 -->
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<c:choose>
			<c:when test='${module == ""}'>
			<li class="active"><a href="#">总览<span class="sr-only">(current)</span></a></li>
			</c:when>
			<c:otherwise>
			<li><a href="${base}/">总览<span class="sr-only">(current)</span></a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test='${module == "order"}'>
			<li class="active"><a href="#">订单监控</a></li>
			</c:when>
			<c:otherwise>
			<li><a href="${base}/order">订单监控</a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test='${module == "server"}'>
			<li class="active"><a href="#">服务监控</a></li>
			</c:when>
			<c:otherwise>
			<li><a href="${base}/server">服务监控</a></li>
			</c:otherwise>
		</c:choose>
		<li><a href="#">硬件监控</a></li>
	</ul>
</div>
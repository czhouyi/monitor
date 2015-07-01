<!-- 通用头部文件 -->
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script>top['base']='${base}';</script>
<script src="${base}/static/jquery/jquery.min.js"></script>
<script src="${base}/static/jquery/jquery-ui.js"></script>
<script src="${base}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${base}/static/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
<script src="${base}/static/bootstrap/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script>
	$(function() {
		var site_nav_url = window.location.pathname.replace("${base}", "");
		var regs = [/^\/$/, /^\/order.*/, /^\/server.*/, /^\/hardware.*/];
		var selectors = ["#site_nav_index", "#site_nav_order", "#site_nav_server", "#site_nav_hardware"];
		for (var i = 0; i < regs.length; i++) {
			if (regs[i].exec(site_nav_url)) {
				$(selectors[i]).addClass("active");
				break;
			}
		}
	});
</script>

<!-- ECharts单文件引入 -->
<script src="${base}/static/echarts/echarts.js"></script>
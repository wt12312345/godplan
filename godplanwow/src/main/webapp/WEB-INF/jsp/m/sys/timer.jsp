<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>定时器 - 后台管理</title>
<jsp:include page="../include/pageStyle.jsp"></jsp:include>
</head>
<body>
	<div class="body-top">
		<jsp:include page="../include/header.jsp"></jsp:include>
	</div>
	<div class="body-page">
		<div class="body-page-menu">
			<jsp:include page="../include/leftMenu.jsp"></jsp:include>
		</div>
		<div class="body-page-main">
			<div class="body-page-main-ctn">
				<div class="main-title">
					<h1>定时器管理</h1>
				</div>
				<div class="main-form">
					<h2>定时器操作</h2>
					<input type="button" class="btn btn-default" value="生成静态首页"
						onclick="ManuallyTimer('indexPage')" /> <input type="button"
						class="btn btn-default" value="同步视频数据"
						onclick="ManuallyTimer('getVideoInfo')" /> <input type="button"
						class="btn btn-default" value="刷新客户、栏目、账户数据"
						onclick="ManuallyTimer('refreshVideoInfo')" />
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../include/pageScript.jsp"></jsp:include>
	<script type="text/javascript">
		var ifAjax = false;
		function ManuallyTimer(mode) {
			if (ifAjax) {
				return;
			}
			ifAjax = true;
			wtJquery.Ajax("${path}m/sys/doTimer", "mode=" + mode,
					function(data) {
						if (data.code == 1) {
							alert("OK");
						} else {
							alert(data.msg);
						}
						ifAjax = false;
					});
		}
	</script>
</body>
</html>
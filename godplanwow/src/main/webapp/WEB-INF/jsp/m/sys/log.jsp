<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>日志管理 - 管理后台</title>
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
					<h1>日志管理</h1>
				</div>
				<div class="main-form">
					<h2>日志列表</h2>
					<table class="table table-bordered" id="dataTable">
						<thead>
							<tr>
								<th>信息</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../include/pageScript.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			getLogList();
			setInterval(getLogList, 3000);
		});
		var index = 0;
		var ifAjax = false;
		function getLogList() {
			if (ifAjax) {
				return;
			}
			ifAjax = true;
			$.post("${path}m/sys/getLogList", "index=" + index,
				function(data) {
					if (data.code == 1) {
						index = data.id;
						var arr = data.obj;
						var html = "";
						for(var i = 0; i < arr.length; i++) {
							var item = arr[i];
							var belong = item.belong;
							
							if(belong == 2){
								html += '<tr class="label-danger">';
							}else{
								html += '<tr>';
							}
							html += '<td>'+ item.content +'</td></tr>';
						}
						$("#dataTable tbody").prepend(html);
					} else {
						alert(data.msg);
					}
					ifAjax = false;
				});
		}
	</script>
</body>
</html>
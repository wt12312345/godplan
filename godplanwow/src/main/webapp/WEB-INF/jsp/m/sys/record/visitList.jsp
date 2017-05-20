<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>后台访问记录列表 - 管理后台</title>
<jsp:include page="../../include/pageStyle.jsp"></jsp:include><link
	rel="stylesheet"
	href="${assets }plugin/datetimepicker/jquery.datetimepicker.css">
</head>
<body>
	<div class="body-top">
		<jsp:include page="../../include/header.jsp"></jsp:include>
	</div>
	<div class="body-page">
		<div class="body-page-menu">
			<jsp:include page="../../include/leftMenu.jsp"></jsp:include>
		</div>
		<div class="body-page-content">
			<div class="page-title">
				<h3>后台访问记录列表</h3>
			</div>
			<form id="formData" action="${path }m/sys/recordVisit"
				method="get">
				<div class="vv-search-model">
					<label>时间</label> <input id="startTime" name="startTime"
						value="${startTime }" /> 到 <input id="endTime" name="endTime"
						value="${endTime }" /> <select name="userSysId">
						<option value="0">所有用户</option>
						<c:forEach items="${listUser }" var="item">
							<option value="${item.id }"
								<c:if test="${item.id eq userSysId }">selected="selected"</c:if>>${item.name }</option>
						</c:forEach>
					</select> <input type="submit" class="btn btn-success">查询</span>
				</div>
			</form>
			<div class="w_panel role2">
				<div class="w_panel_title">
					<h4 class="role2">查询结果列表</h4>
				</div>
				<table class="table table-hover" id="itemTable">
					<thead>
						<tr>
							<th>#</th>
							<th>访问时间</th>
							<th>用户</th>
							<th>地址</th>
						</tr>
					</thead>
					<c:forEach items="${list }" var="item" varStatus="num">
						<tr>
							<th>${num.index + 1 }</th>
							<th>${item.createTime }</th>
							<th>${item.userName }</th>
							<th>${item.url }</th>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="../../include/pageScript.jsp"></jsp:include>
	<script type="text/javascript"
		src="${assets}plugin/datetimepicker/jquery.datetimepicker.js"></script>
	<script type="text/javascript">
		var ifAjax = false;
		$(function() {
			$('#startTime').datetimepicker({
				//value : WtDate.NowStr3() + " 10:00",
				lang : 'ch',
				format : "Y-m-d",
				step : 10
			});
			$('#endTime').datetimepicker({
				//value : WtDate.NowStr3() + " 10:00",
				lang : 'ch',
				format : "Y-m-d",
				step : 10
			});
		});
	</script>
</body>
</html>
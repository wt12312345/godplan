<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge; chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="stylesheet"
	href="${assets}plugin/bootstrap/css/bootstrap.min.css">
<link href="${assets }css/admin/total.css" rel="stylesheet"
	type="text/css" />
<link href="${assets }plugin/wt/css/wt.css" rel="stylesheet"
	type="text/css" />
<script src="${assets}plugin/jquery-1.11.1.min.js"></script>
<script src="${assets}plugin/wt/js/wt.js" type="text/javascript"></script>
<script type="text/javascript"
	src="${assets }plugin/ajax-fileuploader/ajaxfileupload.js"></script>
<script type="text/javascript">
	$(function() {
	});
</script>
</head>
<body>
	<div class="body-top">
		<jsp:include page="../include/header.jsp"></jsp:include>
	</div>
	<div class="body-page">
		<div class="body-page-menu">
			<jsp:include page="../include/leftMenu.jsp"></jsp:include>
		</div>
		<div class="body-page-content">
			<div class="vv-search-model">
				<a class="btn btn-default" href="${path}m/articleSortEdit">新增 </a>
			</div>
			<h1>文章类别列表</h1>
			<table class="table table-hover" id="listTable">
				<tr>
					<th>类别</th>
					<th>描述</th>
					<th>排序</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${sortList}" var="item" varStatus="status">
					<tr>
						<td>${item.title}</td>
						<td>${item.remark}</td>
						<td>${item.sortIndex}</td>
						<td><a href="${path}m/articleSortEdit?id=${item.id}">修改</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>

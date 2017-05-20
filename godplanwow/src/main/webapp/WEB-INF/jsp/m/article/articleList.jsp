<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>文章管理 - 管理后台</title>
<link rel="stylesheet"
	href="${assets}plugin/bootstrap/css/bootstrap.min.css">
<link href="${assets }css/admin/total.css" rel="stylesheet"
	type="text/css" />
<link href="${assets }plugin/wt/css/wt.css" rel="stylesheet"
	type="text/css" />
<script src="${assets}plugin/jquery-1.11.1.min.js"></script>
<script src="${assets}plugin/wt/js/wt.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {

	});
	function submitForm() {
		$("#doSave").submit();
	}
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
				<a class="btn btn-default" href="${path}m/articleEdit"> 新增 </a>
			</div>
			<h1>图文管理</h1>
			<form action="${path}m/articleList" id="doSave" method="get">
				<div class="vv-search-model">
					<ul class="vv-search">
						<li>类别&nbsp;:&nbsp; <select name="sortId">
								<option value="0">全部</option>
								<c:forEach items="${sortList}" var="item">
									<option value="${item.id}"
										<c:if test="${item.id == sortId}">selected="selected"</c:if>>${item.title}</option>
								</c:forEach>
						</select>
						</li>
					</ul>
					<div class="operate_box">
						<span class="btn btn-default" onclick="submitForm()"> <i
							class="glyphicon glyphicon-search"></i> 查询
						</span>
					</div>
				</div>
			</form>
			<table class="table table-hover" id="listTable">
				<tr>
					<th>图片</th>
					<th>类别</th>
					<th>标题</th>
					<th>描述</th>
					<th>排序</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${listArticle}" var="item">
					<tr>
						<td><img style="height:50px;width:80px;"
							src="${img}${item.imgUrl }" /></td>
						<td>${item.articleSort.title}</td>
						<td>${item.title}</td>
						<td>${item.remark}</td>
						<td>${item.sortIndex}</td>
						<td><a href="${path}m/articleEdit?id=${item.id}">修改</a></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>
</body>

</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>商城字典 - 管理后台</title>
<jsp:include page="../../include/pageStyle.jsp"></jsp:include>
</head>
<body>
	<div class="body-top">
		<jsp:include page="../../include/header.jsp"></jsp:include>
	</div>
	<div class="body-page">
		<div class="body-page-menu">
			<jsp:include page="../../include/leftMenu.jsp"></jsp:include>
		</div>
		<div class="body-page-main">
			<div class="body-page-main-ctn">
				<div class="main-title">
					<h1>字典管理</h1>
					<div class="main-title-btn">
						<a class="btn btn-primary" href="${path}m/sys/dicEdit"><i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-plus"></i></a>
					</div>
				</div>
				<div class="main-form">
					<h2>字典列表</h2>
					<table class="table table-hover table-bordered">
						<thead>
							<tr>
								<th>关键词</th>
								<th>名称</th>
								<th>valint</th>
								<th>valdbl</th>
								<th>valstr</th>
								<th>描述</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="item">
								<tr>
									<td>${item.keyId }</td>
									<td>${item.name }</td>
									<td>${item.valint }</td>
									<td>${item.valdbl }</td>
									<td>${item.valstr }</td>
									<td>${item.remark }</td>
									<td><a class="btn btn-warning btn-sm" href="${path}m/sys/dicEdit?id=${item.id}"><i class="glyphicon glyphicon-pencil"></i></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../../include/pageScript.jsp"></jsp:include>
</body>
</html>
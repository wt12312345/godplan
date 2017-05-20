<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>角色管理 - 菜单管理</title>
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
					<h1>角色管理</h1>
					<div class="main-title-btn">
						<a class="btn btn-primary" href="${path}m/sys/roleEdit"><i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-plus"></i></a>
					</div>
				</div>
				<div class="main-form">
					<h2>角色列表</h2>
					<table class="table table-hover table-bordered">
						<thead>
							<tr>
								<th>编号</th>
								<th>名称</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="item">
								<tr>
									<td>${item.id}</td>
									<td>${item.name}</td>
									<td><a class="btn btn-warning btn-xs" href="${path }m/sys/roleEdit?id=${item.id}"><i class="glyphicon glyphicon-pencil"></i></a>
									<a class="btn btn-danger btn-xs" onclick="delRole(${item.id})"><i class="glyphicon glyphicon-remove"></i></a>
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
		<script type="text/javascript" src="${assets}plugin/bootbox.min.js"></script>
	<script type="text/javascript">
		function delRole(_id) {
			bootbox.confirm('确认删除此角色？', function(result) {
				if (result) {
					$.post("${path}m/sys/delRole", "id=" + _id, function(
							data) {
						if (data.code == 1) {
							wtCom.RefreshPage();
						} else {
							alert(data.msg);
						}
					});
				}
			});
		}
	</script>
</body>
</html>

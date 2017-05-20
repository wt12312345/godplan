<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>菜单管理 - 管理后台</title>
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
					<h1>菜单管理</h1>
					<div class="main-title-btn">
						<a class="btn btn-primary" href="${path}m/page/menuEdit"><i
							class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i
							class="glyphicon glyphicon-plus"></i></a>
					</div>
				</div>
				<div class="main-form">
					<h2>菜单列表</h2>
					<table class="table table-hover table-bordered">
						<thead>
							<tr>
								<th colspan="2">编号</th>
								<th>名称</th>
								<th>链接</th>
								<th>操作码</th>
								<th>排序</th>
								<th style="width:200px;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${items }" var="item">
								<tr>
									<td>${item.id }</td>
									<td><i class="glyphicon ${item.iconName }"></i></td>
									<td colspan="2"><input type="text" class="form-control"
										value="${item.name }" /></td>
									<td><input type="text" class="form-control"
										value="${item.action }" /></td>
									<td><input type="text" class="form-control"
										value="${item.sortIndex }" /></td>
									<td><a class="btn btn-primary btn-sm"
										href="${path}m/page/menuEdit?parentId=${item.id}"><i
											class="glyphicon glyphicon-plus"></i></a> <a
										class="btn btn-warning btn-sm"
										href="${path}m/page/menuEdit?id=${item.id}"><i
											class="glyphicon glyphicon-pencil"></i></a> <span
										class="btn btn-danger btn-sm" onclick="delMenu(${item.id})"><i
											class="glyphicon glyphicon-remove"></i></span></td>
								</tr>
								<c:forEach items="${item.listSub }" var="itemSub">
									<tr>
										<td></td>
										<td>${menu.id }</td>
										<td><input type="text" class="form-control"
											value="${itemSub.name }" /></td>
										<td><input type="text" class="form-control"
											value="${itemSub.url }" /></td>
										<td><input type="text" class="form-control"
											value="${itemSub.action }" /></td>
										<td><input type="text" class="form-control"
											value="${itemSub.sortIndex }" /></td>
										<td><a class="btn btn-warning btn-sm"
											href="${path }m/page/menuEdit?id=${itemSub.id}"><i
												class="glyphicon glyphicon-pencil"></i></a> <span
											class="btn btn-danger btn-sm"
											onclick="delMenu(${itemSub.id})"><i
												class="glyphicon glyphicon-remove"></i></span></td>
									</tr>
								</c:forEach>
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
		function delMenu(_id) {
			bootbox.confirm('确认删除此菜单？', function(result) {
				if (result) {
					ajax.del("m/menus/" + _id, "", function(data) {
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

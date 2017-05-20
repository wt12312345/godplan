<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>邮件权限列表 - 菜单管理</title>
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
					<h1>邮件权限管理</h1>
					<div class="main-title-btn">
						<a class="btn btn-primary" href="${path}m/sys/uriMailEdit"><i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-plus"></i></a>
					</div>
				</div>
				<div class="main-form">
					<h2>邮件权限列表</h2>
					<table class="table table-hover table-bordered">
						<thead>
							<tr>
								<td style="font-size:14px;font-weight:bold">名称</td>
								<td style="font-size:14px;font-weight:bold">操作码</td>
								<td style="font-size:14px;font-weight:bold">备注</td>
								<td style="font-size:14px;font-weight:bold">操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list }" var="item">
								<tr style="background-color: #eee;">
									<td>${item.name }</td>
									<td>${item.action }</td>
									<td>${item.remark }</td>
									<td><a class="btn btn-danger btn-xs"
										href="${path }m/sys/uriMailEdit?id=${item.id}">修改</a> <span
										class="btn btn-danger btn-xs" onclick="delMenu(${item.id})">删除</span>
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
		function delMenu(_id) {
			bootbox.confirm('确认删除此邮件权限？', function(result) {
				if (result) {
					$.post("${path}m/sys/delUriMail", "id=" + _id, function(
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

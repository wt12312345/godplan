<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>菜单编辑 - 菜单管理</title>
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
					<h1>菜单编辑</h1>
				</div>
				<div class="main-form">
					<h2>信息填写</h2>
					<input type="hidden" id="id" value="${item.id }" />
					<form name="frmData" id="frmData" method="post">
						<table class="table table-hover">
							<thead>
								<tr>
									<td>上级菜单</td>
									<td>${itemParent.name}<input type="hidden" name="parentId"
										value="${itemParent.id}" />
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>ID</th>
									<td>${item.id }</td>
								</tr>
								<tr>
									<th>菜单名称</th>
									<td><input type="text" class="form-control" name="name"
										id="name" value="${item.name }" /></td>
								</tr>
								<tr>
									<th>链接地址</th>
									<td><input type="text" class="form-control" name="url"
										id="url" value="${item.url }" /></td>
								</tr>
								<tr>
									<th>操作码</th>
									<td><input type="text" class="form-control" name="action"
										id="action" value="${item.action }" /></td>
								</tr>
								<tr>
									<th>符号class</th>
									<td><input type="text" class="form-control"
										name="iconName" id="iconName" placeholder="1级菜单必填"
										value="${item.iconName }" /></td>
								</tr>
								<tr>
									<th>排列序号</th>
									<td><input type="text" class="form-control"
										name="sortIndex" id="sortIndex" value="${item.sortIndex }" /></td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<th><span class="btn btn-primary" onclick="doSave()">
											<i class="glyphicon glyphicon-floppy-disk"></i> 保存
									</span></th>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../include/pageScript.jsp"></jsp:include>
	<script type="text/javascript">
		function doSave() {
			var args = $("#frmData").serialize();
			$.post("${path}m/menus/" + $("#id").val(), args, function(data) {
				if (data.code == 1) {
					wtCom.OpenUrl("${path}m/page/menuList");
				} else {
					alert(data.msg);
				}
			});
		}
	</script>
</body>

</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>角色编辑 - 菜单管理</title>
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
					<h1>角色编辑</h1>
				</div>
				<div class="main-form">
					<h2>信息填写</h2>
					<form id="frmData">
						<div class="w_panel">
							<input type="hidden" name="id" id="id" value="${item.id }" />
							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2">
										<label>角色名称</label>
									</div>
									<div class="col-md-10">
										<input type="text" class="form-control" name="name" id="name" value="${item.name }" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-2">
										<label>功能模块</label>
									</div>
									<div class="col-md-10">
										<c:forEach items="${listUri}" var="uri" varStatus="num">
											<div class="panel panel-default">
												<div class="panel-heading">
													<h3 class="panel-title">${uri.name}</h3>
												</div>
												<div class="panel-body">
													<ul class="list-group ">
														<c:forEach items="${uri.listChild}" var="child"
															varStatus="num">
															<li class="list-group-item"><input type="checkbox"
																name="uri" id="uri${child.id }" value="${child.id }">
																${child.name }</li>
														</c:forEach>
													</ul>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-2">
										<label>邮件通知</label>
									</div>
									<div class="col-md-10">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h3 class="panel-title">邮件通知列表</h3>
											</div>
											<div class="panel-body">
												<ul class="list-group">
													<c:forEach items="${listMail}" var="mail" varStatus="num">
														<li class="list-group-item"><input type="checkbox"
															name="uriMail" id="uriMail${mail.id }"
															value="${mail.id }"> ${mail.name }</li>
													</c:forEach>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-2"></div>
									<div class="col-md-10">
										<span class="btn btn-primary" onclick="doSave()">
										    <i class="glyphicon glyphicon-fire"></i> 保存
										</span>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../include/pageScript.jsp"></jsp:include>
	<script type="text/javascript">
		$(function() {
			var menus = "${item.uri}";
			var arr = menus.split(',');
			for (var i = 0; i < arr.length; i++) {
				var one = arr[i];
				if (one == "") {
					continue;
				}
				$("#uri" + one).attr("checked", "checked");
			}

			var urimail = "${item.uriMail}";
			var arrMail = urimail.split(',');
			for (var i = 0; i < arrMail.length; i++) {
				var one = arrMail[i];
				if (one == "") {
					continue;
				}
				$("#uriMail" + one).attr("checked", "checked");
			}

			var uriStorage = "${item.uriStorage}";
			var arrUriStorage = uriStorage.split(',');
			for (var i = 0; i < arrUriStorage.length; i++) {
				var one = arrUriStorage[i];
				if (one == "") {
					continue;
				}
				$("#uriStorage" + one).attr("checked", "checked");
			}

			var uriSupplier = "${item.uriSupplier}";
			var arrUriSupplier = uriSupplier.split(',');
			for (var i = 0; i < arrUriSupplier.length; i++) {
				var one = arrUriSupplier[i];
				if (one == "") {
					continue;
				}
				$("#uriSupplier" + one).attr("checked", "checked");
			}
		});

		function doSave() {
			var args = $("#frmData").serialize();
			$.post("${path}m/sys/saveRole", args, function(data) {
				if (data.code == 1) {
					wtCom.OpenUrl("${path}m/sys/roleList");
				} else {
					alert(data.msg);
				}
			});
		}
	</script>
</body>

</html>

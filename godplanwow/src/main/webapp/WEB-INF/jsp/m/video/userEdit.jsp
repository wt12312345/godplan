<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>视频客户编辑 - 管理后台</title>
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
					<h1>视频客户编辑</h1>
				</div>
				<div class="main-form">
					<h2>信息填写</h2>
					<form name="frmData" id="frmData" method="post">
						<input type="hidden" id="id" name="id" readonly="readonly"
							value="${item.id}">
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>主体名</label>
								</div>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="orgName"
										id="orgName" value="${item.orgName }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>负责人</label>
								</div>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="realName"
										id="realName" value="${item.realName }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>联系方式</label>
								</div>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="mobile"
										id="mobile" value="${item.mobile }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>登录名（建议用手机号）</label>
								</div>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="loginName"
										id="loginName" value="${item.loginName }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>登录密码</label>
								</div>
								<div class="col-sm-10">
									<input type="password" class="form-control" name="password"
										id="password" value="${item.password }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>地址</label>
								</div>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="address"
										id="address" value="${item.address }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>描述</label>
								</div>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="content"
										id="content" value="${item.content }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<span class="btn btn-primary" onclick="doSave()"> <i
										class="glyphicon glyphicon-fire"></i> 保存
									</span>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../include/pageScript.jsp"></jsp:include>
	<script type="text/javascript">
		$(function() {

		});

		function doSave() {
			var orgName = $("#orgName").val();
			if (orgName == "") {
				alert("主体名不能为空");
				return;
			}
			var loginName = $("#loginName").val();
			if (loginName == "") {
				alert("登录名不能为空");
				return;
			}
			if (ifAjax) {
				return;
			}
			ifAjax = true;
			var args = $("#frmData").serialize();
			$.post("${path}m/video/saveUser", args, function(data) {
				if (data.code == 1) {
					alert("保存成功");
					window.location.href = '${path}m/video/userList';
				} else {
					alert("失败！" + data.msg);
				}
				ifAjax = false;
			});
		}
	</script>
</body>
</html>

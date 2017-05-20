<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>修改密码 - 后台管理</title>
<link rel="stylesheet"
	href="${assets}plugin/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${assets}css/admin/total.css"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${assets }plugin/wt/css/wt.css?id=1">
<link rel="stylesheet"
	href="${assets }plugin/validator-0.7.0/jquery.validator.css">
<script src="${assets}plugin/jquery-1.11.1.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="${assets }plugin/wt/js/wt.js"></script>
<script type="text/javascript"
	src="${assets }plugin/datetimepicker/jquery.datetimepicker.js"></script>
<script type="text/javascript"
	src="${assets }plugin/validator-0.7.0/jquery.validator.js"></script>
<script type="text/javascript"
	src="${assets }plugin/validator-0.7.0/local/zh_CN.js"></script>
</head>
<body>
	<div class="body-top">
		<jsp:include page="include/header.jsp"></jsp:include>
	</div>
	<div class="body-page">
		<div class="body-page-menu">
			<jsp:include page="include/leftMenu.jsp"></jsp:include>
		</div>
		<div class="body-page-content">
			<div class="vv-search-model"></div>
			<h1>修改密码</h1>
			<form id="frm" method="post">
				<table class="table table-hover">
					<tr>
						<td class="title">原密码：</td>
					</tr>
					<tr>
						<td><input type="password" name="oldPasswd" id="oldPasswd"
							style="height:25px;margin:10px 0" /></td>
					</tr>
					<tr>
						<td class="title">新密码：</td>
					</tr>
					<tr>
						<td><input type="password" name="passwd" id="passwd"
							style="height:25px;margin:10px 0" /></td>
					</tr>
					<tr>
						<td class="title">密码确认：</td>
					</tr>
					<tr>
						<td><input type="password" name="rePasswd" id="rePasswd"
							style="height:25px;margin:10px 0" /></td>
					</tr>
					<tr>
						<td class="title">&nbsp;</td>
					</tr>
					<tr>
						<td>
							<div class="operate_box at_bottom">
								<addr> <i class="icon-file-text-alt"></i>
								<button type="button" id="submit">修改</button>
								</addr>
								<addr> <i class="icon-file-text-alt"></i>
								<button type="button" id="cancel">返回</button>
								</addr>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$('#frm').validator({
			theme : 'yellow_right_effect',
			stopOnError : true,
			focusInvalid : true,
			fields : {
				"oldPasswd" : {
					rule : "required;",
					ok : "",
				},
				'passwd' : {
					rule : "required;password",
					ok : "",
				},
				'rePasswd' : {
					rule : 'required;match(passwd)',
					msg : '密码不一致',
					ok : "",
				}
			}
		}).on("click", "#submit", function() {
			changePassword();
		});
	});
	function changePassword() {
		$("#frm").trigger("validate");
		/* var menus="";
		$("input[name='menus']:checkbox:checked").each(function(){ 
		            menus += $(this).val()+",";
		   }) */
		if ($('#frm').isValid()) {
			$.post("${path}/m/savePassword", {
				oldPassword : $("#oldPasswd").val(),
				password : $("#passwd").val()
			}, function(data) {
				if (data.code == 1) {
					window.location = "${path}/m";
				} else {
					alert(data.msg);
				}
			});
		}
	}
</script>
</html>

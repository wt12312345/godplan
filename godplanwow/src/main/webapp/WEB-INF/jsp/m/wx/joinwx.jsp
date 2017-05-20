<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>微信公众号接入管理 - 管理后台</title>
<script src="${assets}js/jquery.js"></script>
<script src="${assets}js/back.js"></script>
<script
	src="${assets}js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script type="text/javascript" src="${assets}js/jquery.md5.js"></script>
<script type="text/javascript">
	$(function() {
		$("#joinwx_link").click(function() {
			$.post("${path}wxAccount/accessWx", {
				username : $("#wxAccount").val(),
				password : $.md5($("#wxPassword").val()),
				appSecret : $("#appSecret").val()
			}, function(data) {
				if (data.code == 0) {
					alert(data.msg);
					window.location = data.nextUrl;
				} else {
					$("#errMsg").html(data.msg);
				}
			});
		});
	});
</script>
</head>
<body>
	<div class="body-page">
		<div class="body-page-menu">
		</div>
		<div class="body-page-content">
			<h1>微信公众号接入管理</h1>
			<table class="table table-hover">
				<tr>
					<td>微信账号信息（用于接收用户信息）</td>
					<td><a id="joinwx_link" href="#" style="color:darkblue">一键接入</a></td>
				</tr>
				<tr>
					<td>微信公众账号</td>
					<td><input type="text" id="wxAccount" /></td>
				</tr>
				<tr>
					<td>公众账号密码</td>
					<td><input type="password" id="wxPassword" /></td>
				</tr>
				<tr>
					<td>AppSecret(应用密钥)</td>
					<td><input type="text" id="appSecret" /></td>
				</tr>
				<tr>
					<td></td>
					<td><span class="vv-errinfo" id="errMsg"></span></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
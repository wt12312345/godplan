<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>登录 - 后台管理</title>
<jsp:include page="include/pageStyle.jsp"></jsp:include>
<style type="text/css">
html,body {
	width: 100%;
	height: 100%;
	background: url(img/1.jpg) no-repeat;
	background-position: center;
	background-attachment: fixed;
	overflow: hidden;
}

#pageBody {
	position: fixed;
	top: 10%;
	width: 500px;
	left: 50%;
	margin-left: -250px;
}

.input-group {
	margin-bottom: 20px;
}

.thislogo {
	position: fixed;
	bottom: 5%;
	width: 180px;
	left: 50%;
	margin-left: -90px;
}

.btn {
	width: 100%;
}

@media screen and (max-width: 640px) {
	#pageBody {
		width: 92%;
		left: 4%;
		margin-left: 0;
	}
	.thislogo {
		width: 40%;
		left: 30%;
		margin-left: auto;
	}
}

.alert {
	margin-top: 10px;
	padding: 5px;
	word-break: break-all;
}
</style>
</head>
<body>
	<div id="pageBody">
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1"><i
				class="glyphicon glyphicon-user"></i></span> <input type="text"
				class="form-control" placeholder="登录名/手机号" id="loginName" />
		</div>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1"><i
				class="glyphicon glyphicon-lock"></i></span> <input type="password"
				class="form-control" placeholder="登录密码" id="password" />
		</div>
		<input type="button" class="btn btn-success" value="开启" onclick="aaaaaaaa()" />
		<p class="alert alert-danger" id="errInfo"></p>
	</div>
	<img class="thislogo" src="${assets}img/logo300.jpg" />
	<jsp:include page="include/pageScript.jsp"></jsp:include>
	<script>
		$(function() {
		});
		function aaaaaaaa() {
			$("#errInfo").html("");
			var loginName = $("#loginName").val();
			var password = $("#password").val();
			if (loginName == "") {
				$("#errInfo").html("用户名不能为空");
				return;
			}
			if (password == "") {
				$("#errInfo").html("密码不能为空");
				return;
			}
			var args = 'loginName=' + loginName + '&password=' + password;
			console.log(args);
			$.post(wwwPath + "m/doLogin", args, function(data) {
				if (data.code) {
					localStorage.setItem("menu-auth", JSON.stringify(data.obj));
					window.location.href = "${path}m/index";
				} else {
					$("#errInfo").html(data.msg);
				}
			});
		}
	</script>
</body>
</html>

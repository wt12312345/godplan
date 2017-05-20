<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html lang="zh">
<head>
<title>后台管理</title>
<jsp:include page="include/pageStyle.jsp"></jsp:include>
</head>
</head>
<body>
	<div class="body-top">
		<jsp:include page="include/header.jsp"></jsp:include>
	</div>
	<div class="body-page">
		<nav class="body-page-menu">
			<jsp:include page="include/leftMenu.jsp"></jsp:include>
		</nav>
		<div class="body-page-main">
			<div class="body-page-main-ctn">
				<div class="main-title">
					<h1>欢迎登陆管理后台</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="include/pageScript.jsp"></jsp:include>
</body>
</html>
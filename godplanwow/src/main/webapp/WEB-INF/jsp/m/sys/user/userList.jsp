<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>系统管理 - 用户管理</title>
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
					<h1>系统用户管理</h1>
					<div class="main-title-btn">
						<a class="btn btn-primary" href="${path}m/sys/userEdit"><i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-plus"></i></a>
					</div>
				</div>
				<div class="main-form">
					<h2>系统用户列表</h2>
					<table class="table table-bordered" id="userList">
						<thead>
							<tr>
								<th>用户名称</th>
								<th>角色分配</th>
								<th>登录账号</th>
								<th>账号状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../../include/pageScript.jsp"></jsp:include>
<script>
$(function(){
	var userList = [];
	$.get('${path}m/sys/getUserList',function(data){
		if(data.code == 1) {
			userList = data.obj;
			userList.sort(function(a,b){
				return a.role.id - b.role.id;
			});
			var html = "";
			for(var i = 0; i < userList.length; i++) {
				var user = userList[i];
				var status = user.status == 1 ? "启用" : "禁用";
				html += '<tr>'
					+ '<td>'+ user.realName +'</td>'
					+ '<td>'+ user.role.name +'</td>'
					+ '<td>'+ user.loginName +'</td>'
					+ '<td>'+ status +'</td>'
					+ '<td><a class="btn btn-primary btn-xs" href="${path}m/sys/userEdit?id='+ user.id +'"><i class="glyphicon glyphicon-pencil"></i></a></td></tr>';
			}
			$("#userList tbody").html(html);
		} else {
			alert(data.msg);
		}
	});
});
</script>
</body>
</html>

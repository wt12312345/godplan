<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>视频账户管理 - 后台管理</title>
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
					<h1>视频账户管理</h1>
					<div class="main-title-btn">
						<a class="btn btn-primary" href="${path}m/video/accountEdit"><i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-plus"></i></a>
					</div>
				</div>
				<div class="main-form">
					<h2>视频账户列表</h2>
					<table class="table table-bordered" id="tableList">
						<thead>
							<tr>
								<th>ID</th>
								<th>主体名</th>
								<th>负责人</th>
								<th>联系方式</th>
								<th>视频数</th>
								<th>播放数</th>
								<th>评论数</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../include/pageScript.jsp"></jsp:include>
	<script>
	$(function(){
		var listItem = [];
		$.post('${path}m/video/getAccountList',function(data){
			if(data.code == 1) {
				listItem = data.obj;
				for(var i = 0; i < listItem.length; i++) {
					var item = listItem[i];
					var html = "";
					html += '<tr>'
						+ '<td>'+ item.id +'</td>'
						+ '<td>'+ item.orgName +'</td>'
						+ '<td>'+ item.realName +'</td>'
						+ '<td>'+ item.mobile +'</td>'
						+ '<td>'+ item.numVideo +'</td>'
						+ '<td>'+ item.numPlay +'</td>'
						+ '<td>'+ item.numReply +'</td>'
						+ '<td><a class="btn btn-primary btn-xs" href="${path}m/video/accountEdit?id='+ item.id +'"><i class="glyphicon glyphicon-pencil"></i></a></td></tr>';
					$("#tableList tbody").append(html);
				}
			} else {
				alert(data.msg);
			}
		});
	});
	</script>
</body>
</html>

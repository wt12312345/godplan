<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>视频管理 - 管理后台</title>
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
					<h1>视频管理</h1>
				</div>
				<div class="main-form">
					<h2>视频列表</h2>
					<form id="frmSearch">
						<input type="hidden" />
						<div class="main-search">
							
						</div>
					</form>
					<table class="table table-bordered" id="tableList">
						<thead>
							<tr>
								<th>ID</th>
								<th>图片</th>
								<th>标题</th>
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
		$.post('${path}m/video/getItemList',function(data){
			if(data.code == 1) {
				listItem = data.obj;
				for(var i = 0; i < listItem.length; i++) {
					var item = listItem[i];
					var html = "";
					html += '<tr>'
						+ '<td>'+ item.id +'</td>'
						+ '<td><img src="'+ item.img +'" title="' + item.title + '" alt="' + item.title + '" /></td>'
						+ '<td>'+ item.title +'</td>'
						+ '<td>'+ item.numPlay +'</td>'
						+ '<td>'+ item.numReply +'</td>'
						+ '<td><a class="btn btn-primary btn-xs" href="${path}m/video/itemDetail?id='+ item.id +'"><i class="glyphicon glyphicon-file"></i></a></td></tr>';
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

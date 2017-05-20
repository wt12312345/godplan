<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>视频客户管理 - 后台管理</title>
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
					<h1>视频客户管理</h1>
					<div class="main-title-btn">
						<a class="btn btn-primary" href="${path}m/video/userEdit"><i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-plus"></i></a>
					</div>
				</div>
				<div class="main-form">
					<h2>视频客户列表</h2>
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
	<div class="modal fade modalwt" id="itemListWin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
	                <span class="close" data-dismiss="modal" aria-hidden="true">&times;</span>
	                <h4 class="modal-title" id="columnName"></h4>
	            </div>
				<div class="modal-body">
					<form id="frmData" name="frmData">
						<input type="hidden" name="id" id="id" />
						<table class="table table-bordered" id="itemTableList">
						<thead>
							<tr>
								<th>ID</th>
								<th>名称</th>
								<th>平台</th>
								<th style="max-width:500px;">标题</th>
								<th>播放数</th>
								<th>评论数</th>
								<!-- <th>操作</th> -->
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../include/pageScript.jsp"></jsp:include>
	<script>
	$(function(){
		var listItem = [];
		$.post('${path}m/video/getUserList',function(data){
			console.log(data);
			if(data.code == 1) {
				listItem = data.obj;
				for(var i = 0; i < listItem.length; i++) {
					var item = listItem[i];
					var html = "";
					html += '<tr>'
						+ '<td>'+ item.id +'</td>'
						+ '<td><a target="_blank" href="${path}m/video/loginV?id=' + item.id + '">'+ item.orgName +'</a></td>'
						+ '<td>'+ item.realName +'</td>'
						+ '<td>'+ item.mobile +'</td>'
						+ '<td>'+ item.numVideo +'</td>'
						+ '<td>'+ item.numPlay +'</td>'
						+ '<td>'+ item.numReply +'</td>'
						+ '<td><a class="btn btn-primary btn-xs" href="${path}m/video/userEdit?id='+ item.id +'"><i class="glyphicon glyphicon-pencil"></i></a> '
						+ ' <a class="btn btn-primary btn-xs" href="${path}m/video/userItemEdit?id=' + item.id + '"> <i class="glyphicon glyphicon-plus"></i>&nbsp;&nbsp;<i class="glyphicon glyphicon-film"></i></a></td></tr>';
					if(item.listAccount.length > 0){
						html += '<tr>' 
 							+ '<th>名下<br />账户<br /><a class="btn btn-primary btn-xs" href="${path}m/video/accountEdit?videoUserId=' + item.id + '"><i class="glyphicon glyphicon-plus"></i></a></th>'
							+ '<td colspan="7"><table class="table table-hover"><thead><th>账户名</th><th>所属平台</th>	<th>负责人</th><th>联系方式</th><th>视频数</th><th>播放数</th><th>评论数</th><th>操作</th></tr></thead><tbody>';
						for(var j = 0; j < item.listAccount.length; j++) {
							var account = item.listAccount[j];
							html += '<tr><td>' + account.name + '</td><td>' 
								+ account.sourceName + '</td><td>' 
								+ account.realName + '</td><td>' 
								+ account.mobile + '</td><td>' 
								+ account.numVideo + '</td><td>' 
								+ account.numPlay + '</td><td>' 
								+ account.numReply + '</td><td><a class="btn btn-primary btn-xs" href="${path}m/video/accountEdit?id='+ account.id +'&videoUserId=' + item.id + '""><i class="glyphicon glyphicon-pencil"></i></a></td></tr>';
							if(account.url != ""){
								html += '<tr><td></td><td colspan="7"><a href="' + account.url + '" target="_blank">' + account.url + '</a></td></tr>';
							}
						}
						html += '</tbody></table></td></tr>';
					}
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

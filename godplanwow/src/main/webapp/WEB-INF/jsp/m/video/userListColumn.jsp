<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>视频客户管理 - 管理后台</title>
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
	<jsp:include page="../include/pageScript.jsp"></jsp:include>
	<script src="${assets}plugin/echarts.min.js"></script>
	<script>
	$(function(){
		var listItem = [];
		$.post('${path}m/video/getUserListColumn',function(data){
			console.log(data);
			if(data.code == 1) {
				listItem = data.obj;
				var html = "";
				for(var i = 0; i < listItem.length; i++) {
					var item = listItem[i];
					html += '<tr>'
						+ '<td>'+ item.id +'</td>'
						+ '<td>'+ item.orgName +'</td>'
						+ '<td>'+ item.realName +'</td>'
						+ '<td>'+ item.mobile +'</td>'
						+ '<td>'+ item.numVideo +' <a class="btn btn-primary btn-xs" target="_blank" href="${path}m/video/itemList?videoUserId=' + item.id + '"><i class="glyphicon glyphicon-film"></i></a></td>'
						+ '<td>'+ item.numPlay +'</td>'
						+ '<td>'+ item.numReply +'</td>'
						+ '<td><a class="btn btn-primary btn-xs" href="${path}m/video/userEdit?id='+ item.id +'"><i class="glyphicon glyphicon-pencil"></i></a> '
						+ ' <a class="btn btn-primary btn-xs" href="${path}m/video/columnEdit?id=0&videoUserId=' + item.id + '"> <i class="glyphicon glyphicon-plus"></i>&nbsp;&nbsp;<i class="glyphicon glyphicon-film"></i></a></td></tr>';
					if(item.listColumn.length > 0){
						html += '<tr>' 
 							+ '<th>栏<br />目<br />列<br />表<br /></th>'
							+ '<td colspan="7"><table class="table table-hover"><thead><th>栏目名</th><th>视频数</th><th>播放数</th><th>评论数</th><th>操作</th></tr></thead><tbody>';
						for(var j = 0; j < item.listColumn.length; j++) {
							var column = item.listColumn[j];
							html += '<tr><td>' + column.title + '</td><td>' 
								+ column.numVideo + '  <span class="btn btn-primary btn-xs" data-toggle="modal" data-target="#itemListWin" onclick="getItemList(' + column.id + ',\'' + column.title + '\')"><i class="glyphicon glyphicon-film"></i></span></td><td>' 
								+ column.numPlay + '</td><td>' 
								+ column.numReply + '</td><td><a class="btn btn-primary btn-xs" href="${path}m/video/columnEdit?id='+ column.id +'&videoUserId=' + item.id + '""><i class="glyphicon glyphicon-pencil"></i></a></td></tr>';
						}
						html += '</tbody></table></td></tr>';
					}
				}
				$("#tableList tbody").html(html);
			} else {
				alert(data.msg);
			}
		});
	});
	
	function getItemList(_columnId,_title){
		if(ifAjax){
			return;
		}
		ifAjax = true;
		$.post('${path}m/video/getItemList',"videoColumnId="+_columnId,function(data){
			if(data.code == 1) {
				$("#columnName").html(_title);
				var listItem = data.obj;
				var html = "";
				for(var i = 0; i < listItem.length; i++) {
					var item = listItem[i];
					html += '<tr>'
						+ '<td>'+ item.id +'</td>'
						+ '<td>' + item.name + '</td>'
						+ '<td>' + item.sourceName + '</td>'
						+ '<td>'+ item.title +'</td>'
						+ '<td>'+ item.numPlay +'</td>'
						+ '<td>'+ item.numReply +'</td></tr>';
						//+ '<td><a class="btn btn-primary btn-xs" href="${path}v/itemDetail?id='+ item.id +'"><i class="glyphicon glyphicon-file"></i></a></td>';
				}
				$("#itemTableList tbody").html(html);
			} else {
				alert(data.msg);
			}
			ifAjax = false;
		});
	}
	</script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>流行词管理 - 管理后台</title>
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
					<h1>流行词管理</h1>
					<div class="main-title-btn">
						<a class="btn btn-primary" data-toggle="modal"
							data-target="#myModal" onclick="addReady()"><i
							class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i
							class="glyphicon glyphicon-plus"></i></a>
					</div>
				</div>
				<div class="main-form">
					<h2>数据列表</h2>
					<table class="table table-bordered" id="tableList">
						<thead>
							<tr>
								<th style="width:100px;">时间</th>
								<th>标题</th>
								<th>介绍</th>
								<th>标签</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<form id="frmData" name="frmData">
						<input type="hidden" name="id" id="id" />

						<table class="table table-bordered">
							<tr>
								<th>标题</th>
								<td><input type="text" class="form-control" name="title"
									id="title" /></td>
							</tr>
							<tr>
								<th>内容</th>
								<td><textarea class="form-control" name="content"
										id="content"></textarea></td>
							</tr>
							<tr>
								<th>标签</th>
								<td><input type="text" class="form-control" name="tags"
									id="tags" /></td>
							</tr>
							<tr>
								<th>时间</th>
								<td><input type="month" class="form-control" name="time"  id="time" /></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="modal-footer">
					<span class="btn btn-default" data-dismiss="modal">关闭</span> <span
						class="btn btn-primary" onclick="doSave()">保存</span>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../include/pageScript.jsp"></jsp:include>
	<script type="text/javascript">
		$(function() {
			$.get('${path}m/buzzwords', "", function(data) {
				if (data.code == 1) {
					var arr = data.obj;
					var htmlArr = [];
					for (var i = 0; i < arr.length; i++) {
						var one = arr[i];
						htmlArr[i] = createOneData(one);
					}
					$("#tableList tbody").html(htmlArr.join(''));
				} else {
					alert(data.msg);
				}
			});
		});
	
		function createOneData(one) {
			var id = one.id;
			var html = '<tr><td id="time' + id + '">' + one.time + '</td><td id="title' + id + '">' + one.title
				+ '</td><td style="max-width:500px;" id="content' + id + '">' + one.content
				+ '</td><td id="tags' + id + '">' + one.tags
				+ '</td><td><span class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal" onclick="editReady(' + one.id
				+ ')"><i class="glyphicon glyphicon-pencil"></i></span></td></tr>';
			return html;
		}
	
		function addReady() {
			selectId = 0;
			$("#id").val(0);
			$("#title").val("");
			$("#content").val("");
			$("#tags").val("");
		}
		function editReady(_id) {
			selectId = _id;
			$.get(wwwPath + "m/buzzwords/" + _id, "", function(data) {
				if (data.code == 1) {
					var one = data.obj;
					$("#id").val(one.id);
					$("#title").val(one.title);
					$("#content").val(one.content);
					$("#tags").val(one.tags);
					$("#time").val(one.time);
				} else {
					alert(data.msg);
				}
			});
		}
		var selectId = 0;
		function doSave() {
			var args = $("#frmData").serialize();
			$.post(wwwPath + "m/buzzwords/" + selectId, args, function(data) {
				if (data.code == 1) {
					var one = data.obj;
					if (selectId > 0) {
						$("#title" + selectId).html(one.title);
						$("#content" + selectId).html(one.content);
						$("#tags" + selectId).html(one.tags);
						$("#time" + selectId).html(one.time);
					} else {
						$("#tableList tbody").append(createOneData(one));
					}
					$('#myModal').modal('hide');
					alert("保存成功");
				} else {
					alert(data.msg);
				}
			});
		}
	</script>
</body>
</html>
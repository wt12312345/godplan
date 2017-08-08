<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>编年史管理 - 管理后台</title>
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
					<h1>编年史管理</h1>
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
								<th style="min-width:50px;">序号</th>
								<th style="width:100px;">时间</th>
								<th style="min-width:50px;">阵营</th>
								<th>标题</th>
								<th>介绍</th>
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
								<th>阵营</th>
								<td><select name="groupIndex" id="groupIndex"><option
											value="1">中国</option>
										<option value="2">其它</option></select></td>
							</tr>
							<tr>
								<th>图标</th>
								<td><select name="iconIndex" id="iconIndex"><option
											value="1">人</option>
										<option value="2">战事/领土</option>
										<option value="3">文学/定律/记录/悖论</option>
										<option value="4">皇帝/朝代/改革/政治</option>
										<option value="5">天灾</option>
										<option value="6">会议</option>
										<option value="7">创造/发明/发现</option>
								</select></td>
							</tr>
							<tr>
								<th>标签</th>
								<td><input type="text" class="form-control" name="tags"
									id="tags" /></td>
							</tr>
							<tr>
								<th>年</th>
								<td><input type="number" class="form-control" name="year"
									min="1900" max="2016" value="2016" id="year" /></td>
							</tr>
							<tr>
								<th>月</th>
								<td><input type="number" class="form-control" name="month"
									min="1" max="12" value="1" id="month" /></td>
							</tr>
							<tr>
								<th>日</th>
								<td><input type="number" class="form-control" name="day"
									min="1" max="31" value="1" id="day" /></td>
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
			$.get('${path}m/annalses', "", function(data) {
				if (data.code == 1) {
					var arr = data.obj;
					var htmlArr = [];
					for (var i = 0; i < arr.length; i++) {
						var one = arr[i];
						htmlArr[i] = createOneData(one, i + 1);
					}
					$("#tableList tbody").html(htmlArr.join(''));
				} else {
					alert(data.msg);
				}
			});
		});
	
		function createOneData(one, index) {
			var id = one.id;
			var html = '<tr><td>' + index
				+ '</td><td id="time' + id + '">' + (one.year < 0 ? "公元前" : "公元") + one.year + '年'
				+ one.month + '月' + one.day + '日'
				+ '</td><td id="groupName' + id + '">' + one.groupName
				+ '</td><td id="title' + id + '">' + one.title
				+ '</td><td style="max-width:500px;" id="content' + id + '">' + one.content
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
			$("#year").val(2016);
			$("#month").val(1);
			$("#day").val(1);
		}
		function editReady(_id) {
			selectId = _id;
			$.get(wwwPath + "m/annalses/" + _id, "", function(data) {
				if (data.code == 1) {
					var one = data.obj;
					$("#id").val(one.id);
					$("#title").val(one.title);
					$("#groupIndex").val(one.groupIndex);
					$("#iconIndex").val(one.iconIndex);
					$("#content").val(one.content);
					$("#tags").val(one.tags);
					$("#year").val(one.year);
					$("#month").val(one.month);
					$("#day").val(one.day);
				} else {
					alert(data.msg);
				}
			});
		}
		var selectId = 0;
		function doSave() {
			var args = $("#frmData").serialize();
			$.post(wwwPath + "m/annalses/" + selectId, args, function(data) {
				if (data.code == 1) {
					var one = data.obj;
					if (selectId > 0) {
						$("#title" + selectId).html(one.title);
						$("#content" + selectId).html(one.content);
						switch (one.groupIndex) {
						case 1:
							$("#groupName" + selectId).html("中国");
							break;
						case 2:
							$("#groupName" + selectId).html("其它");
							break;
						}
						$("#time" + selectId).html(one.year < 0 ? "公元前" : "公元" +
						one.year + '年' + one.month + '月' + one.day + '日');
					} else {
						$("#tableList tbody").append(createOneData(one, 0));
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
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>阅无止境 - 后台管理</title>
<jsp:include page="../include/pageStyle.jsp"></jsp:include>
<style type="text/css">
textarea {
	height: 100px;
}

.btn {
	margin-top: 10px;
}
</style>
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
					<h1>JOKE管理</h1>
				</div>
				<div class="main-form">
					<h2>JOKE列表</h2>
					<table class="table table-hover" id="listTable">
						<thead>
							<tr>
								<th>标题</th>
								<th>内容</th>
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
	<script type="text/javascript">
		
	</script>
	<script type="text/javascript">
		var page = 1;
		$(function() {
			$.get(wwwPath + "m/joke/getJokeList", "page=" + page, getSuc);
		});
		function ctlWin() {
			$("#addTable").toggle();
		}
		function getSuc(data) {
			if (data.code == 1) {
				var arr = data.obj;
				var htmlArr = [];
				for (var i = 0; i < arr.length; i++) {
					var one = arr[i];
					var id = one.id;
					var statusName = "";
					if (one.status == 1) {
						statusName = "冻结";
					} else if (one.status == 0) {
						statusName = "激活";
					}
					htmlArr[i] = '<tr><td><input type="text" class="form-control" value="'
							+ one.name
							+ '" id="name'
							+ id
							+ '"/>'
							+ '</td><td><textarea class="form-control" id="content'
							+ id
							+ '">'
							+ one.content
							+ '</textarea></td><td><span class="btn btn-primary" id="saveBtn'
							+ id
							+ '" onclick="saveJoke('
							+ id
							+ ')"><i class="glyphicon glyphicon-fire"></i></span> <span class="btn btn-danger btn-sm" id="joke'
							+ id
							+ '" onclick="changeStatus('
							+ id
							+ ')">'
							+ statusName + '</span><i></td>';
				}
				page++;
				$("#listTable tbody").append(
						'<tr>' + htmlArr.join('</tr><tr>') + '</tr>');
			} else {
				alert(data.msg);
			}
		}
		function changeStatus(selectId) {
			$.post(wwwPath + "m/changeStatus", "id=" + selectId,
					function(data) {
						if (data.code == 1) {
							if (data.status == 0) {
								$("#joke" + selectId).html("激活");
							} else if (data.status == 1) {
								$("#joke" + selectId).html("冻结");
							}

						} else {
							alert(data.msg);
						}
					});
		}

		function saveJoke(id) {
			var name = $("#name" + id).val();
			var content = $("#content" + id).val();
			$.post(wwwPath + "m/saveJoke", "name=" + name + "&content="
					+ content + "&id=" + id, function(data) {
				if (data.code == 1) {
					alert("OK");
				} else {
					alert(data.msg);
				}
			});
		}
	</script>
</body>
</html>
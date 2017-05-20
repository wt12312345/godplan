<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>编年史编辑 - 管理后台</title>
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
					<h1>编年史编辑</h1>
				</div>
				<div class="main-form">
					<h2>信息填写</h2>
					<form name="frmData" id="frmData" method="post">
						<input type="hidden" id="id" name="id" readonly="readonly" value="${item.id}">
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>标题</label>
								</div>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="title" id="title" value="${item.title }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>描述</label>
								</div>
								<div class="col-sm-10">
									<textarea class="form-control" name="content" id="content">${item.content }</textarea>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>年</label>
								</div>
								<div class="col-sm-10">
									<input type="number" class="form-control" name="year" id="year" value="${item.year }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>月</label>
								</div>
								<div class="col-sm-10">
									<input type="number" class="form-control" name="month" id="month" value="${item.month }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>日</label>
								</div>
								<div class="col-sm-10">
									<input type="number" class="form-control" name="day" id="day" value="${item.day }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>时</label>
								</div>
								<div class="col-sm-10">
									<input type="number" class="form-control" name="hour" id="hour" value="${item.hour }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>分</label>
								</div>
								<div class="col-sm-10">
									<input type="number" class="form-control" name="minute" id="minute" value="${item.minute }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>秒</label>
								</div>
								<div class="col-sm-10">
									<input type="number" class="form-control" name="second" id="second" value="${item.second }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<span class="btn btn-primary" onclick="doSave()"> <i class="glyphicon glyphicon-floppy-disk"></i> 保存
									</span>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../include/pageScript.jsp"></jsp:include>
	<script type="text/javascript">
		$(function() {

		});

		function doSave() {
			var title = $("#title").val();
			if (title == "") {
				alert("标题不能为空");
				return;
			}
			if(ifAjax){
				return;
			}
			ifAjax = true;
			var args = $("#frmData").serialize();
			$.post("${path}m/annals/saveAnnals", args, function(data) {
				if (data.code == 1) {
					alert("保存成功");
					window.location.href = '${path}m/annals/list';
				} else {
					alert("失败！" + data.msg);
				}
				ifAjax = false;
			});
		}
	</script>
</body>
</html>

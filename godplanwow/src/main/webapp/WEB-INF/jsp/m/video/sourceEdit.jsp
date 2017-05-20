<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>视频来源编辑 - 管理后台</title>
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
					<h1>视频来源编辑</h1>
				</div>
				<div class="main-form">
					<h2>信息填写</h2>
					<form name="frmData" id="frmData" method="post">
						<input type="hidden" id="id" name="id" readonly="readonly"
							value="${item.id}">
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>名称</label>
								</div>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="name"
										id="name" value="${item.name }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<span class="btn btn-primary" onclick="doSave()"> <i
										class="glyphicon glyphicon-floppy-disk"></i> 保存
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
			var name = $("#name").val();
			if (name == "") {
				alert("名称不能为空");
				return;
			}
			if(ifAjax){
				return;
			}
			ifAjax = true;
			var args = $("#frmData").serialize();
			$.post("${path}m/video/saveSource", args, function(data) {
				if (data.code == 1) {
					alert("保存成功");
					window.location.href = '${path}m/video/sourceList';
				} else {
					alert("失败！" + data.msg);
				}
				ifAjax = false;
			});
		}
	</script>
</body>
</html>

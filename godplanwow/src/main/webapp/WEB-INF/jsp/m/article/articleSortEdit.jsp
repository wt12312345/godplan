<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>添加/修改类别 - 管理后台</title>
<link rel="stylesheet"
	href="${assets}plugin/bootstrap/css/bootstrap.min.css">
<link href="${assets }css/admin/total.css" rel="stylesheet"
	type="text/css" />
<link href="${assets }plugin/wt/css/wt.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
#uploadPreview {
	width: 150px;
	height: 150px;
	background-position: center center;
	background-size: cover;
	border: 4px solid #fff;
	-webkit-box-shadow: 0 0 1px 1px rgba(0, 0, 0, .3);
	display: inline-block;
}

.menu_ul {
	height: 1180px;
}
</style>
<script src="${assets}plugin/jquery-1.11.1.min.js"></script>
<script src="${assets}plugin/wt/js/wt.js" type="text/javascript"></script>
<%--百度富文本 要导入的文件--%>
<script type="text/javascript" charset="utf-8"
	src="${assets }plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${assets }plugin/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${assets }plugin/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript"
	src="${assets }plugin/ajax-fileuploader/ajaxfileupload.js"></script>
<script type="text/javascript">
	function submitForm() {
		if ($("#title").val() == "") {
			wtAlert("类别名不能为空");
			return;
		}
		if ($("#sortIndex").val() == "") {
			$("#sortIndex").val(0);
		}

		$("#doSave").submit();
	}
	$(function() {
		wtJquery.ValidateNumberWithZeroStart("sortIndex");
	});

	function addUpload(sender) {
		var objPreviewFake = document.getElementById("uploadPreview");
		if (sender.files && sender.files[0]) { //这里面就是chrome和ff可以兼容的了 
			var files = !!sender.files ? sender.files : [];
			if (/^image/.test(files[0].type)) {
				var reader = new FileReader();
				reader.readAsDataURL(files[0]);
				reader.onloadend = function() {
					$("#uploadPreview").html(
							'<img alt="" src="'+this.result+'" />');
				};
			} else {
				wtAlert("图片类型必须是.gif,jpeg,jpg,png中的一种！");
				return false;
			}
		} else if (objPreviewFake.filters) {
			sender.select();
			sender.blur();
			var imgSrc = document.selection.createRange().text;
			objPreviewFake.filters
					.item('DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;
		}
		$.ajaxFileUpload({
			url : '${path}upload/uploadimg/dzka/home',
			secureuri : false,
			fileElementId : 'imgFile',
			dataType : 'json',
			data : {
				preImg : $("#titleImg").val(),
			},
			success : function(data, status) {
				if (data.code == 0) {
					$("#titleImg").val(data.msg);
				} else {
					wtAlert(data.msg);
				}
			},
			error : function(data, status, e) {
				wtAlert("上传失败");
			}
		});
	}
</script>
</head>

<body>
	<div class="body-top">
		<jsp:include page="../include/header.jsp"></jsp:include>
	</div>
	<div class="body-page">
		<div class="body-page-menu">
			<jsp:include page="../include/leftMenu.jsp"></jsp:include>
		</div>
		<div class="body-page-content">
			<h1>文章类别列表</h1>
			<form action="${path}m/saveArticleSort" id="doSave" method="post">
				<input type="hidden" value="${sort.id }" name="id" /> <input
					type="hidden" value="${sort.version}" name="version" />
				<table class="table table-hover" id="listTable">
					<tr>
						<th style="width:100px;">类别名</th>
						<td><input type="text" id="title" name="title"
							value="${sort.title}" /></td>
					</tr>
					<tr>
						<th>描述</th>
						<td><input id="remark" type="text" name="remark"
							value="${sort.remark}" /></td>
					</tr>
					<tr>
						<th>排序</th>
						<td><input id="sortIndex" type="text" name="sortIndex"
							value="${sort.sortIndex}" /></td>
					</tr>
					<tr>
						<th>图片</th>
						<td>
							<div id="imageDisplayDiv"></div>
							<div class="imgpreview" id="uploadPreview"
								style="background-image:url('${trends}${sort.imgUrl}');"></div>
							<div style="padding-bottom:5px;">
								<input type="file" name="imgFile" id="imgFile"
									onchange="addUpload(this)" /> <input type="hidden"
									name="titleImg" id="titleImg" value="${sort.imgUrl}" />
							</div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input class="btn btn-default" type="button" value="保存"
							id="submitBtn" onclick="submitForm()"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>

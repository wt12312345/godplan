<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>视频栏目编辑 - 后台管理</title>
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
					<h1>视频栏目编辑</h1>
				</div>
				<div class="main-form">
					<h2>信息填写</h2>
					<form name="frmData" id="frmData">
						<input type="hidden" id="videoUserId" name="videoUserId"
							readonly="readonly" value="${videoUser.id}"> <input
							type="hidden" id="id" name="id" readonly="readonly"
							value="${item.id}">
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>所属客户</label>
								</div>
								<div class="col-sm-10">${videoUser.orgName }</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>专题名</label>
								</div>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="title" id="title"
										value="${item.title }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>描述</label>
								</div>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="content"
										id="content" value="${item.content }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2">
									<label>视频链接</label>
								</div>
								<div class="col-sm-10" id="urlList">
									<c:forEach items="${item.listItem }" var="videoItem">
										<div class="row" id="videoItem${videoItem.id}">
											<div class="form-group col-sm-12">
												<div class="col-sm-2">
													<span class="label label-danger" onclick="delVideoItem(${videoItem.id })"><i class="glyphicon glyphicon-remove"></i></span> 
													&nbsp;
													<label>${videoItem.sourceName }</label>
												</div>
												<div class="col-sm-3">
													<input type="text" class="form-control" name="videoItemName" value="${videoItem.name }" />
												</div>
												<div class="col-sm-7">
													<input type="hidden" id="videoItemId${videoItem.id }" name="videoItemId" value="${videoItem.id }" />
													<input type="hidden" id="videoItemStatus${videoItem.id }" name="videoItemStatus" value="${videoItem.status }" />
													<input type="text" class="form-control" name="videoItemUrl" value="${videoItem.url }" />
												</div>
											</div>
										</div>
									</c:forEach>
									<div class="row" id="addUrl">
										<div class="form-group col-sm-12">
											<div class="col-sm-2"></div>
											<div class="col-sm-10">
												<span class="btn btn-primary" onclick="addUrl()"> <i
													class="glyphicon glyphicon-plus"></i>
												</span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<span class="btn btn-primary" onclick="doSave()"> <i
										class="glyphicon glyphicon-fire"></i> 保存
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
	<script type="text/javascript" src="${assets}plugin/bootbox.min.js"></script>
	<script type="text/javascript">
		$(function() {

		});

		//新增的在ID前加0
		var newVideoItemIndex = 1;
		function addUrl() {
			var html = '<div class="row" id="videoItem0' + newVideoItemIndex + '">'
				+ '<div class="form-group col-sm-12"><div class="col-sm-2"><span class="label label-danger" onclick="delVideoItem(\'0' + newVideoItemIndex + '\')"><i class="glyphicon glyphicon-remove"></i></span></div>'
				+ '<div class="col-sm-3"><input type="text" class="form-control" name="videoItemName" value="" /></div>'
			    + '<div class="col-sm-7"><input type="hidden" id="videoItemId0' + newVideoItemIndex + '" name="videoItemId" value="0" /><input type="hidden" id="videoItemStatus0' + newVideoItemIndex + '" name="videoItemStatus" value="1" /><input type="text" class="form-control" name="videoItemUrl" value="" /></div></div></div>';
			$("#addUrl").before(html);
			newVideoItemIndex++;
		}

		function delVideoItem(_videoItemId) {
			if ($("#videoItemId" + _videoItemId).val() > 0) {
				$("#videoItemStatus" + _videoItemId).val(-1);
				$("#videoItem" + _videoItemId).hide();
			} else {
				$("#videoItem" + _videoItemId).remove();
			}
		}

		function doSave() {
			var name = $("#name").val();
			if (name == "") {
				alert("专题名不能为空");
				return;
			}
			var hasUrl = false;
			$('input[name="videoItemUrl"]').each(function(i) {
				var url = $(this).val();
				if (url != "") {
					hasUrl = true;
				}
			});
			if (!hasUrl) {
				alert("至少填写一个视频链接");
				return;
			}
			if (ifAjax) {
				return;
			}
			ifAjax = true;
			var args = $("#frmData").serialize();
			$.post("${path}m/video/saveColumn", args, function(data) {
				if (data.code == 1) {
					alert("保存成功");
					wtCom.OpenUrl(wwwPath + "m/video/columnEdit?id=" + data.id
							+ "&videoUserId=${videoUser.id}");
				} else {
					alert("失败！" + data.msg);
					ifAjax = false;
				}
			});
		}
	</script>
</body>
</html>

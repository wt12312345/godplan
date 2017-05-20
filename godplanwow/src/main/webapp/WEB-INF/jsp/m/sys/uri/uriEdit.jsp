<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>功能接口编辑 - 菜单管理</title>
<jsp:include page="../../include/pageStyle.jsp"></jsp:include>
</head>
<body>
	<div class="body-top">
		<jsp:include page="../../include/header.jsp"></jsp:include>
	</div>
	<div class="body-page">
		<div class="body-page-menu">
			<jsp:include page="../../include/leftMenu.jsp"></jsp:include>
		</div>
		<div class="body-page-main">
			<div class="body-page-main-ctn">
				<div class="main-title">
					<h1>操作权限编辑</h1>
				</div>
				<div class="main-form">
					<h2>信息填写</h2>
					<form name="frmData" id="frmData" method="post">
						<input type="hidden" name="id" id="id" value="${item.id }" />
						<input type="hidden" name="parentId" id="parentId" value="${itemParent.id }" />
						<table class="table table-hover">
							<tr>
								<th>上级操作权限</th>
								<th>${itemParent.name }</th>
							</tr>
							<tr>
								<th>名称</th>
								<td><input type="text" class="form-control" name="name" id="name"
									value="${item.name }" />
							</tr>
							<tr>
								<th>链接地址</th>
								<td><input type="text" class="form-control" name="url" id="url"
									value="${item.url }" />
							</tr>
							<tr>
								<th>操作码</th>
								<td><input type="text" class="form-control" name="action" id="action"
									value="${item.action }" />
							</tr>
							<tr>
								<th>备注</th>
								<td><input type="text" class="form-control" name="remark" id="remark"
									value="${item.remark }" />
							</tr>
							<tr>
								<th>&nbsp;</th>
								<th><span class="btn btn-primary" onclick="doSave()"><i class="glyphicon glyphicon-fire"></i> 保存</span></th>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../../include/pageScript.jsp"></jsp:include>
<script type="text/javascript">
	function doSave() {
		var args = $("#frmData").serialize();
		$.post("${path}m/sys/saveUri", args, function(data) {
			if (data.code == 1) {
				wtCom.OpenUrl("${path}m/sys/uriList");
			} else {
				alert(data.msg);
			}
		});
	}
</script>
</body>
</html>

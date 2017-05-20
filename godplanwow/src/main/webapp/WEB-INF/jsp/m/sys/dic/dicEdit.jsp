<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html lang="zh">
<head>
<title>字典编辑 - 后台管理</title>
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
					<h1>字典编辑</h1>
					<div class="main-title-btn">
						<a class="btn btn-primary" href="${path}m/sys/menuEdit"><i class="glyphicon glyphicon-plus"></i> 新增菜单模块</a>
					</div>
				</div>
				<div class="main-form">
					<h2>信息填写</h2>
					<form id="frmData" name="frmData" method="post"
						action="${path}m/sys/saveDic">
						<table class="table table-hover">
							<tr>
								<th>关键词</th>
								<td>
									<input type="text" class="form-control" name="keyId" id="keyId" value="${item.keyId }" /> 
									<input type="hidden" name="id" value="${item.id }" /> 
								</td>
							</tr>
							<tr>
								<th>名称</th>
								<td><input type="text" class="form-control" name="name"
									value="${item.name }" /></td>
							</tr>
							<tr>
								<th>valint</th>
								<td><input type="text" class="form-control" name="valint" id="valint"
									value="${item.valint }" /></td>
							</tr>
							<tr>
								<th>valdbl</th>
								<td><input type="text" class="form-control" name="valdbl" id="valdbl"
									value="${item.valdbl }" /></td>
							</tr>
							<tr>
								<th>valstr</th>
								<td><input type="text" class="form-control" name="valstr" id="valstr"
									value="${item.valstr }" /></td>
							</tr>
							<tr>
								<th>活动描述</th>
								<td><input type="text" class="form-control" name="remark"
									value="${item.remark }" /></td>
							</tr>
							<tr>
								<td class="title">&nbsp;</td>
								<td>
									<span class="btn btn-primary" onclick="doSave()">
									    <i class="glyphicon glyphicon-fire"></i> 保存
									</span>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>	
		</div>
	</div>
<jsp:include page="../../include/pageScript.jsp"></jsp:include>
<script type="text/javascript">
	var keyid = "";
	function doSave() {
		keyid = $("#keyId").val();
		if (keyid == "") {
			alert("关键字不能为空");
			return false;
		}
		$("#frmData").submit();
	}
</script>
</body>
</html>
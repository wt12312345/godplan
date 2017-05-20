<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="stylesheet"
	href="${assets}plugin/bootstrap/css/bootstrap.min.css">
<link href="${assets }css/admin/total.css" rel="stylesheet"
	type="text/css" />
<title>微信自定义菜单 - 后台管理</title>
<script src="${assets}plugin/jquery-1.11.1.min.js"></script>
<script src="${assets}plugin/wt/js/wt.js" type="text/javascript"></script>
<script src="${assets}js/godPlan.js?id=4" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		wtJquery.Ajax(wwwPath + "m/wxFun/wxGetMenus", "", getSuc);
	});

	function getSuc(data) {
		var arr = data.obj;
		var html = '';
		for (var i = 0; i < 3; i++) {
			var name = "";
			var ctn = "";
			var oneArr = null;
			if (i < arr.length) {
				oneArr = arr[i];
				name = oneArr.name;
				if (oneArr.type == "view") {
					ctn = oneArr.url;
				} else if (oneArr.type == "click") {
					ctn = oneArr.key;
				}
			}
			html += '<tr><th>菜单' + (i + 1)
					+ '</th><th></th><td>名称 <input type="text" name="name" value="' + name
					+ '" /></td><td>链接/关键词 <input type="text" name="ctn" value="' + ctn + '" /></td></tr>';
			for (var j = 0; j < 6; j++) {
				var childArr = null;
				var chiledName = "";
				var childCtn = "";
				if (oneArr != null) {
					childArr = oneArr.sub_button;
					if (j < childArr.length) {
						oneChildArr = childArr[j];
						chiledName = oneChildArr.name;
						if (oneChildArr.type == "view") {
							childCtn = oneChildArr.url;
						} else if (oneChildArr.type == "click") {
							childCtn = oneChildArr.key;
						}
					}
				}

				html += '<tr><th></th><th>子菜单'
						+ (j + 1)
						+ '</th><td>名称 <input type="text" name="childname'
						+ (i + 1)
						+ '" value="'
						+ chiledName
						+ '" /></td><td>链接/关键词 <input type="text" name="childctn'
						+ (i + 1) + '" value="' + childCtn + '" /></td></tr>';
			}
			$("#menuTable").html(html);
		}
	}
	function saveMenu() {
		$("#submitForm").submit();
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
			<div class="vv-search-model">
				<span class="btn btn-default" onclick="saveMenu()"> <i
					class="glyphicon glyphicon-search"></i> 保存菜单
				</span>
			</div>
			<h1>自定义菜单</h1>
			<form id="submitForm" name="submitForm" action="${path }m/wxFun/wxSaveMenus" method="post">
				<table class="table table-hover" id="menuTable">
				</table>
			</form>
		</div>
	</div>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>系统管理 - 系统用户编辑</title>
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
					<h1>系统用户编辑</h1>
				</div>
				<div class="main-form">
					<h2>信息填写</h2>
					<form name="frmData" id="frmData" method="post">
						<input type="hidden" id="id" name="id" readonly="readonly" value="${item.id}">
						<div class="row">
							<div class="form-group col-md-12">
								<div class="col-md-2">
									<label>登录账号</label>	
								</div>
								<div class="col-md-10">
									<input type="text" class="form-control" name="loginName" id="loginName" value="${item.loginName }" class="bs_ipt" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-12">
								<div class="col-md-2">
									<label>登录密码</label>	
								</div>
								<div class="col-md-10">
									<input type="password" class="form-control" name="password" id="pwd" class="bs_ipt" />	
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-12">
								<div class="col-md-2">
									<label>用户名</label>	
								</div>
								<div class="col-md-10">
									<input type="text" class="form-control" name="realName" id="realName" value="${item.realName }" class="bs_ipt" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-12">
								<div class="col-md-2">
									<label>邮箱</label>	
								</div>
								<div class="col-md-10">
									<input type="text" class="form-control" name="email" id="email" value="${item.email }" class="bs_ipt" />	
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-12">
								<div class="col-md-2">
									<label>状态</label>	
								</div>
								<div class="col-md-10">
									<input type="radio" name="status" value="1" /><label>启用</label>
									<input style="margin-left:10px;" type="radio" name="status" value="0" /><label>冻结</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-12">
								<div class="col-md-2">
									<label>角色</label>	
								</div>
								<div class="col-md-10">
									<select class="form-control" name="role" id="role">
										<option value="0">请选择</option>
										<c:forEach items="${listRole}" var="role" varStatus="num">
											<option value="${role.id }">${role.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-12">
								<div class="col-md-2">
									<label>操作权限</label>
								</div>
								<div class="col-md-10" id="roleUri"></div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-12">
								<div class="col-md-2">
									<label>邮件权限权限</label>
								</div>
								<div class="col-md-10" id="roleMail"></div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-12">
								<div class="col-md-2"></div>
								<div class="col-md-10">
									<span class="btn btn-primary" onclick="doSave()">
									    <i class="glyphicon glyphicon-floppy-disk"></i> 保存
									</span>
								</div>
							</div>
						</div>					
					</form>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../../include/pageScript.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$("input:radio[name='status'][value=${item.status}]").attr("checked",true);
		$("#role option[value='${item.role.id}']").attr("selected", true);
		
		//  由角色下拉框change触发的重新绘制权限板块
		$("#role").change(function(){
			var id = $("#role").val();
			getRoleAuth(id);
		});
			
		//  由用户加载触发的重新绘制权限板块，根据之前保存的勾选项进行勾选
		var tampUri = "${item.uri}";
		var tampUriMail = "${item.uriMail}";
		arrUriId = tampUri.split(",");
		arrUriMailId = tampUriMail.split(",");
		//  获得角色权限
		getRoleAuth("${item.role.id}");
	});
	
	function doSave() {
		var loginName = $("#loginName").val();
		var realName = $("#realName").val();
		if (realName == "") {
			alert("名称不能为空");
			return false;
		} else if (loginName == 0) {
			alert("登录名不能为空");
			return false;
		}
		var args = $("#frmData").serialize();
		
		$.post("${path}m/sys/saveUser", args, function(data) {
			if (data.code == 1) {
				window.location.href = '${path}m/sys/userList';
			} else {
				alert(data.msg);
			}
		});
	}

	function getRoleAuth(roleId){
		$.get("${path}m/sys/getUserRole?id=" + roleId, "", function(data) {
			console.log(data);
			if (data.code == 1) {
				var arrUri = data.obj.listUri;
				var arrMail = data.obj.listMail;
				
				$("#roleUri").empty();
				for(var i = 0;i < arrUri.length;i++){
					createPanel(null, arrUri[i],"uri","roleUri");
				}
				
				$("#roleMail").empty();
				$("#roleStorage").empty();
				$("#roleSupplier").empty();
				createPanel("邮件通知列表", arrMail,"uriMail","roleMail");	
				
				$("div.panel").delegate('a','click',function(event){
					event.preventDefault();
					var $this = $(this);
					var $target = $(event.delegateTarget);
					if($this.hasClass('select_all')) {
						$target.find('input[type="checkbox"]').each(function(){
							$(this).prop('checked',true);
						});
					}
				});
				
				setUserAuth();
			} else {
				alert(data.msg);
			}
		});
	}
	
	//  数据包,name,容器id
	function createPanel(_title, _arr, _name, _ulId){
		var html = "";
		var title = _title || _arr.name;
		html += '<div class="panel panel-default"><div class="panel-heading">'
			+ '<div class="row"><div class="col-md-6"><h3 class="panel-title">'+ title +'</h3></div>'
			+ '<div class="col-md-6"><a class="pull-right select_all">全选</a></div></div>'	
			+ '</div>'
			+ '<div class="panel-body"><ul class="list-group">';
		
		data = _arr.listChild || _arr;
		for(var i = 0; i < data.length; i++) {
			html += '<li class="list-group-item">'
				+ '<input type="checkbox" name="'+ _name + '" id="'+ _name + data[i].id +'" value="'+ data[i].id +'" />&nbsp;&nbsp;'+ data[i].name +'</li>';
		}
		
		html += '</ul></div></div>';
		
		$("#" + _ulId).append(html);
	}
	
	// 用户已获得的权限
	var arrUriId = [];
	var arrUriMailId = [];
	var arrUriStorageId = [];
	var arrUriSupplierId = [];
	
	function setUserAuth() {
		for(var i = 0; i < arrUriId.length; i++) {
			$("#uri"+ arrUriId[i]).attr('checked',true);
		}
		
		for(var j = 0; j < arrUriMailId.length; j++) {
			$("#uriMail"+ arrUriMailId[j]).attr('checked',true);
		}
	}
</script>
</body>
</html>

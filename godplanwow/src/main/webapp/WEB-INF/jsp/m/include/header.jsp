<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="body-top-left">
	<div class="body-top-left">
		<img class="body-top-img" src="${assets}img/logo100.jpg">
	</div>
</div>
<div class="body-top-right">
	<div class="body-top-right-info body-top-right-user" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		<img class="body-top-img" src="${assets}img/logo.jpg"> 
		<span>${sessionScope.UserSys.name}</span>
		<span class="glyphicon glyphicon-menu-down"></span>
	</div>
	<ul class="dropdown-menu">
		<li><a href="${path}m/editPassword">修改密码</a></li>
		<li><a href="${path}m/page/loginOut">退出 </a></li>
	</ul>
</div>

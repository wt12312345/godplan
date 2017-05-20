<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<div class="body-page-menu-title">管理后台</div>
<ul class="body-page-menu-list" id="side-menu">
	<li><a class="index" href="${path}m/index"><i
			class="glyphicon glyphicon-home"></i>首页</a></li>

	<c:forEach items="${sessionScope.UserSys.listMenu}" var="item"
		varStatus="itemIndex">
		<li id="menu-${itemIndex.index }"><a role="button"
			data-toggle="collapse" data-parent="#side-menu"
			href="#menu-${itemIndex.index }-child" aria-expanded="true"
			aria-controls="menu-${itemIndex.index }-child"> <i
				class="glyphicon ${item.iconName }"></i> ${item.name } <span
				class="glyphicon glyphicon-chevron-down"></span>
		</a>
			<ul id="menu-${itemIndex.index }-child"
				class="collapse body-page-menu-item">
				<c:forEach items="${item.listSub}" var="itemSub">
					<li><a id="${itemSub.id }" data-auth="${itemSub.action }"
						href="${path}${itemSub.url }">${itemSub.name }</a></li>
				</c:forEach>
			</ul></li>
	</c:forEach>
</ul>
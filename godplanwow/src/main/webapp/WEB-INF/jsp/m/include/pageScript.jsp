<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 页面公共脚本 -->
<script src="${assets}plugin/jquery-1.11.1.min.js"></script>
<script src="${assets}plugin/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${assets}plugin/wt/js/wt.js"></script>
<script src="${assets}js/godPlan.js" type="text/javascript"></script>
<script>
var ifAjax = false;
/* 用户登出 */
function logout() {
	wtJquery.Ajax("${path}m/loginOut", "", function(data) {
		if (data.code == 1) {
			window.location = "${path}manage";
		} else {
			alert("登出异常");
		}
	});
}

//菜单生成
var menu_auth = JSON.parse(localStorage.getItem('menu-auth')) || "";
var menu = menu_auth.listMenu;
var auth = menu_auth.listUri;
var menu_current = "";
//  页面加载完成后执行
$(function(){
	for(var i = 0; i < menu.length; i++) {
		var menu_html = '<li id="menu-' + menu[i].id +'">'
			+ '<a role="button" data-toggle="collapse" data-parent="#side-menu" href="#menu-'+ menu[i].id +'-child" aria-expanded="true" aria-controls="menu-'+ menu[i].id +'-child">'
			+ '<i class="glyphicon '+ menu[i].iconName +'"></i>'
	  		+  menu[i].name + '<span class="glyphicon glyphicon-chevron-down"></span>'
	 		+ '</a><ul id="menu-'+ menu[i].id +'-child" class="collapse body-page-menu-item">';
		
 		if(menu[i].listChild){
			for(var j = 0; j < menu[i].listChild.length; j++) {
				var a_id = 'menu-' + i + '-' + j;
				var menu_child = menu[i].listChild[j];
				menu_html += '<li><a data-auth="'+ menu_child.action +'" id=' + a_id + ' href="${path}'+ menu_child.url +'">'+ menu_child.name + '</a></li>';
			}
			menu_html += '</ul></li>';
		}
		$("#side-menu").append(menu_html);
	}

	$("ul.body-page-menu-item a").click(function(){
		var menu_id = $(this).attr("id");
		menu_current = menu_id;
	});

	$("a.index").click(function(){
		menu_current = 'index';
	});

	window.onbeforeunload = function(e) {
		sessionStorage.setItem('menu_current', menu_current);
	};
	
	//展开当前页
	menu_current = sessionStorage.getItem('menu_current') || "";
	
	if(window.location.pathname.indexOf('index') == -1){
		$("#"+menu_current).parent().addClass("active");
		$("#"+menu_current).parent().parent().addClass("in");
	}	
});
</script>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 页面公共脚本 -->
<script src="${assets}plugin/jquery-1.11.1.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="${assets}plugin/wt/js/wt.js"></script>
<script src="${assets}js/godPlan.js" type="text/javascript"></script>
<script>
	var ifAjax = false;
	/* 用户登出 */
	function logout() {
		wtJquery.Ajax("${path}m/page/loginOut", "", function(data) {
			if (data.code == 1) {
				window.location = "${path}m/page/login";
			} else {
				alert("登出异常");
			}
		});
	}

	//菜单生成
	var menu_current = "";
	//  页面加载完成后执行
	$(function() {
		$("ul.body-page-menu-item a").click(function() {
			var menu_id = $(this).attr("id");
			menu_current = menu_id;
		});

		$("a.index").click(function() {
			menu_current = 'index';
		});

		window.onbeforeunload = function(e) {
			sessionStorage.setItem('menu_current', menu_current);
		};

		//展开当前页
		menu_current = sessionStorage.getItem('menu_current') || "";

		if (window.location.pathname.indexOf('index') == -1) {
			$("#" + menu_current).parent().addClass("active");
			$("#" + menu_current).parent().parent().addClass("in");
		}
	});
</script>
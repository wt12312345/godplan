$(function() {
	getFirstList();

	var imgUrl = asstesImg + "fx.jpg";
	var title = "阅无止境 - 阅二十一";
	var desc = "21世纪阅出精彩！尽在 [阅二十一]";
	godPlan.GetWxJs(title, desc, wwwPath, imgUrl, fxSucFun);
});
function fxSucFun() {

}
var scrollHandler = function() {
	var winH = $(window).height();
	var pageH = $(document.body).height(); // 页面总高度
	var scrollT = $(window).scrollTop(); // 滚动条top
	var aa = (pageH - winH - scrollT) / winH;
	if (aa < 0.02) {
		getMore();
	}
};

// 初始化的第一组数据
var arr1 = [];
// 后面刷新都用第二组数据加载
var arr2 = [];
var page = 1;
function getFirstList() {
	wtJquery.Ajax(wwwPath + "getJokeList", "", function(data) {
		if (data.code == 1) {
			if (page == 1) {
				$(window).scroll(scrollHandler);
				initHtml(data.obj);
				page++;
				// 第一页，页面刚进来，缓存下一组数据
				getList();
			}
		} else {
			alert(data.msg);
		}
	});
}
// 加载下一组数据
function getMore() {
	initHtml(arr2);
	getList();
}

// 根据数据生成html
function initHtml(thisArr) {
	var htmlArr = [];
	for (var i = 0; i < thisArr.length; i++) {
		var one = thisArr[i];
		htmlArr[i] = '<p class="jokelist-title">[' + one.nickName
				+ ']</p><p class="jokelist-content">' + one.content
				+ '</p><p class="jokelist-name">—— ' + one.name + '</p>';
	}
	var html = '<li>' + htmlArr.join('</li><li>') + '</li>';
	$("#jokeList").append(html);
}
// 获取下一组数据
function getList() {
	wtJquery.Ajax(wwwPath + "getJokeList", "", function(data) {
		if (data.code == 1) {
			arr2 = data.obj;
			page++;
		}
	});
}
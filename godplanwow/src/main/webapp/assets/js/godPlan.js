var wwwPath = "http://" + window.location.host + '/';
var wwwApi = "http://" + window.location.host + '/m/';
var imgPath = wwwPath + "trends/img/";
var asstesImg = wwwPath + "assets/img/";
var enumStatus = {
	enable : {
		index : 1,
		name : "可用"
	},
	freeze : {
		index : 1,
		name : "可用"
	}
};
var api = {
	doLogin : {
		url : wwwApi + "userSyses/login",
		method : "POST"
	}
};

var ajax = {
	del : function(_url, _params, _sucFun) {
		var url = wwwPath + _url;
		$.ajax({
			url : url,
			type : 'delete',
			dataType : 'json',
			data : _params,
			xhrFields : {
				withCredentials : true
			},
			success : function(data) {
				_sucFun(data);
			},
			error : function(data) {
				alert("err：系统错误！");
			}
		});
	}
};

var godPlan = {
	ShowFxWin : function() {
		var html = '<div class="wt-win-bak"></div><div class="wt-win fxwin" onclick="wtHideWin()"><img class="fximg" alt="" src="'
			+ imgPath + 'fx.png" /></div></div>';
		$("body").append(html);
	},
	wxjsTitle : "",
	wxjsDesc : "",
	wxjsImgUrl : "",
	wxjsUrl : "",
	fxSucFun : null,
	GetWxJs : function(title, desc, url, wxjsImgUrl, fxSucFun) {
		godPlan.wxjsTitle = title;
		godPlan.wxjsDesc = desc;
		godPlan.wxjsImgUrl = wxjsImgUrl;
		godPlan.wxjsUrl = url;
		wtJquery.Ajax(wwwPath + "wx/getWxSdk", "url="
			+ location.href.split('#')[0], function(data) {
				var args = {
					appId : data.obj.appId,
					nonceStr : data.obj.nonceStr,
					timestamp : data.obj.timestamp,
					signature : data.obj.signature
				};
				wtWx.SetWxJsConfig(1, args, godPlan.initWxJsSuc);
			});
		if (fxSucFun) {
			godPlan.fxSucFun = fxSucFun;
		// 回调里有参数arg,1 朋友，2 朋友圈，3 QQ，4 QQ微博
		}
	},
	initWxJsSuc : function() {
		wtWx.WxFx(godPlan.wxjsTitle, godPlan.wxjsDesc, godPlan.wxjsUrl,
			godPlan.wxjsImgUrl, godPlan.fxSucFun, null);
	}
};
package com.wt.wx.constant;

import com.wt.base.webconf.PathConf;

public class WxUrl {
	public static String AppId = null;
	public static String AppSecret = null;
	public static String PayMchId = null;
	public static String PayKey = null;
	public static String PayMchPath = null;

	public static String token = null;
	public static long tokenTime = 0;
	public static String jsapiTicket = null;
	public static long jsapiTicketTime = 0;

	private static final String URL_ACESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	public static final String URL_MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";
	public static final String URL_OAUTH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?";
	public static final String URL_MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
	// 模板消息推送
	public static final String URL_PUSH_MODULE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	public static final String ACCESS_TOKEN = "access_token";
	public static final String ERROR_CODE = "errcode";
	public static final String TICKET = "ticket";
	public static final String EXPIRES_TIME = "expires_in";
	// public static String URL_AUTHOR =
	// "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
	// + AppId
	// + "&redirect_uri=http%3A%2F%2F"
	// + PathConf.getRootHost()
	// + "%2Fauthen%2Fcallback2%2Findex";
	public static String URL_AUTHOR_END_INFO = "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	public static String URL_AUTHOR_END_BASE = "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	// 申请退款
	public static String URL_MONEYBACK = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	// 现金红包
	public static String SendRedPack = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	// 微信支付统一接口（post）
	public static String URL_PAY_POST = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 退款查询接口（post）
	public static String URL_PAY_QUERY = "https://api.mch.weixin.qq.com/pay/orderquery";

	public static String URL_AUTHOR() {
		return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ AppId + "&redirect_uri=http%3A%2F%2F"
				+ PathConf.getRootHost() + "%2Fwx%2Fcallback%2Findex";
	}

	public static String URL_AUTHOR_OTHER(String name) {
		return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ AppId + "&redirect_uri=http%3A%2F%2F"
				+ PathConf.getRootHost() + "%2F" + name + "%2Fcallback%2Findex";
	}

	public static String URL_AUTHOR_BASE() {
		return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ AppId + "&redirect_uri=http%3A%2F%2F"
				+ PathConf.getRootHost() + "%2Fwx%2FcallbackBase%2Findex";
	}

	public static String URL_OAUTH_TOKEN(String code) {
		return URL_OAUTH_TOKEN + "appid=" + AppId + "&secret=" + AppSecret
				+ "&code=" + code + "&grant_type=authorization_code";
	}

	/**
	 * 直接获取用户信息。token是网页授权token
	 */
	private static final String URL_USERINFO_USERINFO = "https://api.weixin.qq.com/sns/userinfo?";

	public static String URL_USERINFO_USERINFO(String token, String openid) {
		return URL_USERINFO_USERINFO + "access_token=" + token + "&openid="
				+ openid + "&lang=zh_CN";
	}

	/**
	 * 获取用户信息。返回参数有是否关注。
	 */
	private static final String URL_USERINFO = "https://api.weixin.qq.com/cgi-bin/user/info?";

	public static String URL_USERINFO(String token, String openid) {
		return URL_USERINFO + "access_token=" + token + "&openid=" + openid
				+ "&lang=zh_CN";
	}

	public static String URL_ACESS_TOKEN() {
		return URL_ACESS_TOKEN + "&appid=" + AppId + "&secret=" + AppSecret;
	}

	public static final String URL_JSSDK_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?";

	public static String URL_JSSDK_TICKET(String token) {
		return URL_JSSDK_TICKET + "access_token=" + token + "&type=jsapi";
	}
}

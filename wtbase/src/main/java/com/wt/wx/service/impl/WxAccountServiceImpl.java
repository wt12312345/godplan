package com.wt.wx.service.impl;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.wt.base.util.HttpUtil;
import com.wt.base.util.TypeUtil;
import com.wt.web.service.AbstractServiceImpl;
import com.wt.wx.constant.WxUrl;
import com.wt.wx.dao.WxAccountDao;
import com.wt.wx.entity.WxAccount;
import com.wt.wx.service.WxAccountService;
import com.wt.wx.vo.WxMenuVo;
import com.wt.wx.vo.WxjsVo;

@Service("wxAccountService")
public class WxAccountServiceImpl extends AbstractServiceImpl<WxAccount>
		implements WxAccountService {
	@Resource
	private WxAccountDao wxAccountDao;

	@Override
	public Class<WxAccount> getEntityClass() {
		return WxAccount.class;
	}

	@Override
	public WxAccount getWxAccount() {
		try {
			return wxAccountDao.getWxAccount();
		} catch (Exception e) {
			logger.error("获取微信账号异常：" + e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 给某用户发送信息，改用户48小时内与公众号又互动方能发送成功
	 * 
	 * @param openid
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	@Override
	public void sendMsg(String openid, String msg) throws Exception {
		getToken();
		JSONObject obj = new JSONObject();
		JSONObject objCont = new JSONObject();
		objCont.put("content", msg);
		obj.put("touser", openid);
		obj.put("msgtype", "text");
		obj.put("text", objCont);
		String result = HttpUtil.doPost(
				"https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
						+ WxUrl.token, obj.toString());
		JSONObject resultJson = new JSONObject(result);
		if (resultJson.getInt("errcode") != 0) {
			logger.error("给用户 " + openid + " 发送信息失败："
					+ resultJson.getString("errmsg"));
		} else {
			logger.info("给用户 " + openid + " 发送信息：" + msg);
		}
	}

	/**
	 * 获取微信js-sdk授权信息
	 */
	@Override
	public WxjsVo getWxJs(String url) throws Exception {
		// 随机字符串，自己写一个随机方法
		String nonceStr = String.valueOf(System.currentTimeMillis());
		// 时间戳
		String timestamp = System.currentTimeMillis() + "";
		timestamp = timestamp.substring(0, 10);
		// 对所有待签名参数按照字段名的ASCII 码从小到大排序
		String string1 = "jsapi_ticket=" + getJssdkTicketFun() + "&noncestr="
				+ nonceStr + "&timestamp=" + timestamp + "&url=" + url;
		String signature = encode("SHA1", string1);
		logger.debug(string1);
		WxjsVo wxjsVo = new WxjsVo();
		wxjsVo.setAppId(WxUrl.AppId);
		wxjsVo.setNonceStr(nonceStr);
		wxjsVo.setTimestamp(timestamp);
		wxjsVo.setSignature(signature);
		return wxjsVo;
	}

	/**
	 * 获取自定义菜单配置
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<WxMenuVo> getMenus() throws Exception {
		getToken();
		String result = HttpUtil.doGet(WxUrl.URL_MENU_GET + WxUrl.token);
		JSONObject menuJson = new JSONObject(result);
		List<WxMenuVo> menuList = new ArrayList<WxMenuVo>();
		if (menuJson.has("menu")) {
			JSONArray menusJson = menuJson.getJSONObject("menu").getJSONArray(
					"button");
			for (int i = 0; i < menusJson.length(); i++) {
				JSONObject oneMenuJson = menusJson.getJSONObject(i);
				WxMenuVo menu = WxMenuVo.getWxmenuVo(oneMenuJson);
				menuList.add(menu);
			}
		} else {
			WxAccount account = getWxAccount();
			String menuStr = account.getMenu();
			if (!TypeUtil.isEmpty(menuStr)) {
				JSONObject menuJsonDb = new JSONObject(menuStr);
				JSONArray menusArr = menuJsonDb.getJSONArray("button");
				for (int i = 0; i < menusArr.length(); i++) {
					JSONObject oneMenuJson = menusArr.getJSONObject(i);
					WxMenuVo menu = WxMenuVo.getWxmenuVo(oneMenuJson);
					menuList.add(menu);
				}
			}
		}
		return menuList;
	}

	/**
	 * 保存自定义菜单
	 * 
	 * @param menuList
	 * @return
	 * @throws Exception
	 */
	@Override
	public int saveMenus(List<WxMenuVo> menuList) throws Exception {
		String token = getToken();
		JSONObject obj = new JSONObject();
		JSONArray objArr = new JSONArray(menuList);
		obj.put("button", objArr);
		String menuJson = obj.toString();
		String result = HttpUtil
				.doPost(WxUrl.URL_MENU_CREATE + token, menuJson);
		JSONObject resultJson = new JSONObject(result);
		WxAccount account = getWxAccount();
		account.setMenu(menuJson);
		wxAccountDao.saveOrUpdate(account);
		return resultJson.getInt("errcode");
	}

	/**
	 * 获取accesstoken
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getToken() throws Exception {
		return getTokenFun();
	}

	@Override
	public void setWxArgs(WxAccount account) {
		WxUrl.AppId = account.getAppId();
		WxUrl.AppSecret = account.getAppSecret();
		WxUrl.PayKey = account.getMchKey();
		WxUrl.PayMchId = account.getMchId();
		WxUrl.PayMchPath = account.getMchPath();
		WxUrl.AppSecret = account.getAppSecret();
		WxUrl.token = account.getToken();
		WxUrl.tokenTime = account.getTokenTime();
		WxUrl.jsapiTicket = account.getJsApiTicket();
		WxUrl.jsapiTicketTime = account.getTokenTime();
	}

	private String getTokenFun() throws Exception {
		boolean ifGuoqi = true;
		if (WxUrl.tokenTime > 0 && WxUrl.token != null) {
			// 判断之前读取到的时间存在内存里的，是否过期。ifGuoqi = false 没过期
			ifGuoqi = judgeTime7200(WxUrl.tokenTime);
		} else {
			// 内存木有，说明是启动第一次获取，去数据库取
			WxAccount account = getWxAccount();
			if (account == null) {
				logger.error("项目部署错误。未手工录入微信公众号信息");
				return null;
			}
			setWxArgs(account);
			String tokenTemp = account.getToken();
			long tokenTimeTemp = account.getTokenTime();
			if (TypeUtil.isEmpty(tokenTemp)) {
				ifGuoqi = true;
			} else {
				// 数据库有token，判断时间是否过期
				ifGuoqi = judgeTime7200(tokenTimeTemp);
			}
		}
		if (ifGuoqi) {
			// 如果过期了，重新去取
			WxAccount account = getWxAccount();
			// 向微信获取token
			getAccessToken();
			// 存入数据库
			account.setToken(WxUrl.token);
			account.setTokenTime(WxUrl.tokenTime);
			logger.info("获取新的token：" + WxUrl.token + " - " + WxUrl.tokenTime);
			wxAccountDao.saveOrUpdate(account);
			// 赋值给静态变量
			setWxArgs(account);
		}
		return WxUrl.token;
	}

	private String getJssdkTicketFun() throws Exception {
		boolean ifGuoqi = true;
		if (WxUrl.jsapiTicketTime > 0 && WxUrl.jsapiTicket != null) {
			// 判断之前读取到的时间存在内存里的，是否过期。ifGuoqi = false 没过期
			ifGuoqi = judgeTime7200(WxUrl.jsapiTicketTime);
		} else {
			// 内存木有，说明是启动第一次获取，去数据库取
			WxAccount account = getWxAccount();
			if (account == null) {
				logger.error("项目部署错误。未手工录入微信公众号信息");
				return null;
			}
			if (WxUrl.AppId == null) {
				setWxArgs(account);
			}
			WxUrl.jsapiTicket = account.getJsApiTicket();
			WxUrl.jsapiTicketTime = account.getJsApiTicketTime();
			if (!WxUrl.jsapiTicket.equals("")) {
				// 数据库有token，判断时间是否过期
				ifGuoqi = judgeTime7200(WxUrl.jsapiTicketTime);
			} else {
				ifGuoqi = true;
			}
		}
		if (ifGuoqi) {
			// 如果过期了，重新去取
			WxAccount account = getWxAccount();
			if (WxUrl.AppId == null) {
				setWxArgs(account);
			}
			getJsapiTicket();
			account.setJsApiTicket(WxUrl.jsapiTicket);
			account.setJsApiTicketTime(WxUrl.jsapiTicketTime);
			logger.info("获取新的jsapiTicket：" + WxUrl.jsapiTicket + " - "
					+ WxUrl.jsapiTicketTime);
			wxAccountDao.saveOrUpdate(account);
			setWxArgs(account);
		}
		return WxUrl.jsapiTicket;
	}

	/**
	 * 判断是否超过了 7200秒
	 * 
	 * @param thisTime
	 * @return
	 */
	private boolean judgeTime7200(long thisTime) {
		Date dateNow = new Date();
		long s = (dateNow.getTime() - thisTime) / 1000;
		logger.info("token/ticket刷新于：" + s + " 秒前 ");
		if (s > 7000) {
			return true;
		}
		return false;
	}

	private String encode(String algorithm, String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(str.getBytes(CHAR_SET));
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	private static final String CHAR_SET = "UTF-8";
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 通过get从微信服务器上获取accesstoken
	 * 
	 * @return
	 */
	private void getAccessToken() {
		String url = WxUrl.URL_ACESS_TOKEN();
		logger.info("获取token URL ：" + url);
		String httpResult = HttpUtil.doGetNew(url);
		logger.info("获取token ：" + httpResult);
		if (!TypeUtil.isEmpty(httpResult)) {
			// if the httpResult is not null transfer to jsonobject
			JSONObject jsonObj = new JSONObject(httpResult);
			if (jsonObj.has(WxUrl.ERROR_CODE)) {
				// this should be jsonObj.getInt(),not getString() zyn
			} else {
				WxUrl.token = jsonObj.getString(WxUrl.ACCESS_TOKEN);
				WxUrl.tokenTime = new Date().getTime();
				logger.info("从微信上获取新的 Token:" + WxUrl.token + " - "
						+ WxUrl.tokenTime);
			}
		}
	}

	private void getJsapiTicket() throws Exception {
		String url = WxUrl.URL_JSSDK_TICKET(getToken());
		String httpResult = HttpUtil.doGetNew(url);
		if (!TypeUtil.isEmpty(httpResult)) {
			JSONObject jsonObj = new JSONObject(httpResult);
			if (jsonObj.getInt(WxUrl.ERROR_CODE) == 0) {
				WxUrl.jsapiTicket = jsonObj.getString(WxUrl.TICKET);
				WxUrl.jsapiTicketTime = new Date().getTime();
				logger.info("从微信上获取新的 Ticket:" + WxUrl.jsapiTicket);
			}
		}
	}

	@Override
	public void clearArgs() throws Exception {
		WxUrl.AppId = null;
		WxUrl.AppSecret = null;
		WxUrl.PayKey = null;
		WxUrl.PayMchId = null;
		WxUrl.PayMchPath = null;
		WxUrl.AppSecret = null;
		WxUrl.token = null;
		WxUrl.tokenTime = 0;
		WxUrl.jsapiTicket = null;
		WxUrl.jsapiTicketTime = 0;
		WxAccount account = getWxAccount();
		account.setToken("");
		account.setTokenTime(0);
		account.setJsApiTicket("");
		account.setJsApiTicketTime(0);
		wxAccountDao.saveOrUpdate(account);
	}

}

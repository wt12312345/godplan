package com.wt.wx.service;

import java.util.List;

import com.wt.web.service.AbstractService;
import com.wt.wx.entity.WxAccount;
import com.wt.wx.vo.WxMenuVo;
import com.wt.wx.vo.WxjsVo;

/** 微信公众账号接口层 */
public interface WxAccountService extends AbstractService<WxAccount> {
	WxAccount getWxAccount();

	void setWxArgs(WxAccount account);

	void sendMsg(String openid, String msg) throws Exception;

	WxjsVo getWxJs(String url) throws Exception;

	List<WxMenuVo> getMenus() throws Exception;

	int saveMenus(List<WxMenuVo> menuList) throws Exception;

	String getToken() throws Exception;

	void clearArgs() throws Exception;

}
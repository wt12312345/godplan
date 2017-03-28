package com.hq.manage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hq.SessionName;
import com.hq.manage.vo.UserSysVo;

/** 从request，session中获取信息 */
public class SessionUtil {

	/**
	 * 从session中获取 adminUser 系统管理员
	 * 
	 * @param session
	 *            session
	 * @param return systemManger 登入后存入session的对象
	 */
	public static UserSysVo getSystemManager(HttpSession session) {
		if (session.getAttribute(SessionName.SysUser) == null) {
			return null;
		} else {
			return (UserSysVo) session.getAttribute(SessionName.SysUser);
		}
	}

	/**
	 * 从request中获取 adminUser 系统管理员
	 * 
	 * @param request
	 *            request
	 * @param return systemManger 登入后存入session的对象
	 */
	public static UserSysVo getSystemManager(HttpServletRequest request) {
		return getSystemManager(request.getSession());
	}

}

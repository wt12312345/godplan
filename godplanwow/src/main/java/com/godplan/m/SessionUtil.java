package com.godplan.m;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.godplan.SessionName;
import com.godplan.m.vo.UserSysVo;

/** 从request，session中获取信息 */
public class SessionUtil {

	/**
	 * 从session中获取 adminUser 系统管理员
	 * 
	 * @param session
	 *            session
	 * @param return systemManger 登入后存入session的对象
	 */
	public static UserSysVo getUserSys(HttpSession session) {
		if (session.getAttribute(SessionName.UserSys) == null) {
			return null;
		} else {
			return (UserSysVo) session.getAttribute(SessionName.UserSys);
		}
	}

	/**
	 * 从request中获取 adminUser 系统管理员
	 * 
	 * @param request
	 *            request
	 * @param return systemManger 登入后存入session的对象
	 */
	public static UserSysVo getUserSys(HttpServletRequest request) {
		return getUserSys(request.getSession());
	}

}

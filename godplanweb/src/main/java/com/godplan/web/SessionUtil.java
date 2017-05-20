package com.godplan.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.godplan.SessionName;
import com.godplan.web.vo.UserVo;

public class SessionUtil {

	public static UserVo getUser(HttpSession session) {
		if (session.getAttribute(SessionName.User) == null) {
			return null;
		} else {
			return (UserVo) session.getAttribute(SessionName.User);
		}
	}

	public static UserVo getUser(HttpServletRequest request) {
		return getUser(request.getSession());
	}

	public static UserVo getUserWx(HttpSession session) {
		if (session.getAttribute(SessionName.UserWx) == null) {
			return null;
		} else {
			return (UserVo) session.getAttribute(SessionName.UserWx);
		}
	}

	public static UserVo getUserWx(HttpServletRequest request) {
		return getUserWx(request.getSession());
	}
}

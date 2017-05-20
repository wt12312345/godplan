package com.godplan.m.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.godplan.SessionName;
import com.godplan.entity.Menu;
import com.godplan.entity.UserSys;
import com.godplan.m.SessionUtil;
import com.godplan.m.service.MenuService;
import com.godplan.m.service.UserSysService;
import com.godplan.m.vo.LoginVo;
import com.godplan.m.vo.MenuVo;
import com.godplan.m.vo.UserSysVo;
import com.sun.jndi.toolkit.url.Uri;
import com.wt.base.constant.BegCode;
import com.wt.base.util.MD5Util;
import com.wt.base.util.TypeUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;
import com.wt.web.domain.WebView;

@Controller
@RequestMapping("/m")
public class LoginController extends AbstractController {

	@Resource
	private UserSysService userSysService;
	@Resource
	private MenuService menuService;
	private ModelAndView mav = null;

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView preHandle(HttpServletRequest request,
			HttpServletResponse response)  {
		mav = new WebView("m/login");
		UserSysVo manager = SessionUtil.getUserSys(request);
		if (manager != null) {
			redirectToPage(response, "m");
		}
		return mav;
	}
	
	@RequestMapping(value = "/login",method = { RequestMethod.GET })
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) {
		mav = new WebView("m/login");
		UserSysVo manager = SessionUtil.getUserSys(request);
		if (manager != null) {
			redirectToPage(response, "m");
		}
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/doLogin", method = { RequestMethod.POST,
			RequestMethod.GET })
	public JsonResponse doLogin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		JsonResponse jr = new JsonResponse();
		try {
			String userName = TypeUtil.toString(request
					.getParameter("loginName"));
			String password = MD5Util.string2MD5(TypeUtil.toString(request
					.getParameter("password")));
			UserSys userSys = userSysService.getByLoginName(userName);
			if (userSys == null) {
				jr.setMsg("用户不存在");
			} else if (!userSys.getPassword().equals(password)) {
				logger.info("密码错误");
				jr.setMsg("密码错误");
			} else {
				userSys.setLastLoginTime(new Date());
				userSysService.saveOrUpdate(userSys);
				
				// 查询menu
				List<Menu> listMenu = menuService.getAll();
				List<MenuVo> listMenuVo = MenuVo.getVo(listMenu);

				LoginVo login = new LoginVo();
				login.setListMenu(listMenuVo);

				UserSysVo userSysVo = UserSysVo.getVo(userSys);
				
				jr.setObj(login);
				jr.setCode(BegCode.SUCCESS);
				
				session.setAttribute(SessionName.UserSys, userSys);
				session.setMaxInactiveInterval(10800);// 单位为秒
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}

	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		mav = new WebView("m/index");
		logger.info("访问后台首页");
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/checkAccount", method = { RequestMethod.POST })
	public JsonResponse checkAccount(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResponse js = new JsonResponse();
//		String account = request.getParameter("account");
		// UserSys systemManager = userSysService.getManagerByAccount(account);
		// if (systemManager != null) {
		// js.setCode(1);
		// }
		return js;
	}

	/** 用户退出 */
	@RequestMapping(value = "/loginOut", method = { RequestMethod.GET })
	public void loginOut(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().invalidate();
		redirectToPage(response, "m");
	}

	/** 进入密码修改页面 */
	@RequestMapping(value = "/editPassword", method = { RequestMethod.GET })
	public ModelAndView editPassword(HttpServletRequest request,
			HttpServletResponse response) {
		mav = new WebView("/m/editPassword");
		return mav;
	}

	/** 修改密码 */
//	@RequestMapping(value = "/savePassword", method = { RequestMethod.POST })
//	@ResponseBody
//	public JsonResponse savePassword(HttpServletRequest request,
//			HttpServletResponse response) {
//		String oldPasswd = request.getParameter("passwordOld");
//		String newPasswd = request.getParameter("password");
//		UserSysVo systemManager = SessionUtil.getUserSys(request);
//		oldPasswd = MD5Util.string2MD5(oldPasswd);
//		newPasswd = MD5Util.string2MD5(newPasswd);
//		JsonResponse jr = userSysService.changePwd((systemManager == null ? ""
//				: systemManager.getLoginName()), oldPasswd, newPasswd);
//		return jr;
//
//	}
}

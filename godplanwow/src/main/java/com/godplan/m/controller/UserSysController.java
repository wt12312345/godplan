package com.godplan.m.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godplan.SessionName;
import com.godplan.entity.Menu;
import com.godplan.entity.UserSys;
import com.godplan.m.SessionUtil;
import com.godplan.m.service.MenuService;
import com.godplan.m.service.UserSysService;
import com.godplan.m.vo.MenuVo;
import com.godplan.m.vo.UserSysVo;
import com.wt.CallBackBo;
import com.wt.base.constant.BegCode;
import com.wt.base.util.MD5Util;
import com.wt.base.util.TypeUtil;
import com.wt.base.util.WebUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;

@Controller
@RequestMapping("/m")
public class UserSysController extends AbstractController {
	@Resource
	private UserSysService userSysService;
	@Resource
	private MenuService menuService;

	/**
	 * 登录操作
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userSyses/login", method = { RequestMethod.POST })
	public JsonResponse loginDo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JsonResponse jr = new JsonResponse();
		try {
			String userName = TypeUtil.toString(request.getParameter("loginName"));
			String password = MD5Util.string2MD5(TypeUtil.toString(request.getParameter("password")));
			UserSys userDb = userSysService.getByLoginName(userName);
			if (userDb == null) {
				jr.setMsg("用户不存在");
			} else if (userDb.getStatus() != 1) {
				logger.info("帐号无效");
				jr.setMsg("帐号无效");
			} else if (!userDb.getPassword().equals(password)) {
				logger.info("密码错误");
				jr.setMsg("密码错误");
			} else {
				userDb.setLastLoginTime(new Date());
				userSysService.saveOrUpdate(userDb);

				UserSysVo userSysVo = UserSysVo.getVo(userDb);
				// 查询menu
				List<Menu> listMenu = menuService.getAll();
				List<MenuVo> listMenuVo = MenuVo.getVo(listMenu);
				userSysVo.setListMenu(listMenuVo);
				jr.setObj(userSysVo);
				jr.setCode(BegCode.SUCCESS);

				session.setAttribute(SessionName.UserSys, userSysVo);
				session.setMaxInactiveInterval(3600);// 单位为秒
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}

	/**
	 * 检查登录状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userSyses/login", method = { RequestMethod.GET })
	public JsonResponse loginCheck(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JsonResponse jr = new JsonResponse();
		try {
			UserSysVo userSys = SessionUtil.getUserSys(request);
			if (userSys != null) {
				jr.setCode(BegCode.SUCCESS);
				jr.setObj(userSys);
				jr.setMsg("已登录");
				logger.info(userSys.getName() + " 已登录");
			} else {
				jr.setMsg("未登录");
				logger.info("未登录");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}

	/** 用户退出 */
	@RequestMapping(value = "/userSyses/login", method = { RequestMethod.DELETE })
	@ResponseBody
	public JsonResponse loginOut(HttpServletRequest request, HttpSession session) {
		JsonResponse jr = new JsonResponse();
		/* 把整个session给挂掉 */
		UserSysVo userSys = SessionUtil.getUserSys(request);
		logger.info("管理员:" + (userSys == null ? null : userSys.getLoginName()) + "退出");
		session.invalidate();

		jr.setCode(BegCode.SUCCESS);
		return jr;
	}

	/** 修改密码 */
	@RequestMapping(value = "/userSyses/password", method = { RequestMethod.PUT })
	@ResponseBody
	public JsonResponse changePassword(HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		UserSysVo userSys = SessionUtil.getUserSys(request);

		String oldPasswd = TypeUtil.toString(request.getParameter("passwordLast"));
		String newPasswd = TypeUtil.toString(request.getParameter("password"));
		String password = MD5Util.string2MD5(oldPasswd);
		CallBackBo cb = userSysService.changePwd(userSys.getId(), password, newPasswd);
		if (cb.getResult()) {
			logger.info("用户：" + SessionUtil.getUserSys(request).getName() + " 修改密码");
			jr.setCode(BegCode.SUCCESS);
		} else {
			jr.setMsg(cb.getErr());
		}
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/userSyses", method = { RequestMethod.GET })
	public JsonResponse getUserSysList(HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		try {
			String keyWord = TypeUtil.toString(request.getParameter("keyWord"));
			keyWord = WebUtil.decode(keyWord);
			List<UserSys> list = userSysService.getUserSys(keyWord);
			jr.setObj(UserSysVo.getVo(list));
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/userSyses/{id}", method = { RequestMethod.GET })
	public JsonResponse getUserSys(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@PathVariable long id) {
		JsonResponse jr = new JsonResponse();
		try {
			UserSys userSys = new UserSys();
			if (id > 0) {
				userSys = userSysService.getEntity(id);
				if (userSys == null) {
					jr.setMsg("用户不存在");
					return jr;
				}
			}
			jr.setObj(UserSysVo.getVo(userSys));
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/userSyses", method = { RequestMethod.POST })
	public JsonResponse userSysEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return userSysEdit(request, response, session, 0);
	}

	@ResponseBody
	@RequestMapping(value = "/userSyses/{id}", method = { RequestMethod.POST, RequestMethod.PUT })
	public JsonResponse userSysEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@PathVariable long id) {
		JsonResponse jr = new JsonResponse();
		try {
			String loginName = TypeUtil.toString(request.getParameter("loginName"));
			String name = TypeUtil.toString(request.getParameter("name"));
			String email = TypeUtil.toString(request.getParameter("email"));
			String password = TypeUtil.toString(request.getParameter("password"));
			int status = TypeUtil.toInt(request.getParameter("status"));

			UserSys user = userSysService.getEntity(id);
			if (user == null) {
				if (password.equals("")) {
					jr.setMsg("密码不能为空");
					return jr;
				}
				user = new UserSys();
				String pwd = MD5Util.string2MD5(password);
				user.setPassword(pwd);
			} else if (!password.equals("")) {
				String pwd = MD5Util.string2MD5(password);
				user.setPassword(pwd);
			}

			user.setName(name);
			user.setStatus(status);
			user.setLoginName(loginName);
			user.setEmail(email);
			userSysService.saveOrUpdate(user);
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}
}

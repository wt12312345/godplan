package com.godplan.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godplan.Conf;
import com.godplan.SessionName;
import com.godplan.entity.Joke;
import com.godplan.entity.User;
import com.godplan.web.SessionUtil;
import com.godplan.web.StaticData;
import com.godplan.web.service.JokeService;
import com.godplan.web.service.UserService;
import com.godplan.web.vo.JokeVo;
import com.godplan.web.vo.UserVo;
import com.wt.base.constant.BegCode;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;

/**
 * 主页
 * */
@Controller
@RequestMapping("/web/j")
public class JokeController extends AbstractController {
	@Resource
	private UserService userService;
	@Resource
	private JokeService jokeService;

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/getList", method = { RequestMethod.GET })
	public JsonResponse getList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		JsonResponse jr = new JsonResponse();
		try {
			StaticData.listJoke = null;
			if (StaticData.listJoke == null) {
				List<JokeVo> listJokeVo = new ArrayList<JokeVo>();
				List<Joke> listJoke = jokeService.getListValid();
				for (int i = 0; i < listJoke.size(); i++) {
					Joke joke = listJoke.get(i);
					JokeVo vo = new JokeVo();
					vo.setId(joke.getId());
					vo.setContent(joke.getContent());
					vo.setName(joke.getName());
					vo.setNickName(joke.getNickName());
					listJokeVo.add(vo);
				}
				StaticData.listJoke = listJokeVo;
			}

			String ip = request.getRemoteAddr();
			UserVo userVo = SessionUtil.getUser(request);
			if (userVo == null) {
				userVo = new UserVo();
				userVo.setNickName("游客");
			}
			int allSize = StaticData.listJoke.size();
			if (allSize > Conf.PageSize) {
				// 已经浏览过的数据
				Object listYetObj = session.getAttribute(SessionName.JokeList);
				List<Integer> listYet = new ArrayList<Integer>();
				if (listYetObj != null) {
					listYet = (List<Integer>) listYetObj;
				}
				int yetSize = listYet.size();
				// 数据不足，重新开始随机
				if ((allSize - yetSize) < Conf.PageSize) {
					listYet = new ArrayList<Integer>();
				}
				// 这次的数据
				List<Integer> listIndex = new ArrayList<Integer>();
				for (int i = 0; i < Conf.PageSize; i++) {
					int index = (int) (Math.random() * allSize);
					while (listYet.contains(index) || listIndex.contains(index)) {
						index = (int) (Math.random() * allSize);
					}
					listIndex.add(index);
				}
				// 把结果数据根据本次随机到的下标取出
				List<JokeVo> listResult = new ArrayList<JokeVo>();
				for (int i = 0; i < Conf.PageSize; i++) {
					int index = listIndex.get(i);
					listYet.add(index);
					listResult.add(StaticData.listJoke.get(index));
				}
				session.setAttribute(SessionName.JokeList, listYet);
				jr.setObj(listResult);
				logger.info(userVo.getNickName() + " （" + ip + "） 获取JOKE "
						+ listResult.size() + " 条");
			} else {
				jr.setObj(StaticData.listJoke);
				logger.info(userVo.getNickName() + " （" + ip + "） 获取JOKE "
						+ StaticData.listJoke.size() + " 条");
			}

			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}

	/**
	 * 保存段子
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doSave", method = { RequestMethod.POST })
	public JsonResponse doSave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, JokeVo jokeVo) {
		JsonResponse jr = new JsonResponse();
		try {
			String content = jokeVo.getContent().trim();
			String name = jokeVo.getName().trim();
			if (content.isEmpty()) {
				jr.setMsg("分享 不能为空");
				return jr;
			} else if (content.length() > 200) {
				jr.setMsg("分享 字数不能超过998");
				return jr;
			} else if (name.length() > 20) {
				jr.setMsg("作者/出处 字数不能超过20");
				return jr;
			}
			String nickName = "";
			long userId = 0;
			User user = null;
			UserVo userVo = SessionUtil.getUser(request);
			if (userVo != null) {
				// 若登录，拿取session中的数据
				userId = userVo.getId();
				user = userService.getEntity(userVo.getId());
			} else {
				// 没登录，拿前端cookie数据
				userId = jokeVo.getId();
				user = userService.getEntity(jokeVo.getId());
			}
			Joke joke = new Joke();
			joke.setName(name);
			joke.setContent(content);
			joke.setUser(user);
			joke.setStatus(1);
			if (user == null) {
				nickName = "游客";
			} else {
				nickName = user.getNickName();
			}
			joke.setNickName(nickName);
			jokeService.saveOrUpdate(joke);
			jr.setCode(BegCode.SUCCESS);
			logger.info("用户：" + nickName + "(" + userId + ")");
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}
}

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
import com.godplan.constant.EnumCom;
import com.godplan.entity.Annals;
import com.godplan.web.SessionUtil;
import com.godplan.web.service.AnnalsService;
import com.godplan.web.vo.JokeVo;
import com.godplan.web.vo.UserVo;
import com.wt.base.constant.BegCode;
import com.wt.base.util.TypeUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;

@Controller
@RequestMapping("/web/annals")
public class AnnalsController extends AbstractController {

	@Resource
	private AnnalsService annalsService;

	@ResponseBody
	@RequestMapping(value = "/getList", method = { RequestMethod.POST })
	public JsonResponse getList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		JsonResponse jr = new JsonResponse();
		try {
			List<JokeVo> listJokeVo = new ArrayList<JokeVo>();
			int page = TypeUtil.toInt(request.getParameter("page"));
			if (page == 0) {
				page = 1;
			}
			List<Annals> listJoke = annalsService.getList(
					EnumCom.Enable.getIndex(), page, Conf.PageSize);
			for (int i = 0; i < listJoke.size(); i++) {
				Annals joke = listJoke.get(i);
				JokeVo vo = new JokeVo();
				vo.setId(joke.getId());
				vo.setContent(joke.getContent());
				vo.setName(joke.getTitle());
				listJokeVo.add(vo);
			}

			String ip = request.getRemoteAddr();
			UserVo userVo = SessionUtil.getUser(request);
			if (userVo == null) {
				userVo = new UserVo();
				userVo.setNickName("游客");
			}
			jr.setObj(listJokeVo);
			logger.info(userVo.getNickName() + " (" + ip + ") 获取Annals (page="
					+ page + ")");
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}

}

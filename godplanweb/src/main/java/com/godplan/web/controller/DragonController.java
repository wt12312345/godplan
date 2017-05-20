package com.godplan.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godplan.entity.RecordChose;
import com.godplan.web.SessionUtil;
import com.godplan.web.service.RecordService;
import com.godplan.web.vo.UserVo;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;

/**
 * 主页
 * */
@Controller
@RequestMapping("/web")
public class DragonController extends AbstractController {
	@Resource
	private RecordService recordService;

	/**
	 * 先用微信注册，直接绑定的模式。这里的注册就先不要了注册
	 */
	@ResponseBody
	@RequestMapping(value = "/doSaveRecord", method = { RequestMethod.POST })
	public JsonResponse doRegister(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, String content,
			String remark) {
		JsonResponse jr = new JsonResponse();
		try {
			if (content.length() > 60) {
				return jr;
			}
			if (remark.length() > 7) {
				return jr;
			}
			long userId = 0;
			UserVo user = SessionUtil.getUser(session);
			if (user != null) {
				userId = user.getId();
			}
			RecordChose chose = new RecordChose();
			chose.setUser_id(userId);
			chose.setContent(content);
			chose.setRemark(remark);
			recordService.saveOrUpdate(chose);
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}

}

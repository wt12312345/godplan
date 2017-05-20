package com.godplan.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godplan.entity.HeadCombo;
import com.godplan.web.SessionUtil;
import com.godplan.web.service.HeadComboService;
import com.godplan.web.vo.HeadComboVo;
import com.godplan.web.vo.UserVo;
import com.wt.base.constant.BegCode;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;

/**
 * 主页
 * */
@Controller
@RequestMapping("/web/headCombo")
public class HeadComboController extends AbstractController {
	@Resource
	private HeadComboService headComboService;

	/**
	 * 先用微信注册，直接绑定的模式。这里的注册就先不要了注册
	 */
	@ResponseBody
	@RequestMapping(value = "/doSaveKey", method = { RequestMethod.POST })
	public JsonResponse doSaveKey(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, int num,
			String key) {
		JsonResponse jr = new JsonResponse();
		try {
			if (key.length() > 10) {
				jr.setMsg("暗号不能超过10位");
				return jr;
			}
			if (num <= 1) {
				jr.setMsg("人数选择错误");
				return jr;
			}
			HeadCombo comboDb = headComboService.getByKey(key);
			if (comboDb != null) {
				jr.setMsg("暗号已存在，请重新设置");
				return jr;
			}
			UserVo userWxFromSession = SessionUtil.getUserWx(session);
			if (userWxFromSession == null) {
				jr.setMsg("请先登录");
				return jr;
			}
			HeadCombo combo = new HeadCombo();
			combo.setKeyId(key);
			combo.setNum(num);
			combo.setNumJoin(1);
			combo.setOpenId(userWxFromSession.getOpenid());
			JSONObject jsonUserInfo = new JSONObject();
			jsonUserInfo.put("openid", userWxFromSession.getOpenid());
			jsonUserInfo.put("nickName", userWxFromSession.getNickName());
			jsonUserInfo.put("img", userWxFromSession.getHeadImg());

			JSONArray arrUser = new JSONArray();
			arrUser.put(jsonUserInfo);

			combo.setUserInfo(arrUser.toString());
			headComboService.saveOrUpdate(combo);
			jr.setObj(combo.getId());
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/doJoinKey", method = { RequestMethod.POST })
	public JsonResponse doJoinKey(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, String key) {
		JsonResponse jr = new JsonResponse();
		try {
			UserVo userWxFromSession = SessionUtil.getUserWx(session);
			if (userWxFromSession == null) {
				jr.setMsg("请先登录");
				return jr;
			}

			if (key.length() > 10) {
				jr.setMsg("暗号不能超过10位");
				return jr;
			}
			HeadCombo combo = headComboService.getByKey(key);
			if (combo == null) {
				jr.setMsg("暗号不存在");
				return jr;
			}
			if (combo.getNum() == combo.getNumJoin()) {
				jr.setMsg("已加满人，合体成功！不能再加入啦");
				return jr;
			}
			JSONArray arrUser = new JSONArray(combo.getUserInfo());

			JSONObject jsonUserInfo = new JSONObject();
			jsonUserInfo.put("openid", userWxFromSession.getOpenid());
			jsonUserInfo.put("nickName", userWxFromSession.getNickName());
			jsonUserInfo.put("img", userWxFromSession.getHeadImg());

			arrUser.put(jsonUserInfo);

			combo.setUserInfo(arrUser.toString());
			combo.setNumJoin(combo.getNumJoin() + 1);
			headComboService.saveOrUpdate(combo);
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/getCombo", method = { RequestMethod.GET })
	public JsonResponse getCombo(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, long id) {
		JsonResponse jr = new JsonResponse();
		try {
			HeadCombo combo = headComboService.getEntity(id);
			if (combo == null) {
				jr.setMsg("暗号不存在");
				return jr;
			}
			UserVo userWxFromSession = SessionUtil.getUserWx(session);
			if (userWxFromSession == null) {
				jr.setMsg("请先登录");
				return jr;
			}
			HeadComboVo comboVo = new HeadComboVo();
			List<HeadComboVo> listChild = new ArrayList<HeadComboVo>();
			JSONArray arrUser = new JSONArray(combo.getUserInfo());
			for (int i = 0; i < arrUser.length(); i++) {
				JSONObject oneUser = arrUser.getJSONObject(i);
				HeadComboVo comboTemp = new HeadComboVo();
				comboTemp.setImg(oneUser.getString("img"));
				comboTemp.setNickName(oneUser.getString("nickName"));
				listChild.add(comboTemp);
			}
			comboVo.setListChild(listChild);

			comboVo.setNum(combo.getNum());
			comboVo.setNumJoin(combo.getNumJoin());
			jr.setObj(comboVo);
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}
}

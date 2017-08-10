package com.godplan.m.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godplan.entity.Emoji;
import com.godplan.m.service.EmojiService;
import com.godplan.m.service.QuartzEmojiService;
import com.godplan.m.service.bo.SearchBo;
import com.godplan.m.vo.EmojiVo;
import com.wt.base.constant.BegCode;
import com.wt.base.util.TypeUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;
import com.wt.web.domain.Page;

/**
 * 编年史
 * 
 * @author wt12312345
 *
 */
@Controller
@RequestMapping("/m")
public class EmojiController extends AbstractController {

	@Resource
	private EmojiService emojiService;
	@Resource
	private QuartzEmojiService quartzEmojiService;

	@ResponseBody
	@RequestMapping(value = "/emojis/html", method = { RequestMethod.GET })
	public JsonResponse emojiRefreshHtml(HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		try {
			quartzEmojiService.createIndexHtml();
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/emojis", method = { RequestMethod.GET })
	public JsonResponse emojiList(HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		try {
			Page page = new Page(0, 0, -1);
			String keyWord = TypeUtil.toString(request.getParameter("keyWord"));
			String orderBy = TypeUtil.toString(request.getParameter("orderBy"));
			List<Emoji> listItem = emojiService.getList(page, orderBy, new SearchBo(keyWord));
			jr.setObj(EmojiVo.getVo(listItem));
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/emojis/{id}", method = { RequestMethod.GET })
	public JsonResponse emoji(HttpServletRequest request, HttpServletResponse response, @PathVariable long id) {
		JsonResponse jr = new JsonResponse();
		Emoji item = null;
		if (id == 0) {
			item = new Emoji();
		} else {
			item = emojiService.getEntity(id);
			if (item == null) {
				redirectToPage(response, "m/emoji/list");
				return jr;
			}
		}
		jr.setObj(EmojiVo.getVo(item));
		jr.setCode(BegCode.SUCCESS);
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/emojis", method = { RequestMethod.POST })
	public JsonResponse emojiEdit(HttpServletRequest request, HttpServletResponse response, EmojiVo itemVo) {
		return emojiEdit(request, response, 0, itemVo);
	}

	@ResponseBody
	@RequestMapping(value = "/emojis/{id}", method = { RequestMethod.POST, RequestMethod.PUT })
	public JsonResponse emojiEdit(HttpServletRequest request, HttpServletResponse response, @PathVariable long id,
			EmojiVo itemVo) {
		JsonResponse jr = new JsonResponse();
		try {
			Emoji item = null;
			if (itemVo.getId() == 0) {
				item = new Emoji();
			} else {
				item = emojiService.getEntity(itemVo.getId());
				if (item == null) {
					jr.setMsg("数据不存在");
					return jr;
				}
			}
			item.setName(itemVo.getName());
			item.setSymbol(itemVo.getSymbol());
			emojiService.saveOrUpdate(item);
			jr.setObj(EmojiVo.getVo(item));
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}
}

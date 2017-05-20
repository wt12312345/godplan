package com.godplan.m.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.godplan.entity.Menu;
import com.godplan.m.service.MenuService;
import com.godplan.m.service.UserSysService;
import com.godplan.m.vo.MenuVo;
import com.wt.base.util.TypeUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.WebView;

@Controller
@RequestMapping("/m/page")
public class PageSysController extends AbstractController {
	@Resource
	private UserSysService menu;
	@Resource
	private MenuService menuService;
	private ModelAndView mav = null;

	@RequestMapping(value = "/menuList", method = { RequestMethod.GET })
	public ModelAndView menuList(HttpServletRequest request, HttpServletResponse response) {
		mav = new WebView("/m/sys/menu/menuList");
		List<Menu> listMenu = menuService.getAll();
		mav.addObject("items", MenuVo.getVo(listMenu));
		return mav;
	}

	@RequestMapping(value = "/menuEdit", method = { RequestMethod.GET })
	public ModelAndView menuEdit(HttpServletRequest request, HttpServletResponse response) {
		mav = new WebView("/m/sys/menu/menuEdit");
		long id = TypeUtil.toInt(request.getParameter("id"));
		long parentId = TypeUtil.toInt(request.getParameter("parentId"));
		Menu item = new Menu();
		Menu itemParent = new Menu();
		// 1为新增，2为修改
		int mode = 1;
		if (id > 0) {
			// 某一条数据编辑
			item = menuService.getEntity(id);
			if (item == null) {
				redirectToPage(response, "manage/sys/menuList");
				return null;
			}
			parentId = item.getParentId();
			mode = 2;
		}

		if (parentId > 0) {
			// 某大类下的新增
			itemParent = menuService.getEntity(parentId);
			if (itemParent == null) {
				redirectToPage(response, "m/page/menuList");
				return null;
			}
		}
		mav.addObject("itemParent", MenuVo.getVo(itemParent));
		mav.addObject("item", MenuVo.getVo(item));
		mav.addObject("mode", mode);
		return mav;
	}

}

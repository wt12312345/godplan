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

import com.godplan.entity.Menu;
import com.godplan.m.service.MenuService;
import com.wt.base.constant.BegCode;
import com.wt.base.util.TypeUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;

@Controller
@RequestMapping("/m")
public class MenuController extends AbstractController {
	@Resource
	private MenuService menuService;

	/**
	 * 保存操作
	 * 
	 * @param request
	 * @param response
	 * @param menu
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/menus/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public JsonResponse menuEdit(HttpServletRequest request, HttpServletResponse response, @PathVariable long id) {
		JsonResponse jr = new JsonResponse();
		try {
			long parentId = TypeUtil.toInt(request.getParameter("parentId"));
			String name = TypeUtil.toString(request.getParameter("name"));
			String url = TypeUtil.toString(request.getParameter("url"));
			String action = TypeUtil.toString(request.getParameter("action"));
			String iconName = TypeUtil.toString(request.getParameter("iconName"));

			if (name.isEmpty()) {
				jr.setMsg("类别名不能为空");
				return jr;
			}
			Menu item = new Menu();
			if (id > 0) {
				item = menuService.getEntity(id);
				if (item == null) {
					jr.setMsg("修改失败！数据不存在");
					return jr;
				}
			} else {
				item.setParentId(parentId);
			}
			item.setUrl(url);
			item.setName(name);
			item.setAction(action);
			item.setIconName(iconName);
			menuService.saveOrUpdate(item);

			jr.setCode(BegCode.SUCCESS);
			jr.setId(item.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}
	
	@ResponseBody
	@RequestMapping(value = "/menus/{id}", method = { RequestMethod.DELETE })
	public JsonResponse menuDelete(HttpServletRequest request,
			HttpServletResponse response, @PathVariable long id) {
		JsonResponse jr = new JsonResponse();
		try {
			Menu item = menuService.getEntity(id);
			if (item == null) {
				jr.setMsg("删除失败！菜单不存在");
				return jr;
			}
			if (item.getParentId() == 0) {
				// 如果是1级菜单，删除所有对应二级菜单
				List<Menu> listMenu = menuService.getListByParent(item.getId());
				for (int i = 0; i < listMenu.size(); i++) {
					menuService.deleteEntity(listMenu.get(i));
				}
			}
			menuService.deleteEntity(item);
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}
}

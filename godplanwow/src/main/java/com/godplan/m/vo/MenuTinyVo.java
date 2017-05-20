package com.godplan.m.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.godplan.entity.Menu;

public class MenuTinyVo {
	private String name = "";
	private long id = 0;
	private String url = "";
	private String iconName = "";
	private String action = "";
	private List<MenuTinyVo> listSub = new ArrayList<MenuTinyVo>();

	public static List<MenuTinyVo> getVo(List<Menu> list) {
		List<MenuTinyVo> listVo = new ArrayList<MenuTinyVo>();

		Map<Long, List<MenuTinyVo>> mapChild = new HashMap<Long, List<MenuTinyVo>>();
		for (int i = 0; i < list.size(); i++) {
			Menu item = list.get(i);
			if (item.getParentId() == 0) {
				listVo.add(getVo(item));
			} else {
				if (!mapChild.containsKey(item.getParentId())) {
					mapChild.put(item.getParentId(), new ArrayList<MenuTinyVo>());
				}
				mapChild.get(item.getParentId()).add(getVo(item));
			}
		}
		for (int i = 0; i < listVo.size(); i++) {
			MenuTinyVo sortVo = listVo.get(i);
			sortVo.setListSub(mapChild.get(sortVo.getId()));
		}
		return listVo;
	}

	public static MenuTinyVo getVo(Menu item) {
		MenuTinyVo vo = new MenuTinyVo();
		vo.setId(item.getId());
		vo.setName(item.getName());
		vo.setUrl(item.getUrl());
		vo.setIconName(item.getIconName());
		vo.setAction(item.getAction());
		return vo;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public String getIconName() {
		return iconName;
	}

	public String getAction() {
		return action;
	}

	public List<MenuTinyVo> getListSub() {
		return listSub;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setListSub(List<MenuTinyVo> listSub) {
		this.listSub = listSub;
	}

}

package com.godplan.m.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.godplan.entity.Menu;

public class MenuVo {
	private String name = "";
	private long id = 0;
	private String remark = "";
	private String url = "";
	private int sortIndex = 0;
	private String iconName = "";
	private String action = "";
	private List<MenuVo> listChild = new ArrayList<MenuVo>();

	public static List<MenuVo> getVo(List<Menu> list) {
		List<MenuVo> listVo = new ArrayList<MenuVo>();

		Map<Long, List<MenuVo>> mapChild = new HashMap<Long, List<MenuVo>>();
		for (int i = 0; i < list.size(); i++) {
			Menu item = list.get(i);
			if (item.getParentId() == 0) {
				listVo.add(getVo(item));
			} else {
				if (!mapChild.containsKey(item.getParentId())) {
					mapChild.put(item.getParentId(), new ArrayList<MenuVo>());
				}
				mapChild.get(item.getParentId()).add(getVo(item));
			}
		}
		for (int i = 0; i < listVo.size(); i++) {
			MenuVo sortVo = listVo.get(i);
			sortVo.setListChild(mapChild.get(sortVo.getId()));
		}
		return listVo;
	}

	public static MenuVo getVo(Menu item) {
		MenuVo vo = new MenuVo();
		vo.setId(item.getId());
		vo.setName(item.getName());
		vo.setUrl(item.getUrl());
		vo.setSortIndex(item.getSortIndex());
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

	public void setName(String name) {
		this.name = name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUrl() {
		return url;
	}

	public int getSortIndex() {
		return sortIndex;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public List<MenuVo> getListChild() {
		return listChild;
	}

	public void setListChild(List<MenuVo> listChild) {
		this.listChild = listChild;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}

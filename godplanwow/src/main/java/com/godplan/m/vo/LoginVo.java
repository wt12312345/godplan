package com.godplan.m.vo;

import java.util.ArrayList;
import java.util.List;

public class LoginVo {
	private List<MenuVo> listMenu = new ArrayList<MenuVo>();

	public List<MenuVo> getListMenu() {
		return listMenu;
	}

	public void setListMenu(List<MenuVo> listMenu) {
		this.listMenu = listMenu;
	}

}

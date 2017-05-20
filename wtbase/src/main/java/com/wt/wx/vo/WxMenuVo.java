package com.wt.wx.vo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.wt.base.util.TypeUtil;

public class WxMenuVo {
	private String type = "";
	private String name = "";
	private String key = "";
	private String url = "";
	private List<WxMenuVo> sub_button = new ArrayList<WxMenuVo>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<WxMenuVo> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<WxMenuVo> sub_button) {
		this.sub_button = sub_button;
	}

	public static WxMenuVo getWxmenuVo(JSONObject menuJson) {
		WxMenuVo menu = new WxMenuVo();
		if (menuJson.has("key")) {
			menu.setKey(TypeUtil.toString(menuJson.getString("key")));
		}
		if (menuJson.has("name")) {
			menu.setName(TypeUtil.toString(menuJson.getString("name")));
		}
		if (menuJson.has("type")) {
			menu.setType(TypeUtil.toString(menuJson.getString("type")));
		}
		if (menuJson.has("url")) {
			menu.setUrl(TypeUtil.toString(menuJson.getString("url")));
		}
		JSONArray children = menuJson.getJSONArray("sub_button");
		if (children.length() > 0) {
			for (int i = 0; i < children.length(); i++) {
				JSONObject oneMenuJson = children.getJSONObject(i);
				WxMenuVo menuChild = WxMenuVo.getWxmenuVo(oneMenuJson);
				menu.getSub_button().add(menuChild);
			}
		}
		return menu;
	}

	public static WxMenuVo getWxmenuVo(String name, String ctn,
			String[] childNames, String[] childCtns) {
		WxMenuVo menu = new WxMenuVo();
		menu.setName(name);
		if (ctn.equals("")) {
			for (int i = 0; i < childNames.length; i++) {
				String tempName = childNames[i];
				String tempCtn = childCtns[i];
				if (tempName.equals("") || tempCtn.equals("")) {
					continue;
				}
				WxMenuVo child = WxMenuVo.getWxmenuVo(tempName, tempCtn);
				menu.getSub_button().add(child);
			}
		} else if (ctn.contains("http://") || ctn.contains("https://")) {
			menu.setType("view");
			menu.setUrl(ctn);
		} else {
			menu.setType("click");
			menu.setKey(ctn);
		}
		return menu;
	}

	public static WxMenuVo getWxmenuVo(String name, String ctn) {
		WxMenuVo menu = new WxMenuVo();
		menu.setName(name);
		if (ctn.contains("http://") || ctn.contains("https://")) {
			menu.setType("view");
			menu.setUrl(ctn);
		} else {
			menu.setType("click");
			menu.setKey(ctn);
		}
		return menu;
	}
}

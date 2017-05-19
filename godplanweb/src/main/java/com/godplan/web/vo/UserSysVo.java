package com.godplan.web.vo;

import java.util.ArrayList;
import java.util.List;

import com.godplan.entity.UserSys;

public class UserSysVo {
	private long id = 0;
	private String name = "";
	private String loginName;
	private String email = "";
	/**
	 * 是否有效 1 有效 0 禁用 -1删除
	 */
	private int status = 0;

	public static List<UserSysVo> getVo(List<UserSys> list) {
		List<UserSysVo> listVo = new ArrayList<UserSysVo>();
		for (int i = 0; i < list.size(); i++) {
			UserSys item = list.get(i);
			listVo.add(getVo(item));
		}
		return listVo;
	}

	public static UserSysVo getVo(UserSys item) {
		UserSysVo itemVo = new UserSysVo();
		itemVo.setName(item.getName());
		itemVo.setId(item.getId());
		itemVo.setLoginName(item.getLoginName());
		itemVo.setEmail(item.getEmail());
		itemVo.setStatus(item.getStatus());
		return itemVo;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getEmail() {
		return email;
	}

	public int getStatus() {
		return status;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}

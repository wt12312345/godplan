package com.godplan.m.vo;

import java.util.ArrayList;
import java.util.List;

import com.godplan.entity.Buzzword;
import com.wt.base.util.TimeUtil;

public class BuzzwordVo {
	private long id = 0;
	private String title = "";
	private String tags = "";
	private String time = "";
	private String content = "";

	public static List<BuzzwordVo> getVo(List<Buzzword> list) {
		List<BuzzwordVo> listVo = new ArrayList<BuzzwordVo>();
		for (int i = 0; i < list.size(); i++) {
			Buzzword item = list.get(i);
			listVo.add(getVo(item));
		}
		return listVo;
	}

	public static BuzzwordVo getVo(Buzzword item) {
		BuzzwordVo itemVo = new BuzzwordVo();
		itemVo.setId(item.getId());
		itemVo.setTitle(item.getTitle());
		itemVo.setTags(item.getTags()); 
		itemVo.setContent(item.getContent());
		itemVo.setTime(TimeUtil.getDateMonthStr(item.getTime()));
		return itemVo;
	}

	public String getTitle() {
		return title;
	}

	public String getTags() {
		return tags;
	}

	public String getTime() {
		return time;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
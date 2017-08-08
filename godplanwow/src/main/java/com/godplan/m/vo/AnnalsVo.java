package com.godplan.m.vo;

import java.util.ArrayList;
import java.util.List;

import com.godplan.entity.Annals;

public class AnnalsVo {
	private long id = 0;
	private String title = "";
	private String tags = "";
	private String content = "";
	private int year = 0;
	private int month = 0;
	private int day = 0;
	private int level = 0;
	/**
	 * 1 人；2 战事；3 文学/定律；4 皇帝/朝代；5 天灾；6 会议；7 创造/发明；
	 */
	private int iconIndex = 0;
	private int groupIndex = 0;
	private String groupName = "";

	public static List<AnnalsVo> getVo(List<Annals> list) {
		List<AnnalsVo> listVo = new ArrayList<AnnalsVo>();
		for (int i = 0; i < list.size(); i++) {
			Annals item = list.get(i);
			listVo.add(getVo(item));
		}
		return listVo;
	}

	public static AnnalsVo getVo(Annals item) {
		AnnalsVo itemVo = new AnnalsVo();
		itemVo.setId(item.getId());
		itemVo.setTitle(item.getTitle());
		itemVo.setContent(item.getContent());
		itemVo.setTags(item.getTags());
		itemVo.setYear(item.getYear());
		itemVo.setMonth(item.getMonth());
		itemVo.setDay(item.getDay());
		itemVo.setLevel(item.getLevel());
		itemVo.setIconIndex(item.getIconIndex());
		itemVo.setGroupIndex(item.getGroupIndex());
		switch (itemVo.getGroupIndex()) {
		case 1:
			itemVo.setGroupName("中国");
			break;
		case 2:
			itemVo.setGroupName("其它");
			break;
		default:
			break;
		}
		return itemVo;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getTags() {
		return tags;
	}

	public String getContent() {
		return content;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getLevel() {
		return level;
	}

	public int getIconIndex() {
		return iconIndex;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setIconIndex(int iconIndex) {
		this.iconIndex = iconIndex;
	}

	public int getGroupIndex() {
		return groupIndex;
	}

	public void setGroupIndex(int groupIndex) {
		this.groupIndex = groupIndex;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}

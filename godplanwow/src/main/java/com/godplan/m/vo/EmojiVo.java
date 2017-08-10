package com.godplan.m.vo;

import java.util.ArrayList;
import java.util.List;

import com.godplan.entity.Emoji;

public class EmojiVo {
	private long id = 0;
	private String name;
	private String symbol = "";

	public static List<EmojiVo> getVo(List<Emoji> list) {
		List<EmojiVo> listVo = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Emoji item = list.get(i);
			listVo.add(getVo(item));
		}
		return listVo;
	}

	public static EmojiVo getVo(Emoji item) {
		EmojiVo itemVo = new EmojiVo();
		itemVo.setId(item.getId());
		itemVo.setName(item.getName());
		itemVo.setSymbol(item.getSymbol());
		return itemVo;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}

package com.godplan.web.vo;

import java.util.ArrayList;
import java.util.List;

public class HeadComboVo {
	private int num = 0;
	private int numJoin = 0;
	private String img = "";
	private String nickName = "";
	private List<HeadComboVo> listChild = new ArrayList<HeadComboVo>();

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getNumJoin() {
		return numJoin;
	}

	public void setNumJoin(int numJoin) {
		this.numJoin = numJoin;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<HeadComboVo> getListChild() {
		return listChild;
	}

	public void setListChild(List<HeadComboVo> listChild) {
		this.listChild = listChild;
	}

}

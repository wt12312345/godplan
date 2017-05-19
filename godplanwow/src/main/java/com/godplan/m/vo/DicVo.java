package com.godplan.m.vo;

import java.util.ArrayList;
import java.util.List;

import com.godplan.entity.Dictionary;

public class DicVo {
	private long id = 0;
	private String name = "";
	private int valint = 0;
	private long vallong = 0;
	private double valdbl = 0.0;
	private String valstr = "";
	private String remark = "";

	public static List<DicVo> getVo(List<Dictionary> list) {
		List<DicVo> listVo = new ArrayList<DicVo>();
		for (int i = 0; i < list.size(); i++) {
			Dictionary item = list.get(i);
			listVo.add(getVo(item));
		}
		return listVo;
	}

	public static DicVo getVo(Dictionary item) {
		DicVo itemVo = new DicVo();
		itemVo.setId(item.getId());
		itemVo.setName(item.getName());
		itemVo.setValint(item.getValint());
		itemVo.setVallong(item.getVallong());
		itemVo.setValdbl(item.getValdbl());
		itemVo.setValstr(item.getValstr());
		itemVo.setRemark(item.getRemark());
		return itemVo;
	}

	public String getName() {
		return name;
	}

	public int getValint() {
		return valint;
	}

	public double getValdbl() {
		return valdbl;
	}

	public String getValstr() {
		return valstr;
	}

	public String getRemark() {
		return remark;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValint(int valint) {
		this.valint = valint;
	}

	public void setValdbl(double valdbl) {
		this.valdbl = valdbl;
	}

	public void setValstr(String valstr) {
		this.valstr = valstr;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVallong() {
		return vallong;
	}

	public void setVallong(long vallong) {
		this.vallong = vallong;
	}

}

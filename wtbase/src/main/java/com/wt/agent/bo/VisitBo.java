package com.wt.agent.bo;

import java.util.List;
import java.util.Map;

import com.wt.agent.entity.RecordVisit;


public class VisitBo {

	private int date;
	Map<Integer, List<RecordVisit>> mapvo;

	public Map<Integer, List<RecordVisit>> getMapvo() {
		return mapvo;
	}

	public void setMapvo(Map<Integer, List<RecordVisit>> mapvo) {
		this.mapvo = mapvo;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

}

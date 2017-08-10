package com.godplan.m.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godplan.dao.AnnalsDao;
import com.godplan.entity.Annals;
import com.wt.web.dao.WtCondition;
import com.wt.web.service.AbstractServiceImpl;

@Service("quartzAnnalsService")
public class QuartzAnnalsServiceImpl extends AbstractServiceImpl<Annals> implements QuartzAnnalsService {
	@Resource
	private AnnalsDao annalsDao;

	@Override
	public Class<Annals> getEntityClass() {
		return Annals.class;
	}

	@Override
	@Transactional
	public void createIndexHtml() throws Exception {
		// 数据先排好序
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.ASC, "orderByTime"));
		List<Annals> listItem = annalsDao.findByCondition(conditions);
		String webRoot = "http://yue21.com/history.html";
		// 所有要展示的时间，后续直接换成每年时间等
		List<String> listTime = new ArrayList<String>();
		// 时间，国内数据
		Map<String, List<Annals>> mapTimeListAnnalsChina = new HashMap<>();
		// 时间，国外数据
		Map<String, List<Annals>> mapTimeListAnnalsOther = new HashMap<>();
		int length = listItem.size();
		for (int i = 0; i < length; i++) {
			Annals item = listItem.get(i);
			String time = "";
			// if (item.getMonth() < 0) {
			// time += "~";
			// }
			if (item.getYear() < 0) {
				time += "公元前" + item.getYear() * -1;
			} else {
				time += "公元" + item.getYear();
			}
			if (!listTime.contains(time)) {
				listTime.add(time);
			}
			if (item.getGroupIndex() == 1) {
				if (!mapTimeListAnnalsChina.containsKey(time)) {
					mapTimeListAnnalsChina.put(time, new ArrayList<Annals>());
				}
				mapTimeListAnnalsChina.get(time).add(item);
			} else {
				if (!mapTimeListAnnalsOther.containsKey(time)) {
					mapTimeListAnnalsOther.put(time, new ArrayList<Annals>());
				}
				mapTimeListAnnalsOther.get(time).add(item);
			}
		}

		StringBuilder htmlBuilder = setHtml(listTime, mapTimeListAnnalsChina, mapTimeListAnnalsOther);

		Document doc = Jsoup.connect(webRoot).get();

		Element content = doc.getElementById("list");

		content.html(htmlBuilder.toString());
		String htmlResult = doc.html();
		String filePath = String.valueOf("/usr/local/godplan/www/history.html");
		// filePath = String.valueOf("C:/workspace/临时存放处/history.html");
		saveFile(filePath, htmlResult);
	}

	private StringBuilder setHtml(List<String> listTime, Map<String, List<Annals>> mapTimeListAnnalsChina,
			Map<String, List<Annals>> mapTimeListAnnalsOther) {
		StringBuilder htmlBuilder = new StringBuilder();
		for (int i = 0; i < listTime.size(); i++) {
			String time = listTime.get(i);
			htmlBuilder.append("<li class=\"item\"><div class=\"china\">");
			if (mapTimeListAnnalsChina.containsKey(time)) {
				List<Annals> listItem = mapTimeListAnnalsChina.get(time);
				for (int j = 0; j < listItem.size(); j++) {
					Annals item = listItem.get(j);
					htmlBuilder.append(
							"<h2>" + item.getTitle() + "</h2><p class=\"detail\">" + item.getContent() + "</p>");
				}
			}
			htmlBuilder.append("</div><div class=\"time\"><span>" + time + "</span></div><div class=\"other\">");
			if (mapTimeListAnnalsOther.containsKey(time)) {
				List<Annals> listItem = mapTimeListAnnalsOther.get(time);
				for (int j = 0; j < listItem.size(); j++) {
					Annals item = listItem.get(j);
					htmlBuilder.append(
							"<h2>" + item.getTitle() + "</h2><p class=\"detail\">" + item.getContent() + "</p>");
				}
			}
			htmlBuilder.append("</div></li>");
		}
		return htmlBuilder;
	}

	private void saveFile(String filePath, String html) throws IOException {
		File file = new File(filePath);
		FileUtils.writeStringToFile(file, html, "UTF-8");
		System.out.println("保存文件成功 - " + filePath);
	}

}

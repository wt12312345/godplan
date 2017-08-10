package com.godplan.m.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godplan.dao.EmojiDao;
import com.godplan.entity.Emoji;
import com.wt.web.dao.WtCondition;
import com.wt.web.service.AbstractServiceImpl;

@Service("quartzEmojiService")
public class QuartzEmojiServiceImpl extends AbstractServiceImpl<Emoji> implements QuartzEmojiService {
	@Resource
	private EmojiDao emojiDao;

	@Override
	public Class<Emoji> getEntityClass() {
		return Emoji.class;
	}

	@Override
	@Transactional
	public void createIndexHtml() throws Exception {
		// 数据先排好序
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		List<Emoji> listItem = emojiDao.findByCondition(conditions);

		String webRoot = "http://yue21.com/emoji.html";

		StringBuilder htmlBuilder = setHtml(listItem);

		Document doc = Jsoup.connect(webRoot).get();

		Element content = doc.getElementById("list");

		content.html(htmlBuilder.toString());
		String htmlResult = doc.html();
		String filePath = String.valueOf("/usr/local/godplan/www/emoji.html");
//		filePath = String.valueOf("C:/workspace/临时存放处/emoji.html");
		saveFile(filePath, htmlResult);
	}

	private StringBuilder setHtml(List<Emoji> listItem) {
		StringBuilder htmlBuilder = new StringBuilder();
		int length = listItem.size();
		for (int i = 0; i < length; i++) {
			Emoji item = listItem.get(i);
			htmlBuilder.append("<div><p>" + item.getSymbol() + "</p><h2>" + item.getName() + "</h2></div>");
		}
		return htmlBuilder;
	}

	private void saveFile(String filePath, String html) throws IOException {
		File file = new File(filePath);
		FileUtils.writeStringToFile(file, html, "UTF-8");
		System.out.println("保存文件成功 - " + filePath);
	}

}

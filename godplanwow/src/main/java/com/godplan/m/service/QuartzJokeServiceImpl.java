package com.godplan.m.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godplan.Conf;
import com.godplan.constant.EnumCom;
import com.godplan.dao.JokeDao;
import com.godplan.entity.Joke;
import com.wt.web.dao.WtCondition;
import com.wt.web.service.AbstractServiceImpl;

@Service("quartzJokeService")
public class QuartzJokeServiceImpl extends AbstractServiceImpl<Joke> implements QuartzJokeService {
	@Resource
	private JokeDao jokeDao;

	@Override
	public Class<Joke> getEntityClass() {
		return Joke.class;
	}

	@Override
	@Transactional
	public void createIndexHtml() throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "status", EnumCom.Enable.getIndex()));
		List<Joke> listJoke = jokeDao.findByCondition(conditions);
		String webRoot = "http://yue21.com/";
		Document doc = Jsoup.connect(webRoot).get();

		Element content = doc.getElementById("jokeList");
		// 每页N条
		int pageSize = Conf.PageSize;
		// 一共N条
		int listJokeSize = listJoke.size();
		// 一共N页
		int page = listJokeSize / pageSize;
		// 凑整N条
		listJokeSize = page * pageSize;
		StringBuilder htmlBuilder = new StringBuilder();

		// 最新39条给首页
		htmlBuilder = new StringBuilder();
		for (int i = 1; i < pageSize; i++) {
			Joke joke = listJoke.get(listJokeSize - i);
			setHtml(htmlBuilder, joke);
		}

		// content.html(content.html() + htmlBuilder.toString());
		content.html(htmlBuilder.toString());
		String htmlResult = doc.html();
		String filePath = String.valueOf("/usr/local/godplan/www/index.html");
		// filePath = String.valueOf("C:/workspace/临时存放处/index.html");
		saveFile(filePath, htmlResult);

		htmlBuilder = new StringBuilder();
		String description = "";
		String keyWord = "";
		String title = "";
		List<String> listFileName = new ArrayList<String>();
		for (int i = 0; i < page; i++) {
			for (int j = i; j < listJokeSize; j += page) {
				Joke joke = listJoke.get(j);
				if (title.length() < 20) {
					title += joke.getName() + " ";
				}
				if (keyWord.length() < 100) {
					keyWord += joke.getName() + ",";
				}
				if (description.length() < 200) {
					description += joke.getContent() + " ";
				}
				setHtml(htmlBuilder, joke);
			}
			// 一个循环一页
			// 保存一个文件，然后进行下一个
			// 设置标题、关键词、描述、正文内容
			Element top = doc.getElementById("jokeTop");
			top.html("");
			title = title.substring(0, title.length() - 1);
			doc.title(title + "- 阅二十一");
			keyWord = keyWord.substring(0, keyWord.length() - 1);
			doc.select("meta[name=keywords]").get(0).attr("content", keyWord);
			doc.select("meta[name=description]").get(0).attr("content", description);
			content.html(htmlBuilder.toString());
			htmlResult = doc.html();
			// 保存文件
			String fileName = "index" + (i + 1) + ".html";
			// 此次所有的文件名，供site.txt使用
			listFileName.add(webRoot + fileName);
			filePath = String.valueOf("/usr/local/godplan/www/" + fileName);
			// filePath = String.valueOf("C:/workspace/临时存放处/" + fileName);
			saveFile(filePath, htmlResult);
			// 初始化数据
			htmlBuilder = new StringBuilder();
			description = "";
			keyWord = "";
			title = "";
		}

		// 已存在的连接
		List<String> listFileNameNow = new ArrayList<String>();
		File file = new File("/usr/local/godplan/www/site.txt");
		// File file = new File("C:/workspace/site.txt");
		if (file.isFile() && file.exists()) { // 判断文件是否存在
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				listFileNameNow.add(lineTxt);
			}
			bufferedReader.close();
			BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8")); // 指定编码格式，以免读取时中文字符异常
			for (int j = 0; j < listFileName.size(); j++) {
				if (!listFileNameNow.contains(listFileName.get(j))) {
					fw.append("\r\n" + listFileName.get(j));
					fw.flush(); // 全部写入缓存中的内容
				}
			}
			fw.close();
		}
	}

	private void setHtml(StringBuilder htmlBuilder, Joke joke) {
		String content = joke.getContent();
		if (content.indexOf("http:") == 0) {
			htmlBuilder.append(
					"<div class=\"border-book joke\"><img alt=\"\" src=\"" + content + "\" /><p class=\"title\">—— "
							+ joke.getName() + "</p><span class=\"name\"> " + joke.getNickName() + "</span></div>");

		} else {
			htmlBuilder.append(
					"<div class=\"border-book joke\"><p class=\"content\">" + content + "</p><p class=\"title\">—— "
							+ joke.getName() + "</p><span class=\"name\"> " + joke.getNickName() + "</span></div>");
		}
	}

	private void saveFile(String filePath, String html) throws IOException {
		File file = new File(filePath);
		FileUtils.writeStringToFile(file, html, "UTF-8");
		System.out.println("保存文件成功 - " + filePath);
	}

}

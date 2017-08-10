package com.test.deepweb;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class YanwenziTest {

	public static void main(String[] args) {
		try {
			String url = "http://www.yanwenzi.com/changyong/2.htm";
			Document doc = Jsoup.connect(url)
					.header("User-Agent",
							"Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1")
					.timeout(5000).get();
			Element eleUl = doc.getElementById("items");
			Elements arrLi = eleUl.getElementsByTag("li");
			for (int i = 0; i < arrLi.size(); i++) {
				String name = "";
				String symbol = "";
				Element eleLi = arrLi.get(i);
				Elements arrP = eleLi.getElementsByTag("p");
				if (arrP.size() > 0) {
					Element eleP = arrP.get(0);
					symbol = eleP.html();
				}
				Elements arrDiv = eleLi.getElementsByTag("div");
				if (arrDiv.size() > 0) {
					Element eleDiv = arrDiv.get(0);
					name = eleDiv.html();
				}
				System.out.println(name+" "+symbol);
			}
		} catch (IOException e) {
			System.out.println("err:" + e.getMessage());
		}

	}

}

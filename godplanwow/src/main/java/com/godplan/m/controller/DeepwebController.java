package com.godplan.m.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godplan.entity.Emoji;
import com.godplan.m.service.EmojiService;
import com.wt.base.util.TypeUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;

/**
 * 爬虫
 * 
 * @author wt12312345
 *
 */
@Controller
@RequestMapping("/m")
public class DeepwebController extends AbstractController {

	@Resource
	private EmojiService emojiService;

	/**
	 * 颜文字
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deepweb/yanwenzi", method = { RequestMethod.GET })
	public JsonResponse deepwebYanwenzi(HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		try {
			int page = TypeUtil.toInt(request.getParameter("page"));
			List<String> listUrl = new ArrayList<>();
			listUrl.add("http://www.yanwenzi.com/changyong/");
			listUrl.add("http://www.yanwenzi.com/gaoxing/");
			listUrl.add("http://www.yanwenzi.com/maimeng/");
			listUrl.add("http://www.yanwenzi.com/zhenjing/");
			listUrl.add("http://www.yanwenzi.com/shengqi/");
			listUrl.add("http://www.yanwenzi.com/wunai/");
			listUrl.add("http://www.yanwenzi.com/yun/");
			listUrl.add("http://www.yanwenzi.com/daoqian/");
			listUrl.add("http://www.yanwenzi.com/dongwu/");
			listUrl.add("http://www.yanwenzi.com/haixiu/");
			listUrl.add("http://www.yanwenzi.com/ku/");
			listUrl.add("http://www.yanwenzi.com/memeda/");
			listUrl.add("http://www.yanwenzi.com/shuila/");
			listUrl.add("http://www.yanwenzi.com/zaijian/");
			listUrl.add("http://www.yanwenzi.com/aojiao/");
			listUrl.add("http://www.yanwenzi.com/chihuo/");
			listUrl.add("http://www.yanwenzi.com/deyi/");
			listUrl.add("http://www.yanwenzi.com/haipa/");
			listUrl.add("http://www.yanwenzi.com/jiong/");
			listUrl.add("http://www.yanwenzi.com/zan/");
			listUrl.add("http://www.yanwenzi.com/nanguo/");
			listUrl.add("http://www.yanwenzi.com/jian/");
			listUrl.add("http://www.yanwenzi.com/qita/");
			String url = listUrl.get(page);
			getAndSaveDeepwebYanwenziByUrl(url);
			for (int j = 2; j < 100; j++) {
				try {
					String urlSub = url + j + ".htm";
					getAndSaveDeepwebYanwenziByUrl(urlSub);
				} catch (Exception e2) {
					break;
				}
			}
			// for (int i = 0; i < listUrl.size(); i++) {
			// String url = listUrl.get(i);
			// getAndSaveDeepwebYanwenziByUrl(url);
			// for (int j = 2; j < 100; j++) {
			// try {
			// String urlSub = url + j + ".htm";
			// getAndSaveDeepwebYanwenziByUrl(urlSub);
			// } catch (Exception e2) {
			// break;
			// }
			// }
			// }
		} catch (Exception e) {
			System.out.println("err:" + e.getMessage());
		}
		return jr;
	}

	private void getAndSaveDeepwebYanwenziByUrl(String url) throws Exception {
		Thread.sleep(1000);
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
			Emoji item = new Emoji();
			item.setName(name);
			item.setSymbol(symbol);

			Emoji check = emojiService.getByName(name);
			if (check == null) {
				emojiService.saveOrUpdate(item);
			}
			System.out.println(name + " " + symbol);
		}
	}
}

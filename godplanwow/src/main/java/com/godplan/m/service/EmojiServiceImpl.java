package com.godplan.m.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.godplan.dao.EmojiDao;
import com.godplan.entity.Emoji;
import com.godplan.m.service.bo.SearchBo;
import com.wt.base.util.TypeUtil;
import com.wt.web.dao.WtCondition;
import com.wt.web.domain.Page;
import com.wt.web.service.AbstractServiceImpl;

@Service("emojiService")
@SuppressWarnings("all")
public class EmojiServiceImpl extends AbstractServiceImpl<Emoji> implements EmojiService {

	@Resource
	private EmojiDao emojiDao;

	@Override
	public Class getEntityClass() {
		return Emoji.class;
	}

	@Override
	public Emoji getByName(String name) throws Exception {
		Emoji item = emojiDao.findUnique("name", name);
		return item;
	}

	@Override
	public List<Emoji> getList(Page page, String orderBy, SearchBo search) throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		if (!TypeUtil.isEmpty(search.getKeyWord())) {
			conditions.add(new WtCondition(new WtCondition(WtCondition.LIKE, "name", search.getKeyWord()),
					new WtCondition(WtCondition.LIKE, "symbol", search.getKeyWord())));
		}
		// 排序
		if (TypeUtil.isEmpty(orderBy)) {
			orderBy = "id-d";
		}
		String sortMode = WtCondition.ASC;
		if (orderBy.contains("-d")) {
			sortMode = WtCondition.DESC;
		}
		orderBy = orderBy.replace("-d", "");
		conditions.add(new WtCondition(sortMode, orderBy));
		// 分页
		if (page.getTotal() == 0) {
			int count = emojiDao.findCountByCondition(conditions);
			page.setTotal(count);
		}
		return emojiDao.findByCondition(page.getSize(), conditions, page.getCurrent());
	}

}

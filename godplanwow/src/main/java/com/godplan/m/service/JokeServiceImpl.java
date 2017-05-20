package com.godplan.m.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.godplan.dao.JokeDao;
import com.godplan.entity.Joke;
import com.godplan.m.service.bo.SearchBo;
import com.wt.base.util.TypeUtil;
import com.wt.web.dao.WtCondition;
import com.wt.web.domain.Page;
import com.wt.web.service.AbstractServiceImpl;

@Service("jokeService")
public class JokeServiceImpl extends AbstractServiceImpl<Joke> implements JokeService {
	@Resource
	private JokeDao jokeDao;

	@Override
	public Class<Joke> getEntityClass() {
		return Joke.class;
	}

	@Override
	public List<Joke> getList(Page page, String orderBy, SearchBo search) throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		if (!TypeUtil.isEmpty(search.getKeyWord())) {
			conditions.add(new WtCondition(new WtCondition(WtCondition.LIKE, "name", search.getKeyWord()),
					new WtCondition(WtCondition.LIKE, "content", search.getKeyWord())));
		}
		if (search.getStatus() > 0) {
			conditions.add(new WtCondition(WtCondition.EQ, "status", search.getStatus()));
		}
		if (search.getStartTime() != null) {
			conditions.add(new WtCondition(WtCondition.GE, "createTime", search.getStartTime()));
		}
		if (search.getEndTime() != null) {
			conditions.add(new WtCondition(WtCondition.LE, "createTime", search.getEndTime()));
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
			int count = jokeDao.findCountByCondition(conditions);
			page.setTotal(count);
		}
		return jokeDao.findByCondition(page.getSize(), conditions, page.getCurrent());
	}

}

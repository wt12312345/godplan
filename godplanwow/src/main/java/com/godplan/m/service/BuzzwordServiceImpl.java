package com.godplan.m.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.godplan.dao.BuzzwordDao;
import com.godplan.entity.Buzzword;
import com.godplan.m.service.bo.SearchBo;
import com.wt.base.util.TypeUtil;
import com.wt.web.dao.WtCondition;
import com.wt.web.domain.Page;
import com.wt.web.service.AbstractServiceImpl;

@Service("buzzwordService")
public class BuzzwordServiceImpl extends AbstractServiceImpl<Buzzword> implements BuzzwordService {
	@Resource
	BuzzwordDao buzzwordDao;

	@Override
	public Class<Buzzword> getEntityClass() {
		return Buzzword.class;
	}

	@Override
	public List<Buzzword> getList(Page page, String orderBy, SearchBo search) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		String keyWord = search.getKeyWord();
		if (!TypeUtil.isEmpty(keyWord)) {
			conditions.add(new WtCondition(new WtCondition(WtCondition.LIKE, "title", keyWord),
					new WtCondition(WtCondition.LIKE, "tags", keyWord),
					new WtCondition(WtCondition.LIKE, "content", keyWord)));
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
			int count = buzzwordDao.findCountByCondition(conditions);
			page.setTotal(count);
		}
		List<Buzzword> list = buzzwordDao.findByCondition(page.getSize(), conditions, page.getCurrent());
		return list;

	}
}

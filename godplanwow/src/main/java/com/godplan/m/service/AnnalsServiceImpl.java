package com.godplan.m.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.godplan.dao.AnnalsDao;
import com.godplan.entity.Annals;
import com.godplan.m.service.bo.SearchBo;
import com.wt.base.util.TypeUtil;
import com.wt.web.dao.WtCondition;
import com.wt.web.domain.Page;
import com.wt.web.service.AbstractServiceImpl;

@Service("annalsService")
public class AnnalsServiceImpl extends AbstractServiceImpl<Annals> implements AnnalsService {
	@Resource
	AnnalsDao annalsDao;

	@Override
	public Class<Annals> getEntityClass() {
		return Annals.class;
	}

	@Override
	public List<Annals> getList(Page page, String orderBy, SearchBo search) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.DESC, "year"));
		conditions.add(new WtCondition(WtCondition.DESC, "month"));
		conditions.add(new WtCondition(WtCondition.DESC, "day"));
		conditions.add(new WtCondition(WtCondition.DESC, "hour"));
		conditions.add(new WtCondition(WtCondition.DESC, "minute"));
		conditions.add(new WtCondition(WtCondition.DESC, "second"));
		String keyWord = search.getKeyWord();
		if (!TypeUtil.isEmpty(keyWord)) {
			conditions.add(new WtCondition(new WtCondition(WtCondition.LIKE, "name", keyWord),
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
			int count = annalsDao.findCountByCondition(conditions);
			page.setTotal(count);
		}
		List<Annals> list = annalsDao.findByCondition(page.getSize(), conditions, page.getCurrent());
		return list;

	}
}

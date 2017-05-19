package com.godplan.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.godplan.dao.DictionaryDao;
import com.godplan.entity.Dictionary;
import com.wt.web.dao.WtCondition;
import com.wt.web.service.AbstractServiceImpl;

@Service("dictionaryService")
@SuppressWarnings("all")
public class DictionaryServiceImpl extends AbstractServiceImpl<Dictionary>
		implements DictionaryService {

	@Resource
	private DictionaryDao dictionaryDao;

	@Override
	public Class getEntityClass() {
		return Dictionary.class;
	}

	@Override
	public void saveOrUpdate(Dictionary item) throws Exception {
		Dictionary dicDb = dictionaryDao.findUnique("keyId", item.getKeyId());
		if (dicDb != null) {
			dictionaryDao.saveOrUpdate(item);
		}
	}

	@Override
	public Dictionary getDictionry(String keyId) throws Exception {
		Dictionary dic = dictionaryDao.findUnique("keyId", keyId);
		if (dic == null) {
			dic = new Dictionary();
			dic.setKeyId(keyId);
			saveOrUpdate(dic);
		}
		return dic;
	}

	@Override
	public List<Dictionary> getDictionryList(String keyWord) throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.LIKE, "keyId", keyWord));
		return dictionaryDao.findByCondition(conditions);
	}

}

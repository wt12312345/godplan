package com.godplan.service;

import java.util.List;

import com.wt.web.service.AbstractService;
import com.godplan.entity.Dictionary;

public interface DictionaryService extends AbstractService<Dictionary> {

	public Dictionary getDictionry(String keyId) throws Exception;

	List<Dictionary> getDictionryList(String keyWord) throws Exception;
}

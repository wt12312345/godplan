package com.hq.service;

import java.util.List;

import com.hq.web.service.AbstractService;
import com.hq.entity.Dictionary;

public interface DictionaryService extends AbstractService<Dictionary> {

	public Dictionary getDictionry(String keyId) throws Exception;

	List<Dictionary> getDictionryList(String keyWord) throws Exception;
}

package com.godplan.m.service;

import java.util.List;

import com.godplan.entity.Emoji;
import com.godplan.m.service.bo.SearchBo;
import com.wt.web.domain.Page;
import com.wt.web.service.AbstractService;

public interface EmojiService extends AbstractService<Emoji> {

	public Emoji getByName(String name) throws Exception;

	List<Emoji> getList(Page page, String orderBy, SearchBo search) throws Exception;
}

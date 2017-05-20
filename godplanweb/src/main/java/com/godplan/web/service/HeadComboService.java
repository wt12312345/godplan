package com.godplan.web.service;

import com.godplan.entity.HeadCombo;
import com.wt.web.service.AbstractService;

public interface HeadComboService extends AbstractService<HeadCombo> {
	HeadCombo getByKey(String key) throws Exception;
}

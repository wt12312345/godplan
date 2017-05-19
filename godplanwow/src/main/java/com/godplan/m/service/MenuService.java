package com.godplan.m.service;

import java.util.List;

import com.godplan.entity.Menu;
import com.wt.web.service.AbstractService;


public interface MenuService extends AbstractService<Menu> {

	List<Menu> getListByParent(long parentId);
	
	List<Menu> getList(List<Object> listId);

	List<Menu> getListByUrl(List<Object> listUrl);

}

package com.godplan.m.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.godplan.dao.MenuDao;
import com.godplan.entity.Menu;
import com.wt.web.dao.WtCondition;
import com.wt.web.service.AbstractServiceImpl;


@Service("menuService")
public class MenuServiceImpl extends AbstractServiceImpl<Menu> implements
		MenuService {

	@Resource
	private MenuDao menuDao;

	@Override
	public Class<Menu> getEntityClass() {
		return Menu.class;
	}

	@Override
	public List<Menu> getAll() {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.ASC, "sortIndex"));
		return menuDao.findByCondition(conditions);
	}

	@Override
	public List<Menu> getListByParent(long parentId) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "parentId", parentId));
		return menuDao.findByCondition(conditions);
	}

	@Override
	public List<Menu> getList(List<Object> listId) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition("id", listId));
		return menuDao.findByCondition(conditions);
	}

	@Override
	public List<Menu> getListByUrl(List<Object> listUrl) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition("url", listUrl));
		return menuDao.findByCondition(conditions);
	}

}

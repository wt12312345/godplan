package com.godplan.dao;

import org.springframework.stereotype.Repository;

import com.godplan.entity.Menu;
import com.wt.web.dao.AbstractDaoImpl;

@Repository(value = "menuDao")
public class MenuDaoImpl extends AbstractDaoImpl<Menu> implements MenuDao {

	@Override
	public Class<Menu> getEntityClass() {
		return Menu.class;
	}
}

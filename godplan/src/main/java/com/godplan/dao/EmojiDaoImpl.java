package com.godplan.dao;

import org.springframework.stereotype.Repository;

import com.godplan.entity.Emoji;
import com.wt.web.dao.AbstractDaoImpl;

@Repository("emojiDao")
public class EmojiDaoImpl extends AbstractDaoImpl<Emoji> implements EmojiDao {

	@Override
	public Class<Emoji> getEntityClass() {
		return Emoji.class;
	}

}

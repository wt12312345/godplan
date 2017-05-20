package com.godplan.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.godplan.constant.EnumCom;
import com.godplan.dao.UserDao;
import com.godplan.entity.User;
import com.wt.base.util.EmojiFilter;
import com.wt.base.util.EmojiFilterUtils;
import com.wt.base.util.TypeUtil;
import com.wt.web.dao.WtCondition;
import com.wt.web.service.AbstractServiceImpl;

@Service("userService")
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	public List<User> getList(int page, int pageSize) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.NE, "status", EnumCom.DELETE.getIndex()));
		return userDao.findByCondition(pageSize, conditions, page);
	}

	@Override
	public List<User> getListById(List<Object> listId) {
		if (listId.size() == 0) {
			return new ArrayList<>();
		}
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition("id", listId));
		return userDao.findByCondition(conditions);
	}

	@Override
	public User getByLoginName(String loginName) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "loginName", loginName));
		return userDao.findByConditionUnique(conditions);
	}

	@Override
	public User getByOpenid(String openid) throws Exception {
		User user = userDao.findUnique("openid", openid);
		return user;
	}

	@Override
	public User getByNickName(String nickName) throws Exception {
		return userDao.findUnique("nickName", nickName);
	}

	@Override
	public User getByMobile(String mobile) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "mobile", mobile));
		return userDao.findByConditionUnique(conditions);
	}

	@Override
	public User getWxUser(JSONObject infoJson) throws Exception {
		String openid = infoJson.getString("openid");
		User user = userDao.findUnique("openid", openid);
		if (user == null) {
			String nickName = infoJson.getString("nickname");
			nickName = EmojiFilter.filterEmoji(nickName);
			nickName = EmojiFilterUtils.filterEmoji(nickName);
			User userCheckNickNameUnique = userDao.findUnique("nickName", nickName);
			String newNickName = nickName;
			while (userCheckNickNameUnique != null) {
				newNickName = nickName + TypeUtil.toString(Math.random()).substring(3, 5);
				userCheckNickNameUnique = userDao.findUnique("nickName", newNickName);
			}

			user = new User();
			logger.info("新微信用户信息保存开始");
			user.setCity(infoJson.getString("city"));
			user.setProvince(infoJson.getString("province"));
			user.setCountry(infoJson.getString("country"));
			user.setHeadImgUrl(infoJson.getString("headimgurl"));
			user.setNickName(newNickName);
			user.setOpenid(openid);
			user.setSex(infoJson.getInt("sex"));
			user.setLastLoginTime(new Date());
			user.setRefreshInfoTime(new Date());

			userDao.saveOrUpdate(user);
			logger.info("新微信用户信息保存成功  " + user.getId() + " " + user.getNickName());
		}
		return user;
	}

	@Override
	public User editInfo(User userInfo) throws Exception {
		User userEdit = getEntity(userInfo.getId());
		String nickName = userInfo.getNickName();
		User userCheckNickNameUnique = userDao.findUnique("nickName", nickName);
		// 如果修改的昵称不存在或者是自己，那就给保存
		if (userCheckNickNameUnique == null || userCheckNickNameUnique.getId() == userEdit.getId()) {
			userEdit.setNickName(nickName);
			userEdit.setBirthday(userInfo.getBirthday());
			saveOrUpdate(userEdit);
		}
		return userEdit;
	}
}

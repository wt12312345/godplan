package com.godplan.web.service;

import java.util.List;

import org.json.JSONObject;

import com.godplan.entity.User;
import com.wt.web.service.AbstractService;

public interface UserService extends AbstractService<User> {
	List<User> getList(int page, int pageSize);

	List<User> getListById(List<Object> listId);

	User getByLoginName(String loginName);

	User getByOpenid(String openid) throws Exception;

	User getByNickName(String nickName) throws Exception;

	User getByMobile(String mobile);

	User getWxUser(JSONObject infoJson) throws Exception;

	User editInfo(User userInfo) throws Exception;
}

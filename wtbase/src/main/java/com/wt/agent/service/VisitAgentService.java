package com.wt.agent.service;

import java.util.List;

import com.wt.agent.entity.VisitAgent;
import com.wt.web.service.AbstractService;

/** 微信公众账号接口层 */
public interface VisitAgentService extends AbstractService<VisitAgent> {
	List<VisitAgent> getInfo(String agent);
}
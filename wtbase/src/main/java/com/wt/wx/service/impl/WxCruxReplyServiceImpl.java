package com.wt.wx.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.wt.web.service.AbstractServiceImpl;
import com.wt.wx.dao.WxCruxReplyDao;
import com.wt.wx.entity.WxCruxReply;
import com.wt.wx.service.WxCruxReplyService;

@Service("wxCruxReplyService")
public class WxCruxReplyServiceImpl extends AbstractServiceImpl<WxCruxReply>
implements WxCruxReplyService{

	//@Resource
	//private WxAccountDao wxAccountDao;
	@Resource
	private WxCruxReplyDao wxCruxReplyDao;
	//@Resource
	//private WxASReplyDao wxASReplyDao;
	
/*	@Override
	public JsonReply getSubSribeReply() {
		// TODO Auto-generated method stub
		return getJsonReply("sub");//id, 
	}*/

	@Override
	public WxCruxReply getKeyWordsReply(String cruxword) {
		try {
			/* 根据indentifier找到微信账号的id */
//			WxAccount account = wxAccountDao.findWxAccountByIdentifier(id);
//			if (account == null) {
//				// can not find the wxaccount
//				return null;
//			}
			/* 根据微信的id，找出它所有的关键词回复 */
//			List<KeywordReply> rpList = account.getKeywordsReply();
//			for (KeywordReply reply : rpList) {
//				/* 如果关键词对的话，那么就进行处理，一般情况下都是text回复 */
//				if (reply.getKeyword().equals(keyword)) {
//					return getJsonFromReplyContent(reply.getContent());
//				}
//			}
			WxCruxReply cruxreply = wxCruxReplyDao.getKeywordsReply(cruxword);
			//logger.info("keyword="+cruxword);
			//logger.info("数据库关键字   表数据"+cruxreply);
				return cruxreply;
			//getJsonFromReplyContent(reply.getContent());
			
		} catch (Exception e) {
			return null;
		}
	}

	/*@Override
	public JsonReply getAutoReply() {
		return getJsonReply("auto");
	}*/

	@Override
	public Class<WxCruxReply> getEntityClass() {
		return WxCruxReply.class;
	}
	
//	private JsonReply getJsonReply(String type) {//String id, 
//		try {
			/* 根据indentifier找到微信账号的id 
			WxAccount account = wxAccountDao.findWxAccountByIdentifier(id);
			if (account == null) {
				can not find the wxaccount
				return null;
			}
			/* 根据微信的id，找出它自动回复 
			List<Wxrecontent> li=null;*/
//			List<Wxasreply> asreply = asreplyDao.findCruxWord();
			/*if (type.equals("sub")) {
				replys = account.getSubscribeReply();
			} else if (type.equals("auto")) {
				replys = account.getAutoReply();
			}*/
		/*	logger.info("从数据库中找出回复内容"+asreply);
			for (int i = 0; i < asreply.size(); i++) {
				if (type.equals("sub")) {
					return getJsonFromReplyContentb(asreply.get(i));//.getRecontent()
					//asreply = account.getSubscribeReply();
				} else if (type.equals("auto")) {
					return getJsonFromReplyContentb(asreply.get(i));//.getRecontent()
					//asreply = account.getAutoReply();
				}
			}
			if (asreply.size() == 0) {
				return null;
			} /*else {
				 暂时我们只返回第一个值 
				Collections.sort(replys, new Comparator<ReplyContent>() {
					@Override
					public int compare(ReplyContent o1, ReplyContent o2) {
						if (o1.getId() > o2.getId()) {
							return 1;
						} else if (o1.getId() < o2.getId()) {
							return -1;
						}
						return 0;
					}
				});
				return getJsonFromReplyContent(asreply.get(0));
			}*/
//		} catch (Exception e) {
//			return null;
//		}
//		return null;
//	}

	/*private JsonReply getJsonFromReplyContenta(Wxcruxreply wxcruxreply) {
		if (wxcruxreply == null) {
			return null;
		}
		JsonReply re = null;
		String jsonObj = wxcruxreply.getRecontent();
		logger.info(jsonObj);
		logger.info(wxcruxreply.getWordtype());
		if (wxcruxreply.getWordtype().equals("text")||wxcruxreply.getWordtype()=="text") {
			logger.info("进来了");
			re = JSON.parseObject(jsonObj, JsonTextReply.class);
			logger.info("进来了 re="+re);
		} else if (wxcruxreply.getWordtype().equals("image")) {
			re = JSON.parseObject(jsonObj, JsonImageReply.class);
		} else if (wxcruxreply.getWordtype().equals("voice")) {
			re = JSON.parseObject(jsonObj, JsonVoiceReply.class);
		} else if (wxcruxreply.getWordtype().equals("video")) {
			re = JSON.parseObject(jsonObj, JsonVideoReply.class);
		} else if (wxcruxreply.getWordtype().equals("music")) {
			re = JSON.parseObject(jsonObj, JsonMusicReply.class);
		} else if (wxcruxreply.getWordtype().equals("news")) {
			re = JSON.parseObject(jsonObj, JsonNewsReply.class);
		} else {  
			logger.info("返回空，操蛋了");
			return null;
		}
		return re;
	}
	private JsonReply getJsonFromReplyContentb(Wxasreply wxasreply) {
		if (wxasreply == null) {
			return null;
		}
		JsonReply re = null;
		String jsonObj = wxasreply.getRecontent();
		logger.info(wxasreply.getWordtype());
		if (wxasreply.getWordtype().equals("text")) {
			re = JSON.parseObject(jsonObj, JsonTextReply.class);
		} else if (wxasreply.getWordtype().equals("image")) {
			re = JSON.parseObject(jsonObj, JsonImageReply.class);
		} else if (wxasreply.getWordtype().equals("voice")) {
			re = JSON.parseObject(jsonObj, JsonVoiceReply.class);
		} else if (wxasreply.getWordtype().equals("video")) {
			re = JSON.parseObject(jsonObj, JsonVideoReply.class);
		} else if (wxasreply.getWordtype().equals("music")) {
			re = JSON.parseObject(jsonObj, JsonMusicReply.class);
		} else if (wxasreply.getWordtype().equals("news")) {
			re = JSON.parseObject(jsonObj, JsonNewsReply.class);
		} else {
			return null;
		}
		return re;
	}*/

	@Override
	public WxCruxReply getWordType(String wordtype) throws Exception {
		WxCruxReply cruxreply = wxCruxReplyDao.getwordtype(wordtype);
			return cruxreply;
		
	}

}

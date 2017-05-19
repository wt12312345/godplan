package com.wt.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wt.dao.SendMailDao;
import com.wt.entity.SendMail;
import com.wt.web.service.AbstractServiceImpl;

@Service("sendMailService")
public class SendMailServiceImpl extends AbstractServiceImpl<SendMail>
		implements SendMailService {

	@Resource
	private SendMailDao sendMailDao;

	@Override
	public Class<SendMail> getEntityClass() {
		return SendMail.class;
	}

	@Override
	public List<SendMail> save(String title, String content, String userName,
			List<String> listMailAddr) throws Exception {
		List<SendMail> listMail = new ArrayList<SendMail>();
		for (int i = 0; i < listMailAddr.size(); i++) {
			SendMail mail = new SendMail();
			mail.setTitle(title);
			mail.setContent(content);
			mail.setUserName(userName);
			mail.setMailAddr(listMailAddr.get(i));
			sendMailDao.saveOrUpdate(mail);
			listMail.add(mail);
		}
		return listMail;
	}

}

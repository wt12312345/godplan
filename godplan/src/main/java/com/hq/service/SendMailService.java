package com.hq.service;

import java.util.List;

import com.hq.entity.SendMail;
import com.hq.web.service.AbstractService;

public interface SendMailService extends AbstractService<SendMail> {

	List<SendMail> save(String title, String content, String userName,
			List<String> listMailAddr) throws Exception;

}

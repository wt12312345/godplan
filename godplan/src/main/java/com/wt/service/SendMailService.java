package com.wt.service;

import java.util.List;

import com.wt.entity.SendMail;
import com.wt.web.service.AbstractService;

public interface SendMailService extends AbstractService<SendMail> {

	List<SendMail> save(String title, String content, String userName,
			List<String> listMailAddr) throws Exception;

}

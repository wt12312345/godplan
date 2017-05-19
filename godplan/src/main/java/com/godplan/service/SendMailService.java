package com.godplan.service;

import java.util.List;

import com.godplan.entity.SendMail;
import com.wt.web.service.AbstractService;

public interface SendMailService extends AbstractService<SendMail> {

	List<SendMail> save(String title, String content, String userName,
			List<String> listMailAddr) throws Exception;

}

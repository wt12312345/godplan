package com.hq.manage.quartz;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("timerDataListener")
public class TimerDataListener {
	private Logger logger = Logger.getLogger(TimerDataListener.class);

	public void execute() {
		logger.info("初始化Map数据");
		try {

		} catch (Exception e) {
			logger.error("初始化Map数据 Err:" + e.getMessage());
		}
	}

}

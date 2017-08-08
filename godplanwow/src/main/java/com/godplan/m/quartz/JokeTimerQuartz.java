package com.godplan.m.quartz;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.godplan.m.service.QuartzJokeService;


/**
 * 定时输出HTML文件：1小时1次，
 * 
 * @author Administrator
 * 
 */
@Component("jokeTimerQuartz")
public class JokeTimerQuartz {

	private Logger logger = Logger.getLogger(JokeTimerQuartz.class);
	@Resource
	private QuartzJokeService quartzService;

	@Transactional
	public void execute() {
		try {
			long time = System.currentTimeMillis();
			logger.info("系统定时任务：生成index?.html文件");
			quartzService.createIndexHtml();
			logger.info("系统定时任务：生成index?.html文件，耗时："
					+ (System.currentTimeMillis() - time));
		} catch (Exception e) {
			logger.info("系统定时任务：生成index?.html文件，错误：" + e.getMessage());
		}
	}

	public QuartzJokeService getQuartzService() {
		return quartzService;
	}

	public void setQuartzService(QuartzJokeService quartzService) {
		this.quartzService = quartzService;
	}
}

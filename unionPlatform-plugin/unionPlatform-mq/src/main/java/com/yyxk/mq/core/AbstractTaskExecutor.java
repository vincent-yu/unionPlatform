package com.yyxk.mq.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置.
 * 
 * @author Houkm
 *
 *         2017年5月5日
 */
public class AbstractTaskExecutor extends ThreadPoolTaskExecutor {
	private Logger logger = LoggerFactory.getLogger(AbstractTaskExecutor.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -7101296700666667300L;

	public AbstractTaskExecutor() {
		super();
		logger.info("初始化mq线程池");
		this.setCorePoolSize(10);
		this.setKeepAliveSeconds(30000);
		this.setMaxPoolSize(1000);
		this.setQueueCapacity(200);
		logger.info("初始化mq线程池完毕");
	}

}

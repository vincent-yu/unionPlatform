package com.yyxk.registercenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心启动
 * 
 * @author Houkm
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer extends SpringBootServletInitializer {

	private static Logger logger = LoggerFactory.getLogger(EurekaServer.class);

	/**
	 * 外部tomcat入口
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		logger.info("启动注册中心");
		System.setProperty("application_name", "register-center");
		return application.sources(EurekaServer.class);
	}

	public static void main(String[] args) {
		logger.info("启动注册中心");
		System.setProperty("application_name", "register-center");
		new SpringApplicationBuilder(EurekaServer.class).run(args);
	}

}

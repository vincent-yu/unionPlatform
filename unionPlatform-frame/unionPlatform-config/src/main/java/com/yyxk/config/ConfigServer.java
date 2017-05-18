package com.yyxk.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心启动类.
 * 
 * @author Houkm
 *
 *         2017年5月5日
 */
@EnableConfigServer
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigServer extends SpringBootServletInitializer {

	private static Logger logger = LoggerFactory.getLogger(ConfigServer.class);

	/**
	 * 外部tomcat入口
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		logger.info("启动配置中心");
		System.setProperty("application_name", "config-server");
		return application.sources(ConfigServer.class);
	}

	public static void main(String[] args) {
		logger.info("启动配置中心");
		System.setProperty("application_name", "config-server");
		new SpringApplicationBuilder(ConfigServer.class).run(args);
	}

}

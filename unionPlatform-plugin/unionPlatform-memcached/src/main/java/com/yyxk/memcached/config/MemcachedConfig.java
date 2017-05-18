package com.yyxk.memcached.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yyxk.memcached.client.ClientXmemcached;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.buffer.SimpleBufferAllocator;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.utils.XMemcachedClientFactoryBean;

/**
 * 配置memcached.<br>
 * 应用可直接注入{@linkplain ClientXmemcached}
 * 
 * @author Houkm
 *
 * @date 2017年5月5日
 */
@SuppressWarnings("deprecation")
@Configuration
@ConfigurationProperties(prefix = "yyxk.memcached")
public class MemcachedConfig {

	private String servers;
	private boolean cacheEnable;

	private Logger logger = LoggerFactory.getLogger(MemcachedConfig.class);

	@Bean
	public XMemcachedClientFactoryBean memcachedClient() {
		logger.info("配置XMemcachedClientFactoryBean");
		XMemcachedClientFactoryBean bean = new XMemcachedClientFactoryBean();
		logger.info("XMemcachedClientFactoryBean.servers: {}", servers);
		bean.setServers(servers);
		bean.setCommandFactory(new BinaryCommandFactory());
		bean.setSessionLocator(new KetamaMemcachedSessionLocator());
		bean.setTranscoder(new SerializingTranscoder());
		bean.setBufferAllocator(new SimpleBufferAllocator());
		logger.info("XMemcachedClientFactoryBean配置完毕");
		return bean;
	}

	@Bean
	public ClientXmemcached clientXmemcached(MemcachedClient memcachedClient) {
		logger.info("配置ClientXmemcached");
		ClientXmemcached client = new ClientXmemcached();
		client.setMemcachedClient(memcachedClient);
		client.setCacheEnabled(cacheEnable);
		logger.info("配置ClientXmemcached完毕");
		return client;
	}

	public String getServers() {
		return servers;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}

	public boolean isCacheEnable() {
		return cacheEnable;
	}

	public void setCacheEnable(boolean cacheEnable) {
		this.cacheEnable = cacheEnable;
	}

}

package com.yyxk.config.client;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 常用配置.
 * <p>
 * 可在项目中的application.properties/application.yaml文件中添加
 * 
 * @author Houkm
 *
 *         2017年5月5日
 */
@ConfigurationProperties(prefix = "yyxk.common")
public class YyxkConfigProperties extends AbstractProperties {

	private Map<String, String> domains;

	/**
	 * 根据key获取目标的域名
	 * 
	 * @param key
	 * @return
	 * @author Houkm 2017年5月12日
	 */
	public String getDomain(String key) {
		return domains.get(key);
	}

	public void setDomains(Map<String, String> domains) {
		this.domains = domains;
	}

}

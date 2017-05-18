package com.yyxk.config.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 支付配置
 * 
 * @author Houkm
 *
 *         2017年5月12日
 */
@ConfigurationProperties(prefix = "yyxk.pay")
public class PayProperties extends AbstractProperties {

	// 支付统一回调
	private String unifiedOrderNotifyUri;

	private String domain;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUnifiedOrderNotifyUri() {
		return unifiedOrderNotifyUri;
	}

	public void setUnifiedOrderNotifyUri(String unifiedOrderNotifyUri) {
		this.unifiedOrderNotifyUri = unifiedOrderNotifyUri;
	}

}

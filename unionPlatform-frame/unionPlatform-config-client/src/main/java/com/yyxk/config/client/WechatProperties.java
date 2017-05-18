package com.yyxk.config.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信配置
 * 
 * @author Houkm
 *
 *         2017年5月12日
 */
@ConfigurationProperties(prefix = "yyxk.wechat")
public class WechatProperties extends AbstractProperties {

	private String appid;
	private String mchid;
	private String secret;
	private String certKey;
	private String ip;
	private String token;
	private String domain;
	private String wxconfigpath;

	private String sevenRoomUrl;
	private String sevenRoomToken;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMchid() {
		return mchid;
	}

	public void setMchid(String mchid) {
		this.mchid = mchid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getCertKey() {
		return certKey;
	}

	public void setCertKey(String certKey) {
		this.certKey = certKey;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getWxconfigpath() {
		return wxconfigpath;
	}

	public void setWxconfigpath(String wxconfigpath) {
		this.wxconfigpath = wxconfigpath;
	}

	public String getSevenRoomUrl() {
		return sevenRoomUrl;
	}

	public void setSevenRoomUrl(String sevenRoomUrl) {
		this.sevenRoomUrl = sevenRoomUrl;
	}

	public String getSevenRoomToken() {
		return sevenRoomToken;
	}

	public void setSevenRoomToken(String sevenRoomToken) {
		this.sevenRoomToken = sevenRoomToken;
	}

}

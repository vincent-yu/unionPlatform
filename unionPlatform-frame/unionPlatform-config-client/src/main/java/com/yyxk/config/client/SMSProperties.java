package com.yyxk.config.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 短信接口配置
 * 
 * @author Houkm
 *
 *         2017年5月10日
 */
@ConfigurationProperties(prefix = "yyxk.sms")
public class SMSProperties extends AbstractProperties {

	private Yunpian yunpian;
	private Zhuwang zhuwang;

	public Yunpian getYunpian() {
		return yunpian;
	}

	public void setYunpian(Yunpian yunpian) {
		this.yunpian = yunpian;
	}

	public Zhuwang getZhuwang() {
		return zhuwang;
	}

	public void setZhuwang(Zhuwang zhuwang) {
		this.zhuwang = zhuwang;
	}

	public static class Yunpian {
		private String api;
		private String apikey;

		public String getApi() {
			return api;
		}

		public void setApi(String api) {
			this.api = api;
		}

		public String getApikey() {
			return apikey;
		}

		public void setApikey(String apikey) {
			this.apikey = apikey;
		}

	}

	public static class Zhuwang {
		private String userid;
		private String account;
		private String password;
		private String action;
		private String api;

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public String getApi() {
			return api;
		}

		public void setApi(String api) {
			this.api = api;
		}

	}

}

package com.yyxk.core.dto;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

public class Msg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2334687262915572024L;

	public enum Type {

		/**
		 * 短信
		 */
		SMS("短信"),
		/**
		 * 邮件
		 */
		MAIL("邮件"),
		/**
		 * 微信模板消息
		 */
		WECHAT_TEMPLATE("微信模板消息"),
		/**
		 * 微信文本消息
		 */
		WECHAT_MSG("微信文本消息"),

		/**
		 * 个推
		 */
		GETUI_MSG("个推推送消息"),
		/**
		 * 企业号消息
		 */
		QYH_MSG("企业号消息");
		;

		private String name;

		private Type(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

	}

	/**
	 * 消息渠道
	 * 
	 * @author Houkm
	 *
	 *         2017年5月11日
	 */
	public enum Channel {
		/**
		 * 短信-云片
		 */
		Yunpian("云片"),
		/**
		 * 短信-筑望
		 */
		Zhuwang("筑望");

		private String name;

		private Channel(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

	}

	/**
	 * {@linkplain Type 消息类型}
	 */
	private Type type;

	/**
	 * {@linkplain Channel 消息渠道}
	 */
	private Channel channel;

	/**
	 * 消息模板代码
	 */
	private String code;

	/**
	 * 需要格式化的数据, 通过{@linkplain MessageFormat 格式化}
	 */
	private List<String> data;

	/**
	 * 目标用户，手机号/邮箱/openid
	 */
	private String target;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}

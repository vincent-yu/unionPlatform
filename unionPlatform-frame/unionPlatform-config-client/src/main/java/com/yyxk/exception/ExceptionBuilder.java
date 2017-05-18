package com.yyxk.exception;

import java.text.MessageFormat;

import com.yyxk.config.client.ExceptionProperties;

/**
 * {@linkplain SystemException 异常}构建.
 * 
 * @author Houkm
 *
 *         2017年5月5日
 */
public class ExceptionBuilder {

	// 异常配置
	private ExceptionProperties prop;
	private String msgId;
	private String msg;

	/**
	 * 创建{@link ExceptionBuilder}
	 * 
	 * @param prop
	 *            ExceptionProperties
	 * @return {@link ExceptionBuilder}
	 * @author Houkm
	 */
	public static ExceptionBuilder build(ExceptionProperties prop) {
		ExceptionBuilder builder = new ExceptionBuilder();
		builder.prop = prop;
		return builder;
	}

	/**
	 * 通过MessageFormat格式化异常信息
	 * 
	 * @param msgId
	 * @param value
	 * @return {@link ExceptionBuilder}
	 * @author Houkm
	 */
	public ExceptionBuilder format(String msgId, String... value) {
		if (!this.prop.containsKey(msgId)) {
			throw new SystemException("noexception", MessageFormat.format(this.prop.get("noexception"), msgId));
		}
		String msg = this.prop.get(msgId);
		Object[] param = value;
		this.msg = MessageFormat.format(msg, param);
		this.msgId = msgId;
		return this;
	}

	/**
	 * 设置msgId与msg
	 * 
	 * @param msgId
	 * @return {@link ExceptionBuilder}
	 * @author Houkm
	 */
	public ExceptionBuilder format(String msgId) {
		if (!this.prop.containsKey(msgId)) {
			throw new SystemException("noexception", MessageFormat.format(this.prop.get("noexception"), msgId));
		}
		String msg = this.prop.get(msgId);
		this.msg = msg;
		this.msgId = msgId;
		return this;
	}

	/**
	 * 创建SystemException
	 * 
	 * @return {@link SystemException}
	 * @author Houkm
	 */
	public SystemException create() {
		return new SystemException(msgId, msg);
	}

}

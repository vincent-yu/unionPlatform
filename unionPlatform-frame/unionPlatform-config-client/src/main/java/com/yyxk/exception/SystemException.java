package com.yyxk.exception;

/**
 * SystemException继承了java.lang.RuntimeException，标志着该类并非强制后续开发人员来处理的异常
 * 
 * System更多的意味着框架的概念，而非传统的业务逻辑概念，所以本类更多是面向框架角度来封装异常行为
 * 
 * 
 * 类设计的初衷是为了满足以下两点需求： 与Struts的ActionMessage能够方便的整合
 * 
 * 能够全面的保留系统产生异常的所有信息，以便用户调试程序
 * 
 * 故此，我们在类中引入了两个常用的实例变量：
 * 
 * ActionMessage actionError Throwable throwable
 * 
 * @author Houkm
 *
 *         2017年5月5日
 */
public class SystemException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4175077003917831650L;
	/** 为能够全面的保留系统产生异常的所有信息，以便用户调试程序而引入的实例变量 */
	private Throwable throwable;
	private String msg;
	// 错误信息代码
	private String msgId;

	@SuppressWarnings("unused")
	private SystemException() {
	}

	/**
	 * 返回异常对象
	 * 
	 * @return Throwable 异常对象
	 */
	public Throwable getThrowable() {
		return throwable;
	}

	/**
	 * 设置异常对象
	 * 
	 * @param throwable
	 *            Throwable 异常对象
	 */
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public SystemException(String msgId, String msg) {
		this.msgId = msgId;
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}
}

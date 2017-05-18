package com.yyxk.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yyxk.core.dto.Msg;
import com.yyxk.mq.producer.Producer;

/**
 * 消息发送类
 * 
 * @author Houkm
 *
 *         2017年5月11日
 */
@Component
public class MsgSender {

	@Autowired
	private Producer producer;

	/**
	 * 发送消息
	 * 
	 * @param msg
	 * 
	 * @author Houkm 2017年5月11日
	 */
	public void send(Msg msg) {
		producer.msg(msg);
	}
}

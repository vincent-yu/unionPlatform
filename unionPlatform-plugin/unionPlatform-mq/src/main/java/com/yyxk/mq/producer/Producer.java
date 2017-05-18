package com.yyxk.mq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yyxk.core.dto.Msg;

/**
 * MQ发送端
 * 
 * @author Houkm
 *
 *         2017年5月5日
 */
@Component
public class Producer {

	@Autowired
	private RabbitTemplate template;

	/**
	 * 医生搜索统计
	 * @param content
	 */
	public void asDoctorCountListener(String content) {
		template.convertAndSend("as_doctor_count_exchange", "as_doctor_count_key", content);
	}

	/**
	 * 医生问诊统计
	 * @param map
	 */
	public void asSymptomCount(String content) {
		template.convertAndSend("as_symptom_count_exchange", "as_symptom_count_key", content);
	}

	/**
	 * 发{@linkplain Msg 消息}
	 * 
	 * @param msg
	 * 
	 * @author Houkm 2017年5月12日
	 */
	public void msg(Msg msg) {
		template.convertAndSend("msg_queue_exchange", "msg_queue_key", msg);
	}
	
	/**
	 * 医生日志
	 * @param content
	 */
	public void logDoctorMsg(String content){
		template.convertAndSend("log_doctor_msg_exchange", "log_doctor_msg_key", content);
	}
	
	

}

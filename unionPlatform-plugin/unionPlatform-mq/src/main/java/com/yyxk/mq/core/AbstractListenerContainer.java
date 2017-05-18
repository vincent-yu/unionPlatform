package com.yyxk.mq.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 队列监听器容器抽象
 * 
 * @author Houkm
 *
 *         2017年4月28日
 */
public class AbstractListenerContainer extends SimpleMessageListenerContainer {

	private Logger logger = LoggerFactory.getLogger(AbstractListenerContainer.class);

	public AbstractListenerContainer(ConnectionFactory connectionFactory, ThreadPoolTaskExecutor taskExecutor,
			AbstractListener listener) {
		super();
		logger.info("初始化Listener：队列[{}] to Listener[{}]", listener.getQueueName(), listener.getClass());
		this.setConnectionFactory(connectionFactory);
		this.setTaskExecutor(taskExecutor);
		this.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		this.setQueueNames(listener.getQueueName());
		this.setMessageListener(new MessageListenerAdapter(listener));
		logger.info("初始化{}完毕", listener.getClass().getName());
	}

}

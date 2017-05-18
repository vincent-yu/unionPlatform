package com.yyxk.mq.core;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.yyxk.common.Result;

/**
 * 监听器抽象接口<br>
 * 继承{@linkplain ChannelAwareMessageListener}<br>
 * 业务执行方法handleMessage，消息是否确认取决于handleMessage返回状态
 * 
 * @author Houkm
 *
 *         2017年4月28日
 */
public interface AbstractListener extends ChannelAwareMessageListener {

	/**
	 * 业务代码实现
	 * 
	 * @param bytes
	 * @return
	 * @author Houkm 2017年4月28日
	 */
	Result<Object> handleMessage(byte[] bytes);

	Logger $logger = LoggerFactory.getLogger(AbstractListener.class);

	/**
	 * 接收MQ消息
	 */
	@Override
	default void onMessage(org.springframework.amqp.core.Message message, com.rabbitmq.client.Channel channel) {
		MessageProperties prop = message.getMessageProperties();
		$logger.info("consumerTag[{}]收到消息: queue[{}] exchange[{}] routingkey[{}]", prop.getConsumerTag(),
				prop.getConsumerQueue(), prop.getReceivedExchange(), prop.getReceivedRoutingKey());
		Result<Object> result = null;
		try {
			// 业务实现
			result = this.handleMessage(message.getBody());
		} catch (Exception e) {
			e.printStackTrace();
			$logger.error("消费失败");
			try {
				channel.basicAck(prop.getDeliveryTag(), false);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		$logger.info("消息处理结果: {}", result.json());

		// 确认消费成功
		if (result.success()) {
			try {
				channel.basicAck(prop.getDeliveryTag(), false);
				$logger.info("消费成功");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			$logger.error("消费失败");
			try {
				channel.basicAck(prop.getDeliveryTag(), false);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 设置监听的队列
	 * 
	 * @return
	 * @author Houkm 2017年4月28日
	 */
	String getQueueName();

}

package com.yyxk.redis.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.yyxk.redis.IRedisUtil;

@Component
public class SocketPub {

	@Autowired
	private IRedisUtil redisUtil;
	private Logger logger = LoggerFactory.getLogger(SocketPub.class);

	public void publishByUserId(String userId, Map<String, Object> data) {
		Map<String, Object> map = new HashMap<>();
		map.put("channel", "1");
		map.put("userId", userId);
		data.put("createTime", System.currentTimeMillis());
		map.put("data", data);
		logger.info("pub消息 {}", JSON.toJSONString(map));
		redisUtil.publish("message_topic_1", JSON.toJSONString(map));
	}

	public IRedisUtil getRedisUtil() {
		return redisUtil;
	}

	public void setRedisUtil(IRedisUtil redisUtil) {
		this.redisUtil = redisUtil;
	}

}
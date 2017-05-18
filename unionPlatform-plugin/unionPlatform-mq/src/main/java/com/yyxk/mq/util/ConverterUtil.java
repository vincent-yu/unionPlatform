package com.yyxk.mq.util;

import org.apache.commons.lang.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 序列化工具
 * 
 * @author Houkm
 *
 *         2017年5月5日
 */
public class ConverterUtil {

	private static Logger logger = LoggerFactory.getLogger(ConverterUtil.class);

	@SuppressWarnings("unchecked")
	public static <T> T toObj(byte[] bytes, Class<T> clazz) {
		T result = (T) SerializationUtils.deserialize(bytes);
		logger.info("反序列化[{}]结果: {}", clazz.getName(), JSON.toJSONString(result));
		return result;
	}

}
package com.yyxk.common;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 用于改造老项目中的CommonConstants
 * 
 * @author Houkm
 *
 *         2017年5月15日
 */
public class PropertiesMap extends HashMap<String, String> {

	private Logger logger = LoggerFactory.getLogger(PropertiesMap.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -3587075491445487899L;

	Map<String, String> prop;

	public PropertiesMap(Map<String, String> prop) {

		logger.info(JSON.toJSONString(prop));

		this.prop = prop;
	}

	public String getProperty(String key) {
		logger.info("获取配置{}", key);
		Object obj = prop.get(key);
		String v = obj == null ? null : obj.toString();
		logger.info("{} 的值为 {}", key, v);
		return v;
	}

}

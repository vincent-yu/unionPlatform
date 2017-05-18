package com.yyxk.config.client;

import java.util.Map;

/**
 * 配置抽象类
 * 
 * @author Houkm
 *
 *         2017年5月12日
 */
public abstract class AbstractProperties {

	private Map<String, String> props;

	/**
	 * 读取配置内容
	 * 
	 * @param key
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public String get(String key) {
		return props.get(key);
	}

	/**
	 * 查看是否有此配置
	 * 
	 * @param key
	 * @return
	 * @author Houkm 2017年5月12日
	 */
	public boolean containsKey(String key) {
		return props.containsKey(key);
	}

	public void setProps(Map<String, String> props) {
		this.props = props;
	}

	public Map<String, String> getProps() {
		return props;
	}

}

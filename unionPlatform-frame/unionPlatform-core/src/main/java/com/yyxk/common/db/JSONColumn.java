package com.yyxk.common.db;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * Mybatis中使用Mysql5.7中Json类型字段对应类型.
 * 
 * @author Houkm
 *
 *         2017年5月5日
 */
public class JSONColumn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3570944387519741553L;
	JSONObject map;

	public JSONObject getMap() {
		return map;
	}

	public void setMap(JSONObject map) {
		this.map = map;
	}

}

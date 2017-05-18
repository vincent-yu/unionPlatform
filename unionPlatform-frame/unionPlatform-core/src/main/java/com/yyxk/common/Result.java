package com.yyxk.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 返回结果包装类,可通过{@linkplain Results}来构建.
 * 
 * @author Houkm
 *
 *         2017年5月5日
 * @param <T>
 */
public class Result<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2499433944948002095L;

	/**
	 * 状态码
	 */
	private int code;

	/**
	 * 返回数据
	 */
	private T data;

	/**
	 * 结果说明
	 */
	private String msg;

	public static int SUCCESS = 0;
	public static int FAILURE = 1;

	/**
	 * 根据Result生成Map
	 * 
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public Map<String, Object> map() {
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		map.put("data", data);
		map.put("msg", msg);
		return map;
	}

	/**
	 * 判断code是否成功
	 * 
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public boolean success() {
		return this.code == SUCCESS;
	}

	/**
	 * 将Result转换为json串
	 * 
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public String json() {
		return JSON.toJSONString(this);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}

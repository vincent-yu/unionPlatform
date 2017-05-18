package com.yyxk.common;

/**
 * 用于构建{@linkplain Result}类.
 * 
 * @author Houkm
 *
 *         2017年5月5日
 * @param <T>
 */
public class Results<T> {

	public static <T> Result<T> success(T data) {
		Result<T> result = new Result<T>();
		result.setCode(0);
		result.setData(data);
		result.setMsg("成功");
		return result;
	}

	public static <T> Result<T> success() {
		Result<T> result = new Result<T>();
		result.setCode(0);
		result.setMsg("成功");
		return result;
	}

	public static <T> Result<T> failure(String msg) {
		Result<T> result = new Result<T>();
		result.setCode(1);
		result.setMsg(msg);
		return result;
	}

	public static <T> Result<T> warn(String msg, int code) {
		Result<T> result = new Result<T>();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

	public static <T> Result<T> warn(String msg, int code, T data) {
		Result<T> result = new Result<T>();
		result.setCode(code);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}

}

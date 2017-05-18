package com.yyxk.core.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.yyxk.common.Result;
import com.yyxk.config.client.ExceptionProperties;
import com.yyxk.exception.SystemException;

/**
 * 请求异常统一处理.
 * <p>
 * 通过{@linkplain Result}包装Exception<br>
 * {@linkplain Result}中的code为http状态码，发生{@linkplain SystemException}时code为999<br>
 * {@linkplain Result}中的data为msgId<br>
 * {@linkplain Result}中的msg为msg<br>
 * 
 * @author Houkm
 *
 *         2017年5月5日
 */
@ControllerAdvice
@ResponseBody
public class ErrorController {

	@Autowired
	ExceptionProperties exceptions;

	/**
	 * 500-Exception
	 * 
	 */
	@ExceptionHandler(Exception.class)
	public Result<Object> exception(Exception ex, HttpServletResponse resp) {
		ex.printStackTrace();
		return result(500, ex.toString(), null, resp);
	}

	/**
	 * 999-SystemException
	 * 
	 */
	@ExceptionHandler(SystemException.class)
	public Result<Object> systemException(SystemException ex, HttpServletResponse resp) {
		ex.printStackTrace();
		return result(999, ex.getMessage(), ex.getMsgId(), resp);
	}

	/**
	 * 400-BindException
	 * 
	 */
	@ExceptionHandler(BindException.class)
	public Result<Object> bindException(BindException ex, HttpServletResponse resp) {
		ex.printStackTrace();
		return $400("BindException", resp);
	}

	/**
	 * 400-MissingServletRequestPartException
	 * 
	 */
	@ExceptionHandler(MissingServletRequestPartException.class)
	public Result<Object> missingServletRequestPartException(MissingServletRequestPartException ex,
			HttpServletResponse resp) {
		ex.printStackTrace();
		return $400("MissingServletRequestPartException", resp);

	}

	/**
	 * 400-MissingServletRequestParameterException
	 * 
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Result<Object> missingServletRequestParameterException(MissingServletRequestParameterException ex,
			HttpServletResponse resp) {
		ex.printStackTrace();
		return $400("MissingServletRequestParameterException", resp);
	}

	/**
	 * 400-MethodArgumentNotValidException
	 * 
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletResponse resp) {
		ex.printStackTrace();
		return $400("MethodArgumentNotValidException", resp);
	}

	/**
	 * 400-TypeMismatchException
	 * 
	 */
	@ExceptionHandler(TypeMismatchException.class)
	public Result<Object> typeMismatchException(TypeMismatchException ex, HttpServletResponse resp) {
		ex.printStackTrace();
		return $400("TypeMismatchException", resp);
	}

	/**
	 * 404-NoHandlerFoundException
	 * 
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public Result<Object> noHandlerFoundException(NoHandlerFoundException ex, HttpServletResponse resp) {
		ex.printStackTrace();
		return result(404, "404", "NoHandlerFoundException", resp);
	}

	/**
	 * 404-IOException
	 * 
	 */
	@ExceptionHandler(IOException.class)
	public Result<Object> iOException(IOException ex, HttpServletResponse resp) {
		ex.printStackTrace();
		return result(404, "404", "IOException", resp);
	}

	/**
	 * 500-ConversionNotSupportedException
	 * 
	 */
	@ExceptionHandler(ConversionNotSupportedException.class)
	public Result<Object> conversionNotSupportedException(ConversionNotSupportedException ex,
			HttpServletResponse resp) {
		return $500("ConversionNotSupportedException", resp);
	}

	/**
	 * 500-HttpMessageNotWritableException
	 * 
	 */
	@ExceptionHandler(HttpMessageNotWritableException.class)
	public Result<Object> httpMessageNotWritableException(HttpMessageNotWritableException ex,
			HttpServletResponse resp) {
		ex.printStackTrace();
		return $500("HttpMessageNotWritableException", resp);
	}

	/**
	 * 500-MissingPathVariableException
	 * 
	 */
	@ExceptionHandler(MissingPathVariableException.class)
	public Result<Object> missingPathVariableException(MissingPathVariableException ex, HttpServletResponse resp) {
		ex.printStackTrace();
		return $500("MissingPathVariableException", resp);
	}

	private Result<Object> $400(String msg, HttpServletResponse resp) {
		return result(400, msg, exceptions.get(msg), resp);
	}

	private Result<Object> $500(String msg, HttpServletResponse resp) {
		return result(500, msg, exceptions.get(msg), resp);
	}

	// private Result<Object> result(int code, Object msg, Object data) {
	// Result<Object> result = new Result<>();
	// result.setCode(code);
	// result.setMsg(msg.toString());
	// if (data != null) {
	// result.setData(data);
	// }
	// return result;
	// }

	private Result<Object> result(int code, Object msg, Object data, HttpServletResponse resp) {
		Result<Object> result = new Result<>();
		result.setCode(code);
		result.setMsg(msg.toString());
		if (data != null) {
			result.setData(data);
		}
		resp.setHeader("Access-Control-Allow-Origin", "*");
		return result;
	}
}
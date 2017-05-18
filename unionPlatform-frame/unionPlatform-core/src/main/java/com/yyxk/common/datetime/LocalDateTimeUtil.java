package com.yyxk.common.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * {@linkplain LocalDateTime 时间}工具类
 * 
 * @author Houkm
 *
 *         2017年5月12日
 */
public class LocalDateTimeUtil {

	/**
	 * 将yyyy-MM-dd HH:mm:ss转换为 {@linkplain LocalDateTime 时间}
	 * 
	 * @param str
	 * @return
	 * @author Houkm 2017年5月12日
	 */
	public static LocalDateTime transfer(String str) {
		LocalDateTime result = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return result;
	}

	/**
	 * 将指定{@linkplain DateTimeFormatter 格式}的str转换为{@linkplain LocalDateTime 时间}
	 * 
	 * @param str
	 * @param formatter
	 * @return
	 * @author Houkm 2017年5月12日
	 */
	public static LocalDateTime transfer(String str, DateTimeFormatter formatter) {
		LocalDateTime result = LocalDateTime.parse(str, formatter);
		return result;
	}

	/**
	 * 将{@linkplain LocalDateTime 时间}转换为 {@linkplain DateTimeFormatter
	 * 2011-12-03T10:15:30}格式字符串
	 * 
	 * @param localDateTime
	 * @return
	 * @author Houkm 2017年5月12日
	 */
	public static String transfer(LocalDateTime localDateTime) {
		String result = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		return result;
	}

	/**
	 * 将{@linkplain LocalDateTime 时间}转换为给定 {@linkplain DateTimeFormatter 格式}的字符串
	 * 
	 * @param localDateTime
	 * @param pattern
	 * @return
	 * @author Houkm 2017年5月12日
	 */
	public static String transfer(LocalDateTime localDateTime, DateTimeFormatter pattern) {
		String result = localDateTime.format(pattern);
		return result;
	}

	/**
	 * 将{@linkplain LocalDateTime 时间}转换为{@linkplain Date 时间}
	 * 
	 * @param localDateTime
	 * @return
	 * @author Houkm 2017年5月12日
	 */
	public static Date toDate(LocalDateTime localDateTime) {
		Date result = Date.from(localDateTime.toInstant(ZoneOffset.UTC));
		return result;
	}

	/**
	 * 将{@linkplain Date 时间}转换为{@linkplain LocalDateTime 时间}
	 * 
	 * @param date
	 * @return
	 * @author Houkm 2017年5月12日
	 */
	public static LocalDateTime toLocalDateTime(Date date) {
		LocalDateTime result = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		return result;
	}

}

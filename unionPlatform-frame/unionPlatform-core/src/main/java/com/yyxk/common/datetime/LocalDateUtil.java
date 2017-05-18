package com.yyxk.common.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * {@linkplain LocalDate 日期}工具类
 * 
 * @author Houkm
 *
 *         2017年5月12日
 */
public class LocalDateUtil {

	/**
	 * 将给定的{@linkplain LocalDate 日期}转换为{@linkplain DateTimeFormatter 格式}的字符串
	 * 
	 * @param date
	 * @return
	 * @author Houkm 2017年5月12日
	 */
	public static String transfer(LocalDate date) {
		return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

	/**
	 * 将给定的{@linkplain LocalDate 日期}转换为给定{@linkplain DateTimeFormatter 格式}的字符串
	 * 
	 * @param date
	 * @return
	 * @author Houkm 2017年5月12日
	 */
	public static String transfer(LocalDate date, DateTimeFormatter formatter) {
		return date.format(formatter);
	}

	/**
	 * 将给定的2007-12-03转换为{@linkplain LocalDate 日期}
	 * 
	 * @param text
	 * @return
	 * @author Houkm 2017年5月12日
	 */
	public static LocalDate transfer(String text) {
		return LocalDate.parse(text);
	}

	/**
	 * 将给定{@linkplain DateTimeFormatter 格式}的字符串转换为{@linkplain LocalDate 日期}
	 * 
	 * @param text
	 * @param formatter
	 * @return
	 * @author Houkm 2017年5月12日
	 */
	public static LocalDate transfer(String text, DateTimeFormatter formatter) {
		return LocalDate.parse(text, formatter);
	}

}

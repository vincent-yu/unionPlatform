package com.yyxk.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串处理工具类
 * 
 * @author Houkm
 *
 *         2017年5月5日
 */
public class StringUtil extends StringUtils {
	/**
	 * 将传入字符中的指定位置字符替换为新的字符 newChar
	 * 
	 * @param str
	 *            原字符串
	 * @param start
	 *            替换开始位置
	 * @param end
	 *            替换截止位置
	 * @param newChar
	 *            替换的目的字符
	 * @return String 替换之后的字符串
	 * @return : String
	 */
	public static String replace(String str, int start, int end, char newChar) {
		if (str == null) {
			return null;
		}
		if (str == "") {
			return "";
		}
		int len = str.length();
		StringBuffer sb = new StringBuffer();
		if (end > len || start < 0 || start > end) {
			return "";
		}
		for (int i = 0; i < len; i++) {
			if (i >= start && i < end) {
				sb.append(newChar);
			} else {
				sb.append(str.charAt(i));
			}
		}
		return sb.toString();
	}

	/**
	 * 把手机号码的第3位到第8位替换成*
	 * 
	 * @param mobile
	 * @return
	 * @return : String
	 */
	public static String replaceMobile(String mobile) {
		return replace(mobile, 3, 8, '*');
	}

	/**
	 * 将身份证号码的第4位到第16位替换成* ，身份证号必须18位
	 * 
	 * @param idCard
	 * @return : String
	 */
	public static String replaceIdCard(String idCard) {
		return replace(idCard, 4, 16, '*');
	}

	/**
	 * 将邮箱@之前的字符串首尾之间的字符替换为*，如536455458@qq.com 替换后为 5*****8@qq.com
	 * 
	 * @param email
	 * @return : String
	 */
	public static String replaceEamil(String email) {
		if (!email.contains("@")) {
			return email;
		}
		String str = email.substring(0, email.indexOf("@"));
		StringBuffer sb = new StringBuffer();
		sb.append(str.charAt(0));
		sb.append("*****");
		sb.append(str.charAt(str.length() - 1));
		sb.append(email.substring(email.indexOf("@"), email.length()));
		return sb.toString();
	}

	/**
	 * 字符串为空
	 * 
	 * @param src
	 * @return
	 */
	public static boolean isEmpty(Object src) {
		if (src == null || "".equals(src.toString().trim()) || "null".equals(src)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 字符串不为空
	 * 
	 * @param src
	 * @return
	 */
	public static boolean notEmpty(Object src) {
		if (src == null || "".equals(src.toString().trim()) || "null".equals(src)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 数组转换成字符串 “,”号隔开
	 * 
	 * @param str
	 * @return
	 */
	public static String convString(String[] str) {
		StringBuilder sb = new StringBuilder();
		if (str != null && str.length > 0) {
			for (int i = 0; i < str.length; i++) {
				sb.append(str[i]).append(",");
			}
		}
		return sb.toString();
	}

	/**
	 * 逗号隔开的数字字符串，转成long类型集合
	 * 
	 * @param str
	 * @return
	 */
	public static List<Long> String2LongList(String str) {
		List<Long> list = new ArrayList<Long>();
		if (StringUtils.isBlank(str)) {
			return list;
		} else {
			// 去掉最后一个空格
			if (str.lastIndexOf(",") == str.length()) {
				str = str.substring(0, str.length() - 1);
			}
			String[] arr = str.split(",");
			for (String s : arr) {
				list.add(Long.valueOf(s));
			}
			return list;
		}
	}

	/**
	 * UUID
	 * 
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 加密医生推荐医生id
	 * 
	 * @param doctorId
	 * @return
	 */
	public static String encryDoctorRecomeDoctorId(String doctorId) {
		if (!StringUtil.isBlank(doctorId)) {
			if (doctorId.length() == 1) {
				doctorId = "00" + doctorId;
			} else if (doctorId.length() == 2) {
				doctorId = "0" + doctorId;
			}
			int f = Integer.parseInt(doctorId.substring(0, 1));
			int c = Integer.parseInt(doctorId.substring(1, 2));
			int t = Integer.parseInt(doctorId.substring(2, 3));
			StringBuffer sb = new StringBuffer();
			Random random = new Random();
			int ff = random.nextInt(10 - f);
			int fc = random.nextInt(10 - c);
			int ft = random.nextInt(10 - t);
			// int ff = f / 2;
			// int fc = c / 2;
			// int ft = t / 2;
			// if (c + fc > 9) {
			// fc = 9 - c;
			// }
			// if (f + ff > 9) {
			// ff = 9 - f;
			// }
			// if (t + ft > 9) {
			// ft = 9 - t;
			// }
			sb.append(c + fc).append(ft).append(f + ff).append(ff).append(t + ft).append(fc);
			if (sb.toString().length() != 6) {
				return null;
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * 解密医生推荐医生id
	 * 
	 * @param doctorId
	 * @return
	 */
	public static String decryDoctorRecomeDoctorId(String doctorId) {
		if (!StringUtil.isBlank(doctorId) && doctorId.length() == 6) {
			try {
				int a1 = Integer.parseInt(doctorId.substring(0, 1));
				int a2 = Integer.parseInt(doctorId.substring(1, 2));
				int a3 = Integer.parseInt(doctorId.substring(2, 3));
				int a4 = Integer.parseInt(doctorId.substring(3, 4));
				int a5 = Integer.parseInt(doctorId.substring(4, 5));
				int a6 = Integer.parseInt(doctorId.substring(5, 6));
				StringBuffer sb = new StringBuffer();

				int s1 = a3 - a4;
				int s2 = a1 - a6;
				int s3 = a5 - a2;
				if (s1 < 0 || s2 < 0 || s3 < 0) {
					return null;
				}
				sb.append(s1).append(s2).append(s3);
				int result = Integer.parseInt(sb.toString());
				return String.valueOf(result);
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * 生成size大小的验证码
	 * 
	 * @param size
	 * @return
	 * @author Houkm
	 */
	public static String randomCode(int size) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			sb.append(random.nextInt(10));
		}

		return sb.toString();
	}

}

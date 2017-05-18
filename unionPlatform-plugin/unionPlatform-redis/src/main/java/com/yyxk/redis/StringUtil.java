package com.yyxk.redis;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Redis操作类
 * 
 * @author Houkm
 *
 *         2017年5月10日
 */
// @Component
public class StringUtil {

	@Autowired
	private StringRedisTemplate template;

	/**
	 * 存String
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 * @author Houkm 2017年5月5日
	 */
	public void set(String key, String value, long expire) {
		string().set(key, value, expire, TimeUnit.SECONDS);
	}

	/**
	 * 存String
	 * 
	 * @param key
	 * @param value
	 * @author Houkm 2017年5月5日
	 */
	public void set(String key, String value) {
		string().set(key, value);
	}

	/**
	 * 获取字符串
	 * 
	 * @param key
	 * @return
	 * @author Houkm 2017年5月2日
	 */
	public String get(String key) {
		String result = string().get(key);
		return result;
	}

	/**
	 * 返回key的值，并将key的值更新为value
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public String getAndSet(String key, String value) {
		return string().getAndSet(key, value);
	}

	/**
	 * 如果 key 已经存在，并且值为字符串，那么这个命令会把 value 追加到原来值（value）的结尾。 如果 key
	 * 不存在，那么它将首先创建一个空字符串的key，再执行追加操作
	 * 
	 * @param key
	 * @param value
	 * @return 返回append后字符串值（value）的长度
	 * @author Houkm 2017年5月10日
	 */
	public int appendString(String key, String value) {
		return string().append(key, value);
	}

	/**
	 * 将key对应的数字加value。如果key不存在，操作之前，key就会被置为0。如果key的value类型错误或者是个不能表示成数字的字符串，就返回错误。这个操作最多支持64位有符号的正型数字。
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public long increment(String key, long value) {
		return string().increment(key, value);
	}

	/**
	 * 将key对应的数字加value。如果key不存在，操作之前，key就会被置为0。如果key的value类型错误或者是个不能表示成数字的字符串，就返回错误。这个操作最多支持64位有符号的正型数字。
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public double increment(String key, double value) {
		return string().increment(key, value);
	}

	/**
	 * 返回所有指定的key的value。对于每个不对应string或者不存在的key，都返回特殊值nil
	 * 
	 * @param keys
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public List<String> multiGet(List<String> keys) {
		return string().multiGet(keys);
	}

	/**
	 * 对应给定的keys到他们相应的values上。MSET会用新的value替换已经存在的value
	 * 
	 * @param map
	 * @author Houkm 2017年5月10日
	 */
	public void multiSet(Map<String, String> map) {
		string().multiSet(map);
	}

	/**
	 * 获取操作string的类
	 * 
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	private ValueOperations<String, String> string() {
		return template.opsForValue();
	}

}

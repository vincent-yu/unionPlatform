package com.yyxk.redis;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis List操作类
 * 
 * @author Houkm
 *
 *         2017年5月10日
 */
// @Component
public class ListUtil {

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ListUtil.class);

	@Autowired
	private StringRedisTemplate template;

	/**
	 * 移除并且返回 key 对应的 list 的第一个元素。
	 * 
	 * @param key
	 * @author Houkm 2017年5月10日
	 */
	public void leftPop(String key) {
		list().leftPop(key);
	}

	/**
	 * 移除并且返回 key 对应的 list 的第一个元素。阻塞式
	 * 
	 * @param key
	 * @param seconds
	 *            指定阻塞的最大秒数
	 * @author Houkm 2017年5月10日
	 */
	public void leftPop(String key, long seconds) {
		list().leftPop(key, seconds, TimeUnit.SECONDS);
	}

	/**
	 * 将指定的值插入到存于 key 的列表的头部。如果 key 不存在，那么在进行 push 操作前会创建一个空列表。 如果 key 对应的值不是一个
	 * list 的话，那么会返回一个错误
	 * 
	 * @param key
	 * @param value
	 * @return 在 push 操作后的 list 长度
	 * @author Houkm 2017年5月10日
	 */
	public long leftPush(String key, String value) {
		return list().leftPush(key, value);
	}

	/**
	 * 把 value 插入存于 key 的列表中在基准值 pivot 的前面
	 * 
	 * @param key
	 * @param pivot
	 *            list中某条数据的值
	 * @param value
	 * @return 在 push 操作后的 list 长度
	 * @author Houkm 2017年5月10日
	 */
	public long leftPush(String key, String pivot, String value) {
		return list().leftPush(key, pivot, value);
	}

	/**
	 * 将所有指定的值插入到存于 key 的列表的头部
	 * 
	 * @param key
	 * @param values
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public long leftPushAll(String key, Collection<String> values) {
		return list().leftPushAll(key, values);
	}

	/**
	 * 移除并返回存于 key 的 list 的最后一个元素
	 * 
	 * @param key
	 * @author Houkm 2017年5月10日
	 */
	public String rightPop(String key) {
		return list().rightPop(key);
	}

	/**
	 * 移除并返回存于 key 的 list 的最后一个元素。阻塞式
	 * 
	 * @param key
	 * @param seconds
	 *            指定阻塞的最大秒数
	 * @author Houkm 2017年5月10日
	 */
	public String rightPop(String key, long seconds) {
		return list().rightPop(key, seconds, TimeUnit.SECONDS);
	}

	/**
	 * 将指定的值插入到存于 key 的列表的尾部。如果 key 不存在，那么在进行 push 操作前会创建一个空列表。 如果 key 对应的值不是一个
	 * list 的话，那么会返回一个错误
	 * 
	 * @param key
	 * @param value
	 * @return 在 push 操作后的 list 长度
	 * @author Houkm 2017年5月10日
	 */
	public long rightPush(String key, String value) {
		return list().rightPush(key, value);
	}

	/**
	 * 把 value 插入存于 key 的列表中在基准值 pivot 的后面
	 * 
	 * @param key
	 * @param pivot
	 *            list中某条数据的值
	 * @param value
	 * @return 在 push 操作后的 list 长度
	 * @author Houkm 2017年5月10日
	 */
	public long rightPush(String key, String pivot, String value) {
		return list().rightPush(key, pivot, value);
	}

	/**
	 * 将所有指定的值插入到存于 key 的列表的尾部
	 * 
	 * @param key
	 * @param values
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public long rightPushAll(String key, Collection<String> values) {
		return list().rightPushAll(key, values);
	}

	/**
	 * 返回列表中第index个元素
	 * 
	 * @param key
	 * @param index
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public String index(String key, int index) {
		return list().index(key, index);
	}

	/**
	 * 返回存储在 key 的列表里指定范围内的元素
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public List<String> range(String key, long start, long end) {
		return list().range(key, start, end);
	}

	/**
	 * 返回存储在 key 里的list的长度
	 * 
	 * @param key
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public long size(String key) {
		return list().size(key);
	}

	/**
	 * 设置 index 位置的list元素的值为 value
	 * 
	 * @param key
	 * @param index
	 * @param value
	 * @author Houkm 2017年5月10日
	 */
	public void listSet(String key, long index, String value) {
		list().set(key, index, value);
	}

	/**
	 * 从存于 key 的列表里移除前 count 次出现的值为 value 的元素
	 * 
	 * @param key
	 * @param count
	 * @param value
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public long remove(String key, int count, Object value) {
		return list().remove(key, count, value);
	}

	/**
	 * 获取操作list的类
	 * 
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	private ListOperations<String, String> list() {
		return template.opsForList();
	}
}

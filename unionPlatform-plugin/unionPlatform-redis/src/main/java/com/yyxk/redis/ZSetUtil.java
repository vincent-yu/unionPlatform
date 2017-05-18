package com.yyxk.redis;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

/**
 * Redis Sorted set操作类
 * 
 * @author Houkm
 *
 *         2017年5月10日
 */
@Component
public class ZSetUtil {

	@Autowired
	private StringRedisTemplate template;

	/**
	 * 将所有指定成员添加到键为key有序集合（sorted set）里面,有序集合按照分数以递增的方式进行排序
	 * 
	 * @param key
	 * @param value
	 * @param score
	 * @author Houkm 2017年5月10日
	 */
	public void add(String key, String value, double score) {
		zset().add(key, value, score);
	}

	/**
	 * 将key与otherKeys的交集存入新的destKey中
	 * 
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public long intersectAndStore(String key, Collection<String> otherKeys, String destKey) {
		return zset().intersectAndStore(key, otherKeys, destKey);
	}

	/**
	 * 将key与otherKeys的交集存入新的destKey中
	 * 
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public long intersectAndStore(String key, String otherKeys, String destKey) {
		return zset().intersectAndStore(key, otherKeys, destKey);
	}

	/**
	 * 将key与otherKeys的并集存入新的destKey中
	 * 
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @author Houkm 2017年5月10日
	 */
	public long unionAndStore(String key, Collection<String> otherKeys, String destKey) {
		return zset().unionAndStore(key, otherKeys, destKey);
	}

	/**
	 * 将key与otherKeys的并集存入新的destKey中
	 * 
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @author Houkm 2017年5月10日
	 */
	public long unionAndStore(String key, String otherKeys, String destKey) {
		return zset().unionAndStore(key, otherKeys, destKey);
	}

	/**
	 * 返回有序集key中，score值在min和max之间(默认包括score值等于min或max)的成员
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public long count(String key, double min, double max) {
		return zset().count(key, min, max);
	}

	/**
	 * 为有序集key的成员member的得分增加delta
	 * 
	 * @param key
	 * @param member
	 * @param delta
	 * @return member的最新得分
	 * @author Houkm 2017年5月10日
	 */
	public double incrementScore(String key, String member, double delta) {
		return zset().incrementScore(key, member, delta);
	}

	/**
	 * 返回存储在 key 的列表里指定范围内的元素
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @author Houkm 2017年5月10日
	 */
	public Set<String> range(String key, long start, long end) {
		return zset().range(key, start, end);
	}

	@Deprecated
	public void rangeByLex() {
	}

	/**
	 * 返回key的有序集合中的分数在min和max之间的所有元素（包括分数等于max或者min的元素）
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public Set<String> rangeByScore(String key, double min, double max) {
		return zset().rangeByScore(key, min, max);
	}

	/**
	 * 返回有序集key中成员member的排名
	 * 
	 * @param key
	 * @param member
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public long rank(String key, String member) {
		return zset().rank(key, member);
	}

	/**
	 * 移除key中值为values的数据
	 * 
	 * @param key
	 * @param values
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public long remove(String key, Object... values) {
		return zset().remove(key, values);
	}

	/**
	 * 移除存储在 key 的列表里指定范围内的元素
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public long remove(String key, long start, long end) {
		return zset().removeRange(key, start, end);
	}

	/**
	 * 移除存储在 key 的列表里指定得分范围内的元素
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public long remove(String key, double min, double max) {
		return zset().removeRangeByScore(key, min, max);
	}

	/**
	 * 倒序返回key的有序集合中的指定范围内的元素
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public Set<String> reverseRange(String key, long start, long end) {
		return zset().reverseRange(key, start, end);
	}

	/**
	 * 倒序返回key的有序集合中的指定得分范围内的元素
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @author Houkm 2017年5月10日
	 */
	public Set<String> reverseRangeByScore(String key, double min, double max) {
		return zset().reverseRangeByScore(key, min, max);
	}

	/**
	 * 获取key的有序集合中member的得分
	 * 
	 * @param key
	 * @param member
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public double score(String key, String member) {
		return zset().score(key, member);
	}

	/**
	 * 获取key的有序集合大小
	 * 
	 * @param key
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public long size(String key) {
		return zset().size(key);
	}

	/**
	 * 获取操作zset的类
	 * 
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	private ZSetOperations<String, String> zset() {
		return template.opsForZSet();
	}

}

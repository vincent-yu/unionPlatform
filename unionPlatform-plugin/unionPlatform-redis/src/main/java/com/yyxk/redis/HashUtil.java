package com.yyxk.redis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis Hahs操作类
 * 
 * @author Houkm
 *
 *         2017年5月10日
 */
// @Component
public class HashUtil {

	@Autowired
	private StringRedisTemplate template;

	/**
	 * 设置 key 指定的哈希集中指定字段的值。 <br>
	 * 如果 key 指定的哈希集不存在，会创建一个新的哈希集并与 key 关联。 <br>
	 * 如果字段在哈希集中存在，它将被重写
	 * 
	 * @param key
	 * @param hashKey
	 * @param value
	 * @author Houkm 2017年5月10日
	 */
	public void add(String key, String hashKey, String value) {
		hash().put(key, hashKey, value);
	}

	/**
	 * 设置 key 指定的哈希集。 <br>
	 * 
	 * @param key
	 * @param m
	 * @author Houkm 2017年5月10日
	 */
	public void add(String key, Map<String, String> m) {
		hash().putAll(key, m);
	}

	/**
	 * 删除key的哈希集中hashKeys的键值对
	 * 
	 * @param key
	 * @param hashKeys
	 * @author Houkm 2017年5月10日
	 */
	public void delete(String key, Object... hashKeys) {
		hash().delete(key, hashKeys);
	}

	/**
	 * 获取key的哈希集
	 * 
	 * @param key
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public Map<String, String> get(String key) {
		return hash().entries(key);
	}

	/**
	 * 获取key的哈希集中hashKey的值
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public String get(String key, String hashKey) {
		return hash().get(key, hashKey);
	}

	/**
	 * 查看key的哈希集中是否含有hashKey
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public boolean hasKey(String key, String hashKey) {
		return hash().hasKey(key, hashKey);
	}

	/**
	 * 获取key的哈希集中的所有hashkeys
	 * 
	 * @param key
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public Set<String> keys(String key) {
		return hash().keys(key);
	}

	/**
	 * 获取key的哈希集中的所有hashkeys
	 * 
	 * @param key
	 * @param hashKeys
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public List<String> get(String key, Collection<String> hashKeys) {
		return hash().multiGet(key, hashKeys);
	}

	/**
	 * 获取key的哈希集的大小
	 * 
	 * @param key
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public long size(String key) {
		return hash().size(key);
	}

	/**
	 * 获取key的哈希集的所有值
	 * 
	 * @param key
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public List<String> values(String key) {
		return hash().values(key);
	}

	/**
	 * 获取操作hash的类
	 * 
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	private HashOperations<String, String, String> hash() {
		return template.opsForHash();
	}
}

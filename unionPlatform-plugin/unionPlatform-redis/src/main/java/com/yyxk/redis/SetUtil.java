package com.yyxk.redis;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

//@Component
public class SetUtil {

	@Autowired
	private RedisTemplate<String, ? extends Serializable> template;

	/**
	 * 返回key对应的集合的所有成员
	 * 
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Set<? extends Serializable> members(String key) {
		return set().members(key);
	}

	/**
	 * 删除key对应set集合中值为value的元素
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public long remove(String key, Serializable value) {
		return set().remove(key, value);
	}

	/**
	 * 获取操作set的类
	 * 
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	private SetOperations<String, ? extends Serializable> set() {
		return template.opsForSet();
	}
}

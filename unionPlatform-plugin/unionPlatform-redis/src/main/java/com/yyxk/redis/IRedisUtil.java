package com.yyxk.redis;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class IRedisUtil {

	private Logger logger = LoggerFactory.getLogger(IRedisUtil.class);

	// @Autowired
	// private StringUtil string;
	//
	// @Autowired
	// private HashUtil hash;
	//
	// @Autowired
	// private ListUtil list;
	//
	// @Autowired
	// private SetUtil set;

	@Resource(name = "redisTemplate")
	private RedisTemplate<Object, Object> template;

	/**
	 * 返回存储在 key 的列表里指定范围内的元素
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public List<Object> lrange(String key, int start, int end) {
		List<Object> result = l(key).range(start, end);
		logger.info("获取List<String>, key[{}], start[{}], end[{}],value.size[{}]", key, start, end, result.size());
		return result;
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
	public Long lrem(String key, int count, Serializable value) {
		logger.info("从存于 {} 的列表里移除前 {} 次出现的值为 {} 的元素", key, count, value);
		return l(key).remove(count, value);
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
	public Long rpush(String key, String value) {
		logger.info("将{}插入到存于 {} 的列表的尾部", value, key);
		return l(key).rightPush(value);
	}

	/**
	 * 获取key的哈希集中的所有hashkeys
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月10日
	 */
	public Set<String> keys(Integer idKey, String key) {
		Set<String> keys = h(key).keys();
		logger.info("获取所有{}中的所有key,个数为{}", key, keys.size());
		return keys;
	}

	/**
	 * 获取字符串
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月2日
	 */
	public String get(String key) {
		Object obj = str(key).get();
		String result = obj == null ? null : obj.toString();
		logger.info("获取字符串{} : {}", key, result);
		return result;
	}

	/**
	 * 存String
	 *
	 * @param key
	 * @param value
	 * @author Houkm 2017年5月5日
	 */
	public void set(String key, String value) {
		logger.info("存入字符串 {} ： {}", key, value);
		str(key).set(value);
	}

	/**
	 * 返回key对应的集合的所有成员
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Set<Object> smembers(String key) {
		Set<Object> result = s(key).members();
		logger.info("获取key中的所有元素: {}, 元素个数为{}", key, result.size());
		return result;
	}

	/**
	 * 删除key对应set集合中值为value的元素
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long srem(String key, Serializable value) {
		logger.info("删除{}中的{}", key, value);
		return s(key).remove(value);
	}

	/**
	 * set集合中添加元素
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long sadd(String key, Serializable value) {
		logger.info("{}集合中添加元素{}", key, value);
		return s(key).add(value);
	}

	/**
	 * 删除指定的key
	 *
	 * @param key
	 * @author Houkm 2017年5月10日
	 */
	public Long del(String key) {
		logger.info("删除{}", key);
		if (!template.hasKey(key)) {
			logger.info("{}不存在", key);
			return 0l;
		}
		template.delete(key);
		return 1l;
	}

	/************************* 存对象 ***********************************/
	/**
	 * 存一个对象 如果key已经存在 覆盖原值 成功返回 OK 失败返回 null
	 */
	public String setObject(String key, Serializable value) {
		logger.info("存入对象 {} : {}", key, value);
		template.boundValueOps(key).set(value);
		return null;
	}

	/**
	 * 存一个对象 如果key已经存在 覆盖原值 成功返回 OK 失败返回 null
	 */
	public String setObject(String key, Object value) {
		logger.info("存入对象 {} : {}", key, value);
		template.boundValueOps(key).set(value);
		return null;
	}

	/**
	 * 存一个List 对象
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public String setObject(String key, List<? extends Serializable> value) {
		template.boundListOps(key).leftPushAll(value);
		logger.info("存入对象 {} : {}", key, value);
		return null;
		// return setObjectImpl(key, value);
	}

	/**
	 * 存入对象并设置超时时间
	 *
	 * @param key
	 * @param seconds
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public String setExObject(String key, Integer seconds, Serializable value) {
		logger.info("存入对象 {} : {}, {}秒后超时", key, value, seconds);
		template.boundValueOps(key).set(value, seconds, TimeUnit.MICROSECONDS);
		return null;
	}

	/**
	 * 存一个Map对象
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public String setObject(String key, Map<?, ? extends Serializable> value) {
		logger.info("存入Map {} : Map大小{}", key, value.size());
		template.boundHashOps(key).putAll(value);
		return null;
	}

	/**
	 * 存一个Set对象
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public String setObject(String key, Set<? extends Serializable> value) {
		logger.info("存入Set {} : Set大小{}", key, value.size());
		s(key).add(value);
		return null;
	}

	/**
	 * 获取对象
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Object getObject(String key) {
		Object result = template.opsForValue().get(key);
		logger.info("获取对象 {} : {}", key, result);
		return result;
	}

	/**
	 * 获取key中索引为index的值
	 *
	 * @param key
	 * @param index
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Object lindex(String key, int index) {
		Object result = template.opsForList().index(key, index);
		logger.info("获取{}中索引为{}的值: {}", key, index, result);
		return template.opsForList().index(key, index);
	}

	/**
	 * 返回list的长度
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long llen(String key) {
		Long size = template.boundListOps(key).size();
		logger.info("{}的长度为{}", key, size);
		return size;
	}

	/**
	 * 获取并删除list中第一个元素
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Object lpop(String key) {
		Object result = template.boundListOps(key).leftPop();
		logger.info("{} leftPop : {}", key, result);
		return result;
	}

	/**
	 * 获取并删除list中最后一个元素
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Object lrpop(String key) {
		Object result = template.boundListOps(key).rightPop();
		logger.info("{} rightPop : {}", key, result);
		return result;
	}

	/**
	 * 将元素添加到list的头部
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long lpush(String key, Serializable value) {
		Long result = template.boundListOps(key).leftPush(value);
		logger.info("{} leftPush ： {}", key, value);
		return result;
	}

	/**
	 * 将元素添加到list的尾部
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long rpush(String key, Serializable value) {
		Long result = template.boundListOps(key).rightPush(value);
		logger.info("{} rightPush ： {}", key, value);
		return result;
	}

	/**
	 * 将元素添加到list的头部
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long lpushx(String key, Serializable value) {
		return lpushx(key, value);
	}

	/**
	 * 将元素添加到list的头部
	 *
	 * @param key
	 * @param values
	 * @author Houkm 2017年5月11日
	 */
	public void lpushx(String key, List<? extends Serializable> values) {
		logger.info("{} leftPushAll ： size {}", key, values.size());
		template.boundListOps(key).leftPushAll(values);
	}

	/**
	 * 将元素添加到list的尾部
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long rpushx(String key, Serializable value) {
		return rpush(key, value);
	}

	/**
	 * 将元素添加到list的尾部
	 *
	 * @param key
	 * @param values
	 * @author Houkm 2017年5月11日
	 */
	public void rpushx(String key, List<? extends Serializable> values) {
		logger.info("{} rightPushAll ： size {}", key, values.size());
		template.boundListOps(key).rightPushAll(values);
	}

	/**
	 * 修改list中索引index的值
	 *
	 * @param key
	 * @param index
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public String lset(String key, int index, Serializable value) {
		template.boundListOps(key).set(index, value);
		logger.info("{}中{}的值修改为{}", key, index, value);
		return null;
	}

	/**
	 * 删除不在start到end范围内的元素
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public String ltrim(String key, int start, int end) {
		template.boundListOps(key).trim(start, end);
		logger.info("{} trim start[{}] end[{}]", key, start, end);
		return null;
	}

	/**
	 * 返回key的长度
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long scard(String key) {
		Long card = s(key).size();
		logger.info("{} scard {}", key, card);
		return card;
	}

	/**
	 * value是否是key中的元素
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public boolean sismember(String key, Serializable value) {
		boolean ismember = s(key).isMember(value);
		logger.info("{} {} {}中的元素", value, ismember ? "是" : "不是", key);
		return ismember;
	}

	/**
	 * 获取并删除集合中的一个元素
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Object spop(String key) {
		Object result = s(key).pop();
		logger.info("spop: {}, {}", key, result);
		return result;
	}

	/**
	 * 随机获取集合中的一个元素
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Object srandmember(String key) {
		Object result = s(key).randomMember();
		logger.info("srandmember: {}, {}", key, result);
		return result;
	}

	/**
	 * 有序集中添加元素
	 *
	 * @param key
	 * @param score
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long zadd(String key, double score, Serializable value) {
		z(key).add(value, score);
		logger.info("{}中添加元素{}，分数为{}", key, value, score);
		return (long) 0;
	}

	/**
	 * 返回key对应的有序集的基数
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long zcard(String key) {

		Long card = z(key).zCard();
		logger.info("{} card {}", key, card);
		return card;
	}

	/**
	 * 返回key对应的有序集 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long zcount(String key, double min, double max) {
		Long count = z(key).count(min, max);
		logger.info("{} min{} max{} count {}", key, min, max, count);
		return count;
	}

	/**
	 * 为key对应的有序集中member的score值加上增量incrementscore
	 *
	 * @param key
	 * @param incrementscore
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public double zincrby(String key, double incrementscore, Serializable value) {
		Double score = z(key).incrementScore(value, incrementscore);
		logger.info("{} 中{}的score增加{}后变为{}", key, value, incrementscore, score);
		return score;
	}

	/**
	 * 返回key对应的有序set集合中start到end的元素
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Set<Object> zrange(String key, int start, int end) {
		Set<Object> result = z(key).range(start, end);
		logger.info("{} range start[{}] end [{}] 的size为{}", key, start, end, result.size());
		return result;
	}

	/**
	 * 返回key对应的有序set集合中分数在min到max之间的元素
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Set<Object> zrangeByScore(String key, double min, double max) {
		Set<Object> result = z(key).rangeByScore(min, max);
		logger.info("{} range min[{}] max [{}] 的size为{}", key, min, max, result.size());
		return result;
	}

	/**
	 * 移除key对应的有序集中的member(s)元素
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long zrem(String key, Serializable value) {
		Long count = z(key).remove(value);
		logger.info("{} zrem {} count{}", key, value, count);
		return count;
	}

	/**
	 * 删除有序集中start到end的元素
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long zremrangeByRank(String key, int start, int end) {
		z(key).removeRange(start, end);
		logger.info("{} zrem {} start{} end{}", key, start, end);
		return 0l;
	}

	/**
	 * 删除有序集中分数为min到max的元素
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long zremrangeByScore(String key, double min, double max) {
		z(key).removeRangeByScore(min, max);
		return 0l;
	}

	/**
	 * 返回key对应的有序集中指定区间内的成员
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Set<Object> zrevrange(String key, int start, int end) {
		Set<Object> result = z(key).reverseRange(start, end);
		logger.info("{} zrevrange start{} end{} size{}", key, start, end, result.size());
		return result;
	}

	/**
	 * 返回key对应的有序集 中成员 member 的排名
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long zrank(String key, Serializable value) {
		Long rank = z(key).rank(value);
		logger.info("{} {} zrank {} ", key, value, rank);
		return rank;
	}

	/**
	 * 返回key对应的有序集 中成员 member 的排名
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long zrevrank(String key, Serializable value) {
		Long rank = z(key).reverseRank(value);
		logger.info("{} {} zrank {} ", key, value, rank);
		return rank;
	}

	/**
	 * 返回key对应的有序集中member对应的score值
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public double zscore(String key, Serializable value) {
		double score = z(key).score(value);
		logger.info("{} {} score{}", key, value, score);
		return score;
	}

	/**
	 * 为哈希表 key 中的域 field 的值加上增量 value
	 *
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long hincrBy(String key, String field, long value) {
		long result = h(key).increment(field, value);
		logger.info("{}中{}增加{}", key, field, value);
		return result;
	}

	/**
	 * hash表中添加元素
	 *
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long hset(String key, String field, Serializable value) {
		h(key).put(field, value);
		logger.info("{} put {} {}", key, field, value);
		return 0l;
	}

	/**
	 * key中的hash是否存在field的域
	 *
	 * @param key
	 * @param field
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public boolean hexists(String key, String field) {
		boolean exist = h(key).hasKey(field);
		logger.info("{} {} exist {}", key, field, exist);
		return exist;
	}

	/**
	 * Long hsetnx(String key,String field,String value);
	 * 在key对应的哈希表中，当且仅当域field不存在时，新建域field设置值为value 如果域field已经存在 不执行操作 如果key不存在
	 * 新建一个哈希表然后进行hsetnx操作 如果key存在且对应的不是哈希表 ERR Operation against a key holding
	 * the wrong kind of value 返回： 设置成功 返回1 如果给定的域已经存在且没有操作被执行 返回0
	 *
	 */
	public Long hsetnx(String key, String field, Serializable value) {
		if (!h(key).hasKey(field)) {
			h(key).put(key, value);
			logger.info("{} {} 更新为{}", key, field, value);
		} else {
			logger.info("{}中不存在{}", key, field);
		}
		return 0l;
	}

	/**
	 * key存入hash表
	 *
	 * @param key
	 * @param values
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public String hmset(String key, Map<String, ? extends Serializable> values) {
		h(key).putAll(values);
		logger.info("{} putall values.size{}", key, values.size());
		return null;
	}

	/**
	 * 获取哈希表中的数据
	 *
	 * @param key
	 * @param field
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Object hget(String key, String field) {
		Object obj = h(key).get(field);
		logger.info("{} {}: {}", key, field, obj);
		return obj;
	}

	/**
	 * 获取key中哈希集的多个域的值
	 *
	 * @param key
	 * @param fields
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public List<Object> hmget(String key, List<String> fields) {
		List<Object> result = h(key).multiGet(fields);
		logger.info("{} fields.size{} result.size{}", key, fields.size(), result.size());
		return result;
	}

	/**
	 * 获取key的哈希集
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Map<String, Object> hgetAll(String key) {
		Map<String, Object> map = h(key).entries();
		logger.info("{} entries.size{}", key, map.size());
		return map;
	}

	/**
	 * 删除key中哈希集中的指定域field
	 *
	 * @param key
	 * @param field
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long hdel(String key, String field) {
		Long count = h(key).delete(field);
		logger.info("{} delete {} -> {}", key, field, count);
		return count;
	}

	/**
	 * key中哈希集的size
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long hlen(String key) {
		long size = h(key).size();
		logger.info("{} size {}", key, size);
		return size;
	}

	/**
	 * 返回key中哈希集中的所有key
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Set<String> hkeys(String key) {
		Set<String> keys = h(key).keys();
		logger.info("{} keys.size {}", key, keys.size());
		return keys;
	}

	/**
	 * 返回key中哈希集中的所有域的值
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public List<Object> hvals(String key) {
		List<Object> list = h(key).values();
		logger.info("{} values.size {} ", key, list.size());
		return list;
	}

	/**
	 * 当且仅当key不存在 将字符串值value 存到key中 如果 key 已经存在 不做任何操作 返回0 成功 返回1
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long setnx(String key, String value) {
		boolean add = str(key).setIfAbsent(value);
		logger.info("{} setIfAbsent {} {}", key, value, add);
		return add ? 1l : 0l;
	}

	/**
	 * value存入key中，seconds秒后过期
	 *
	 * @param key
	 * @param seconds
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public String setex(String key, int seconds, String value) {
		str(key).set(value, seconds, TimeUnit.SECONDS);
		logger.info("{} set {} expire {}秒", key, value, seconds);
		return null;
	}

	/**
	 * 将value追加到key原来的值的末尾
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long append(String key, String value) {
		str(key).append(value);
		logger.info("{} append {}", key, value);
		return 0l;
	}

	/**
	 * key中值设置为value，并返回旧值
	 *
	 * @param key
	 * @param value
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public String getSet(String key, String value) {
		Object obj = str(key).getAndSet(value);
		String result = obj == null ? null : obj.toString();
		logger.info("{} getAndSet old{} new{}", key, result, value);
		return result;
	}

	/**
	 * 将key中所储存的数字值减1 注:key中存的必须是64位(long)整数
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long decr(String key) {
		Long result = str(key).increment(-1);
		logger.info("{} decr 1 {}", key, result);
		return result;
	}

	/**
	 * 将key中所储存的数字值减integer 注:key中存的必须是64位(long)整数
	 *
	 * @param key
	 * @param integer
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long decrBy(String key, long integer) {
		Long result = str(key).increment(integer - integer - integer);
		logger.info("{} decr {} {}", key, integer, result);
		return result;
	}

	/**
	 * 将key中所储存的数字值加1 注:key中存的必须是64位(long)整数
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long incr(String key) {
		Long result = str(key).increment(1);
		logger.info("{} incr 1 {}", key, result);
		return result;
	}

	/**
	 * 将key中所储存的数字值加integer 注:key中存的必须是64位(long)整数
	 *
	 * @param key
	 * @param integer
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long incrBy(String key, long integer) {
		Long result = str(key).increment(integer);
		logger.info("{} incrBy {} {}", key, integer, result);
		return result;
	}

	/**
	 * 以秒为单位，返回给定 key 的剩余生存时间
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long ttl(String key) {
		Long result = str(key).getExpire();
		logger.info("{} ttl {}", key, result);
		return result;
	}

	/**
	 * 判断key是不是已经存在
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public boolean exists(String key) {
		boolean exist = str(key).get() == null;
		logger.info("{} exists {}", key, exist);
		return exist;
	}

	/**
	 * 返回key所对应的value的类型。 返回值：none - key不存在 string - 字符串 list - 列表 set - 集合 zset
	 * - 有序集 hash - 哈希表
	 *
	 * @param key
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public String type(String key) {
		String result = str(key).getType().code();
		logger.info("{} type {}", key, result);
		return result;
	}

	/**
	 * 给指定的key设置生存时间 以秒为单位
	 *
	 * @param key
	 * @param seconds
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long expire(String key, int seconds) {
		boolean success = str(key).expire(seconds, TimeUnit.SECONDS);
		logger.info("{} expire {}, ", key, success);
		return success ? 1l : 0l;
	}

	/**
	 * 设置key的到期时间
	 *
	 * @param key
	 * @param timestamp
	 * @return
	 * @author Houkm 2017年5月11日
	 */
	public Long expireAt(String key, long timestamp) {
		Date date = Date.from(Instant.ofEpochSecond(timestamp));
		boolean success = str(key).expireAt(date);
		logger.info("{} expireAt {}, {} ", key, date, success);
		return success ? 1l : 0l;
	}

	/**
	 * 发布消息
	 * 
	 * @param channel
	 * @param message
	 * @author Houkm 2017年5月14日
	 */
	public void publish(String channel, String message) {
		template.convertAndSend(channel, message);
	}

	private BoundListOperations<Object, Object> l(String key) {
		return template.boundListOps(key);
	}

	private BoundValueOperations<Object, Object> str(String key) {
		return template.boundValueOps(key);
	}

	private BoundHashOperations<Object, String, Object> h(String key) {
		return template.boundHashOps(key);
	}

	private BoundZSetOperations<Object, Object> z(String key) {
		return template.boundZSetOps(key);
	}

	private BoundSetOperations<Object, Object> s(String key) {
		return template.boundSetOps(key);
	}
}

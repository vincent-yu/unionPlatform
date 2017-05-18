package com.yyxk.memcached.client;

import java.util.Collection;
import java.util.Map;

/**
 * Title:MemcachedClientIF Description: memcached操作接口
 * 
 * @Create_by:Vince Yu
 * @Create_date:2012-5-5
 * @Last_Edit_By:
 * @Edit_Description
 * @version:178xf 1.0
 * 
 */
public interface MemcachedClientIF {

	/**
	 * key存在时覆盖，不存在时添加
	 * 
	 * @Method_Name : set
	 * @param key
	 * @param exp
	 *            :超时时间 秒
	 * @param value
	 * @return
	 * @return : true 执行成功，false 失败
	 * @Creation Date : 2014-9-17 下午2:30:24
	 * @version : v1.00
	 * @Author : zhangyan
	 * @Update Date :
	 * @Update Author :
	 */
	public boolean set(String key, int exp, Object value);

	/**
	 * 根据key获取对象
	 * 
	 * @Method_Name : get
	 * @param key
	 * @return
	 * @return : Object
	 * @Creation Date : 2014-9-17 下午2:26:30
	 * @version : v1.00
	 * @Author : zhangyan
	 * @Update Date :
	 * @Update Author :
	 */
	public Object get(String key);

	public Map<String, Object> get(Collection<String> key);

	/**
	 * 通过key获取value并更新过期时间
	 * 
	 * @Method_Name : getAndTouch
	 * @param key
	 *            :memcached中的key
	 * @return
	 * @Creation Date : 2014-9-16 下午3:53:18
	 * @version : v1.00
	 * @Author : zhangyan
	 * @Update Date :
	 * @Update Author :
	 */
	public Object getAndTouch(String key, int exp);

	/**
	 * 根据key删除对象
	 * 
	 * @Method_Name : delete
	 * @param key
	 * @return
	 * @return : boolean
	 * @Creation Date : 2014-9-17 下午2:26:07
	 * @version : v1.00
	 * @Author : zhangyan
	 * @Update Date :
	 * @Update Author :
	 */
	public boolean delete(String key);

	/**
	 * 只有key不存在时进行添加
	 * 
	 * @Method_Name : add
	 * @param key
	 * @param value
	 * @return
	 * @return : boolean
	 * @Creation Date : 2014-9-17 下午2:30:06
	 * @version : v1.00
	 * @Author : zhangyan
	 * @Update Date :
	 * @Update Author :
	 */
	boolean add(String key, Object value);

	/**
	 * 只有key不存在时进行添加
	 * 
	 * @Method_Name : add
	 * @param key
	 * @param exp
	 *            :超时时间 秒
	 * @param value
	 * @return
	 * @return : boolean
	 * @Creation Date : 2014-9-17 下午2:31:12
	 * @version : v1.00
	 * @Author : zhangyan
	 * @Update Date :
	 * @Update Author :
	 */
	boolean add(String key, int exp, Object value);

	public boolean isCacheEnabled();

	/**
	 * 只有key存在时才会替换对象
	 * 
	 * @Method_Name : replace
	 * @param key
	 * @param exp
	 *            :超时时间（秒）
	 * @param value
	 * @return
	 * @return : boolean
	 * @Creation Date : 2014-9-17 下午2:27:00
	 * @version : v1.00
	 * @Author : zhangyan
	 * @Update Date :
	 * @Update Author :
	 */
	public boolean replace(String key, int exp, Object value);

}

package com.yyxk.memcached.client;

import java.util.Collection;
import java.util.Map;

import net.rubyeye.xmemcached.MemcachedClient;

/**
 * Title:ClientXmemcached Description:memcached客户端操作封装类
 * 
 * @Create_by:Vince Yu
 * @Create_date:2012-5-5
 * @Last_Edit_By:
 * @Edit_Description
 * @version:178xf 1.0
 * 
 */
public class ClientXmemcached implements MemcachedClientIF {
	private MemcachedClient memcachedClient;
	private boolean cacheEnabled = true;

	public boolean isCacheEnabled() {
		return cacheEnabled;
	}

	public void setCacheEnabled(boolean cacheEnabled) {
		this.cacheEnabled = cacheEnabled;
	}

	/**
	 * 通过key
	 * 
	 * @Method_Name : set
	 * @param key
	 * @param exp
	 * @param value
	 * @return
	 * @Creation Date : 2014-9-16 下午3:52:47
	 * @version : v1.00
	 * @Author : zhangyan
	 * @Update Date :
	 * @Update Author :
	 */
	public boolean set(String key, int exp, Object value) {
		try {
			return this.memcachedClient.set(key, exp, value);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 通过key获取value
	 * 
	 * @Method_Name : get
	 * @param key
	 *            :memcached中的key
	 * @return
	 * @Creation Date : 2014-9-16 下午3:53:18
	 * @version : v1.00
	 * @Author : zhangyan
	 * @Update Date :
	 * @Update Author :
	 */
	public Object get(String key) {
		try {
			return this.memcachedClient.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

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
	public Object getAndTouch(String key, int exp) {
		try {
			return this.memcachedClient.getAndTouch(key, exp);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 向memcached中添加元素,
	 * 
	 * @Method_Name : add
	 * @param key
	 *            :key
	 * @param exp
	 * @param value
	 * @return
	 * @Creation Date : 2014-9-16 下午3:53:46
	 * @version : v1.00
	 * @Author : zhangyan
	 * @Update Date :
	 * @Update Author :
	 */
	public boolean add(String key, int exp, Object value) {
		try {
			return this.memcachedClient.add(key, exp, value);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 通过key删除元素
	 * 
	 * @Method_Name : delete
	 * @param key
	 *            :memcached中的key
	 * @return ：true 处理成功 false 失败
	 * @Creation Date : 2014-9-16 下午3:56:13
	 * @version : v1.00
	 * @Author : zhangyan
	 * @Update Date :
	 * @Update Author :
	 */
	public boolean delete(String key) {
		try {
			return this.memcachedClient.delete(key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean replace(String key, int exp, Object session) {
		try {
			return this.memcachedClient.replace(key, exp, session);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean add(String key, Object value) {
		return add(key, 0, value);
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
		this.memcachedClient.setEnableHeartBeat(false);
		// this.memcachedClient.setOpTimeout(2000);
	}

	@Override
	public Map<String, Object> get(Collection<String> key) {
		try {
			return this.memcachedClient.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

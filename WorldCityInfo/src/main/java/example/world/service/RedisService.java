package example.world.service;

import java.util.concurrent.TimeUnit;

public interface RedisService {
	/**
	 * 一天的秒数
	 */
	public static final long DEFAULT_EXPIRE = 0x15180;

	/**
	 * 默认过期时间为一天
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public void set(String key, String value);

	/**
	 * @param key
	 * @param value
	 * @param expire 过期时间 单位： 秒
	 * @return
	 */
	public void set(String key, String value, long expire);

	public String get(String key);

	public Boolean del(String key);

	/**
	 * @param key
	 * @param expire 存在的时间（秒）
	 * @return
	 */
	public Boolean expire(String key, long expire);

	public Boolean expire(String key, long expire, TimeUnit timeUnit);

	void set(String key, Object value);
}

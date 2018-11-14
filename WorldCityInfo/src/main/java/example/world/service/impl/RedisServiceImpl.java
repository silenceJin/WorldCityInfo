package example.world.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import example.world.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	private final StringRedisTemplate redisTemplate;
	private final String PREFIX;

	@Autowired
	public RedisServiceImpl(StringRedisTemplate stringRedisTemplate,
			@Value("${spring.application.name: }") String prefix) {
		super();
		this.redisTemplate = stringRedisTemplate;
		this.PREFIX = prefix.concat("_");
	}

	@Override
	public void set(String key, String value) {
		this.set(key, value, DEFAULT_EXPIRE);
	}

	@Override
	public void set(String key, Object value) {
		this.set(key, JSON.toJSONString(value));
	}

	@Override
	public void set(String key, String value, long expire) {
		redisTemplate.opsForValue().set(PREFIX.concat(key), value, expire, TimeUnit.SECONDS);
	}

	@Override
	public String get(String key) {
		return redisTemplate.opsForValue().get(PREFIX.concat(key));
	}

	@Override
	public Boolean del(String key) {
		return redisTemplate.delete(PREFIX.concat(key));
	}

	@Override
	public Boolean expire(String key, long expire) {
		return expire(key, expire, TimeUnit.SECONDS);
	}

	@Override
	public Boolean expire(String key, long expire, TimeUnit timeUnit) {
		return redisTemplate.expire(PREFIX.concat(key), expire, timeUnit);
	}

}

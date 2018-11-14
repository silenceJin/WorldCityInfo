package example.world.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
@EnableCaching
public class CacheConfig {
	@Bean
	@Autowired
	public CacheManager cacheManager(RedisConnectionFactory connectionFactory,
			@Value("${spring.cache.redis.time-to-live.seconds: 86400}") long seconds) {
		RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(seconds)).disableCachingNullValues()
				.serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

		return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
				.cacheDefaults(defaultCacheConfig).transactionAware().build();
	}
}

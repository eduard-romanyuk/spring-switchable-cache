package io.github.eduardromanyuk.runtimecachedisabling.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
@EnableCaching
public class CacheConfig {
	@Bean("switchableCacheManager")
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory, CacheProperties cacheProperties) {
		RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisConnectionFactory)
				.cacheDefaults(RedisCacheConfiguration
						.defaultCacheConfig()
						.entryTtl(Duration.ofMinutes(10)))
				.build();
		return new SwitchableCacheManager(redisCacheManager, cacheProperties::isEnabled);
	}
}

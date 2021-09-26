package io.github.eduardromanyuk.runtimecachedisabling.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("cache")
public class CacheProperties {
	private boolean enabled = true;
}

package io.github.eduardromanyuk.runtimecachedisabling.config;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCache;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SwitchableCacheManager implements CacheManager {
	private final CacheManager cacheManager;
	private final Supplier<Boolean> isEnabledSupplier;

	@Override
	public Cache getCache(String name) {
		return this.isEnabled() ? cacheManager.getCache(name) : new NoOpCache(name);
	}

	@Override
	public Collection<String> getCacheNames() {
		return cacheManager.getCacheNames();
	}

	private boolean isEnabled() {
		return Optional.of(isEnabledSupplier)
				.map(Supplier::get)
				.orElse(false);
	}
}

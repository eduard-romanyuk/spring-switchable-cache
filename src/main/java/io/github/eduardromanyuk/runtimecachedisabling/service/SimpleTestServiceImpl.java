package io.github.eduardromanyuk.runtimecachedisabling.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SimpleTestServiceImpl implements SimpleTestService {
	private String value = "initial";

	@Override
	@Cacheable(value = "test-val", cacheManager = "switchableCacheManager")
	public String getValue() {
		return this.value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}
}

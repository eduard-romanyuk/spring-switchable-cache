package io.github.eduardromanyuk.runtimecachedisabling.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.eduardromanyuk.runtimecachedisabling.config.CacheProperties;

@SpringBootTest
class CacheDisablingTest {
	@Autowired
	private SimpleTestService simpleTestService;
	@Autowired
	private CacheProperties cacheProperties;

	@Test
	void test() {
		// cache init value
		String value = "initial";
		assertEquals(value, simpleTestService.getValue());

		// check that value was cached
		value = "new";
		simpleTestService.setValue(value);
		assertNotEquals(value, simpleTestService.getValue());

		// disable cache
		cacheProperties.setEnabled(false);
		assertEquals(value, simpleTestService.getValue());

	}
}
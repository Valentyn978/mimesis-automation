package com.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum ScenarioContext {
	SCENARIO_CONTEXT;

	private ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

	public void initialize() {
		threadLocal.set(new HashMap<>());
	}

	public <T> Optional<T> get(String key) {
		return Optional.ofNullable(threadLocal.get()).map(map -> (T) map.get(key));
	}

	public <T> void put(String key, T value) {
		Map<String, Object> map = threadLocal.get();
		if (map != null) {
			map.put(key, value);
		}
	}

	public void removeContextValue(String key) {
		Map<String, Object> map = threadLocal.get();
		if (map != null) {
			map.remove(key);
		}
	}

	public void release() {
		threadLocal.remove();
	}
}

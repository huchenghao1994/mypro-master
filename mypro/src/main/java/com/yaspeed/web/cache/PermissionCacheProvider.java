package com.yaspeed.web.cache;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.yaspeed.core.cache.CacheProvider;

public class PermissionCacheProvider implements CacheProvider {

	private static Map<String, Serializable> cacheContainer = new ConcurrentHashMap<String,Serializable>();

	@Override
	public void put(String key, Serializable cacheObject) {
		cacheContainer.put(key, cacheObject);
	}

	@Override
	public Serializable get(String key) {
		return cacheContainer.get(key);
	}

	@Override
	public void remove(String key) {
		cacheContainer.remove(key);
	}

	@Override
	public void clear() {
		cacheContainer.clear();
	}

	@Override
	public void loadAllCache() {

	}

}

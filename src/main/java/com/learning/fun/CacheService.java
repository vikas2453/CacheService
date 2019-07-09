package com.learning.fun;

public interface CacheService {
	public Object get(String key);
	public void put(String key, Object Value);
}

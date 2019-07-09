package com.learning.fun;

import java.util.HashMap;
import java.util.Map;

public class LimitedSizeHashMap<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = 1L;

	private Map<K, V> map;
	
	//default size of 100
	private int maxSize=100;

	public LimitedSizeHashMap( int maxSize) {
		map = new HashMap<K, V>();
		this.maxSize = maxSize;
	}

	
	public V put(K key, V value) {
		if (map.size() >= maxSize && !map.containsKey(key)) {
			return value;
		} else {
			map.put(key, value);
			return value;
		}
	}
	public V get(String key) {
		return map.get(key);
	}

}

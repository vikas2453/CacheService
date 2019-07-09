package com.learning.fun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
public class MemoryCacheServiceImpl implements CacheService {
	
	private LimitedSizeHashMap<String, Object> limitedSizeHashMap;
	
	@Value("${maxSize}")
	private int maxSize;
	
	private MemoryCacheServiceImpl() {
		
	}
	
	@Bean(name="memoryCacheService")
	public CacheService creatMemoryCacheBean() {
		MemoryCacheServiceImpl memoryCacheService =  new MemoryCacheServiceImpl();
		limitedSizeHashMap = new LimitedSizeHashMap<>(maxSize);	
		memoryCacheService.setLimitedSizeHashMap(limitedSizeHashMap);
		return memoryCacheService;
		
	}

	
	@Override
	public Object get(String key) {
		return limitedSizeHashMap.get(key);
	}

	@Override
	public void put(String key, Object value) {
		limitedSizeHashMap.put(key, value);
		
	}

}

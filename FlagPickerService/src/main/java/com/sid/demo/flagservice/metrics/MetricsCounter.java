package com.sid.demo.flagservice.metrics;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MetricsCounter {
	
	static Map<String, AtomicInteger> counts = new ConcurrentHashMap<>();
	

	public static void add(String key){
	    counts.get(key).incrementAndGet();
	}

	public static void initializeCounts (List<String> keys){
		keys.forEach(key -> counts.put(key,  new AtomicInteger(0)));
	}
	
	public static int getCounter(String key){
		return counts.get(key).get();
	}

}

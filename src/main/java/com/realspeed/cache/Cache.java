package com.realspeed.cache;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
/**
 * This class has been written to store the values in key and value format.  
 * @author Dhananjay Samanta
 *
 */
public class Cache {
	private  HashMap<String,Object> dataMap;
	private static Cache cache;
	private static ReentrantLock reentrantLock;
	private Cache() {
		super();
		dataMap=new HashMap<>();
	}
	
	public static Cache getCache(){
		if (cache==null) {
			reentrantLock.lock();
			if(cache==null){
				cache=new Cache();
			}
			reentrantLock.unlock();
		}
		return cache;
	}
	
	public void put(String key,Object value){
		dataMap.put(key, value);
	}
	public Object get(String key){
		return dataMap.get(key);
	}
	public boolean containsKey(String key){
		return dataMap.containsKey(key);
	}
}

package com.sapient.FileCache;
import java.util.Map;
import java.util.WeakHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
/**
 * Simple time-based cache.
 */
@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class SimpleCache {
	   @Value("${SimpleCache.maxAge}")
	private long maxAge ;
	private Map store;
	 
 
	
	/**
	 * @param maxAge maximum age of an entry in milliseconds
	 * @param store map to hold entries
	 */
	 
	/**
	 * Cache an object.
	 * @param key unique identifier to retrieve object
	 * @param value object to cache
	 */
	public void put(Object key, Object value) {
		store.put(key, new Item(value));
	}
	
	/**
	 * Fetch an object.
	 * @param key unique identifier to retrieve object
	 * @return an object or null in case it isn't stored or it expired
	 */
	public Object get(Object key) {
		Item item = getItem(key);
		return item == null ? null : item.payload;
	}
	
	/**
	 * Fetch an object or store and return output of callback.
	 * @param key unique identifier to retrieve object
	 * @param block code executed when object not in cache
	 * @return an object
	 */
	public synchronized Object get(Object key, Callback block) {
		Item item = getItem(key);
		if (item == null) {
			Object value = block.execute();
			item = new Item(value);
			store.put(key, item);
		}
		return item.payload;
	}
	
	public SimpleCache(Map store) {
		super();
		this.store = store;
	}

	public long getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(long maxAge) {
		this.maxAge = maxAge;
	}

	public Map getStore() {
		return store;
	}

	public void setStore(Map store) {
		this.store = store;
	}

	/**
	 * Remove an object from cache.
	 * @param key unique identifier to retrieve object
	 */
	public void remove(Object key) {
		store.remove(key);
	}
	
	/**
	 * Get an item, if it expired remove it from cache and return null.
	 * @param key unique identifier to retrieve object
	 * @return an item or null
	 */
	private Item getItem(Object key) {
		Item item = (Item) store.get(key);
		if (item == null) {
			return null;
		}
		if (System.currentTimeMillis() - item.birth > maxAge) {
			store.remove(key);
			return null;
		}
		return item;		
	}
	/**
	 * Value container.
	
	/**
	 * A visitor interface.
	 */
	public static interface Callback {
		Object execute();
	}
}
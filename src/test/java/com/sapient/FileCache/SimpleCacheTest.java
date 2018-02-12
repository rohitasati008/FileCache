package com.sapient.FileCache;

import java.util.HashMap;
import junit.framework.TestCase;
public class SimpleCacheTest extends TestCase {
	public void testPutGet () {
		SimpleCache c = new SimpleCache(new HashMap());
		c.put("key1", "value1");
		assertEquals("value1", c.get("key1"));
		c.put("key1", "value1.0");
		assertEquals("value1.0", c.get("key1"));
		c.put("key2", "value2");
		assertEquals("value2", c.get("key2"));
		assertEquals("value1.0", c.get("key1"));
	}
	
	public void testMaxAge () throws InterruptedException {
		SimpleCache c = new SimpleCache( new HashMap());
		c.put("key1", "value1");
		assertEquals("value1", c.get("key1"));
		Thread.sleep(1500);
		assertNull(c.get("key1"));
		
	/*	c.put("key2", "value2");
		Thread.sleep(750);
		c.put("key3", "value3");
		Thread.sleep(750);
		assertNull(c.get("key2"));
		assertNotNull(c.get("key3"));
		Thread.sleep(750);
		assertNull(c.get("key3"));*/
	}
	
	public void testRemove () {
		SimpleCache c = new SimpleCache(new HashMap());
		c.remove("key");
		assertNull(c.get("key"));
		c.put("key", "value");
		assertNotNull(c.get("key"));
		c.remove("key");
		assertNull(c.get("key"));
	}
	
	public void testCallBack () {
		SimpleCache c = new SimpleCache( new HashMap());
		assertEquals("value1", c.get("key1", new SimpleCache.Callback() {
			public Object execute() {
				return "value1";
			}
		}));
		assertEquals("value1", c.get("key1"));
		
		// again with a new callback (value)
		c.get("key1", new SimpleCache.Callback() {
			public Object execute() {
				return "value2";
			}
		});
		assertEquals("value1", c.get("key1"));
	}
}
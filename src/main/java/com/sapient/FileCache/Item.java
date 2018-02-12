package com.sapient.FileCache;
 
	public  class Item {
		long birth;
		Object payload;
		Item(Object payload) {
			this.birth = System.currentTimeMillis();
			this.payload = payload;
		}
	}
	
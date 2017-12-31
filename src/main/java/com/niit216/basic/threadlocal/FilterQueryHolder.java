package com.niit216.basic.threadlocal;

public class FilterQueryHolder {
	private static ThreadLocal<String> queryLocal = new ThreadLocal<String>();
	
	public static void setQuery(String query) {
		queryLocal.set(query);
	}
	
	public static String getQuery() {
		return queryLocal.get();
	}
	
	public static void remove() {
		queryLocal.remove();
	}
	
}

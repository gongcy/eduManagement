package com.niit216.basic.threadlocal;

import com.niit216.basic.model.SystemRequest;

public class SystemRequestHolder {
	private final static ThreadLocal<SystemRequest> systemRequesthreadLocal = new ThreadLocal<SystemRequest>();
	
	public static void initRequestHolder(SystemRequest systemRequest) {
		systemRequesthreadLocal.set(systemRequest);
	}
	
	public static SystemRequest getSystemRequest() {
		return systemRequesthreadLocal.get();
	}
	
	public static void remove() {
		systemRequesthreadLocal.remove();
	}
}

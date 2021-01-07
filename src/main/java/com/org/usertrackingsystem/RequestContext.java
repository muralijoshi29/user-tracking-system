/**
 * 
 */
package com.org.usertrackingsystem;

/**
 * @author 7000018390
 *
 */
/*
 * RequestHolder creates a singleton object to ContextHolderClass to use thread
 * local variable
 */
public class RequestContext {

	
	RequestContext() {
		
	}
	
	private static ThreadLocal<ContextHolder> threadLocal = new ThreadLocal<>();

	public static ContextHolder getContext() {
		ContextHolder contextHolder = threadLocal.get();
		if (contextHolder == null) {
			contextHolder = new ContextHolder();
			threadLocal.set(contextHolder);
		}
		return contextHolder;
	}
}

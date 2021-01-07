/**
 * 
 */
package com.org.usertrackingsystem;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 7000018390
 *
 */
/*
 * ContextHolder holds values using thread local which is used all across
 * project
 */
public class ContextHolder {

	private Map<String, Object> attribbuteMap = new HashMap<>();

	public void setAttribute(String key, Object value) {

		attribbuteMap.put(key, value);
	}

	public Object getAttribute(String key) {
		return attribbuteMap.get(key);
	}
}

/**
 * 
 */
package com.org.usertrackingsystem.secutiry;

import org.springframework.security.core.AuthenticationException;

/**
 * @author BadGateWay
 *
 */
public class CustomException extends AuthenticationException{

	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(String msg, Throwable t) {
		super(msg, t);
	}
}

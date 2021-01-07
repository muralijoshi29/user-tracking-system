/**
 * 
 */
package com.org.usertrackingsystem.service;

import com.org.usertrackingsystem.vo.UserDetailsVo;

/**
 * @author BadGateWay
 *
 */
public interface TrackingSystemService {
	
	public UserDetailsVo getOnlineUsers(String userName);

	public void logout(String userName);

}

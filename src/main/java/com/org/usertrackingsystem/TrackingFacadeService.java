/**
 * 
 */
package com.org.usertrackingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.usertrackingsystem.service.TrackingSystemService;
import com.org.usertrackingsystem.vo.UserDetailsVo;

/**
 * @author BadGateWay
 *
 */
@Service
public class TrackingFacadeService {

	@Autowired
	private TrackingSystemService trackingSystemService;
	
	public UserDetailsVo getOnlineUsers(String userName) {
		return trackingSystemService.getOnlineUsers(userName);
	}
	
	public void logout(String userName) {
		trackingSystemService.logout(userName);
	}
}

/**
 * 
 */
package com.org.usertrackingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.usertrackingsystem.RequestContext;
import com.org.usertrackingsystem.TrackingFacadeService;
import com.org.usertrackingsystem.common.utils.CommonConstants;
import com.org.usertrackingsystem.vo.UserDetailsVo;
import com.org.usertrackingsystem.vo.UserVO;

/**
 * @author BadGateWay
 *
 */
@RestController
@RequestMapping("/tracker")
public class TrackingController {
	
	@Autowired
	private TrackingFacadeService trackingFacadeService;
	
	@GetMapping("/online-users")
	public UserDetailsVo getOnlineUsers() {
		UserVO userVO = (UserVO) RequestContext.getContext().getAttribute(CommonConstants.USERVO);
		return trackingFacadeService.getOnlineUsers(userVO.getUserName());
	}

	@GetMapping("/logout")
	public void logout() {
		UserVO userVO = (UserVO) RequestContext.getContext().getAttribute(CommonConstants.USERVO);
		trackingFacadeService.logout(userVO.getUserName());
	}
}

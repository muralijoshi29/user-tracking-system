/**
 * 
 */
package com.org.usertrackingsystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.usertrackingsystem.model.User;
import com.org.usertrackingsystem.repository.UserRepository;
import com.org.usertrackingsystem.service.TrackingSystemService;
import com.org.usertrackingsystem.vo.UserDetailsVo;
import com.org.usertrackingsystem.vo.UserVO;

/**
 * @author BadGateWay
 *
 */
@Service
public class TrackingSystemServiceImpl implements TrackingSystemService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetailsVo getOnlineUsers(String userName) {
		User user = userRepository.findByUserName(userName);
		List<User> allLicensedUsers = userRepository.findByLicenseKey(user.getLicenseKey());
		UserDetailsVo userDetailsVo = new UserDetailsVo();
		List<UserVO> userVOs = new ArrayList<>();
		if(null != allLicensedUsers) {
			allLicensedUsers.stream().forEach(licensedUser -> {
				UserVO userVO = new UserVO();
				userVO.setUserName(licensedUser.getUserName());
				userVO.setActive(licensedUser.getActive());
				userVOs.add(userVO);
			});
			userDetailsVo.setUserVOs(userVOs);
		}
		
		return userDetailsVo;
	}
	
	@Override
	public void logout(String userName) {
		User user = userRepository.findByUserName(userName);
		user.setActive("N");
		userRepository.save(user);
	}

}

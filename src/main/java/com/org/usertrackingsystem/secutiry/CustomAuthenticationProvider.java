package com.org.usertrackingsystem.secutiry;

import java.util.List;

import org.apache.tomcat.websocket.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.org.usertrackingsystem.model.User;
import com.org.usertrackingsystem.repository.UserRepository;

@Service
@Configurable
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public Authentication authenticate(Authentication authentication)  {
		
		doLogin(authentication.getPrincipal().toString(),
				authentication.getCredentials().toString());
		
		if(logger.isDebugEnabled()) {
		logger.info(authentication.toString());
		}
		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}
	
	public boolean doLogin(String user, String pass) {
		boolean success = false;
		
		if (StringUtils.hasText(pass)) {
			try {
				User userData = userRepository.findByUserName(user);
				if(null == userData) {
					throw new BadCredentialsException("Bad Credentials");
				}
				if(!userData.getPassword().equals(pass)) {
					throw new BadCredentialsException("Bad Credentials");
				}
				List<User> userList = userRepository.findByLicenseKeyAndActive(userData.getLicenseKey(), "Y");
				if(userList.size()>=3) {
					throw new CustomException("License is completely Utilized");
				}
				success = true;
				userData.setActive("Y");
				userRepository.save(userData);
			}catch (CustomException ce) {
				throw new CustomException("License is completely Utilized");
			}
			catch(Exception ne) {
				throw new BadCredentialsException(ne.getMessage());
			}
			
		}
		
		return success;
	}

}

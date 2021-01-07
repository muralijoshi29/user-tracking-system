/**
 * 
 */
package com.org.usertrackingsystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.usertrackingsystem.common.utils.MessageConstants;
import com.org.usertrackingsystem.secutiry.CustomAuthenticationProvider;
import com.org.usertrackingsystem.secutiry.CustomException;
import com.org.usertrackingsystem.secutiry.JwtAuthenticationResponse;
import com.org.usertrackingsystem.secutiry.JwtTokenProvider;
import com.org.usertrackingsystem.vo.ApiResponse;
import com.org.usertrackingsystem.vo.LoginRequest;
import com.org.usertrackingsystem.vo.ValidateTokenRequest;



/**
 * @author 7000018390
 *
 */
@RestController
@RequestMapping(path = "/auth")
public class AuthController {

	

	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;
    
    @Autowired
    JwtTokenProvider tokenProvider;

   @SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/generatetoken")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
    	if(loginRequest.getUsername().isEmpty() || loginRequest.getPassword().isEmpty()) {
    		 return new ResponseEntity(new JwtAuthenticationResponse(false, MessageConstants.USR_OR_PSWD_INVALID),
                     HttpStatus.BAD_REQUEST);
    		 
    	}
    	List<SimpleGrantedAuthority> li = new ArrayList<>();
    	li.add(new SimpleGrantedAuthority("ROLE_PROJECT_MANAGER"));
    	Authentication authentication =  customAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword(),
                        li
                ));
       
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/validatetoken")
    public ResponseEntity<ApiResponse> getTokenByCredentials(@Valid @RequestBody ValidateTokenRequest validateToken) {
    	 String username = null;
    	 String jwt =validateToken.getToken();
         if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                username = tokenProvider.getUsernameFromJWT(jwt);
	          //If required we can have one more check here to load the user from LDAP server
             return ResponseEntity.ok(new ApiResponse(Boolean.TRUE,MessageConstants.VALID_TOKEN + username));
           }else {
        	   return new ResponseEntity(new ApiResponse(false, MessageConstants.INVALID_TOKEN),
                       HttpStatus.BAD_REQUEST);
           }
         
    }
 
}

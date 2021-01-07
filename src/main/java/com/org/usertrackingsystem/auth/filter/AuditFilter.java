package com.org.usertrackingsystem.auth.filter;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.org.usertrackingsystem.RequestContext;
import com.org.usertrackingsystem.common.utils.CommonConstants;
import com.org.usertrackingsystem.secutiry.CustomException;
import com.org.usertrackingsystem.secutiry.JwtTokenProvider;
import com.org.usertrackingsystem.vo.UserVO;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class AuditFilter extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	private static final String[] IP_HEADER_CANDIDATES = { 
		    "X-Forwarded-For",
		    "Proxy-Client-IP",
		    "WL-Proxy-Client-IP",
		    "HTTP_X_FORWARDED_FOR",
		    "HTTP_X_FORWARDED",
		    "HTTP_X_CLUSTER_CLIENT_IP",
		    "HTTP_CLIENT_IP",
		    "HTTP_FORWARDED_FOR",
		    "HTTP_FORWARDED",
		    "HTTP_VIA",
		    "REMOTE_ADDR" };
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		logger.info("In preHandle we are Intercepting the Request");
		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken  = requestTokenHeader.substring(7);
		String jwtTokenStr = new StringBuilder("jwtToken>>").append(jwtToken).toString();
		logger.debug(jwtTokenStr);
		String remoteAddr = "";
		 if (request != null) {
	            remoteAddr = request.getHeader("X-Forwarded-For");
	            if (remoteAddr == null || "".equals(remoteAddr)) {
	                remoteAddr = request.getRemoteAddr();
	                if (remoteAddr.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
					    InetAddress inetAddress = InetAddress.getLocalHost();
					    String ipAddress = inetAddress.getHostAddress();
					    remoteAddr = ipAddress;
					}
	            }
	        }
		 System.out.print("Remote Address : " + remoteAddr);
		 
			try {
				username = jwtTokenProvider.getUsernameFromJWT(jwtToken);

				List<SimpleGrantedAuthority> li = new ArrayList<>();
				li.add(new SimpleGrantedAuthority("ROLE_PROJECT_MANAGER"));

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(

						username, null,li);

				usernamePasswordAuthenticationToken

						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				UserVO value= new UserVO(); 
				value.setUserName(username);
				RequestContext.getContext().setAttribute(CommonConstants.USERVO, value);

			} catch (CustomException ce) {
				return false;
			}
			catch (IllegalArgumentException e) {

				logger.error("Unable to get JWT Token");

			} catch (ExpiredJwtException e) {

				logger.error("JWT Token has expired");

			}
		
		return true;
	}
	

}

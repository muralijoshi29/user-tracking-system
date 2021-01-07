/**
 * 
 */
package com.org.usertrackingsystem.auth.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 7000018390
 *
 */
@Configuration
public class RequestInterceptorConfiguration implements WebMvcConfigurer {

	@Autowired
	private AuditFilter auditFilter;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(auditFilter).addPathPatterns("/**").excludePathPatterns("/auth/generatetoken");
	}

}

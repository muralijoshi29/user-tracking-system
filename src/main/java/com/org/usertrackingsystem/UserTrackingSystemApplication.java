package com.org.usertrackingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration
public class UserTrackingSystemApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(UserTrackingSystemApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	      return builder.sources(UserTrackingSystemApplication.class);
	}

}

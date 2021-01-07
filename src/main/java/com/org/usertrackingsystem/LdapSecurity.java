package com.org.usertrackingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.org.usertrackingsystem.secutiry.CustomAuthenticationProvider;
import com.org.usertrackingsystem.secutiry.JwtAuthenticationEntryPoint;



@Configuration
@EnableWebSecurity
public class LdapSecurity extends WebSecurityConfigurerAdapter {
	

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity
		.csrf()
			.disable()
		.cors()
			.disable()
		.exceptionHandling()
			.authenticationEntryPoint(unauthorizedHandler)
		.and()
        .authorizeRequests()
            .antMatchers("/auth/**").permitAll()
           /* .and()
            .authorizeRequests().antMatchers("/invoice/**").hasRole("DELIVERY_MANAGER").and().httpBasic()
            .and()
            .authorizeRequests().antMatchers("/invoice/**").hasRole("PROJECT_MANAGER").and().httpBasic()
            .and()
            .authorizeRequests().antMatchers("/invoice/**").hasAnyRole("DELIVERY_MANAGER","PROJECT_MANAGER","FINANCE").and().httpBasic()*/
            
    	.and().authorizeRequests().anyRequest().authenticated();
    	
    }

    
    
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    

    @Bean()
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Autowired
    private CustomAuthenticationProvider authProvider;
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
 
    

}
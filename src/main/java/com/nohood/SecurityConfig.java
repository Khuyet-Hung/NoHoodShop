package com.nohood;



import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nohood.duantotnghiep.entity.ACCOUNT;
import com.nohood.duantotnghiep.service.ACCOUNTSERVICE;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	BCryptPasswordEncoder pe;
	@Autowired
	UserDetail service;
	@Autowired
	ACCOUNTSERVICE dao;
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(HttpMethod.OPTIONS);
	}
	@Bean
	public BCryptPasswordEncoder getPasswordEnCoder() {
		 BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	       return bCryptPasswordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/order/**").authenticated().antMatchers("/admin/**")
				.hasAnyRole("ADMN", "STAF", "SPST").antMatchers("/rest/authorities").hasRole("ADMN").anyRequest()
				.permitAll();

		http.formLogin().loginPage("/security/login/form").loginProcessingUrl("/security/login")
				.defaultSuccessUrl("/security/login/success", false).failureUrl("/security/login/error");
		http.rememberMe().tokenValiditySeconds(86400);
		http.exceptionHandling().accessDeniedPage("/security/unauthoried");
		http.logout().logoutUrl("/security/logoff").logoutSuccessUrl("/security/logoff/success");
		http.oauth2Login().loginPage("/auth/login/form")
		.defaultSuccessUrl("/oauth2/login/success",true)
		.failureUrl("/auth/login/error").authorizationEndpoint()
		.baseUri("/oauth2/authorization");
	}

	
}

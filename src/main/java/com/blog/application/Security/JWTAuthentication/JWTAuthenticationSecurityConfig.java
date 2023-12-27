package com.blog.application.Security.JWTAuthentication;

import org.springframework.context.annotation.Lazy ;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.blog.application.Security.UserConfig.EXNUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class JWTAuthenticationSecurityConfig {
	
	@Autowired
	JWTAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	@Lazy
	JWTAuthenticationFilter authenticationFilter;
	 
	@Bean
	public UserDetailsService getuserDetailsService() {
		return new EXNUserDetailsService();
	}
	
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider getDaoAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(getuserDetailsService());
		authenticationProvider.setPasswordEncoder(getPasswordEncoder());
		return authenticationProvider;
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector)
			throws Exception {
		MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
		httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((authorize) -> {
			authorize.requestMatchers(mvcMatcherBuilder.pattern("/api/users/create")).permitAll();
			authorize.requestMatchers(mvcMatcherBuilder.pattern("/api/token/generateToken")).permitAll();
			authorize.requestMatchers(HttpMethod.GET).permitAll().anyRequest().authenticated();
		}).exceptionHandling(exception -> {
			exception.authenticationEntryPoint(authenticationEntryPoint);
		}).sessionManagement(sessionManagement -> {
			sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}).addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}
	
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }
}

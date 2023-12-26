package com.blog.application.Security.JWTAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class JWTAuthenticationSecurityConfig {
	
	@Autowired
	JWTAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	JWTAuthenticationFilter authenticationFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity,HandlerMappingIntrospector introspector)
			throws Exception {
		MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
		httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((authorize) -> {
            authorize.requestMatchers(mvcMatcherBuilder.pattern("/api/login")).permitAll();
          authorize.anyRequest().authenticated();
      }).exceptionHandling(exception->{
    	  exception.authenticationEntryPoint(authenticationEntryPoint);
      }).sessionManagement(sessionManagement->{
    	  sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      }).addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}
	
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }
}

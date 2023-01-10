package com.mgr.MgrSpringApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
	
		
	@Autowired
	private JwtAuthenticationEntityPoint jwtAuthenticationEntityPoint;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(userDetails).passwordEncoder(PasswordEncoder());
//    }
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/mgr/**").permitAll()
			.antMatchers("/mgr/api/**").permitAll()
			.antMatchers("/mgr/api/sales/**").hasAnyAuthority("USER")
			.antMatchers("/v2/api-docs","/swagger-resources/**","/swagger-ui.html","/webjars/**" ,"/swagger.json").permitAll()
			.antMatchers(HttpHeaders.ALLOW).permitAll()
			//.anyRequest().authenticated()
			.anyRequest().permitAll()
			.and()
			.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntityPoint)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}


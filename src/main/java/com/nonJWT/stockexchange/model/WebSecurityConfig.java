package com.nonJWT.stockexchange.model;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	//@Bean
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		
		httpSecurity.csrf().disable()
	
				// dont authenticate this particular request
				.authorizeRequests()
				//for permit all no need to add extra slash in the end
				//when invoking endpint
				.antMatchers("/authenticate").permitAll().
				antMatchers("/setuserapi2").permitAll().
				//antMatchers("/getuserapi").permitAll().
				antMatchers("/register").permitAll().
				antMatchers("/h2-console/").permitAll().
				antMatchers("/h2-console/**").permitAll().
				antMatchers("/h2-console/***").permitAll().
				antMatchers("/h2-console/*").permitAll().
				//this can affect cors sometimes so use authority and note role
					 //antMatchers("/getuserapi").hasRole("admin").
					// you will have to specify /getuserapi/ as endpoint in calling app like react or postamannot/getuser api
					 antMatchers("/getuserapi").hasRole("admin").
					//will match /getuserapi/ and not /getuserapi
					 // very important
				anyRequest().authenticated().and().
			 
				
				// make sure we use stateless session; session won't be used to
				// store user's state.
				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.cors(); //critial step to avoid perflight cors errors when both cors and antmatchers are there
		//this enables h2 console freames in local machine
		   httpSecurity.headers().frameOptions().sameOrigin();
		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}

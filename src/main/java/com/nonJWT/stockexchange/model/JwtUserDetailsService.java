package com.nonJWT.stockexchange.model;

import java.util.ArrayList;
//add jwt.secret=abcd to application properties
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.nonJWT.stockexchange.model.*;
@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	Userrepository userrepo;


	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
	       
		com.nonJWT.stockexchange.model.User1 user = new com.nonJWT.stockexchange.model.User1() ;
     
     List<SimpleGrantedAuthority> authorities = new ArrayList<>();
      
     
         authorities.add(new SimpleGrantedAuthority(user.getRole()));
    
      
     return authorities;
 }
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.nonJWT.stockexchange.model.User1 user = userrepo.findByname(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	//non dto code below	//return new org.springframework.security.core.userdetails.User(user.getname(), user.getpassword(),
			//	new ArrayList<>());
		return new UserDetails1(user);//you have to implement userdetails if you dont want to use dto
	}

//implement without dto	public com.stockexchange.phase3.User1 save(UserDto user) {
	public com.nonJWT.stockexchange.model.User1 save(User1 user) {
		com.nonJWT.stockexchange.model.User1 newUser = new com.nonJWT.stockexchange.model.User1();
		//newUser.setname(user.getUsername());
		//newUser.setpassword(bcryptEncoder.encode(user.getPassword()));
	    System.out.println(user.getname() +"Checko");
		
		newUser.setname(user.getname());
	    newUser.setpassword(bcryptEncoder.encode(user.getpassword()));
	    newUser.setemail(user.getemail());
		newUser.setRole(user.getRole());
		return userrepo.save(newUser);
	}





	}
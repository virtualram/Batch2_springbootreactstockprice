package com.nonJWT.stockexchange.model;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import org.springframework.data.repository.CrudRepository;
@Repository
//public interface Userrepository extends JpaRepository<User, Long> {
public interface Userrepository extends JpaRepository<User1, Long>{
	
	User1 findByname(String name);


	Boolean existsByEmail(String email);
	
}

package com.nonJWT.stockexchange.model;





import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;




import java.util.Properties;

import org.apache.tomcat.jni.Buffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin
public class  UserController {
	@Autowired
	Userrepository userrepo;
	

	//@CrossOrigin(origins ="http://localhost:3000")
	@RequestMapping(value = "/getuserapi",method=RequestMethod.GET)	
	public List<User1> reactuserapi(@RequestParam Map<String, String> userobj)  {	
		
		return userrepo.findAll();

	}

	//@CrossOrigin(origins ="http://localhost:3000")
	@RequestMapping(value = "/setuserapi2",method=RequestMethod.POST, headers = "Accept=application/json"  )

	public  ResponseEntity<Object> reactuserapi2(@RequestBody User1 user) {

		User1 usrsaved = userrepo.save(user);
       // make sure your entity class properties of user are in lower case and match the json,to avoid errors
		System.out.println(user +"check this " +usrsaved.getname());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usrsaved.getId())
				.toUri();


		return ResponseEntity.created(location).build();
	}

   //CORs has been defined in main springboot application class






	@RequestMapping(value="/confirmuser/{userid}", method=RequestMethod.GET)
	public String welcomepage(@PathVariable Long userid) {
		Optional<User1> userlist =   userrepo.findById(userid);
		//do a null check for home work
		
		User1 us = userlist.get();
		//usr.setConfirmed(true);
		userrepo.save(us);
		return "User confirmed" +us.getname();
	}

}

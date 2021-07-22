package com.nonJWT.stockexchange.model;
//pass map of string in requestbody ,instead of pojo class to get
	// non entity based params
	// you can also use a POJO instead of map of strings
	//but then you have to add 2 properties to the entity class
	//companycode and exchangename ,so that they can be passed
	// and searched and then the company and exchange properties
	//can be updated
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@RestController
public class Maincontroller {
	@Autowired
	Companyrepository cmprep;
	@Autowired
	Stockrepository stkrep;
	@Autowired
	Companystockexchangemaprepository stkcmpmaprep;
	@Autowired
	EntityManager em;

	//properties defined here can be returned as requestbody for 
	//a restcontroller post call,any additional fields you want to
	//return from the post,you can add properties to this entity
			//and use the requstbody json of this entity
	//otheroption of retyrning valyes instead of request body of entity
	//is to use a map of strings

	@RequestMapping(value = "/company", method = RequestMethod.POST)
	public ResponseEntity<Object> createcompany(@RequestBody Company cmp) {

		cmprep.save(cmp);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cmp.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	//use requestbody as map of strings or pojo entity
	@RequestMapping(value = "/mapcompanycode", method = RequestMethod.POST)
	
	public String mapcode(@RequestBody Map<String, String> text) {
	
		//you will search for company or stockexchange by name and then pass the returned company entity and 
		//assign to field,you will not use id for query or pass it
		System.out.println("params100" + text.get("companyname"));
		Query query = em.createNamedQuery("Company.findByname");
		query.setParameter("name", text.get("companyname"));
		Company c = (Company) query.getSingleResult();

		Stockexchange e = stkrep.findByName(text.get("exchangename"));
		Companystockexchangemap cse = new Companystockexchangemap();
		cse.setCompany(c);
		cse.setStockexchange(e);
		stkcmpmaprep.save(cse);
		return "Test";
		// return companyname;
	}
	
	

	@RequestMapping(value = "/listall", method = RequestMethod.GET)	
	public String listit() {
		
		String x = "";
		List<Companystockexchangemap> csem = stkcmpmaprep.findAll();
		for (Companystockexchangemap c:csem)  {
		Optional<Stockexchange> s =	stkrep.findById(c.getStockexchange().getId()); 
		Optional<Company> cc =cmprep.findById(c.getCompany().getId());
			x= x + "   "+cc.get().getname() + "   "+s.get().getname();
		}
		
		
		
		return x;
		// return companyname;
	}

	
	
	
}

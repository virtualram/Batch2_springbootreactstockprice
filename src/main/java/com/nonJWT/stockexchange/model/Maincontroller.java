package com.nonJWT.stockexchange.model;

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

	

	@RequestMapping(value = "/company", method = RequestMethod.POST)
	public ResponseEntity<Object> createcompany(@RequestBody Company cmp) {

		cmprep.save(cmp);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cmp.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/mapcompanycode", method = RequestMethod.POST)
	// pass map of string in requestbody ,instead of pojo class to get
	// non entity based params
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

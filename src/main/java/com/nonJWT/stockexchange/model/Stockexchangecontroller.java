package com.nonJWT.stockexchange.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class Stockexchangecontroller {
	@Autowired
	Stockrepository stkrep ;
	@RequestMapping(value="/exchange",method=RequestMethod.POST)
	public void createexchange(@RequestBody Stockexchange exch)  {

			stkrep.save(exch);
			//URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(exch.getId())
				//	.toUri();
			
			//return ResponseEntity.created(location).build();
		}

}

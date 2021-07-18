package com.nonJWT.stockexchange.model;



import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@RestController
@CrossOrigin
public class Stockpricecontroller {
@Autowired
Stockpricerepository stkpricerepo;
/*{	"exchangename": "bse",
	"companycode": "TCS",
	
		"datee ": "2014-01-01T23:28:56.782Z",
		"timee" :"10:20:00"  } expected json format*/
	

//@CrossOrigin(origins ="http://reactive01.herokuapp.com")
//@CrossOrigin(origins ="http://localhost:3000")
@RequestMapping(value = "/addstockprices",method=RequestMethod.POST)

public  ResponseEntity<Object> stockpriceapi(@RequestBody Stockprice stockprice) throws ClassNotFoundException, IOException {

	Stockprice stkprice= stkpricerepo.save(stockprice);
   // make sure your entity class properties of price are in lower case and match the json,to avoid errors
	System.out.println(stkprice +"check this " +stkprice.getCompanycode());

	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stkprice.getId())
			.toUri();
	
	return ResponseEntity.created(location).build();
}


//@CrossOrigin(origins ="http://localhost:3000")
@RequestMapping(value = "/getstockprices",method=RequestMethod.GET, headers = "Accept=application/json"  )

public List<Stockprice> getstockprice() throws ClassNotFoundException, IOException {

	List<Stockprice> stkprice= stkpricerepo.findAll();
   // make sure your entity class properties of user are in lower case and match the json,to avoid errors
	return stkprice;
}
}
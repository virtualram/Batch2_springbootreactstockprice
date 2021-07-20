package com.nonJWT.stockexchange.model;



import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@NamedQuery(name = "Company.findByname", query = "SELECT c FROM Company c WHERE c.name = :name")
@Entity
@Table(name = "Company")
//properties defined here can be returned as requestbody for 
//a restcontroller post call,any additional fields you want to
//return from the post,you can add properties to this entity
		//and use the requstbody json of this entity
//otheroption of retyrning valyes instead of request body of entity
//is to use a map of strings
public class Company {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	@OneToMany(targetEntity = Companystockexchangemap.class)
	private List<Companystockexchangemap> compstockmap;
	private String Description;

	
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Company(String name) {
		super();
		this.name =name;
		// TODO Auto-generated constructor stub
	}


	 
	public Company( String name, List<Companystockexchangemap> compstockmap) {
		super();
		
		this.name = name;
		this.compstockmap = compstockmap;
	}

	public Company(String name, String Description) {
		super();

		this.name = name;
		
	}
	
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}


	public List<Companystockexchangemap> getCompstockmap() {
		return compstockmap;
	}

	public void setCompstockmap(List<Companystockexchangemap> compstockmap) {
		this.compstockmap = compstockmap;
	}

	
	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

}

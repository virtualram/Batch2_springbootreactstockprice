package com.nonJWT.stockexchange.model;





import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Stockprice")
public class Stockprice {
	
	@Id
	@GeneratedValue
	private long id;
	private String exchangename;
	private String companycode;
//	private LocalDateTime localdatetime;
	@ManyToOne(fetch = FetchType.LAZY)
	private Company company;
	private Date datee;
//test
    //for local thime formatter is needed otherwise you will get parse error
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")  
	private LocalTime timee;//change this to localtime to time to avoid errors
	private float shareprice;
	public Stockprice() {   
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getExchangename() {
		return exchangename;
	}


	public void setExchangename(String exchangename) {
		this.exchangename = exchangename;
	}


	public String getCompanycode() {
		return companycode;
	}


	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}


	
	

	public Stockprice( String exchangename, String companycode,  
			Date datee, LocalTime timee, float shareprice) {
		super();
	
		this.exchangename = exchangename;
		this.companycode = companycode;
		//this.localdatetime = localdatetime;
		//this.company = company;
		this.datee = datee;
		this.timee= timee;
		this.shareprice = shareprice;
	}
	

	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public Date getDatee() {
		return datee;
	}


	public void setDatee(Date datee) {
		this.datee = datee;
	}


	public LocalTime getTimee() {
		return timee;
	}


	public void setTimee(LocalTime timee) {
		this.timee = timee;
	}


	public float getShareprice() {
		return shareprice;
	}


	public void setShareprice(float shareprice) {
		this.shareprice = shareprice;
	}


	
	
	

}
	
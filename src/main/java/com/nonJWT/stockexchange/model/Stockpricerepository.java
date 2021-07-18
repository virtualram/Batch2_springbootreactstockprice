package com.nonJWT.stockexchange.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
@Repository
public interface Stockpricerepository extends JpaRepository<Stockprice,Long>{
  
}

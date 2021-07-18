package com.nonJWT.stockexchange.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Companyrepository  extends JpaRepository<Company,Long> {
   Company  findByName(String Name);
}

package com.sid.demo.flagservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sid.demo.flagservice.repository.document.ContinentEntity;
import com.sid.demo.flagservice.repository.document.CountryEntity;

public interface CountryRepository extends CrudRepository<CountryEntity, String> {
	
	public Optional<CountryEntity> findByCountryName(String countryName);

}

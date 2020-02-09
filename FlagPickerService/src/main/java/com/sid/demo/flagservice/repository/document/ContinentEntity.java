package com.sid.demo.flagservice.repository.document;

import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection="ContinentEntity")
public class ContinentEntity {
	
	@Id
	private String id;
	
	@Indexed
	private String continentName;
	
	@DBRef
	private Collection<CountryEntity> countries;

	public Collection<CountryEntity> getCountries() {
		return countries;
	}

	public void setCountries(Collection<CountryEntity> countries) {
		this.countries = countries;
	}

	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ContinentEntity withId(String id){
		this.setId(id);
		return this;
	}
	
	public ContinentEntity withContinentName(String countinentName){
		this.setContinentName(countinentName);
		return this;
	}
	
	public ContinentEntity withCountries(Collection<CountryEntity> countries){
		this.setCountries(countries);
		return this;
	}

}

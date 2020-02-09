package com.sid.demo.flagservice.model;

import java.util.List;

public class ContinentDTO {

	private String name;
	
	private List<CountryDTO> countries;
	
	public List<CountryDTO> getCountries() {
		return countries;
	}

	public void setCountries(List<CountryDTO> countries) {
		this.countries = countries;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ContinentDTO withName(String name){
		this.setName(name);
		return this;
	}
	
	public ContinentDTO withCountries(List<CountryDTO> countries){
		this.setCountries(countries);
		return this;
	}
}

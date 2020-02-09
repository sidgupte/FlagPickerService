package com.sid.demo.flagservice.repository.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="CountryEntity")
public class CountryEntity {

	@Id
	private String id;
	@Indexed
	private String countryName;
	
	private String flag;


	public CountryEntity withFlag(String flag){
		this.setFlag(flag);
		return this;
	}
	

	

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	
	
	public CountryEntity withCountryName(String countryName){
		this.setCountryName(countryName);
		return this;
	}
	
}

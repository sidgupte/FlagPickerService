package com.sid.demo.flagservice.model;

public class CountryDTO {
	
	private String name;
	
	private String continentName;

	private String flag;
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}
	
	public CountryDTO withContinentName(String continentName){
		this.setContinentName(continentName);
		return this;
	}	
	
	public CountryDTO withName(String name){
		this.setName(name);
		return this;
	}
	
	
	public CountryDTO withFlag(String flag){
		this.setFlag(flag);
		return this;
	}

}

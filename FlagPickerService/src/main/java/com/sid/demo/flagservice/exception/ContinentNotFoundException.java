package com.sid.demo.flagservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (code = HttpStatus.NOT_FOUND)
public class ContinentNotFoundException extends RuntimeException{

	private String continentName;
	
	public ContinentNotFoundException(String continentName){
		super("Continent not found");
		this.continentName = continentName;
	}
	
	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

}

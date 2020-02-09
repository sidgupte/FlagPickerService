package com.sid.demo.flagservice.utils;

import java.nio.charset.StandardCharsets;

import com.sid.demo.flagservice.model.CountryDTO;
import com.sid.demo.flagservice.repository.document.CountryEntity;

public class CountryConverter {
	
	public static CountryDTO convert(CountryEntity entity, String continentName){
		return new CountryDTO().withName(entity.getCountryName())
				.withFlag(entity.getFlag()).withContinentName(continentName);
	}
	
	public static CountryEntity convert(CountryDTO country){
		return new CountryEntity().withCountryName(country.getName())
				.withFlag(country.getFlag());
	}

}

package com.sid.demo.flagservice.controllers;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sid.demo.flagservice.model.ContinentDTO;
import com.sid.demo.flagservice.model.CountryDTO;
import com.sid.demo.flagservice.service.FlagPickerService;

@RestController
public class FlagPickerServiceController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	private FlagPickerService flagService;
	
	@Autowired
	public FlagPickerServiceController(FlagPickerService flagService){
		this.flagService = flagService;
	}
	
	
	@GetMapping("/continent")
	public ResponseEntity getAllContinents(){
		logger.info("Retrieving all continents !!");
		List<ContinentDTO> continents = flagService.getAllContinents();
		return ResponseEntity.status(HttpStatus.OK).body(continents);
	}
	
	@GetMapping("/continent/{continent-name}")
	public ResponseEntity getCountryByContinent(@NotEmpty @PathVariable("continent-name") String continentName){
		logger.info("Retrieving all countries for continent={} !!", continentName);
		List<CountryDTO> countries = flagService.getCountriesByContinent(continentName);
		return ResponseEntity.status(HttpStatus.OK).body(countries);
	}
	
	@GetMapping("/country/{country-name}")
	public ResponseEntity getCountryByName(@NotEmpty @PathVariable("country-name") String countryName){
		logger.info("Retrieving country={} !!", countryName);
		return ResponseEntity.status(HttpStatus.OK).body(flagService.getCountryByName(countryName));
	}
	
	@GetMapping("/continent/deleteAll")
	public ResponseEntity deleteAll(){
		flagService.deleteAll();
		return ResponseEntity.status(HttpStatus.OK).body("deleted");
	}
	
	
}

package com.sid.demo.flagservice.controllers;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sid.demo.flagservice.metrics.MetricsCounter;
import com.sid.demo.flagservice.service.MetricsService;

@RestController
public class MetricsController {
	
	@Autowired
	private MetricsService service;

	@GetMapping("/metrics/continent/{continentName}")
	public ResponseEntity getMetricsForContinent(@NotEmpty @PathVariable("continentName") String continentName){
		return ResponseEntity.status(HttpStatus.OK).body(MetricsCounter.getCounter(continentName));
	}
	
	@GetMapping("/metrics/country/{countryName}")
	public ResponseEntity getMetricsForCountry(@NotEmpty @PathVariable("countryName") String countryName){
		return ResponseEntity.status(HttpStatus.OK).body(MetricsCounter.getCounter(countryName));
	}
}

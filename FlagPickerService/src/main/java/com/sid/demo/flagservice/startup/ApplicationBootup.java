package com.sid.demo.flagservice.startup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.sid.demo.flagservice.metrics.MetricsCounter;
import com.sid.demo.flagservice.model.ContinentDTO;
import com.sid.demo.flagservice.model.CountryDTO;
import com.sid.demo.flagservice.service.FlagPickerService;

@Component
public class ApplicationBootup implements ApplicationListener<ApplicationReadyEvent> {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FlagPickerService flagPickerService;
	
	private List<String> keys = new ArrayList<String>();

	 @Override
	  public void onApplicationEvent(final ApplicationReadyEvent event) {
	 
		 try {
			 File resource = new ClassPathResource("continents.json.txt").getFile();
			 JSONParser jsonParser = new JSONParser();
			
			 String jsonFileText = new String(Files.readAllBytes(resource.toPath()), "UTF-8");
			 logger.info("Read file continents.json.txt from classpath, now parsing it");
			 
			 JSONArray continentsArr = (JSONArray) jsonParser.parse(jsonFileText);
			 logger.info("Parsed the file, found {} continents, saving them", continentsArr.size());
			 List<ContinentDTO> continents = (List<ContinentDTO>) continentsArr.stream().map(jc -> extractContinent(jc)).collect(Collectors.toList());
			 
			 MetricsCounter.initializeCounts(keys);
			 continents.forEach(continent -> flagPickerService.saveContinent(continent));
		 }
		 catch (IOException ie){
			 logger.error("Unable to read file continents.json");
		 }
		 catch (ParseException pe){
			 logger.error("Error Parsing file continents.json");
		 }
		
	 }
	 
	 private ContinentDTO extractContinent(Object continentObj){
		 
		 JSONObject jsonContinent = (JSONObject) continentObj;		 
		 String continentName = (String)jsonContinent.get("continent");		
		 keys.add(continentName);
		 JSONArray jsonCountries = (JSONArray) jsonContinent.get("countries");		 
		 List<CountryDTO> countries = (List<CountryDTO>) jsonCountries.stream().map(jc -> extractCountry(jc)).collect(Collectors.toList());
		 
		 return new ContinentDTO().withName(continentName).withCountries(countries);
	 }
	 
	 private CountryDTO extractCountry(Object countryObj){
		 String flag  = ((JSONObject)countryObj).get("flag").toString();
		 String name = (String)((JSONObject)countryObj).get("name");
		 keys.add(name);
		 
		 return new CountryDTO().withFlag(flag)
				 .withName(name);
	 }
}


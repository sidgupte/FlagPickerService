package com.sid.demo.flagservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sid.demo.flagservice.exception.ContinentNotFoundException;
import com.sid.demo.flagservice.exception.CountryNotFoundException;
import com.sid.demo.flagservice.model.ContinentDTO;
import com.sid.demo.flagservice.model.CountryDTO;
import com.sid.demo.flagservice.repository.ContinentRepository;
import com.sid.demo.flagservice.repository.CountryRepository;
import com.sid.demo.flagservice.repository.document.ContinentEntity;
import com.sid.demo.flagservice.repository.document.CountryEntity;
import com.sid.demo.flagservice.utils.ContinentConverter;
import com.sid.demo.flagservice.utils.CountryConverter;

@Service
public class FlagPickerService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	private CountryRepository countryRepo;
	
	
	private ContinentRepository continentRepo;
	
	@Autowired
	public FlagPickerService(CountryRepository countryRepo, ContinentRepository continentRepo){
		
		this.countryRepo = countryRepo;
		this.continentRepo = continentRepo;
	}
	
	public List<ContinentDTO> getAllContinents(){	
		logger.info("Retrieving all continents from Repository !!");
		return continentRepo.findAll().stream().map(e -> ContinentConverter.convert(e)).collect(Collectors.toList());
	}
	
	public List<CountryDTO> getCountriesByContinent(String name){
		logger.info("Retrieving all countries for continent={} from Repository !!", name);
		Optional<ContinentEntity> entity = continentRepo.findByContinentName(name);
		entity.orElseThrow(()-> new ContinentNotFoundException(name));
		logger.info("Found {} countries for continent={}", entity.get().getCountries().size(), name);
		return entity.get().getCountries().stream().map(e -> CountryConverter.convert(e, name)).collect(Collectors.toList());
	}
	
	public CountryDTO getCountryByName (String name){
		logger.info("Trying to find country={} in the Repository", name);
		Optional<CountryEntity> entity = countryRepo.findByCountryName(name);
		entity.orElseThrow(() -> new CountryNotFoundException(name));
		logger.info(" Found country={} with flag={}", name, entity.get().getFlag());
		return CountryConverter.convert(entity.get(), "");
	}
	
	private List<CountryEntity> saveCountries(List<CountryDTO> countries){
		
		List<CountryEntity> countryEntities  = countries.stream().map(c -> CountryConverter.convert(c)).collect(Collectors.toList());
		countryRepo.saveAll(countryEntities);
		return countryEntities;
	}
	
	public void saveContinent(ContinentDTO continent){
		
		Optional<ContinentEntity> existingContinent = continentRepo.findByContinentName(continent.getName());
		if (!existingContinent.isPresent()) {
			ContinentEntity continentEntity = ContinentConverter.convert(continent);
			logger.info("Saving {} countries for the continent={}", continent.getCountries().size(), continent.getName());
			continentEntity.withCountries(saveCountries(continent.getCountries()));
			continentRepo.save(continentEntity);
		}
	}
	
	public void deleteAll(){
		countryRepo.deleteAll();
		continentRepo.deleteAll();
	}

}

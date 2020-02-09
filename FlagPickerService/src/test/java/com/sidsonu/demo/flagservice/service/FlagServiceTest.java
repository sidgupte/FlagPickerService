package com.sidsonu.demo.flagservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sid.demo.flagservice.exception.ContinentNotFoundException;
import com.sid.demo.flagservice.exception.CountryNotFoundException;
import com.sid.demo.flagservice.model.ContinentDTO;
import com.sid.demo.flagservice.model.CountryDTO;
import com.sid.demo.flagservice.repository.ContinentRepository;
import com.sid.demo.flagservice.repository.CountryRepository;
import com.sid.demo.flagservice.repository.document.ContinentEntity;
import com.sid.demo.flagservice.repository.document.CountryEntity;
import com.sid.demo.flagservice.service.FlagPickerService;
import com.sid.demo.flagservice.utils.ContinentConverter;
import com.sid.demo.flagservice.utils.CountryConverter;

import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
public class FlagServiceTest {
	
	private FlagPickerService service;
	
	@Mock
	private CountryRepository countryRepo;
	
	@Mock
	private ContinentRepository continentRepo;
	
	@Before
	public void setUp(){
		service = new FlagPickerService(countryRepo, continentRepo);
	}
	
	@Test
	public void getAllContinentsTest (){
		ContinentEntity entity = new ContinentEntity().withContinentName("America");
		List<ContinentEntity> entities = new ArrayList<ContinentEntity>();
		entities.add(entity);
		Mockito.when(continentRepo.findAll()).thenReturn(entities);
		List<ContinentDTO> dtos = service.getAllContinents();
		Assert.assertNotNull(dtos);
		Assert.assertEquals(1, dtos.size());
		Assert.assertEquals("America", dtos.get(0).getName());
	}
	
	@Test
	public void getCountriesByContinentSucceedsTest(){
		CountryEntity entity = new CountryEntity().withCountryName("USA").withFlag("US");
		
		List<CountryEntity> entities = new ArrayList<CountryEntity>();
		entities.add(entity);
		ContinentEntity continent = new ContinentEntity().withCountries(entities);
	
		Mockito.when(continentRepo.findByContinentName("America")).thenReturn(Optional.of(continent));
		List<CountryDTO> dtos = service.getCountriesByContinent("America");
		Assert.assertNotNull(dtos);
		Assert.assertEquals(1, dtos.size());
		Assert.assertEquals("USA", dtos.get(0).getName());
		Assert.assertEquals("US", dtos.get(0).getFlag());
	}
	
	@Test (expected = ContinentNotFoundException.class)
	public void getCountriesByContinentFailureTest(){
		Mockito.when(continentRepo.findByContinentName("America")).thenReturn(Optional.ofNullable(null));
		List<CountryDTO> dtos = service.getCountriesByContinent("America");
		Assert.assertNotNull(dtos);
		Assert.assertEquals(1, dtos.size());
		Assert.assertEquals("USA", dtos.get(0).getName());
		Assert.assertEquals("US", dtos.get(0).getFlag());
	}
	
	@Test
	public void getCountryByNameSucceedsTest(){
		
		CountryEntity entity = new CountryEntity().withCountryName("USA").withFlag("US");
		Mockito.when(countryRepo.findByCountryName("USA")).thenReturn(Optional.of(entity));
		CountryDTO dto = service.getCountryByName("USA");
		Assert.assertNotNull(dto);
		Assert.assertEquals("USA", dto.getName());
		Assert.assertEquals("US", dto.getFlag());
		
	}
	
	@Test (expected=CountryNotFoundException.class)
	public void getCountryByNameFailureTest(){
		
		CountryEntity entity = new CountryEntity().withCountryName("USA").withFlag("US");
		Mockito.when(countryRepo.findByCountryName("USA")).thenReturn(Optional.ofNullable(null));
		CountryDTO dto = service.getCountryByName("USA");
		Assert.assertNotNull(dto);
		Assert.assertEquals("USA", dto.getName());
		Assert.assertEquals("US", dto.getFlag());
	}
	
	@Test
	public void saveContinentTest(){
		
		CountryEntity entity = new CountryEntity().withCountryName("USA").withFlag("US");
		
		List<CountryEntity> entities = new ArrayList<CountryEntity>();
		entities.add(entity);
		ContinentEntity continent = new ContinentEntity().withCountries(entities);
	
		Mockito.when(continentRepo.findByContinentName("America")).thenReturn(Optional.of(continent));
		
		Mockito.verifyZeroInteractions(countryRepo);
	}
	
}

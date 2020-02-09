package com.sidsonu.demo.flagservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sid.demo.flagservice.controllers.FlagPickerServiceController;
import com.sid.demo.flagservice.exception.ContinentNotFoundException;
import com.sid.demo.flagservice.exception.CountryNotFoundException;
import com.sid.demo.flagservice.model.CountryDTO;
import com.sid.demo.flagservice.service.FlagPickerService;

@RunWith(SpringJUnit4ClassRunner.class)
public class FlagPickerServiceControllerTest {
	
	@Mock
	private FlagPickerService service;
	
	private FlagPickerServiceController controller;
	
	@Before
	public void setUp(){
		controller = new FlagPickerServiceController(service);
	}
	
	@Test
	public void testCountrySyccessful(){
		CountryDTO dto = new CountryDTO().withContinentName("America")
				.withFlag("US").withName("USA");
		Mockito.when(service.getCountryByName("USA")).thenReturn(dto);
		ResponseEntity response = controller.getCountryByName("USA");
		
		CountryDTO actualDTO = (CountryDTO)response.getBody();
		
		Assert.assertNotNull(actualDTO);
		Assert.assertEquals("USA", actualDTO.getName());
		Assert.assertEquals("US", actualDTO.getFlag());
	}
	
	@Test
	public void testContinentSyccessful(){
		CountryDTO dto = new CountryDTO().withContinentName("America")
				.withFlag("US").withName("USA");
		List<CountryDTO> dtos = new ArrayList<CountryDTO>();
		dtos.add(dto);
		Mockito.when(service.getCountriesByContinent("America")).thenReturn(dtos);
		ResponseEntity response = controller.getCountryByContinent("America");
		
		List<CountryDTO> actualDTOs = (List<CountryDTO>)response.getBody();
		
		Assert.assertNotNull(actualDTOs);
		Assert.assertEquals(1, actualDTOs.size());
		Assert.assertEquals("USA", actualDTOs.get(0).getName());
		Assert.assertEquals("US", actualDTOs.get(0).getFlag());
	}
	
	@Test (expected = ContinentNotFoundException.class)
	public void testContinentFailurel(){
		CountryDTO dto = new CountryDTO().withContinentName("America")
				.withFlag("US").withName("USA");
		List<CountryDTO> dtos = new ArrayList<CountryDTO>();
		dtos.add(dto);
		Mockito.when(service.getCountriesByContinent("America")).thenThrow(ContinentNotFoundException.class);
		ResponseEntity response = controller.getCountryByContinent("America");
		
		List<CountryDTO> actualDTOs = (List<CountryDTO>)response.getBody();
		
		Assert.assertNotNull(actualDTOs);
		Assert.assertEquals(1, actualDTOs.size());
		Assert.assertEquals("USA", actualDTOs.get(0).getName());
		Assert.assertEquals("US", actualDTOs.get(0).getFlag());
	}
	
	@Test (expected = CountryNotFoundException.class)
	public void testCountryFailurel(){
		CountryDTO dto = new CountryDTO().withContinentName("America")
				.withFlag("US").withName("USA");
		Mockito.when(service.getCountryByName("USA")).thenThrow(CountryNotFoundException.class);
		ResponseEntity response = controller.getCountryByName("USA");
		
		CountryDTO actualDTO = (CountryDTO)response.getBody();
		
		Assert.assertNotNull(actualDTO);
		Assert.assertEquals("USA", actualDTO.getName());
		Assert.assertEquals("US", actualDTO.getFlag());
	}

}

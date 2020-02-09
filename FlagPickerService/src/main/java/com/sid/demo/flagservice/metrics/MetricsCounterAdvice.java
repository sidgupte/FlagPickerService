package com.sid.demo.flagservice.metrics;

import java.util.ArrayList;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sid.demo.flagservice.model.CountryDTO;

@Aspect
@Component
public class MetricsCounterAdvice {

	
    @Pointcut(value= "execution(* com.sid.demo.flagservice.controllers.FlagPickerServiceController.getCountryByContinent(..))")
    private void updateMetricsCounterPointcutForContinent() { 
    	
    }
    @Pointcut(value= "execution(* com.sid.demo.flagservice.controllers.FlagPickerServiceController.getCountryByName(..))")
    private void updateMetricsCounterPointcutForCountry() { 
    	
    }
    
    @AfterReturning(value = "updateMetricsCounterPointcutForContinent()", returning = "entity")
    public void updateMetricsCounterContinent(JoinPoint jp, Object entity) throws Throwable {
       ResponseEntity response = (ResponseEntity) entity;
       ArrayList<CountryDTO> countries = (ArrayList<CountryDTO>)response.getBody();
       if (!CollectionUtils.isEmpty(countries)){
    	   MetricsCounter.add(countries.get(0).getContinentName());
       }
    }
    
    @AfterReturning(value = "updateMetricsCounterPointcutForCountry()", returning = "entity")
    public void updateMetricsCounterCountry(JoinPoint jp, Object entity) throws Throwable {
       ResponseEntity response = (ResponseEntity) entity;
       CountryDTO country = (CountryDTO) response.getBody();
       if (country != null){
    	   MetricsCounter.add(country.getName());
       }
    }
 
}

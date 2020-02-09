package com.sid.demo.flagservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sid.demo.flagservice.repository.MetricsRepository;
import com.sid.demo.flagservice.repository.document.MetricsEntity;

@Service
public class MetricsService {
	
	@Autowired
	private MetricsRepository metricsRepo;

	public int getMetricsByKey(String key){
		return metricsRepo.findByKey(key).isPresent() ? metricsRepo.findByKey(key).get().getValue() : 0;
	}
	
	public void updateMetrics(String key){
		Optional<MetricsEntity> entityOptional = metricsRepo.findByKey(key);
		MetricsEntity entity;
		if(entityOptional.isPresent()){
			int value = entityOptional.get().getValue();
			value+=1;
			entityOptional.get().setValue(value);
			entity = entityOptional.get();
		}
		else {
			entity = new MetricsEntity().withKey(key)
					.withValue(1);
		}
		
		metricsRepo.save(entity);
	}
}

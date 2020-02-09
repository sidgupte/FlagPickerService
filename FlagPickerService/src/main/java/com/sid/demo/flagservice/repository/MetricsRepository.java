package com.sid.demo.flagservice.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sid.demo.flagservice.repository.document.MetricsEntity;

public interface MetricsRepository extends CrudRepository<MetricsEntity, String>{
	
	Optional<MetricsEntity> findByKey(String key);

}

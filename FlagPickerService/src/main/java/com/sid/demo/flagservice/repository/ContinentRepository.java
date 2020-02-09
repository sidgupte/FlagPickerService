package com.sid.demo.flagservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sid.demo.flagservice.repository.document.ContinentEntity;

public interface ContinentRepository extends CrudRepository<ContinentEntity, String> {
	
	public List<ContinentEntity> findAll();
	
	public Optional<ContinentEntity> findByContinentName();
	
	public Optional<ContinentEntity> findByContinentName(String name);

}

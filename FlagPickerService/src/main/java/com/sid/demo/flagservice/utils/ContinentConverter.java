package com.sid.demo.flagservice.utils;

import com.sid.demo.flagservice.model.ContinentDTO;
import com.sid.demo.flagservice.repository.document.ContinentEntity;

public class ContinentConverter {
	
	public static ContinentDTO convert(ContinentEntity entity){
		return new ContinentDTO().withName(entity.getContinentName());
	}
	
	public static ContinentEntity convert(ContinentDTO continent){
		return new ContinentEntity().withContinentName(continent.getName());
	}

}

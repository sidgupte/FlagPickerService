package com.sid.demo.flagservice.repository.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Metrics")
public class MetricsEntity {
	
	@Id
	private String id;
	
	private String key;
	
	private int value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public MetricsEntity withKey(String key){
		this.setKey(key);
		return this;
	}
	
	public MetricsEntity withValue(int value){
		this.setValue(value);
		return this;
	}

}

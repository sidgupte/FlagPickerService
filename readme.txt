
Application Prerequisites

1) Solution implemented using MongoDB as data store with Spring Data MongoDB
2) Appliations needs to have a local MongoDB running
3) MongoDB config in application.properties in ./src/main/resources
4) The Application needs continents.json.txt to be presend under ./src/main/resources as it treats it as a ClassPath resource and ingests it into MongoDB.


Application endpoints when run locally

1) Flag Picker endpoints are 
	http://localhost:8080/country/{countryName}
	http://localhost:8080/continent/{continentName}
	
2) Metrics endpoints are

	http://localhost:8080/metrics/country/{countryName}
	http://localhost:8080/metrics/continent/{continentName}
	
3) Management endpoints are
	http://localhost:8080/flagPickerService/
	
	
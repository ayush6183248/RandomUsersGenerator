package com.nagarro.randomuserprofilefetcher.service;

import java.util.*;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.randomuserprofilefetcher.model.RandomUserResponse;


public class NationalityFetcherApiService implements Callable<Set<String>> {

	private Logger logger = LoggerFactory.getLogger(NationalityFetcherApiService.class);
	
	private final WebClient apiWebClient;
	private final String name;
	
	
	
	public NationalityFetcherApiService(String name, WebClient apiWebClient) {
		this.name = name;
		this.apiWebClient = apiWebClient;
		
	}

	@Override
	public Set<String> call() throws Exception {
		
		String jsonString = apiWebClient.get()
    			.uri(uriBuilder -> uriBuilder
    					.queryParam("name", name)
    					.build())
    			.retrieve()
    			.bodyToMono(String.class)
    			.block();

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> jsonData = objectMapper.readValue(jsonString, Map.class);
    	
    	Set<String> countryIdsSet = new HashSet<>();
    	((List<Object>) jsonData.get("country"))
    	    .stream()
    	    .map(countryObj -> (Map<String, Object>) countryObj)
    	    .map(countryData -> (String) countryData.get("country_id"))
    	    .forEach(countryIdsSet::add);	
    	
    	logger.info(""+countryIdsSet);
    	
    	return countryIdsSet;

	}
	
	
	

}

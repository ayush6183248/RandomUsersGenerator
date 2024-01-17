package com.nagarro.randomuserprofilefetcher.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GenderFetcherApiService implements Callable<String>{

	private Logger logger = LoggerFactory.getLogger(GenderFetcherApiService.class);

	private final WebClient apiWebClient;
	private final String name;
	
	
	public GenderFetcherApiService(String name, WebClient apiWebClient) {
		this.name = name;
		this.apiWebClient = apiWebClient;
	}

	@Override
	public String call() throws Exception {
		
		String jsonString = apiWebClient.get()
    			.uri(uriBuilder -> uriBuilder
    					.queryParam("name", name)
    					.build())
    			.retrieve()
    			.bodyToMono(String.class)
    			.block();

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> jsonData = objectMapper.readValue(jsonString, Map.class);
		
		logger.info(jsonString);
		    	return (String) jsonData.get("gender");

	}
}


package com.nagarro.randomuserprofilefetcher.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.randomuserprofilefetcher.model.RandomUser;
import com.nagarro.randomuserprofilefetcher.model.RandomUserResponse;

import reactor.core.publisher.Mono;


public class RandomUserFetcherApiService {
	
	private Logger logger = LoggerFactory.getLogger(RandomUserFetcherApiService.class);
	
	private final WebClient apiWebClient;
	
    
	public RandomUserFetcherApiService(WebClient apiWebClient) {
		this.apiWebClient = apiWebClient;
	}
    
    public RandomUserResponse getRandomUsers() throws JsonMappingException, JsonProcessingException{
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	String jsonString = 
    			apiWebClient.get()
    			.retrieve()
    			.bodyToMono(String.class)
    			.block();
    	
    	logger.info("Got RandomUser");
    	
    	return objectMapper.readValue(jsonString, RandomUserResponse.class);
    	
    }
	
	
	
	
	

}

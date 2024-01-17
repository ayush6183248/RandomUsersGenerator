package com.nagarro.randomuserprofilefetcher.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.nagarro.randomuserprofilefetcher.model.GetResponseModel;
import com.nagarro.randomuserprofilefetcher.model.RandomUser;
import com.nagarro.randomuserprofilefetcher.model.UserRequestParams;
import com.nagarro.randomuserprofilefetcher.service.MainRandomUserFetcherService;
import com.nagarro.randomuserprofilefetcher.service.RandomUserProfileFetcherServiceImplementaion;
import com.nagarro.randomuserprofilefetcher.validators.Validator;
import com.nagarro.randomuserprofilefetcher.validators.ValidatorInstancesFactory;
import com.nagarro.randomuserprofilefetcher.constants.*;
import com.nagarro.randomuserprofilefetcher.entities.RandomUsersInfo;

@RestController
@RequestMapping("/users")
@Validated
public class RandomUserProfileController {
	
//	private static final int ResponseEntity = 0;


	private final MainRandomUserFetcherService randomUserFetcherService;

	
    private Logger logger = LoggerFactory.getLogger(RandomUserProfileController.class);
	

	
	@Autowired
	public RandomUserProfileController(MainRandomUserFetcherService randomUserFetcherService) {
		this.randomUserFetcherService = randomUserFetcherService;
	}
	

    @PostMapping
    public ResponseEntity<List<RandomUsersInfo>> createUser(@RequestParam(defaultValue = "1") String size) {
    	logger.info("Inside Post request");
    	
//    	Validator validator = ValidatorInstancesFactory.createValidator(size);
//    	if(!validator.validate(ValidationConstants.SIZE, size)) {
//    		throw new InvalidParameterException(validator.getErrorMessage());
//    	}
    	validateParameters(ValidationConstants.SIZE,size);
    	return new ResponseEntity<List<RandomUsersInfo>>(randomUserFetcherService.randomProfileFetcher(Integer.parseInt(size)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<GetResponseModel> getUsers(@Valid UserRequestParams userRequestParams) {
    	
    	logger.info(userRequestParams.toString());
    	

    	return new ResponseEntity<GetResponseModel>(randomUserFetcherService.getRandomUsers(
    			Integer.parseInt(userRequestParams.getLimit()),
    			Integer.parseInt(userRequestParams.getOffset()),
    			userRequestParams.getSortType(),
    			userRequestParams.getSortOrder()) , HttpStatus.OK);
    }
    
    
    private boolean validateParameters(String paramName, String value) {
    	
    	return ValidatorInstancesFactory
    			.createValidator(paramName, value)
    			.validate(paramName, value);
    }
    
    

    

}









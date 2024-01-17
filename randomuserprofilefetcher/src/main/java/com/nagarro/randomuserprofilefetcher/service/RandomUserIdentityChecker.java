package com.nagarro.randomuserprofilefetcher.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nagarro.randomuserprofilefetcher.model.RandomUserResponse;


public class RandomUserIdentityChecker {
	
	private static Logger logger = LoggerFactory.getLogger(RandomUserIdentityChecker.class);

	
//	private RandomUserResponse randomUserIdentityInfo;
//	private Set<String> nationalitySet;
//	private String gender;
//	
//	public RandomUserIdentityChecker(RandomUserResponse randomUserIdentityInfo, Set<String> nat, String gender) {
//		this.randomUserIdentityInfo = randomUserIdentityInfo;
//		this.nationalitySet = nat;
//		this.gender = gender;
//	}
	
	public static boolean profileChecker(
			RandomUserResponse randomUserIdentityInfo,
			Set<String> nationalitySet,
			String gender) {
		
		logger.info("Entered Checker");
		
		if(randomUserIdentityInfo !=null && randomUserIdentityInfo.getResults() !=null && !randomUserIdentityInfo.getResults().isEmpty()
				&& nationalitySet!= null && gender != null) {
			RandomUserResponse.UserResult userResult = randomUserIdentityInfo.getResults().get(0);
			
			if(userResult != null && userResult.getName() != null) {
				
				String userNationality = userResult.getNat();
				String userGender = userResult.getGender();
				logger.info("UserNationality from API 1: "+userNationality);
				logger.info("NationalitySet from API 2: "+nationalitySet);
				logger.info("Gender from API 1: " + userGender);
				logger.info("Gender from API 3: " + gender);

				if(nationalitySet.contains(userNationality) && userGender.equalsIgnoreCase(gender)) {
					logger.info("Profile info: VERIFIED");
					return true;
			}
		}
		
		}
		
		logger.info("Profile info: TO_BE_VERIFIED");
		return false;
	}
	
	
	
	
	
	

}

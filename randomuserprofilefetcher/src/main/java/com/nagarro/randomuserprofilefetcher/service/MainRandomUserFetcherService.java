package com.nagarro.randomuserprofilefetcher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.randomuserprofilefetcher.entities.RandomUsersInfo;
import com.nagarro.randomuserprofilefetcher.model.GetResponseModel;
import com.nagarro.randomuserprofilefetcher.model.RandomUser;

@Service
public class MainRandomUserFetcherService {
	
	private final RandomUserProfileFetcherService randomUserFetcherService;

	@Autowired
	public MainRandomUserFetcherService(RandomUserProfileFetcherService randomUserFetcherService) {
		this.randomUserFetcherService = randomUserFetcherService;
	}
	
	public List<RandomUsersInfo> randomProfileFetcher(int size){
		return randomUserFetcherService.createRandomUsersProfile(size);
	}
	
	public GetResponseModel getRandomUsers(int limit, int offset, String sortType, String sortOrder) {
		return randomUserFetcherService.getRandomUserProfile(limit, offset, sortType, sortOrder);
	}
	
	

}

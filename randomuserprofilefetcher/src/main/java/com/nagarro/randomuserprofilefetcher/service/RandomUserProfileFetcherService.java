package com.nagarro.randomuserprofilefetcher.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nagarro.randomuserprofilefetcher.entities.RandomUsersInfo;
import com.nagarro.randomuserprofilefetcher.model.GetResponseModel;
import com.nagarro.randomuserprofilefetcher.model.RandomUser;

public interface RandomUserProfileFetcherService {
	
	public List<RandomUsersInfo> fetchRandomUser(int size);
	
	public List<RandomUsersInfo> createRandomUsersProfile(int size);
	
	public List<RandomUsersInfo> saveRandomUsersProfile(List<RandomUsersInfo> latestRandomUsersList);
	
	public GetResponseModel getRandomUserProfile(int limit, int offset, String sortType, String sortOrder);
	
	public List<RandomUsersInfo> getUsers(int limit, int offset);
	
	public long getTotalRandomUsers();

}

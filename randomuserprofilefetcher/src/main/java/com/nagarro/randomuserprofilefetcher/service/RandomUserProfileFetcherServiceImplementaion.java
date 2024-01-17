package com.nagarro.randomuserprofilefetcher.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nagarro.randomuserprofilefetcher.constants.ValidationConstants;
import com.nagarro.randomuserprofilefetcher.entities.RandomUsersInfo;
import com.nagarro.randomuserprofilefetcher.model.GetResponseModel;
import com.nagarro.randomuserprofilefetcher.model.PageInfo;
import com.nagarro.randomuserprofilefetcher.model.RandomUser;
import com.nagarro.randomuserprofilefetcher.repository.RandomUsersInformationRepository;
import com.nagarro.randomuserprofilefetcher.sortingStrategyHandlers.AgeEvenlySorter;
import com.nagarro.randomuserprofilefetcher.sortingStrategyHandlers.AgeOddlySorter;
import com.nagarro.randomuserprofilefetcher.sortingStrategyHandlers.NameLengthEvenlySorter;
import com.nagarro.randomuserprofilefetcher.sortingStrategyHandlers.NameLengthOddlySorter;
import com.nagarro.randomuserprofilefetcher.sortingStrategyHandlers.RandomUsersSorter;
import com.nagarro.randomuserprofilefetcher.sortingStrategyHandlers.SortStrategy;

@Service
public class RandomUserProfileFetcherServiceImplementaion implements RandomUserProfileFetcherService {

	
	private Logger logger = LoggerFactory.getLogger(RandomUserProfileFetcherServiceImplementaion.class);
//	private final WebClient randomUserApiWebClient;
//    private final WebClient nationalityApiWebClient;
//    private final WebClient genderApiWebClient;
//    
//
//    @Autowired
//    public RandomUserProfileFetcherServiceImplementaion(
//            @Qualifier("api1WebClient") WebClient randomUserApiWebClient,
//            @Qualifier("api2WebClient") WebClient nationalityApiWebClient,
//            @Qualifier("api3WebClient") WebClient genderApiWebClient) {
//    	this.randomUserApiWebClient = randomUserApiWebClient;
//    	this.nationalityApiWebClient = nationalityApiWebClient;
//    	this.genderApiWebClient = genderApiWebClient;
//        
//    }
	
	private final ApiTaskHandlerService apiTaskExecutorService;
	private final RandomUsersInformationRepository randomUsersInformationRepository;
	
    @Autowired
	public RandomUserProfileFetcherServiceImplementaion(
			ApiTaskHandlerService apiTaskExecutorService,
			RandomUsersInformationRepository randomUsersInformationRepository) {
    	

	this.apiTaskExecutorService = apiTaskExecutorService;
	this.randomUsersInformationRepository = randomUsersInformationRepository;
}


	@Override
	public List<RandomUsersInfo> fetchRandomUser(int size) {
		List<RandomUsersInfo> randomUsersList = new ArrayList<>();
		
		for(int i = 1; i<=size; i++) {
			try {
				randomUsersList.add(apiTaskExecutorService.apiTaskHandler());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		logger.info(randomUsersList.toString());
		
		
		return randomUsersList;
	}


	@Override
	public List<RandomUsersInfo> createRandomUsersProfile(int size) {
		List<RandomUsersInfo> randomUsersList = fetchRandomUser(size);
//		if(saveRandomUsersProfile(randomUsersList)== null) {
//			throw new NullPointerException("The list of randomUsers was empty");
//		
//		}
//		else {
//			return randomUsersList;
//		}
		
		List<RandomUsersInfo> savedList = saveRandomUsersProfile(randomUsersList);
		if(savedList == null) {
			throw new NullPointerException("The list of randomUsers was empty");
		}
		else {
			return savedList;
		}
	}


	@Override
	public List<RandomUsersInfo> saveRandomUsersProfile(List<RandomUsersInfo> latestRandomUsersList) {
		
		try {
			return randomUsersInformationRepository.saveAll(latestRandomUsersList);
		}catch(Exception e) {
			logger.error("Error occured while saving list of random users");
			e.printStackTrace();
		}
		return null;
	
	}


	
	@Override
	public GetResponseModel getRandomUserProfile(int limit, int offset, String sortType, String sortOrder) {
		GetResponseModel responseModel = new GetResponseModel();
		
		//Gets the list of use
		List<RandomUsersInfo> listOfRandomUsers = getUsers(limit,offset);
		long total = getTotalRandomUsers();
		logger.error("got list: "+listOfRandomUsers);
		logger.error("total: "+total);
		
		//Sorting as required
		sortRandomUsers(listOfRandomUsers,sortType,sortOrder);
		logger.error("sorted list: "+listOfRandomUsers);

		
		PageInfo pageInfoInstance = new PageInfo();
		pageInfoInstance.setTotal(total);
		pageInfoInstance.setHasNextPage(hasNextPage(limit, offset, total));
		pageInfoInstance.setHasPreviousPage(hasPreviousPage(offset));
        
		responseModel.setData(listOfRandomUsers);
		responseModel.setPageInfo(pageInfoInstance);
		
		
		return responseModel;
	}
	
	


	@Override
	public List<RandomUsersInfo> getUsers(int limit, int offset) {
		logger.error(""+limit+"  "+offset);
//		int newOffset = offset - 1//Since PageRequest follows zero-based indexing
//		Pageable pageable = PageRequest.of(offset, limit); 
		
//		return randomUsersInformationRepository.findAllWithLimitAndOffset(limit, offset);
		return randomUsersInformationRepository.findAllWithLimitAndOffset(offset, limit);
		
	}


	@Override
	public long getTotalRandomUsers() {
		
		return randomUsersInformationRepository.count();
	}
	
	
	
	private boolean hasNextPage(int limit, int offset, long totalElements) {
	  int currentElement = offset + limit;
	  if(currentElement>=(int)totalElements) {
		  return false;
	  }
	  else {
		  return true;
	  }
	}
	
	private boolean hasPreviousPage(int offset) {
		  if(offset==0) {
			  return false;
		  }
		  else {
			  return true;
		  }
		}
	
	private void sortRandomUsers(List<RandomUsersInfo> unsortedRandomUsersList, String sortType, String sortOrder){
		SortStrategy sortStrategy = null;
		 if(sortType.equalsIgnoreCase(ValidationConstants.AGE) && sortOrder.equalsIgnoreCase(ValidationConstants.EVEN)) {
			 sortStrategy = new AgeEvenlySorter();
		 }
		 else if(sortType.equalsIgnoreCase(ValidationConstants.AGE) && sortOrder.equalsIgnoreCase(ValidationConstants.ODD)) {
			 sortStrategy = new AgeOddlySorter();
		 }
		 else if(sortType.equalsIgnoreCase(ValidationConstants.NAME) && sortOrder.equalsIgnoreCase(ValidationConstants.EVEN)) {
			 sortStrategy = new NameLengthEvenlySorter();
		 }
		 else if(sortType.equalsIgnoreCase(ValidationConstants.NAME) && sortOrder.equalsIgnoreCase(ValidationConstants.ODD)) {
			 sortStrategy = new NameLengthOddlySorter();
		 }
		 else {
             throw new IllegalArgumentException("Invalid sort type and sort order values ");
		 }
		 
		 RandomUsersSorter randomUsersSorter = new RandomUsersSorter(sortStrategy);
		 randomUsersSorter.getSorted(unsortedRandomUsersList);
     
		
	}
	
	
	
    
	
}

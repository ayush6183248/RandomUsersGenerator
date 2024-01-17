package com.nagarro.randomuserprofilefetcher.sortingStrategyHandlers;

import java.util.Comparator;
import java.util.List;

import com.nagarro.randomuserprofilefetcher.constants.ValidationConstants;
import com.nagarro.randomuserprofilefetcher.entities.RandomUsersInfo;

public class AgeOddlySorter implements SortStrategy {

	@Override
	public void sortList(List<RandomUsersInfo> RandomUsersInfoList) {
//		Comparator<RandomUsersInfo> comparator = (RandomUsersInfo1, RandomUsersInfo2) -> {
//            int comparison;
//
//            
//            comparison = Integer.compare(RandomUsersInfo1.getAge() % 2, RandomUsersInfo2.getAge() % 2);  
//                
//		  
//            return  -comparison;
//		};
      
		 RandomUsersInfoList.sort((RandomUsersInfo1, RandomUsersInfo2) -> {
	            return -Integer.compare(RandomUsersInfo1.getAge() % 2, RandomUsersInfo2.getAge() % 2);  
	                
			});
	    
	}

}


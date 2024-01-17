package com.nagarro.randomuserprofilefetcher.sortingStrategyHandlers;

import java.util.List;

import com.nagarro.randomuserprofilefetcher.entities.RandomUsersInfo;

public class NameLengthOddlySorter implements SortStrategy {

	@Override
	public void sortList(List<RandomUsersInfo> RandomUsersInfoList) {
		
		RandomUsersInfoList.sort((RandomUsersInfo1, RandomUsersInfo2) -> {
            return -Integer.compare(RandomUsersInfo1.getName().length() % 2, RandomUsersInfo2.getName().length() % 2);

     
		});
		
	}

}

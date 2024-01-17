package com.nagarro.randomuserprofilefetcher.sortingStrategyHandlers;

import java.util.List;

import com.nagarro.randomuserprofilefetcher.entities.RandomUsersInfo;

public class RandomUsersSorter {
	
	private SortStrategy sortStrategy;

	public RandomUsersSorter(SortStrategy sortStrategy) {
		this.sortStrategy = sortStrategy;
	}
	
	public void getSorted(List<RandomUsersInfo> RandomUsersInfoList) {
		sortStrategy.sortList(RandomUsersInfoList);
	}
	
	

}

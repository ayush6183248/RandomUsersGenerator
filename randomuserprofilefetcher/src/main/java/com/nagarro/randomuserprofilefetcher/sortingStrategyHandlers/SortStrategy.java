package com.nagarro.randomuserprofilefetcher.sortingStrategyHandlers;

import java.util.List;

import com.nagarro.randomuserprofilefetcher.entities.RandomUsersInfo;

public interface SortStrategy {
	
	public void sortList(List<RandomUsersInfo> list);

}

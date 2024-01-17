package com.nagarro.randomuserprofilefetcher.model;

import java.util.List;

import com.nagarro.randomuserprofilefetcher.entities.RandomUsersInfo;

public class GetResponseModel {
	
	private List<RandomUsersInfo> data;
	private PageInfo pageInfo;
	
	

	public GetResponseModel() {
		
	}
	
	public List<RandomUsersInfo> getData() {
		return data;
	}
	public void setData(List<RandomUsersInfo> data) {
		this.data = data;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
	
	

}

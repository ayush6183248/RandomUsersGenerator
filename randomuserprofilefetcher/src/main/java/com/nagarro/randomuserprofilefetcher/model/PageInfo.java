package com.nagarro.randomuserprofilefetcher.model;

public class PageInfo {
	
	private boolean hasNextPage;
	private boolean hasPreviousPage;
	private long total;
	
	
	
	public PageInfo() {
	}
	
	
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	
	

}

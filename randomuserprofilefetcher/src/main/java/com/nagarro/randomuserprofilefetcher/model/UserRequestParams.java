package com.nagarro.randomuserprofilefetcher.model;

import javax.validation.constraints.NotNull;

import com.nagarro.randomuserprofilefetcher.validators.ValidationConstraintHandler;

public class UserRequestParams {

	@ValidationConstraintHandler(paramName = "sortType")
    private String sortType;

	@ValidationConstraintHandler(paramName = "sortOrder")
    private String sortOrder;

	@ValidationConstraintHandler(paramName = "limit")
    private String limit;

	@ValidationConstraintHandler(paramName = "offset")
    private String offset;
	
	
	

	public UserRequestParams(@NotNull(message = "Sort_Type must not be null") String sortType,
			@NotNull(message = "Sort_Order must not be null") String sortOrder,
			@NotNull(message = "Limit must not be null") String limit,
			@NotNull(message = "Offset must not be null") String offset) {
		super();
		this.sortType = sortType;
		this.sortOrder = sortOrder;
		this.limit = limit;
		this.offset = offset;
	}
	
	




	public String getSortType() {
		return sortType;
	}






	public void setSortType(String sortType) {
		this.sortType = sortType;
	}






	public String getSortOrder() {
		return sortOrder;
	}






	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}






	public String getLimit() {
		return limit;
	}






	public void setLimit(String limit) {
		this.limit = limit;
	}






	public String getOffset() {
		return offset;
	}






	public void setOffset(String offset) {
		this.offset = offset;
	}






	@Override
	public String toString() {
		return sortType+" "+sortOrder+" "+limit+" "+offset;
	}
	
	
    
   

  
    
    

    
}

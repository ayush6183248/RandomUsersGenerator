package com.nagarro.randomuserprofilefetcher.validators;

public interface Validator {
	
	boolean validate(String paramName, String value);

	String getErrorMessage();

}

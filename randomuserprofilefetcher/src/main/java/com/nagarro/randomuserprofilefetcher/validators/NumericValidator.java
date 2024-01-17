package com.nagarro.randomuserprofilefetcher.validators;

import java.security.InvalidParameterException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagarro.randomuserprofilefetcher.constants.ValidationConstants;
import com.nagarro.randomuserprofilefetcher.controller.RandomUserProfileController;

public class NumericValidator implements Validator {
	
	 private static final NumericValidator numericValidatorInstance = new NumericValidator();
	 private Logger logger = LoggerFactory.getLogger(NumericValidator.class);
	 private String errorMessage;


	    private NumericValidator() {
	    }

	    public static NumericValidator getInstance() {
	        return numericValidatorInstance;
	    }

	    @Override
	    public boolean validate(String paramName, String value) {
	    	
	    	boolean result = false;
	    	
	    	
	        try {
	            int numericValue = Integer.parseInt(value);
	            switch(paramName) {
	            case ValidationConstants.SIZE:
	            	result = sizeValidator(numericValue);
	            	break;
	            
	            case ValidationConstants.LIMIT:
	            	result = limitValidator(numericValue);
	            	break;
	            	
	            case ValidationConstants.OFFSET:
	            	result = offsetValidator(numericValue);
	            	break;
	            
	            default:
	            	logger.error("Invalid Parameter type");
	            	errorMessage = "Invalid Parameter type. Please enter String type of value for: "+paramName;
		        	
		    		throw new InvalidParameterException(errorMessage);
	            	
	            }
	            
	            return result;
	        } catch (NumberFormatException e) {
            	logger.error("Exception occured");

            	e.printStackTrace();
            	return false;
	            
	        }
	    }
	    
	    
	    private boolean sizeValidator(int value) {
	    	if(value >= 1 && value <= 5) {
	    		return true;
	    	}
	    	else {
	            errorMessage = "Invalid value for SIZE. Please provide a valid Integer value for key: Size within "
	            		+ "the range of 1 to 5 (both included) ";
	    		throw new InvalidParameterException(errorMessage);

	        }
	    }
	    
	    private boolean limitValidator(int value) {
	    	if(value >= 1 && value <= 5) {
	    		return true;
	    	}
	    	else {
            	logger.error("Exception occured in limitValidator");

            	 errorMessage = "Invalid value for LIMIT. Please provide a valid Integer value for key: Limit within "
 	            		+ "the range of 1 to 5 (both included) ";
	    		throw new InvalidParameterException(errorMessage);

	        }
	    }
	    
	    private boolean offsetValidator(int value) {
	    	if(value >= 0 && value <= 5) {
	    		return true;
	    	}
	    	else {
	    		 errorMessage = "Invalid value for OFFSET. Please provide a valid Integer value for key: Offset within "
		            		+ "the range of 0 to 5 (both included) ";
	    		throw new InvalidParameterException(errorMessage);

	        }
	    }
	    
	    @Override
	    public String getErrorMessage() {
	        return errorMessage;
	    }

}

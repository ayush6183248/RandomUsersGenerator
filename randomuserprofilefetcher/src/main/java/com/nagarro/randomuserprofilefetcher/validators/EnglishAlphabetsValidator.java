package com.nagarro.randomuserprofilefetcher.validators;

import java.security.InvalidParameterException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagarro.randomuserprofilefetcher.constants.ValidationConstants;

public class EnglishAlphabetsValidator implements Validator {
	
    private static final EnglishAlphabetsValidator alphabetsValidatorInstance = new EnglishAlphabetsValidator();
    

	private Logger logger = LoggerFactory.getLogger(EnglishAlphabetsValidator.class);
    private String errorMessage;
 
    
    private EnglishAlphabetsValidator() {
    }

    public static EnglishAlphabetsValidator getInstance() {
        return alphabetsValidatorInstance;
    }

    @Override
    public boolean validate(String paramName, String value) {
    	
    	boolean result = false;
    	
        switch(paramName) {
        case ValidationConstants.SORT_TYPE:
        	result = sortTypeValidator(value);
        	break;
        
        	
        case ValidationConstants.SORT_ORDER:
        	result = orderTypeValidator(value);
        	break;
        	
        
        default:
        	logger.error("Invalid Parameter type");
        	errorMessage = "Invalid Parameter type. Please enter Integer type of value for: "+paramName;
    		throw new InvalidParameterException(errorMessage);

        	
        	
        }
     
        return result ;
    }
    
    private boolean sortTypeValidator(String value) {
        if (value.equalsIgnoreCase(ValidationConstants.NAME) || value.equalsIgnoreCase(ValidationConstants.AGE)) {
            return true;
        }
        else {
            errorMessage = "Invalid value for Sort_Type. It should be 'NAME' or 'AGE'.";
    		throw new InvalidParameterException(errorMessage);

        }
    }

    private boolean orderTypeValidator(String value) {
        if (value.equalsIgnoreCase(ValidationConstants.EVEN) || value.equalsIgnoreCase(ValidationConstants.ODD)) {
            return true;
        }
        else {
            errorMessage = "Invalid value for Sort_Order. It should be 'EVEN' or 'ODD'.";
    		throw new InvalidParameterException(errorMessage);

        }
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
    
    

}
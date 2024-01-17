package com.nagarro.randomuserprofilefetcher.validators;

import java.security.InvalidParameterException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidatorInstancesFactory {
	
//	private static Logger logger = LoggerFactory.getLogger(EnglishAlphabetsValidator.class);
//	private static Set
//
//	
//	public static Validator createValidator(String paramName) {
//		
//		if(paramName)
//		
//		switch(paramName) {
//		
//		case "SIZE":
//          return NumericValidator.getInstance();
//        	break;
//        
//        case "LIMIT":
//            return NumericValidator.getInstance();
//        	break;
//        	
//        case "OFFSET":
//            return NumericValidator.getInstance();
//        	break;
//		
//		
//        case "SORT_TYPE":
//          return EnglishAlphabetsValidator.getInstance();
//        	break;
//        
//        	
//        case "ORDER_TYPE":
//            return EnglishAlphabetsValidator.getInstance();
//        	break;
//        	
//        
//        default:
//        	logger.error("Invalid Parameter name");
//        	
//        	
//        }
//	}
	
	
	
	
	public static Validator createValidator(String paramName, String value) {
		if(!value.isEmpty()) {
        if (isNumericValue(value)) {
            return NumericValidator.getInstance();
        } else {
            return EnglishAlphabetsValidator.getInstance();
        }
		}
		else {
    		throw new InvalidParameterException("Value for key: "+paramName+", cannot be null. Please enter a valid value as per key.");
    		
    	}
    }

    private static boolean isNumericValue(String value) {
    	
    		try {
                Integer.parseInt(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
    	}
    	
        
    }



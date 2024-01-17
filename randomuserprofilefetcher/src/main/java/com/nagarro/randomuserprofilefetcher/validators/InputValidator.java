package com.nagarro.randomuserprofilefetcher.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputValidator implements ConstraintValidator<ValidationConstraintHandler, String> {

    private String paramName;
	 private Logger logger = LoggerFactory.getLogger(InputValidator.class);


    @Override
    public void initialize(ValidationConstraintHandler constraintAnnotation) {
        this.paramName = constraintAnnotation.paramName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Perform validation based on paramName
    	
    	logger.error("for ParamName: "+paramName+" value is : "+value);
    	if(value.isEmpty()) {
    		logger.error("value is empty");
    	}
    	try {
    		boolean b = ValidatorInstancesFactory
        			.createValidator(paramName, value)
        			.validate(paramName, value);
    		logger.error("output for "+paramName+" "+b);
    		return b;
    		
    	}catch(Exception e) {
    		logger.error("error thrown for "+paramName+" "+e);
    		throw e;
    	}
    	
    	
        }

     
    }

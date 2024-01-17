package com.nagarro.randomuserprofilefetcher.errorhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nagarro.randomuserprofilefetcher.validators.NumericValidator;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;

@ControllerAdvice
public class MainErrorHandler extends ResponseEntityExceptionHandler {
	
	 private Logger logger = LoggerFactory.getLogger(MainErrorHandler.class);

	 
	
//    @Override
//    public ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//        String error = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
//        ErrorResponseGenerator errorResponse = new ErrorResponseGenerator(error, HttpStatus.BAD_REQUEST.value(), getCurrentTimestamp(), HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, errorResponse, headers, errorResponse.getStatus(), request);
//    }
    
    
//
//    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
//    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
//            MethodArgumentTypeMismatchException ex) {
//        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
//        ErrorResponseGenerator errorResponse = new ErrorResponseGenerator(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), getCurrentTimestamp(), HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
//    }
    
    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<Object> handleInvalidParameterException(InvalidParameterException ex) {
//        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddth MMM yyyy HH:mm:ss"));
        ErrorResponseGenerator errorResponse = new ErrorResponseGenerator(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), getCurrentTimestamp(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
    }

//    @ExceptionHandler({Exception.class})
//    public ResponseEntity<Object> handleAll(
//            Exception ex,
//            WebRequest request) {
//    	logger.error("Ayush"+ex);
//        ErrorResponseGenerator errorResponse = new ErrorResponseGenerator(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), getCurrentTimestamp(), HttpStatus.INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
//    }
    
   

    private String getCurrentTimestamp() {
        return LocalDateTime.now().toString();
    }
    
    
}
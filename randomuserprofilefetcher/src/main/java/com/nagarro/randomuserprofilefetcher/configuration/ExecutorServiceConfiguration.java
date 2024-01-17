package com.nagarro.randomuserprofilefetcher.configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutorServiceConfiguration {
	
	@Bean(name = "apiCallExecutorService")
    public ExecutorService executorServiceWithTwoThreads() {
        return Executors.newFixedThreadPool(2);
    }

//    @Bean(name = "largeTaskExecutorService")
//    public ExecutorService executorServiceWithFiveThreads() {
//        return Executors.newFixedThreadPool(5);
//    }

}

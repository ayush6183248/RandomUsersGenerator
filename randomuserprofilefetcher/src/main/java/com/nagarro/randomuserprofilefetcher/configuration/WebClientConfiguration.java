package com.nagarro.randomuserprofilefetcher.configuration;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfiguration {

    private WebClient.Builder createWebClientBuilder(int timeoutMillis) {
    	
    	HttpClient httpClient = HttpClient.create()
    			  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeoutMillis)
    			  .responseTimeout(Duration.ofMillis(timeoutMillis))
    			  .doOnConnected(conn -> 
    			  conn.addHandlerLast(new ReadTimeoutHandler(timeoutMillis, TimeUnit.MILLISECONDS))
    			      .addHandlerLast(new WriteTimeoutHandler(timeoutMillis, TimeUnit.MILLISECONDS)));

//    			WebClient client = WebClient.builder()
//    			  .clientConnector(new ReactorClientHttpConnector(httpClient))
//    			  .build();
    	
    	
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient));
    }

    @Bean(name = "api1WebClient")
    public WebClient api1WebClient() {
        return createWebClientBuilder(2000)
                .baseUrl("https://randomuser.me/api/")
                .build();
    }

    @Bean(name = "api2WebClient")
    public WebClient api2WebClient() {
        return createWebClientBuilder(1000)
                .baseUrl("https://api.nationalize.io/")
                .build();
    }

    @Bean(name = "api3WebClient")
    public WebClient api3WebClient() {
        return createWebClientBuilder(1000)
                .baseUrl("https://api.genderize.io/")
                .build();
    }
}
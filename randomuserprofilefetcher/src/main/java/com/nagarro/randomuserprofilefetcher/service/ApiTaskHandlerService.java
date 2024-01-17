package com.nagarro.randomuserprofilefetcher.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nagarro.randomuserprofilefetcher.entities.RandomUsersInfo;
import com.nagarro.randomuserprofilefetcher.model.RandomUserResponse;
import com.nagarro.randomuserprofilefetcher.validators.NumericValidator;

@Service
public class ApiTaskHandlerService {

	private final WebClient randomUserApiWebClient;
	private final WebClient nationalityApiWebClient;
	private final WebClient genderApiWebClient;
	private final ExecutorService apiCallExecutor;
	private final String VERIFIED = "VERIFIED";
	private final String TO_BE_VERIFIED = "TO_BE_VERIFIED";
	private Logger logger = LoggerFactory.getLogger(ApiTaskHandlerService.class);


	@Autowired
	public ApiTaskHandlerService(@Qualifier("api1WebClient") WebClient randomUserApiWebClient,
			@Qualifier("api2WebClient") WebClient nationalityApiWebClient,
			@Qualifier("api3WebClient") WebClient genderApiWebClient,
			@Qualifier("apiCallExecutorService") ExecutorService apiCallExecutor) {
		this.randomUserApiWebClient = randomUserApiWebClient;
		this.nationalityApiWebClient = nationalityApiWebClient;
		this.genderApiWebClient = genderApiWebClient;
		this.apiCallExecutor = apiCallExecutor;

	}

	public RandomUsersInfo apiTaskHandler() throws Exception {

		RandomUsersInfo randomUserToBeSaved = null;

		try {

//			Thread.sleep(1000);
			randomUserToBeSaved = new RandomUsersInfo();

			RandomUserResponse randomUserApiResponseData = randomUserApiHandler();

			if (randomUserApiResponseData != null && randomUserApiResponseData.getResults() != null
					&& !randomUserApiResponseData.getResults().isEmpty()) {
				RandomUserResponse.UserResult userResult = randomUserApiResponseData.getResults().get(0);

				if (userResult != null && userResult.getName() != null) {

					RandomUserResponse.Name name = userResult.getName();

					if (!name.getFirst().isEmpty()) {

						logger.error(name.getFirst());

						Future<Set<String>> nationalityFutureResult = nationalityApiHandler(name.getFirst());

						Future<String> genderFutureResult = genderApiHandler(name.getFirst());

//						apiCallExecutor.shutdown();

						// Getting nationality and gender of specific user.
						Set<String> nationalityData = nationalityFutureResult.get();
						String genderData = genderFutureResult.get();

						randomUserToBeSaved = randomUserEntityCreator(randomUserApiResponseData, userResult,
								nationalityData, genderData);

//						System.out.println(randomUserToBeSaved);
//						System.out.println(nationalityData);
//						System.out.println(genderData);

					}

				}

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return randomUserToBeSaved;
	}

	private boolean randomUserProfileChecker(RandomUserResponse randomUserIdentityInfo, Set<String> nat,
			String gender) {
		return RandomUserIdentityChecker.profileChecker(randomUserIdentityInfo, nat, gender);
	}

	private RandomUserResponse randomUserApiHandler() throws JsonMappingException, JsonProcessingException {
		RandomUserFetcherApiService randomUserFetcher = new RandomUserFetcherApiService(randomUserApiWebClient);
		return randomUserFetcher.getRandomUsers();
	}

	private Future<Set<String>> nationalityApiHandler(String name) {
		return apiCallExecutor.submit(new NationalityFetcherApiService(name, nationalityApiWebClient));

	}

	private Future<String> genderApiHandler(String name) {
		return apiCallExecutor.submit(new GenderFetcherApiService(name, genderApiWebClient));
	}

	private RandomUsersInfo randomUserEntityCreator(RandomUserResponse randomUserApiResponseData,
			RandomUserResponse.UserResult userResult, Set<String> nationalityData, String genderData) {

		RandomUsersInfo randomUserToBeSaved = new RandomUsersInfo();
		//  To verify before saving in custom class
		if (randomUserProfileChecker(randomUserApiResponseData, nationalityData, genderData)) {
			randomUserToBeSaved.setVerificationStatus(VERIFIED);
		} else {
			randomUserToBeSaved.setVerificationStatus(TO_BE_VERIFIED);
		}

		RandomUserResponse.Dob dob = userResult.getDob();
		RandomUserResponse.Name name = userResult.getName();

		randomUserToBeSaved.setAge(dob.getAge());
		randomUserToBeSaved.setDob(getFormattedDob(dob.getDate()));
		randomUserToBeSaved.setName(name.getFirst() + " " + name.getLast());
		randomUserToBeSaved.setGender(userResult.getGender());
		randomUserToBeSaved.setNationality(userResult.getNat());

		return randomUserToBeSaved;

	}
	
	private Date getFormattedDob(String dobString) {
		Date formattedDate = null;
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy");

		try {
		    Date date = inputFormat.parse(dobString);

		    String formattedDob = outputFormat.format(date);
		    logger.error(formattedDob);

		    formattedDate = outputFormat.parse(formattedDob);
		    logger.error("date: "+formattedDate.toString());


		} catch (ParseException e) {
		    e.printStackTrace(); // Handle the exception appropriately
		}
		return formattedDate;
    }

}

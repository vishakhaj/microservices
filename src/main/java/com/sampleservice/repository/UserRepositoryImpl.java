package com.sampleservice.repository;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sampleservice.domain.User;
import com.sampleservice.urlfactory.ApiUrlFactory;
import com.sampleservice.urlfactory.RestTemplateConfig;

@Repository
public class UserRepositoryImpl {

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

	@Autowired
	private RestTemplateConfig restTemplateConfig;

	@Autowired
	private ApiUrlFactory apiUrlFactory;


	public User fetchConsulValueUrl() throws URISyntaxException {
		URI userUri = apiUrlFactory.createUserUrl();
		User user = restTemplateConfig.restTemplate().getForObject(userUri, User.class);
		System.out.println("User name: " + user.getName());
		return user;
	}

//	public Map<String, String> createUsers() throws URISyntaxException {
//		try {
//			Map<String, String> result = new HashMap<>();
//
//			for (User user : fetchConsulValueUrl()) {
//				String id = user.getId();
//				String userInfo = result.get(id);
//				if (userInfo == null) {
//					result.put(id, new String());
//				}
//				String name = user.getName();
//				result.put(id, name);
//				return result;
//			}
//		} catch (RestClientException ex) {
//			logger.error("Error retrieving User Url ", ex.getMessage());
//			logger.trace("Error retrieving User Url ", ex);
//		} catch (HttpMessageConversionException ex) {
//			logger.error("Unable to parse JSON response ", ex.getMessage());
//			logger.trace("Unable to parse JSON response", ex.getMessage());
//		}
//
//		return null;
//	}

}

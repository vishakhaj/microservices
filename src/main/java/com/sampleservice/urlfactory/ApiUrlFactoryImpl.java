package com.sampleservice.urlfactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sampleservice.consul.ConsulConfigItem;
import com.sampleservice.repository.ConsulRepository;

@Component
public class ApiUrlFactoryImpl implements ApiUrlFactory {

	private static final Logger logger = LoggerFactory.getLogger(ApiUrlFactoryImpl.class);

	private static final String CONSUL_USER_KEY_PATH = "config/sampleservice/users";

	@Autowired
	private ConsulRepository consulRepository;

	@Override
	public URI createUserUrl() throws URISyntaxException {
		List<ConsulConfigItem> consulValue = consulRepository.getConsulValue(CONSUL_USER_KEY_PATH);
		try {
			if (consulValue != null) {
				for (ConsulConfigItem consulItem : consulValue) {
					String decodedConsulValue = consulRepository.getDecodedConsulValue(consulItem.getKey(),
							consulItem.getValue());
					return new URI(decodedConsulValue);
				}
			}
		} catch (URISyntaxException ex) {
			logger.trace("Error occured when requesting for URI: ", ex);
			logger.error("An error occured during retrieving URI", ex.getMessage());
		}

		return null;
	}

}

package com.sampleservice.repository;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import com.sampleservice.consul.ConsulConfigItem;
import com.sampleservice.urlfactory.RestTemplateConfig;

@Component
public class ConsulRepository {

	private static final Logger logger = LoggerFactory.getLogger(ConsulRepository.class);

	private static final String CONSUL_PATH = "http://" + System.getenv("CONSUL") + ":8500";

	private static final String CONSUL_KEY_VALUE = "v1/kv/";

	private static final String URL_SEPARATOR = "/";

	@Autowired
	private RestTemplateConfig restTemplateConfig;

	public List<ConsulConfigItem> getConsulValue(String path) {

		String consulAddress = CONSUL_PATH + URL_SEPARATOR + CONSUL_KEY_VALUE + path;
		try {
			ConsulConfigItem[] consulPath = restTemplateConfig.restTemplate().getForObject(consulAddress,
					ConsulConfigItem[].class);
			if (consulPath != null) {
				return Arrays.asList(consulPath);
			}
		} catch (ResourceAccessException ex) {
			logger.trace("Error occured when requesting Consul value: ", ex);
			logger.error("I/O error occured during accessing Consul with URL '" + consulAddress + "'.\n",
					ex.getMessage());
		} catch (RestClientException ex) {
			logger.trace("Error occured when requesting Consul value: ", ex);
			logger.error("An error occured during retreiving of key/values out of Consul.", ex.getMessage());
		}
		return null;
	}

	public String getDecodedConsulValue(String key, String encodedValue) {

		try {
			if (encodedValue != null) {
				byte[] decodedByte = Base64.getDecoder().decode(encodedValue);
				String decodedValue = new String(decodedByte, StandardCharsets.UTF_8);
				return decodedValue;
			}
		} catch (IllegalArgumentException iae) {
			logger.trace("Error occured when decoding Consul value: ", iae);
			logger.error("Could not Base64-decode consul value for key: " + key, iae.getMessage());
		}

		return null;
	}
}

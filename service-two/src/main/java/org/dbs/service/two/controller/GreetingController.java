package org.dbs.service.two.controller;

import java.util.Map;

import org.dbs.service.two.model.GreetingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingController {
	private static final Logger LOG = LoggerFactory.getLogger(GreetingController.class);

	@RequestMapping(method = RequestMethod.POST, path = "/test2", consumes = MediaType.APPLICATION_JSON_VALUE)
	public GreetingResponse greetingTwo(final @RequestBody Map<String, Object> requestBody) {
		LOG.info("POST /test2");
		final GreetingResponse greetingResponse = new GreetingResponse("/test2", requestBody);
		return greetingResponse;
	}
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LoadBalancerClient loadBalancer;

	@RequestMapping(method = RequestMethod.GET, path = "/sleuthTest", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String sleuthTest(final @RequestHeader("Authorization") String authorizationHeader) {
		//LOG.info("Authorization token=> {}", authorizationHeader);
		final String baseUrl = new StringBuilder(loadBalancer.choose("service3").getUri().toString()).append("/service3/sleuthTest")
				.toString();
		LOG.info("GET service2/sleuthTest=> {}", baseUrl);

		final ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.GET,
				getHeaders(authorizationHeader), String.class);

		return new StringBuilder("Service-Two=>").append(response.getBody()).toString();
	}

	private static HttpEntity<?> getHeaders(final String authorizationHeader) {
		final HttpHeaders headers = new HttpHeaders();
		try {
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
			headers.set("Authorization", authorizationHeader);
			headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		} catch (Exception e) {
			LOG.error("Unable to set headers");
		}

		return new HttpEntity<>(headers);
	}
}
package org.dbs.service.one.controller;

import org.dbs.service.one.model.GreetingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingController {
	private static final Logger LOG = LoggerFactory.getLogger(GreetingController.class);

	@RequestMapping(path = "/test1")
	GreetingResponse greetingOne(@RequestParam(defaultValue = "param1") String param1) {
		LOG.info("GET /test1");
		final GreetingResponse greetingResponse = new GreetingResponse("/test1",
				String.format("Received Param: %s", param1));
		return greetingResponse;
	}

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LoadBalancerClient loadBalancer;

	@RequestMapping(method = RequestMethod.GET, path = "/sleuthTest", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String sleuthTest(final @RequestHeader("Authorization") String authorizationHeader) {
		//LOG.info("Authorization token=> {}", authorizationHeader);
		final String baseUrl = new StringBuilder(loadBalancer.choose("service2").getUri().toString()).append("/service2/sleuthTest")
				.toString();
		LOG.info("GET service1/sleuthTest=> {}", baseUrl);

		final ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.GET,
				getHeaders(authorizationHeader), String.class);

		return new StringBuilder("Service-One=>").append(response.getBody()).toString();
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
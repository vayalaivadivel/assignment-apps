package org.dbs.service.three.controller;

import java.util.Map;

import org.dbs.service.three.model.GreetingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	private static final Logger LOG = LoggerFactory.getLogger(GreetingController.class);

	@RequestMapping(method = RequestMethod.PATCH, path = "/test3", consumes = MediaType.APPLICATION_JSON_VALUE)
	public GreetingResponse greetingThree(final @RequestBody Map<String, Object> requestBody) {
		LOG.info("PATCH /test3");
		final GreetingResponse greetingResponse = new GreetingResponse("/test3", requestBody);
		return greetingResponse;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/sleuthTest", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String sleuthTest() {
		LOG.info("GET service3/sleuthTest");
		return "Service-Three";
	}
}
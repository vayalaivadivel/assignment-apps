package org.dbs.service.two.controller;

import java.util.Map;

import org.dbs.service.two.model.GreetingResponse;
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

	@RequestMapping(method = RequestMethod.POST, path = "/test2", consumes = MediaType.APPLICATION_JSON_VALUE)
	public GreetingResponse greetingTwo(final @RequestBody Map<String, Object> requestBody) {
		LOG.info("POST /test2");
		final GreetingResponse greetingResponse = new GreetingResponse("/test2", requestBody);
		return greetingResponse;
	}
}
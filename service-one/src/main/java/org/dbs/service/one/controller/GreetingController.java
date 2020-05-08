package org.dbs.service.one.controller;
import org.dbs.service.one.model.GreetingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController{
	private static final Logger LOG = LoggerFactory.getLogger(GreetingController.class);
	@RequestMapping(path = "/test1")
	GreetingResponse greetingOne(@RequestParam(defaultValue = "param1") String param1) {
		LOG.info("GET /test1");
		final GreetingResponse greetingResponse = new GreetingResponse("/test1",
				String.format("Received Param: %s", param1));
		return greetingResponse;
	}
}
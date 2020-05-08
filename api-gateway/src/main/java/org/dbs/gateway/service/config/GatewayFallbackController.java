package org.dbs.gateway.service.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class GatewayFallbackController {
	@RequestMapping("/inCaseOfFailureUseThis")
	public Mono<String> fallback(final ServerHttpRequest httpRequest ) {
		return Mono.just("Unable to get response and reached maximum attempt 2");
	}
}
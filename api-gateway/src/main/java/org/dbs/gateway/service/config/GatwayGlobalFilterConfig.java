package org.dbs.gateway.service.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import reactor.core.publisher.Mono;

@Configuration
public class GatwayGlobalFilterConfig {
	private static final Logger LOG = LoggerFactory.getLogger(GatwayGlobalFilterConfig.class);
	
	@Bean
	@Order(0)
	public GlobalFilter b() {
		return (exchange, chain) -> {
			final String url = exchange.getRequest().getURI().toString();
			LOG.info("Pre Filter {}",url);
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				System.out.println("Post filter");
			}));
		};
	}
}

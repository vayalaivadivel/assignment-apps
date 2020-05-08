package org.dbs.gateway.service.config;

import org.dbs.gateway.service.entity.RequestTrace;
import org.dbs.gateway.service.repository.RequestTraceRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import reactor.core.publisher.Mono;

@Configuration
public class GatwayGlobalFilterConfig {
	private static final Logger LOG = LoggerFactory.getLogger(GatwayGlobalFilterConfig.class);
	@Autowired
	private RequestTraceRepo repo;

	@Bean
	@Order(0)
	public GlobalFilter b() {
		return (exchange, chain) -> {
			final String url = exchange.getRequest().getURI().toString();
			LOG.info("Pre Filter {}",url);
			repo.save(new RequestTrace(url));
			LOG.info("Request details saved in database");
			LOG.info("Content of the table request_trace shown below");
			LOG.info("=====================================================================");
			LOG.info(repo.findAll().toString());
			LOG.info("=====================================================================");
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				System.out.println("Post filter");
			}));
		};
	}
}

package com.redhat.examples.wfswarm.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class BackendCommand extends com.netflix.hystrix.HystrixCommand<BackendDTO> {

	private String host;
	private int port;
	private String saying;

	public BackendCommand(String host, int port) {
		// 5 calls in 5 seconds
		// super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("wildflyswarm.backend"))
		// .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true)
		// .withCircuitBreakerRequestVolumeThreshold(5)
		// .withMetricsRollingStatisticalWindowInMilliseconds(5000)));

		// add Bulkhead of 10 max threads to call services
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("wildflyswarm.backend"))
				.andThreadPoolPropertiesDefaults(
						HystrixThreadPoolProperties.Setter().withCoreSize(10).withMaxQueueSize(-1))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true)
						.withCircuitBreakerRequestVolumeThreshold(5)
						.withMetricsRollingStatisticalWindowInMilliseconds(5000)));

		this.host = host;
		this.port = port;
	}

	public BackendCommand withSaying(String saying) {
		this.saying = saying;
		return this;
	}

	@Override
	protected BackendDTO run() throws Exception {
		String backendServiceUrl = String.format("http://%s:%d", host, port);
		System.out.println("Sending to: " + backendServiceUrl);
		Client client = ClientBuilder.newClient();
		return client.target(backendServiceUrl).path("api").path("backend").queryParam("greeting", saying)
				.request(MediaType.APPLICATION_JSON_TYPE).get(BackendDTO.class);
	}

	@Override
	protected BackendDTO getFallback() {
		BackendDTO rc = new BackendDTO();
		rc.setGreeting("Greeting fallback!");
		rc.setIp("127.0.0,1");
		rc.setTime(System.currentTimeMillis());
		return rc;
	}

}

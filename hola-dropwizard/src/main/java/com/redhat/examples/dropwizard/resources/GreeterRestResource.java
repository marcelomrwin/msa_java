package com.redhat.examples.dropwizard.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;

import com.codahale.metrics.annotation.Timed;
import com.redhat.examples.dropwizard.api.BackendCommand;
import com.redhat.examples.dropwizard.api.BackendDTO;

@Path("/api")
public class GreeterRestResource {
	private String saying;
	private String backendServiceHost;
	private int backendServicePort;
	private Client client;

	public GreeterRestResource(final String saying, String host, int port, Client client) {
		this.saying = saying;
		this.backendServiceHost = host;
		this.backendServicePort = port;
		this.client = client;
	}

	@Path("/greeting")
	@GET
	@Timed
	public String greeting() {
		String backendServiceUrl = String.format("http://%s:%d", backendServiceHost, backendServicePort);
		System.out.println("Sending to: " + backendServiceUrl);
		BackendDTO backendDTO = client.target(backendServiceUrl).path("api").path("backend")
				.queryParam("greeting", saying).request().accept("application/json").get(BackendDTO.class);
		return backendDTO.getGreeting() + " at host: " + backendDTO.getIp();
	}

	@Path("/greeting-hystrix")
	@GET
	@Timed
	public String greetingHystrix() {
		BackendCommand command = new BackendCommand(backendServiceHost, backendServicePort).withSaying(saying);
		BackendDTO backendDTO = command.execute();
		return backendDTO.getGreeting() + " at host: " + backendDTO.getIp();
	}
}

package com.redhat.examples.wfswarm.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.apache.deltaspike.core.api.config.ConfigProperty;

@Path("/api")
public class GreeterResource {

	@Inject
	@ConfigProperty(name = "WF_SWARM_SAYING", defaultValue = "Hola")
	private String saying;

	@Inject
	@ConfigProperty(name = "GREETING_BACKEND_SERVICE_HOST", defaultValue = "localhost")
	private String backendServiceHost;

	@Inject
	@ConfigProperty(name = "GREETING_BACKEND_SERVICE_PORT", defaultValue = "8090")
	private int backendServicePort;

	@Path("/greeting")
	@GET
	public String greeting() {
		String backendServiceUrl = String.format("http://%s:%d", backendServiceHost, backendServicePort);
		System.out.println("Sending to: " + backendServiceUrl);
		Client client = ClientBuilder.newClient();
		BackendDTO backendDTO = client.target(backendServiceUrl).path("api").path("backend")
				.queryParam("greeting", saying).request(MediaType.APPLICATION_JSON_TYPE).get(BackendDTO.class);
		return backendDTO.getGreeting() + " at host: " + backendDTO.getIp();
	}
}

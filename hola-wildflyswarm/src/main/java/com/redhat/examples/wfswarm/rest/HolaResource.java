package com.redhat.examples.wfswarm.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.deltaspike.core.api.config.ConfigProperty;

@Path("/api/hola")
public class HolaResource {

	@Inject
	@ConfigProperty(name = "WF_SWARM_SAYING", defaultValue = "Hola")
	private String saying;

	@GET
	@Produces("text/plain")
	public Response doGet() {
		return Response.ok(saying + " from WF Swarm").build();
	}
}
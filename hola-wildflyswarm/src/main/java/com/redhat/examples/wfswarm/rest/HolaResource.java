package com.redhat.examples.wfswarm.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.deltaspike.core.api.config.ConfigProperty;

import io.swagger.annotations.ApiOperation;

@Path("/hola")
public class HolaResource {

	@Inject
	@ConfigProperty(name = "WF_SWARM_SAYING", defaultValue = "Hola")
	private String saying;

	@GET
	@Produces("text/plain")
	@ApiOperation("Returns the greeting Hola!!")
	public Response doGet() {
		return Response.ok(saying + " from WF Swarm").build();
	}
}
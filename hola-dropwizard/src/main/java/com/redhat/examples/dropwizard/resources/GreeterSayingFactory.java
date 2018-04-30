package com.redhat.examples.dropwizard.resources;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.client.JerseyClientConfiguration;

public class GreeterSayingFactory {
	@NotEmpty
	private String saying;
	@NotEmpty
	private String host;
	@NotEmpty
	private int port;
	private JerseyClientConfiguration jerseyClientConfig = new JerseyClientConfiguration();

	@JsonProperty("jerseyClient")
	public JerseyClientConfiguration getJerseyClientConfig() {
		return jerseyClientConfig;
	}

	public String getSaying() {
		return saying;
	}

	public void setSaying(String saying) {
		this.saying = saying;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}

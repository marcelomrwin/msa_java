package com.redhat.examples.wfswarm.rest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import io.swagger.jaxrs.config.BeanConfig;

@WebListener
public class SwaggerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.0");
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setTitle("Hola microservices REST API");
		beanConfig.setDescription("Operations that can be invoked in the hola microservices");
		beanConfig.setResourcePackage("com.redhat.examples.wfswarm.rest");
		beanConfig.setLicense("Apache 2.0");
		beanConfig.setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
		beanConfig.setContact("developer@redhat.com");
		beanConfig.setBasePath("/api");
		beanConfig.setPrettyPrint(true);
		beanConfig.setScan(true);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}

package com.redhat.examples.backend;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class BackendHttpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setStatus(200);

		System.out.println("Receiving request");

		ObjectMapper mapper = new ObjectMapper();
		String greeting = req.getParameter("greeting");

		ResponseDTO response = new ResponseDTO();
		response.setGreeting(greeting + " from cluster Backend");
		response.setTime(System.currentTimeMillis());
		response.setIp(getIp());

		PrintWriter out = resp.getWriter();
		mapper.writerWithDefaultPrettyPrinter().writeValue(out, response);
	}

	private String getIp() {
		String hostname = null;
		try {
			hostname = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			hostname = "unknown";
		}
		return hostname;
	}
}

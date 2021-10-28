package com.example.springingressnginx;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringIngressNginxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIngressNginxApplication.class, args);
	}

	@GetMapping(value = "/echo")
	public void echo(HttpServletRequest request, HttpServletResponse response) {
		Enumeration<String> headerNames = request.getHeaderNames();
		if (headerNames != null) {
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				response.setHeader(String.format("x-echo-%s", headerName.toLowerCase()), request.getHeader(headerName));
			}
		}
	}

	@GetMapping(value = "/302")
	public void redirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("/echo");
	}

}

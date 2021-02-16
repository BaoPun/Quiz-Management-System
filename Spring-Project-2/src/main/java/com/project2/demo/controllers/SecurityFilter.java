package com.project2.demo.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.project2.demo.entities.Engine;

@Order(1)
@Component
public class SecurityFilter implements Filter {

	@Autowired
	private Engine engine;
	
	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String sessionID = req.getSession().getId();
		
		String originalURI = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
		String uriStr;
		if (originalURI != null) {
			uriStr=originalURI;
		} else {
			uriStr=req.getRequestURI();
		}
		
		URI uri;
		try {
			uri = new URI(uriStr);
		} catch (URISyntaxException e) {
			throw new ServletException(e);
		}
		
		if (engine.isPermittedPage(sessionID, uri)) {
			chain.doFilter(request, response);
		} else {
//			resp.sendError(403);
			resp.sendRedirect("/");
		}
		
		
	}

	@Override
	public void destroy() {
	}
}
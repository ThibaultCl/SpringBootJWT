package com.springboot.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.gateway.model.AuthenticationRequest;

@RestController
public class GatewayRestController {
	
	@Autowired
	private GatewayService gatewayService;
	
	@RequestMapping("/")
	private String root() {
		return "Welcome to the Gateway service !";
	}
	
	// Available to access without a JWT
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		return gatewayService.createAuthenticationToken(authenticationRequest);
	}
}

package com.springboot.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.springboot.gateway.model.AuthenticationRequest;
import com.springboot.gateway.model.AuthenticationResponse;
import com.springboot.gateway.security.MyUserDetailsService;
import com.springboot.gateway.util.JwtUtil;

@Service
public class GatewayService {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	/**
	 * Check credential of a user and generate a JWT if ok
	 * @param AuthenticationRequest with username and password
	 * @return ResponseEntity which contain the JWT in the body if credential ok
	 * @throws Exception
	 */
	public ResponseEntity<?> createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		try {
			UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			String jwt = jwtTokenUtil.generateToken(userDetails);
			return ResponseEntity.ok(new AuthenticationResponse(jwt));
		} 
		catch (UsernameNotFoundException e) {
			// Username not found
			System.out.println(e);
			return new ResponseEntity<UsernameNotFoundException>(e, HttpStatus.FORBIDDEN);
		}
	}

}

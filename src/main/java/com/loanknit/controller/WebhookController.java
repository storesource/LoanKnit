package com.loanknit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class WebhookController {
	
	@PostMapping(value="/webhook",consumes=MediaType.ALL_VALUE)
	public ResponseEntity<String> webhookRequest(@RequestBody ObjectNode payload) {
		
		System.out.println(payload.toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value="/webhook")
	public void getResponse() {
		
	}

}

package com.loanknit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class WebhookController {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WebhookController.class);

	@PostMapping(value = "/webhook", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<String> webhookRequest(@RequestBody ObjectNode payload) {

		LOGGER.info("Payload: {}",payload);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/webhook")
	public ResponseEntity<String> verifyRequest(@RequestParam("hub.mode") final String mode,
			@RequestParam("hub.verify_token") final String verifyToken,
			@RequestParam("hub.challenge") final String challenge) {

		String token = "0fdb9f323216";

		if (!StringUtils.isEmpty(mode) && !StringUtils.isEmpty(verifyToken)) {
			if (mode.equals("subscribe") && verifyToken.equals(token))
				return new ResponseEntity<>(challenge, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

}

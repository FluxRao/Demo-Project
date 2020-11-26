package com.rampo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.model.input.OfferIdInput;
import com.rampo.service.OfferService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/offer/v1")
public class OfferController {

	@Autowired
	private OfferService offerService;

	@Operation(summary = "endpoint to get perticular offer details")
	@PostMapping("/findById")
	public ResponseEntity<Map<String, Object>> findById(@RequestBody OfferIdInput input) {

		try {
			Object output = offerService.findById(input);

			Map<String, Object> outputMap = new HashMap<>();
			outputMap.put("data", output);
			return new ResponseEntity<Map<String, Object>>(outputMap, HttpStatus.OK);

		} catch (Exception e) {
			Map<String, Object> outputMap = new HashMap<>();
			outputMap.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
	}
}

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.model.input.LoginInput;
import com.rampo.model.input.UserInput;
import com.rampo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@Operation(summary = "endpoint to save user details")
	@PostMapping(value = "/register")
	public ResponseEntity<String> saveUser(@RequestBody UserInput userInput) {
		try {
			String message = userService.saveUser(userInput);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "endpoint to show whether username is available or already taken ")
	@RequestMapping(value = "/autoSearch", method = RequestMethod.POST)
	public ResponseEntity<String> checkAvailabilityOfUserName(@RequestParam String userName) {

		try {
			String message = userService.checkAvailabilityOfUserName(userName);
			return new ResponseEntity<String>(message, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "endpoint to login")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody LoginInput input) {
		try {
			String message = userService.login(input);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "endpoint to get user details")
	@RequestMapping(value = "/details", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getUserDetails(@RequestParam String userName) {

		try {
			Map<String, Object> output = new HashMap<>();
			output.put("data", userService.getUserDetails(userName));
			return new ResponseEntity<Map<String, Object>>(output, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> output = new HashMap<>();
			output.putIfAbsent("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(output, HttpStatus.BAD_REQUEST);
		}
	}
}

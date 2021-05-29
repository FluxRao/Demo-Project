package com.rampo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.model.input.LoginInput;
import com.rampo.model.input.UserInput;
import com.rampo.model.output.ResponseOutput;
import com.rampo.service.ShopperService;
import com.rampo.util.Constants;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/shopper/v1")
public class ShopperController {

	@Autowired
	private ShopperService shopperService;

	@Operation(summary = "endpoint to save shopper details")
	@PostMapping(value = "/register")
	public ResponseEntity<ResponseOutput> registerShopper(@RequestBody UserInput userInput) {

		try {
			shopperService.registerShopper(userInput);
			ResponseOutput output = new ResponseOutput(null, Constants.user_save_success, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to login")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ResponseOutput> login(@RequestBody LoginInput input) {

		try {
			String message = shopperService.login(input);
			ResponseOutput output = new ResponseOutput(null, message, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}
}

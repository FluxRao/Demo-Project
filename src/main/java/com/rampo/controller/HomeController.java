package com.rampo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.model.input.UsernameInput;
import com.rampo.model.input.pagination.HomeInput;
import com.rampo.model.output.ResponseOutput;
import com.rampo.service.HomeService;
import com.rampo.util.Constants;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/home/v1")
public class HomeController {

	@Autowired
	private HomeService dataService;

	@Operation(summary = "endpoint to get all shop, brand and offer details")
	@PostMapping("/all")
	public ResponseEntity<ResponseOutput> getAllData(@RequestBody HomeInput input) {

		String userName = input.getUsername();

		try {
			Object data = dataService.getHomePageData(input, userName);
			ResponseOutput output = new ResponseOutput(data, null, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to get master city data")
	@PostMapping("/cities")
	public ResponseEntity<ResponseOutput> getCityData(@RequestBody UsernameInput input) {

		if (input.getUserName() == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			Object data = dataService.getCityList();
			ResponseOutput output = new ResponseOutput(data, null, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

	}
}

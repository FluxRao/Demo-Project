package com.rampo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.model.input.RatingInput;
import com.rampo.model.output.ResponseOutput;
import com.rampo.service.RatingService;
import com.rampo.util.Constants;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/rating/v1")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@Operation(summary = "endpoint to get all shop, brand and offer details")
	@PostMapping("/item/{userName}")
	public ResponseEntity<ResponseOutput> rateItem(@PathVariable String userName, @RequestBody RatingInput input) {
		if (userName == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			ratingService.rateItem(input, userName);
			ResponseOutput output = new ResponseOutput(null, Constants.rated, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to get all shop, brand and offer details")
	@PostMapping("/shop/{userName}")
	public ResponseEntity<ResponseOutput> rateShop(@PathVariable String userName, @RequestBody RatingInput input) {
		if (userName == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			ratingService.rateShop(input, userName);
			ResponseOutput output = new ResponseOutput(null, Constants.rated, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to get all shop, brand and offer details")
	@PostMapping("/offer/{userName}")
	public ResponseEntity<ResponseOutput> rateOffer(@PathVariable String userName, @RequestBody RatingInput input) {
		if (userName == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			ratingService.rateOffer(input, userName);
			ResponseOutput output = new ResponseOutput(null, Constants.rated, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}
}

package com.rampo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.model.input.OfferIdInput;
import com.rampo.model.input.OfferSaveInput;
import com.rampo.model.input.pagination.PaginationInput;
import com.rampo.model.output.ResponseOutput;
import com.rampo.service.OfferService;
import com.rampo.util.Constants;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/offer/v1")
public class OfferController {

	@Autowired
	private OfferService offerService;

	@Operation(summary = "endpoint to get perticular offer details")
	@PostMapping("/findById")
	public ResponseEntity<ResponseOutput> findById(@RequestBody OfferIdInput input) {

		if (input.getUserName() == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			Object data = offerService.findById(input);
			ResponseOutput output = new ResponseOutput(data, null, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

	}

	@Operation(summary = "endpoint to get all offers")
	@PostMapping("/all")
	public ResponseEntity<ResponseOutput> viewAllOffers(@RequestBody PaginationInput input) {

		if (input.getUserName() == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			Object data = offerService.viewAllOffers(input);
			ResponseOutput output = new ResponseOutput(data, Constants.offer_save_success, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to save offers")
	@PostMapping("/save")
	public ResponseEntity<ResponseOutput> saveOffer(@RequestBody OfferSaveInput input) {

		if (input.getUserName() == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			Object data = offerService.saveOffer(input, input.getUserName());
			ResponseOutput output = new ResponseOutput(data, null, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}
}

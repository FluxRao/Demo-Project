package com.rampo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.model.input.ShopIdInput;
import com.rampo.model.input.ShopInputForExistingShopper;
import com.rampo.model.input.ShopInputForNewShopper;
import com.rampo.model.input.pagination.PaginationInput;
import com.rampo.model.output.ResponseOutput;
import com.rampo.service.ShopService;
import com.rampo.util.Constants;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/shop/v1")
public class ShopController {

	@Autowired
	private ShopService shopService;

	@Operation(summary = "endpoint to save shop details for new Shopper")
	@RequestMapping(value = "/save/newShopper", method = RequestMethod.POST)
	public ResponseEntity<ResponseOutput> saveShopForNewUser(@RequestBody ShopInputForNewShopper input) {

		try {
			Object data = shopService.saveShopForNewUser(input);
			ResponseOutput output = new ResponseOutput(data, Constants.shop_save_success, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to save shop details for existing Shopper")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseOutput> saveShopForExistingUser(@RequestBody ShopInputForExistingShopper input) {

		try {
			Object data = shopService.saveShopForExistingUser(input);
			ResponseOutput output = new ResponseOutput(data, Constants.shop_save_success, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to get all shop details")
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public ResponseEntity<ResponseOutput> viewAllShops(@RequestBody PaginationInput input) {

		if (input.getUserName() == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			Object data = shopService.viewAllShops(input);
			ResponseOutput output = new ResponseOutput(data, null, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to get perticular shop details")
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	public ResponseEntity<ResponseOutput> findById(@RequestBody ShopIdInput input) {

		if (input.getUserName() == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			Object data = shopService.findById(input, input.getUserName());
			ResponseOutput output = new ResponseOutput(data, null, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}
}

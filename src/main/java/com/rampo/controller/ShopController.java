package com.rampo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.model.ShopDTO;
import com.rampo.model.input.ShopIdInput;
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

	@Operation(summary = "endpoint to save shop details")
	@RequestMapping(value = "/saveData", method = RequestMethod.POST)
	public ResponseEntity<ResponseOutput> saveShopData(@RequestBody ShopDTO shopDTO, @PathVariable String userName) {

		if (userName == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			Object data = shopService.saveShopData(shopDTO, userName);
			ResponseOutput output = new ResponseOutput(data, null, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

	}

	@Operation(summary = "endpoint to get all shop details")
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public ResponseEntity<ResponseOutput> getAllShopData(@RequestBody PaginationInput input,
			@PathVariable String userName) {

		if (userName == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			Object data = shopService.getAllShopData(input, userName);
			ResponseOutput output = new ResponseOutput(data, null, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

	}

	@Operation(summary = "endpoint to get perticular shop details")
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	public ResponseEntity<ResponseOutput> findById(@RequestBody ShopIdInput input, @PathVariable String userName) {

		if (userName == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			Object data = shopService.findById(input, userName);
			ResponseOutput output = new ResponseOutput(data, null, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}
}

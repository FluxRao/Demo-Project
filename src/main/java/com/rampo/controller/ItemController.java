package com.rampo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.model.input.ItemIdInput;
import com.rampo.model.input.ItemInput;
import com.rampo.model.output.ResponseOutput;
import com.rampo.service.ItemService;
import com.rampo.util.Constants;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/item/v1")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Operation(summary = "endpoint to get all shop, brand and offer details")
	@PostMapping("/findById")
	public ResponseEntity<ResponseOutput> findById(@RequestBody ItemIdInput input) {

		if (input.getUserName() == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			Object data = itemService.findById(input.getItemId(), input.getUserName());
			ResponseOutput output = new ResponseOutput(data, null, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "Save item data")
	@PostMapping("/save")
	public ResponseEntity<ResponseOutput> saveItem(@RequestBody ItemInput input) {

		try {
			Object data = itemService.saveItem(input);
			ResponseOutput output = new ResponseOutput(data, Constants.item_save_success, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}
}

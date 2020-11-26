package com.rampo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.model.ShopDTO;
import com.rampo.model.input.ShopIdInput;
import com.rampo.model.input.pagination.ShopPaginationInput;
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
	public ResponseEntity<String> saveShopData(@RequestBody ShopDTO shopDTO) {
		try {
			String message = shopService.saveShopData(shopDTO);
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "endpoint to get all shop details")
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getAllShopData(@RequestBody ShopPaginationInput input) {
		try {
			List<ShopDTO> shopList = shopService.getAllShopData(input);

			if (shopList.isEmpty()) {
				Map<String, Object> outputMap = new HashMap<>();
				outputMap.put("message", Constants.shop_no_record_found);
				return new ResponseEntity<>(outputMap, HttpStatus.NO_CONTENT);
			}
			Map<String, Object> outputMap = new HashMap<>();
			outputMap.put("data", shopList);
			return new ResponseEntity<>(outputMap, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> outputMap = new HashMap<>();
			outputMap.put("message", e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "endpoint to get perticular shop details")
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findById(@RequestBody ShopIdInput input) {

		try {
			Map<String, Object> output = shopService.findById(input);

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

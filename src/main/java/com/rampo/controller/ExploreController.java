package com.rampo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.model.input.pagination.ExploreInput;
import com.rampo.model.output.ResponseOutput;
import com.rampo.service.ExploreService;
import com.rampo.util.Constants;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/explore/v1")
public class ExploreController {

	@Autowired
	private ExploreService exploreService;

	@Operation(summary = "endpoint to get content of explore page")
	@PostMapping("/all")
	public ResponseEntity<ResponseOutput> getExplorePageData(@RequestBody ExploreInput input) {

		if (input.getUserName() == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			HashMap<String, Object> data = exploreService.getExplorePageData(input);
			ResponseOutput output = new ResponseOutput(data, Constants.data_fetch_success, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}
}

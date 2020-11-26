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
import com.rampo.service.ExploreService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/explore/v1")
public class ExploreController {

	@Autowired
	private ExploreService exploreService;

	@Operation(summary = "endpoint to get content of explore page")
	@PostMapping("/all")
	public ResponseEntity<HashMap<String, Object>> getExplorePageData(@RequestBody ExploreInput input) {

		try {
			HashMap<String, Object> output = exploreService.getExplorePageData(input);
			if (output.isEmpty()) {
				return new ResponseEntity<HashMap<String, Object>>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<HashMap<String, Object>>(output, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<HashMap<String, Object>>(HttpStatus.BAD_REQUEST);
		}

	}
}

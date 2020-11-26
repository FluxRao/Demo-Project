package com.rampo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.service.SearchService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/search/v1")
public class SearchController {

	@Autowired
	private SearchService searchService;

	@Operation(summary = "returns search result")
	@GetMapping(value = "/all")
	public ResponseEntity<HashMap<String, Object>> search(@RequestParam String keyword) {
		try {
			HashMap<String, Object> output = searchService.search(keyword);
			if (output.isEmpty()) {
				return new ResponseEntity<HashMap<String, Object>>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<HashMap<String, Object>>(output, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<HashMap<String, Object>>(HttpStatus.BAD_REQUEST);
		}
	}

}

package com.rampo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.model.CategoryDTO;
import com.rampo.model.input.pagination.CategoryPaginationInput;
import com.rampo.service.CategoryService;
import com.rampo.util.Constants;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/category/v1")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Operation(summary = "endpoint to get all category details")
	@PostMapping("/all")
	public ResponseEntity<Map<String, Object>> getAllCategoryData(@RequestBody CategoryPaginationInput input) {
		try {
			List<CategoryDTO> output = categoryService.getAllCategoryData(input);
			if (output.isEmpty()) {
				Map<String, Object> outputMap = new HashMap<>();
				outputMap.put("message", Constants.category_no_record_found);
				return new ResponseEntity<Map<String, Object>>(outputMap, HttpStatus.NO_CONTENT);
			}
			Map<String, Object> outputMap = new HashMap<>();
			outputMap.put("data", output);
			return new ResponseEntity<Map<String, Object>>(outputMap, HttpStatus.OK);

		} catch (Exception e) {
			Map<String, Object> outputMap = new HashMap<>();
			outputMap.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "endpoint to get items of a perticular category")
	@PostMapping("/findByCategory")
	public ResponseEntity<Map<String, Object>> findByCategory(@RequestParam String categoryName) {

		try {
			return new ResponseEntity<Map<String, Object>>(categoryService.findByCategory(categoryName), HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> output = new HashMap<>();
			output.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(output, HttpStatus.OK);
		}
	}
}

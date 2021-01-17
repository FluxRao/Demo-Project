package com.rampo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.model.CategoryDTO;
import com.rampo.model.input.pagination.PaginationInput;
import com.rampo.model.output.ResponseOutput;
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
	@PostMapping("/all/{userName}")
	public ResponseEntity<ResponseOutput> getAllCategoryData(@RequestBody PaginationInput input,
			@PathVariable String userName) {

		if (userName == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			List<CategoryDTO> data = categoryService.getAllCategoryData(input, userName);
			ResponseOutput output = new ResponseOutput(data, Constants.data_fetch_success, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to get items of a perticular category")
	@PostMapping("/findByCategory/{userName}")
	public ResponseEntity<ResponseOutput> findByCategory(@RequestParam String categoryName,
			@PathVariable String userName) {

		if (userName == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			Map<String, Object> data = categoryService.findByCategory(categoryName, userName);
			ResponseOutput output = new ResponseOutput(data, Constants.data_fetch_success, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}
}

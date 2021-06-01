package com.rampo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.model.input.FindByCategoryInput;
import com.rampo.model.input.GetAllCategoriesInput;
import com.rampo.model.output.ResponseOutput;
import com.rampo.model.output.pagination.CategoryPaginationOutput;
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
	public ResponseEntity<ResponseOutput> getAllCategoryData(@RequestBody GetAllCategoriesInput input) {

		if (input.getUserName() == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			CategoryPaginationOutput data = categoryService.getAllCategoryData(input);
			ResponseOutput output = new ResponseOutput(data, Constants.data_fetch_success, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to get items of a perticular category")
	@PostMapping("/findByCategory")
	public ResponseEntity<ResponseOutput> findByCategory(@RequestBody FindByCategoryInput input) {

		if (input.getUserName() == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			Map<String, Object> data = categoryService.findByCategory(input.getCategory(), input.getUserName(),
					input.getCity());
			ResponseOutput output = new ResponseOutput(data, Constants.data_fetch_success, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}
}

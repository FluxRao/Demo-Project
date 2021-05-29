package com.rampo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.model.input.ChangePasswordInput;
import com.rampo.model.input.EditUserInput;
import com.rampo.model.input.ForgotPasswordInput;
import com.rampo.model.input.LoginInput;
import com.rampo.model.input.UserInput;
import com.rampo.model.output.ResponseOutput;
import com.rampo.service.UserService;
import com.rampo.util.Constants;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@Operation(summary = "endpoint to save user details")
	@PostMapping(value = "/register")
	public ResponseEntity<ResponseOutput> registerUser(@RequestBody UserInput userInput) {

		try {
			String message = userService.registerUser(userInput);
			ResponseOutput output = new ResponseOutput(null, message, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

	}

	@Operation(summary = "endpoint to show whether username is available or already taken ")
	@RequestMapping(value = "/autoSearch", method = RequestMethod.POST)
	public ResponseEntity<ResponseOutput> checkAvailabilityOfUserName(@RequestParam String userName) {

		try {
			String message = userService.checkAvailabilityOfUserName(userName);
			ResponseOutput output = new ResponseOutput(null, message, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to login")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ResponseOutput> login(@RequestBody LoginInput input) {

		try {
			String message = userService.login(input);
			ResponseOutput output = new ResponseOutput(null, message, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to get user details")
	@RequestMapping(value = "/details", method = RequestMethod.POST)
	public ResponseEntity<ResponseOutput> getUserDetails(@RequestParam String userName) {

		if (userName == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			Object data = userService.getUserDetails(userName);
			ResponseOutput output = new ResponseOutput(data, null, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to edit user details")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<ResponseOutput> editUserDetails(@RequestBody EditUserInput input) {

		if (input.getUserName() == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			userService.editUserDetails(input);
			ResponseOutput output = new ResponseOutput(null, Constants.user_data_updated_successfully, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to edit user details")
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<ResponseOutput> changePassword(@RequestBody ChangePasswordInput input) {

		if (input.getUserName() == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			userService.changePassword(input);
			ResponseOutput output = new ResponseOutput(null, Constants.user_password_updated_successfully, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to reset password")
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ResponseEntity<ResponseOutput> forgotPassword(@RequestBody ForgotPasswordInput input) {

		if (input.getUserName() == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_provide_username, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {
			userService.forgotPassword(input);
			ResponseOutput output = new ResponseOutput(null, "New password has been sent to emailId: "
					+ input.getEMail() + " Please do not share the mail or password with anyone.", true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 400);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}
}

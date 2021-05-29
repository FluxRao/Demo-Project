package com.rampo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rampo.model.output.ResponseOutput;
import com.rampo.service.LikeService;
import com.rampo.util.Constants;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/like/v1")
public class LikeController {

	@Autowired
	private LikeService likeService;

	@Operation(summary = "endpoint to get all shop, brand and offer details")
	@PostMapping("/item/{userName}/{itemId}")
	public ResponseEntity<ResponseOutput> likeItem(@PathVariable String userName, @RequestParam long itemId) {
		if (userName == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {

			likeService.likeItem(itemId, userName);
			ResponseOutput output = new ResponseOutput(null, Constants.liked, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to get all shop, brand and offer details")
	@PostMapping("/shop/{userName}/{shopId}")
	public ResponseEntity<ResponseOutput> likeShop(@PathVariable String userName, @RequestParam long shopId) {
		if (userName == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {

			likeService.likeShop(shopId, userName);
			ResponseOutput output = new ResponseOutput(null, Constants.liked, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to get all shop, brand and offer details")
	@PostMapping("/brand/{userName}/{brandName}")
	public ResponseEntity<ResponseOutput> likeBrand(@PathVariable String userName, @RequestParam String brandName) {
		if (userName == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {

			likeService.likeBrand(brandName, userName);
			ResponseOutput output = new ResponseOutput(null, Constants.liked, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}

	@Operation(summary = "endpoint to like offer")
	@PostMapping("/offer/{userName}/{offerId}")
	public ResponseEntity<ResponseOutput> likeOffer(@PathVariable String userName, @RequestParam long offerId) {
		if (userName == null) {
			ResponseOutput output = new ResponseOutput(null, Constants.please_login_to_continue, false, 401);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}

		try {

			likeService.likeOffer(offerId, userName);
			ResponseOutput output = new ResponseOutput(null, Constants.liked, true, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);

		} catch (Exception e) {
			ResponseOutput output = new ResponseOutput(null, e.getMessage(), false, 200);
			return new ResponseEntity<ResponseOutput>(output, HttpStatus.OK);
		}
	}
}

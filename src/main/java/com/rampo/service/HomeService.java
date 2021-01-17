package com.rampo.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rampo.model.input.pagination.HomeInput;

@Service
public class HomeService {

	@Autowired
	private ShopService shopService;

	@Autowired
	private OfferService offerService;

	@Autowired
	private CategoryService categoryService;

	public Object getHomePageData(HomeInput input, String userName) {

		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("shop", shopService.getAllShopData(input.getShopInput(), userName));
		output.put("offer", offerService.getAllOfferData(input.getOfferInput()));
		output.put("category", categoryService.getAllCategoryData(input.getCategoryInput(), userName));
		return output;

	}

}

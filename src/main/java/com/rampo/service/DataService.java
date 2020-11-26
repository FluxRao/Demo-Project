package com.rampo.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rampo.model.input.pagination.CategoryPaginationInput;
import com.rampo.model.input.pagination.OfferPaginationInput;
import com.rampo.model.input.pagination.HomeInput;
import com.rampo.model.input.pagination.ShopPaginationInput;
import com.rampo.util.ObjectMapper;

@Service
public class DataService {

	@Autowired
	private ShopService shopService;

	@Autowired
	private OfferService offerService;

	@Autowired
	private CategoryService categoryService;

	public HashMap<String, Object> getHomePageData(HomeInput input) {

		OfferPaginationInput offerInput = ObjectMapper.map(input, OfferPaginationInput.class);
		ShopPaginationInput shopInput = ObjectMapper.map(input, ShopPaginationInput.class);
		CategoryPaginationInput categoryInput = ObjectMapper.map(input, CategoryPaginationInput.class);

		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("shop", shopService.getAllShopData(shopInput));
		output.put("offer", offerService.getAllOfferData(offerInput));
		output.put("category", categoryService.getAllCategoryData(categoryInput));
		return output;

	}

}

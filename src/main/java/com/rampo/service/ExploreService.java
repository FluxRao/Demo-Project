package com.rampo.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rampo.model.input.pagination.ExploreInput;
import com.rampo.model.input.pagination.OfferPaginationInput;

@Service
public class ExploreService {

	@Autowired
	private OfferService offerService;

	public HashMap<String, Object> getExplorePageData(ExploreInput input) {

		OfferPaginationInput HurryUpInput = input.getHurryUpInput();
		OfferPaginationInput topRated = input.getTopRated();
		OfferPaginationInput mostPopular = input.getMostPopular();

		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("hurryUp", offerService.getOffersWhichAreOnLastDate(HurryUpInput));
		output.put("topRated", offerService.getAllOfferData(topRated));
		output.put("mostViewed", offerService.getAllOfferData(mostPopular));

		return output;
	}
}

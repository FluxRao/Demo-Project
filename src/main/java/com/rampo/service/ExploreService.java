package com.rampo.service;

import java.text.ParseException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rampo.model.input.pagination.ExploreInput;
import com.rampo.model.input.pagination.PaginationWithoutPageInput;

@Service
public class ExploreService {

	@Autowired
	private OfferService offerService;

	public HashMap<String, Object> getExplorePageData(ExploreInput input) throws ParseException {

		PaginationWithoutPageInput HurryUpInput = input.getHurryUp();
		PaginationWithoutPageInput topRated = input.getTopRated();
		PaginationWithoutPageInput mostPopular = input.getMostPopular();

		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("hurryUp",
				offerService.getOffersWhichAreOnLastDate(HurryUpInput, input.getUserName(), input.getCity()));
		output.put("topRated", offerService.getAllOfferData(topRated, input.getCity(), input.getUserName()));
		output.put("mostPopular", offerService.getAllOfferData(mostPopular, input.getCity(), input.getUserName()));

		return output;
	}
}

package com.rampo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rampo.model.input.pagination.HomeInput;
import com.rampo.model.output.CauroselHomePageOutput;
import com.rampo.repository.ShopRepository;

@Service
public class HomeService {

	@Autowired
	private ShopService shopService;

	@Autowired
	private OfferService offerService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ShopRepository shopRepo;

	public Object getHomePageData(HomeInput input, String userName) {

		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("shop", shopService.getAllShopData(input.getShopInput(), input.getCity(), userName));
		output.put("offer", offerService.getAllOfferData(input.getOfferInput(), input.getCity(), userName));
		output.put("category", categoryService.getHomePageCategoryData(input.getCategoryInput()));
		output.put("images", this.getCauroselImages());
		return output;

	}

	public List<CauroselHomePageOutput> getCauroselImages() {

		List<CauroselHomePageOutput> outputList = new ArrayList<>();

		CauroselHomePageOutput c1 = new CauroselHomePageOutput(
				"https://elasticbeanstalk-us-east-2-962347328384.s3.us-east-2.amazonaws.com/colossal+images/corosal+1.jpg");
		CauroselHomePageOutput c2 = new CauroselHomePageOutput(
				"https://elasticbeanstalk-us-east-2-962347328384.s3.us-east-2.amazonaws.com/colossal+images/corosal+2.jpg");
		CauroselHomePageOutput c3 = new CauroselHomePageOutput(
				"https://elasticbeanstalk-us-east-2-962347328384.s3.us-east-2.amazonaws.com/colossal+images/corosal+3.jpg");
		CauroselHomePageOutput c4 = new CauroselHomePageOutput(
				"https://elasticbeanstalk-us-east-2-962347328384.s3.us-east-2.amazonaws.com/colossal+images/corosal+4.jpg");
		CauroselHomePageOutput c5 = new CauroselHomePageOutput(
				"https://elasticbeanstalk-us-east-2-962347328384.s3.us-east-2.amazonaws.com/colossal+images/corosal+5.jpg");
		CauroselHomePageOutput c6 = new CauroselHomePageOutput(
				"https://elasticbeanstalk-us-east-2-962347328384.s3.us-east-2.amazonaws.com/colossal+images/corosal+6.jpg");
		CauroselHomePageOutput c7 = new CauroselHomePageOutput(
				"https://elasticbeanstalk-us-east-2-962347328384.s3.us-east-2.amazonaws.com/colossal+images/corosal+7.jpg");

		outputList.add(c1);
		outputList.add(c2);
		outputList.add(c3);
		outputList.add(c4);
		outputList.add(c5);
		outputList.add(c6);
		outputList.add(c7);

		return outputList;
	}

	public Object getCityList() {

		List<String> cities = shopRepo.findCityList();
		cities.replaceAll(String::toUpperCase);
		Set<String> distinctCities = new HashSet<>();
		cities.stream().forEach(x -> distinctCities.add(x));
		return distinctCities;

	}

}

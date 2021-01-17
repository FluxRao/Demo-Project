package com.rampo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rampo.entity.Brand;
import com.rampo.entity.Category;
import com.rampo.entity.Offer;
import com.rampo.entity.Shop;
import com.rampo.model.BrandDTO;
import com.rampo.model.OfferDTO;
import com.rampo.model.ShopDTO;
import com.rampo.repository.BrandRepository;
import com.rampo.repository.CategoryRepository;
import com.rampo.repository.OfferRepository;
import com.rampo.repository.ShopRepository;
import com.rampo.util.ObjectMapper;

@Service
public class SearchService {

	@Autowired
	private ShopRepository shopRepo;

	@Autowired
	private BrandRepository brandRepo;

	@Autowired
	private OfferRepository offerRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	public HashMap<String, Object> search(String keyword, String userName) {
		List<Shop> shopList = shopRepo.findByShopNameStartsWith(keyword);
		List<Brand> brandList = brandRepo.findByBrandNameStartsWith(keyword);
		List<Offer> offerList = offerRepo.findByOfferNameStartsWith(keyword);

		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("shop", ObjectMapper.mapList(shopList, ShopDTO.class));
		output.put("brand", ObjectMapper.mapList(brandList, BrandDTO.class));
		output.put("offer", ObjectMapper.mapList(offerList, OfferDTO.class));

		return output;
	}

	public List<Category> searchCategory(String keyword, String userName) {
		List<Category> categoryList = categoryRepo.findByCatNameStartsWith(keyword);
		return categoryList;
	}

}

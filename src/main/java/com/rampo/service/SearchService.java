package com.rampo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rampo.entity.Category;
import com.rampo.entity.Offer;
import com.rampo.entity.OfferUserMap;
import com.rampo.entity.Shop;
import com.rampo.entity.ShopUserMap;
import com.rampo.model.OfferDTO;
import com.rampo.model.ShopDTO;
import com.rampo.model.input.SerachInput;
import com.rampo.repository.CategoryRepository;
import com.rampo.repository.OfferRepository;
import com.rampo.repository.OfferUserMapRepository;
import com.rampo.repository.ShopRepository;
import com.rampo.repository.ShopUserMapRepository;
import com.rampo.util.ObjectMapper;

@Service
public class SearchService {

	@Autowired
	private ShopRepository shopRepo;

	@Autowired
	private OfferRepository offerRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private OfferUserMapRepository offerUserMapRepo;

	@Autowired
	private ShopUserMapRepository shopUserMapRepo;

	public HashMap<String, Object> search(SerachInput input) {
		List<Shop> shopList = shopRepo.findByShopNameContainingIgnoreCaseAndShopCityIgnoreCase(input.getKeyword(),
				input.getCity());
		List<Offer> offerList = offerRepo
				.findByOfferNameContainingIgnoreCaseAndIsActiveTrue(input.getKeyword().toLowerCase(), input.getCity());
		Set<OfferDTO> offerDTOList = new HashSet<>();
		List<ShopDTO> shopDTOList = new ArrayList<>();

		HashMap<String, Object> output = new HashMap<String, Object>();

		for (Offer offer : offerList) {

			OfferDTO offerDTO = ObjectMapper.map(offer, OfferDTO.class);
			OfferUserMap oum = offerUserMapRepo.findByOffer_OfferIdAndUser_UserName(offerDTO.getOfferId(),
					input.getUserName());
			if (oum != null) {
				offerDTO.setDidLike(oum.isDidLike());
			} else {
				offerDTO.setDidLike(false);
			}
			offerDTOList.add(offerDTO);
		}

		for (Shop shop : shopList) {

			ShopDTO shopDTO = ObjectMapper.map(shop, ShopDTO.class);
			ShopUserMap sum = shopUserMapRepo.findByShop_ShopIdAndUser_UserName(shopDTO.getShopId(),
					input.getUserName());
			if (sum != null) {
				shopDTO.setDidLike(sum.isDidLike());
			} else {
				shopDTO.setDidLike(false);
			}
			shopDTOList.add(shopDTO);
		}

		output.put("shop", shopDTOList);
		output.put("offer", offerDTOList);

		return output;
	}

	public List<Category> searchCategory(SerachInput input) {
		List<Category> categoryList = categoryRepo.findByCatNameContainingIgnoreCase(input.getKeyword());
		return categoryList;
	}

	public Object searchOffers(SerachInput input) {

		List<Offer> offerList = offerRepo
				.findByOfferNameContainingIgnoreCaseAndIsActiveTrue(input.getKeyword().toLowerCase(), input.getCity());
		List<OfferDTO> offerDTOList = new ArrayList<>();
		for (Offer offer : offerList) {

			OfferDTO offerDTO = ObjectMapper.map(offer, OfferDTO.class);
			OfferUserMap oum = offerUserMapRepo.findByOffer_OfferIdAndUser_UserName(offerDTO.getOfferId(),
					input.getUserName());
			if (oum != null) {
				offerDTO.setDidLike(oum.isDidLike());
			} else {
				offerDTO.setDidLike(false);
			}
			offerDTOList.add(offerDTO);
		}

		return offerDTOList;
	}

}

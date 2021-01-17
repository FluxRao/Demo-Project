package com.rampo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rampo.entity.Offer;
import com.rampo.entity.ShopItemConfig;
import com.rampo.entity.ShopItemOfferConfig;
import com.rampo.model.ItemDTO;
import com.rampo.model.OfferDTO;
import com.rampo.model.input.OfferIdInput;
import com.rampo.model.input.pagination.PaginationInput;
import com.rampo.repository.OfferRepository;
import com.rampo.repository.ShopItemConfigRepository;
import com.rampo.repository.ShopItemOfferConfigRepository;
import com.rampo.util.ObjectMapper;

@Service
public class OfferService {

	@Autowired
	private OfferRepository offerRepo;

	@Autowired
	private ShopItemOfferConfigRepository sioConfigRepo;

	@Autowired
	private ShopItemConfigRepository siConfigRepo;

	public List<OfferDTO> getAllOfferData(PaginationInput offerInput) {
		Sort sort = Sort.by(offerInput.getSortDirection(), offerInput.getSortBy());
		Pageable pageable = PageRequest.of(offerInput.getPageNumber(), offerInput.getPageSize(), sort);
		Page<Offer> offerPage = offerRepo.findAll(pageable);
		List<OfferDTO> outputList = new ArrayList<>();
		for (Offer offer : offerPage) {
			if (offer.isActive() && offer.getEndDate().isAfter(LocalDate.now())) {
				outputList.add(ObjectMapper.map(offer, OfferDTO.class));
			}
		}
		return outputList;
	}

	public List<OfferDTO> getOffersWhichAreOnLastDate(PaginationInput hurryUpInput) {

		Sort sort = Sort.by(hurryUpInput.getSortDirection(), hurryUpInput.getSortBy());
		Pageable pageable = PageRequest.of(hurryUpInput.getPageNumber(), hurryUpInput.getPageSize(), sort);
		Slice<Offer> offerSlice = offerRepo.findByEndDate(LocalDate.now(), pageable);
		List<OfferDTO> outputList = new ArrayList<>();
		for (Offer offer : offerSlice) {
			if (offer.isActive()) {
				outputList.add(ObjectMapper.map(offer, OfferDTO.class));
			}
		}
		return outputList;
	}

	public Map<String, Object> findById(OfferIdInput input, String userName) {

		Map<String, Object> output = new HashMap<>();
		output.put("offerId", input.getOfferId());

		ShopItemConfig configEntity = sioConfigRepo.findByOffer_OfferId(input.getOfferId()).get().getShopItemConfig();
		long shopId = configEntity.getShop().getShopId();
		long itemId = configEntity.getItem().getItemId();
		output.put("shop", configEntity.getShop());
		output.put("itemId", itemId);

		List<ShopItemConfig> siConfigList = siConfigRepo.findByShop_ShopId(shopId);
		List<ItemDTO> itemList = new ArrayList<>();
		for (ShopItemConfig shopItemConfig : siConfigList) {
			itemList.add(ObjectMapper.map(shopItemConfig.getItem(), ItemDTO.class));
		}
		output.put("otherItems", itemList);

		List<ShopItemOfferConfig> sioConfigList = sioConfigRepo.findByShopItemConfig_Shop_ShopId(shopId);
		List<OfferDTO> offerList = new ArrayList<>();
		for (ShopItemOfferConfig shopItemOfferConfig : sioConfigList) {
			offerList.add(ObjectMapper.map(shopItemOfferConfig.getOffer(), OfferDTO.class));
			output.put("otherOffers", offerList);
		}
		return output;
	}
}
package com.rampo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rampo.entity.Item;
import com.rampo.entity.Shop;
import com.rampo.entity.ShopItemConfig;
import com.rampo.entity.ShopItemOfferConfig;
import com.rampo.model.ItemDTO;
import com.rampo.model.OfferDTO;
import com.rampo.model.ShopDTO;
import com.rampo.model.input.ShopIdInput;
import com.rampo.model.input.pagination.PaginationInput;
import com.rampo.repository.ShopItemConfigRepository;
import com.rampo.repository.ShopItemOfferConfigRepository;
import com.rampo.repository.ShopRepository;
import com.rampo.util.Constants;
import com.rampo.util.ObjectMapper;

@Service
public class ShopService {

	@Autowired
	private ShopRepository shopRepo;

	@Autowired
	private ShopItemConfigRepository siConfigRepo;

	@Autowired
	private ShopItemOfferConfigRepository sioConfigRepo;

	public String saveShopData(ShopDTO shopDTO, String userName) throws Exception {

		Shop shop = ObjectMapper.map(shopDTO, Shop.class);
		shop.setRating(0);
		shop.setViews(0);
		shop.setCreatedOn(new Date());
		shop.setModifiedOn(new Date());
		try {
			shopRepo.save(shop);
			return Constants.shop_save_success;
		} catch (Exception e) {
			throw new Exception(Constants.shop_save_failed + " Error is " + e.getMessage());
		}
	}

	public List<ShopDTO> getAllShopData(PaginationInput shopInput, String userName) {

		Sort sort = Sort.by(shopInput.getSortDirection(), shopInput.getSortBy());
		Pageable pageable = PageRequest.of(shopInput.getPageNumber(), shopInput.getPageSize(), sort);

		Page<Shop> shopPage = shopRepo.findAll(pageable);
		List<ShopDTO> outputList = new ArrayList<>();
		for (Shop shop : shopPage) {
			if (shop.isActive()) {
				outputList.add(ObjectMapper.map(shop, ShopDTO.class));
			}
		}
		return outputList;
	}

	public Map<String, Object> findById(ShopIdInput input, String userName) {

		Map<String, Object> output = new HashMap<>();
		output.put("shop", shopRepo.findById(input.getShopid()));
		List<ShopItemConfig> siConfiglist = siConfigRepo.findByShop_ShopId(input.getShopid());
		List<Item> itemList = new ArrayList<>();
		for (ShopItemConfig sic : siConfiglist) {
			if (sic.getItem().isActive() == true) {
				itemList.add(sic.getItem());
			}
		}
		output.put("items", ObjectMapper.mapList(itemList, ItemDTO.class));

		List<OfferDTO> offerList = new ArrayList<>();
		List<Long> configIds = new ArrayList<>();

		for (ShopItemConfig sic : siConfiglist) {
			configIds.add(sic.getConfigId());
		}

		for (Long configId : configIds) {
			List<ShopItemOfferConfig> list1 = sioConfigRepo.findByShopItemConfig_configId(configId);
			for (ShopItemOfferConfig sioc1 : list1) {
				if (sioc1.getOffer().isActive() == true) {
					offerList.add(ObjectMapper.map(sioc1.getOffer(), OfferDTO.class));
				}
			}
		}

		output.put("offers", offerList);
		return output;
	}

}

package com.rampo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rampo.entity.Item;
import com.rampo.entity.ItemOfferConfig;
import com.rampo.entity.ItemUserMap;
import com.rampo.entity.OfferUserMap;
import com.rampo.entity.Shop;
import com.rampo.entity.ShopUserMap;
import com.rampo.entity.User;
import com.rampo.model.ItemDTO;
import com.rampo.model.OfferDTO;
import com.rampo.model.ShopDTO;
import com.rampo.model.input.ShopIdInput;
import com.rampo.model.input.ShopInputForExistingShopper;
import com.rampo.model.input.ShopInputForNewShopper;
import com.rampo.model.input.UserInput;
import com.rampo.model.input.pagination.PaginationInput;
import com.rampo.model.input.pagination.PaginationWithoutPageInput;
import com.rampo.model.output.pagination.ShopPaginationOutput;
import com.rampo.repository.ItemOfferConfigRepo;
import com.rampo.repository.ItemRepository;
import com.rampo.repository.ItemUserMapRepository;
import com.rampo.repository.OfferUserMapRepository;
import com.rampo.repository.ShopRepository;
import com.rampo.repository.ShopUserMapRepository;
import com.rampo.util.Constants;
import com.rampo.util.ObjectMapper;

@Service
public class ShopService {

	@Autowired
	private ShopRepository shopRepo;

	@Autowired
	private ItemOfferConfigRepo ioConfigRepo;

	@Autowired
	private ItemRepository itemRepo;

	@Autowired
	private ViewService viewService;

	@Autowired
	private ShopUserMapRepository shopUserMapRepo;

	@Autowired
	private OfferUserMapRepository offerUserMapRepo;

	@Autowired
	private ItemUserMapRepository itemUserMapRepo;

	@Autowired
	private UserService userService;

	@Transactional
	public String saveShopForNewUser(ShopInputForNewShopper input) throws Exception {

		UserInput userInput = new UserInput(input.getUserName(), input.getContactNo(), input.getEMail(),
				input.getPassword(), null, input.getCity());

		User owner = userService.registerShopper(userInput);

		Shop shop = ObjectMapper.map(input, Shop.class);
		shop.setRating(0);
		shop.setViews(0);
		shop.setNoOfRatings(0);
		shop.setCreatedOn(new Date());
		shop.setModifiedOn(new Date());
		shop.setActive(false);
		shop.setOwner(owner);
		try {
			shopRepo.save(shop);
			return Constants.shop_save_success;
		} catch (Exception e) {
			throw new Exception(Constants.shop_save_failed + ", Error is " + e.getMessage());
		}
	}

	@Transactional
	public String saveShopForExistingUser(ShopInputForExistingShopper input) throws Exception {

		User owner = userService.getUserDetails(input.getShopperUserName());

		Shop shop = ObjectMapper.map(input, Shop.class);
		shop.setRating(0);
		shop.setViews(0);
		shop.setNoOfRatings(0);
		shop.setCreatedOn(new Date());
		shop.setModifiedOn(new Date());
		shop.setActive(false);
		shop.setOwner(owner);
		try {
			shopRepo.save(shop);
			return Constants.shop_save_success;
		} catch (Exception e) {
			throw new Exception(Constants.shop_save_failed + ", Error is " + e.getMessage());
		}
	}

	public List<ShopDTO> getAllShopData(PaginationWithoutPageInput shopInput, String city, String userName) {

		Sort sort = Sort.by(shopInput.getSortDirection(), shopInput.getSortBy());
		Pageable pageable = PageRequest.of(0, 10, sort);

		Page<Shop> shopPage = shopRepo.findByShopCityIgnoreCaseAndIsActiveTrue(city, pageable);
		List<ShopDTO> outputList = new ArrayList<>();

		if (userName == null) {
			for (Shop shop : shopPage.getContent()) {
				ShopDTO shopDTO = ObjectMapper.map(shop, ShopDTO.class);
				shopDTO.setDidLike(false);
				outputList.add(shopDTO);
			}
		} else {
			for (Shop shop : shopPage.getContent()) {
				ShopUserMap sum = shopUserMapRepo.findByShop_ShopIdAndUser_UserName(shop.getShopId(), userName);
				ShopDTO shopDTO = ObjectMapper.map(shop, ShopDTO.class);
				if (sum != null) {
					shopDTO.setDidLike(sum.isDidLike());
				} else {
					shopDTO.setDidLike(false);
				}
				outputList.add(shopDTO);
			}
		}
		return outputList;
	}

	public Map<String, Object> findById(ShopIdInput input, String userName) {

		viewService.viewShop(input.getShopid(), userName);

		Map<String, Object> output = new HashMap<>();
		ShopDTO shopDTO = ObjectMapper.map(shopRepo.findById(input.getShopid()).get(), ShopDTO.class);
		ShopUserMap sum = shopUserMapRepo.findByShop_ShopIdAndUser_UserName(shopDTO.getShopId(), userName);
		if (sum != null) {
			shopDTO.setDidLike(sum.isDidLike());
		} else {
			shopDTO.setDidLike(false);
		}

		output.put("shop", shopDTO);

		List<Item> itemList = itemRepo.findByShop_ShopIdAndIsActiveTrueAndShop_IsActiveTrue(input.getShopid());
		List<ItemDTO> itemDTOList = new ArrayList<>();
		for (Item item : itemList) {

			ItemDTO itemDTO = ObjectMapper.map(item, ItemDTO.class);
			ItemUserMap ium = itemUserMapRepo.findByItem_ItemIdAndUser_UserName(itemDTO.getItemId(), userName);
			if (ium != null) {
				itemDTO.setDidLike(ium.isDidLike());
			} else {
				itemDTO.setDidLike(false);
			}
			itemDTOList.add(itemDTO);
		}

		output.put("items", itemDTOList);

		List<ItemOfferConfig> configList = ioConfigRepo
				.findByItem_Shop_shopIdAndIsActiveTrueAndItem_IsActiveTrueAndOffer_IsActiveTrueAndItem_Shop_IsActiveTrue(
						input.getShopid());
		Set<OfferDTO> offerList = new HashSet<>();

		for (ItemOfferConfig ioc : configList) {

			OfferDTO offerDTO = ObjectMapper.map(ioc.getOffer(), OfferDTO.class);
			OfferUserMap oum = offerUserMapRepo.findByOffer_OfferIdAndUser_UserName(offerDTO.getOfferId(), userName);
			if (oum != null) {
				offerDTO.setDidLike(oum.isDidLike());
			} else {
				offerDTO.setDidLike(false);
			}
			offerList.add(offerDTO);
		}

		output.put("offers", offerList);
		return output;
	}

	public Object viewAllShops(PaginationInput input) {

		Sort sort = Sort.by(input.getSortDirection(), input.getSortBy());
		Pageable pageable = PageRequest.of(input.getPageNumber(), input.getPageSize(), sort);
		Page<Shop> shops = shopRepo.findByShopCityIgnoreCaseAndIsActiveTrue(input.getCity(), pageable);
		List<ShopDTO> shopList = new ArrayList<>();

		for (Shop shop : shops.getContent()) {
			ShopDTO shopDTO = ObjectMapper.map(shop, ShopDTO.class);

			ShopUserMap sum = shopUserMapRepo.findByShop_ShopIdAndUser_UserName(shopDTO.getShopId(),
					input.getUserName());
			if (sum != null) {
				shopDTO.setDidLike(sum.isDidLike());
			} else {
				shopDTO.setDidLike(false);
			}
			shopList.add(shopDTO);
		}

		ShopPaginationOutput output = new ShopPaginationOutput();
		output.setShopList(shopList);
		output.setLast(shops.isLast());
		output.setNumber(shops.getNumber());
		output.setNumberOfElements(shops.getNumberOfElements());
		output.setSize(shops.getSize());
		output.setTotalElements(shops.getTotalElements());
		output.setTotalPage(shops.getTotalPages());
		return output;
	}

}

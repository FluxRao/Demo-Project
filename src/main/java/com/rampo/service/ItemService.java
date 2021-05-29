package com.rampo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rampo.entity.Item;
import com.rampo.entity.ItemOfferConfig;
import com.rampo.entity.ItemUserMap;
import com.rampo.entity.OfferUserMap;
import com.rampo.entity.Shop;
import com.rampo.entity.ShopUserMap;
import com.rampo.model.ItemDTO;
import com.rampo.model.OfferDTO;
import com.rampo.model.input.ItemInput;
import com.rampo.repository.CategoryRepository;
import com.rampo.repository.ItemOfferConfigRepo;
import com.rampo.repository.ItemRepository;
import com.rampo.repository.ItemUserMapRepository;
import com.rampo.repository.OfferUserMapRepository;
import com.rampo.repository.ShopRepository;
import com.rampo.repository.ShopUserMapRepository;
import com.rampo.util.Constants;
import com.rampo.util.ObjectMapper;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ShopRepository shopRepo;

	@Autowired
	private ItemOfferConfigRepo ioRepo;

	@Autowired
	private ViewService viewService;

	@Autowired
	private OfferUserMapRepository offerUserMapRepo;

	@Autowired
	private ShopUserMapRepository shopUserMapRepo;

	@Autowired
	private ItemUserMapRepository itemUserMapRepo;

	public Object findById(Long itemId, String userName) {

		viewService.viewItem(itemId, userName);

		Map<String, Object> output = new HashMap<>();

		Item item = itemRepo.findById(itemId).get();
		ItemDTO itemDTO = ObjectMapper.map(item, ItemDTO.class);

		ItemUserMap ium = itemUserMapRepo.findByItem_ItemIdAndUser_UserName(itemId, userName);
		if (ium != null) {
			itemDTO.setDidLike(ium.isDidLike());
		} else {
			itemDTO.setDidLike(false);
		}

		output.put("item", itemDTO);

		List<ItemOfferConfig> ioList = ioRepo.findByItem_ItemIdAndIsActiveTrue(itemId);
		List<OfferDTO> offerList = new ArrayList<>();
		for (ItemOfferConfig io : ioList) {
			OfferDTO offerDTO = ObjectMapper.map(io.getOffer(), OfferDTO.class);
			OfferUserMap oum = offerUserMapRepo.findByOffer_OfferIdAndUser_UserName(offerDTO.getOfferId(), userName);
			if (oum != null) {
				offerDTO.setDidLike(oum.isDidLike());
			} else {
				offerDTO.setDidLike(false);
			}
			offerList.add(offerDTO);
		}
		output.put("offer", offerList);

		ShopUserMap sum = shopUserMapRepo.findByShop_ShopIdAndUser_UserName(item.getShop().getShopId(), userName);
		if (sum != null) {
			output.put("didShopLike", sum.isDidLike());
		} else {
			output.put("didShopLike", false);
		}

		return output;
	}

	public Object saveItem(ItemInput input) throws Exception {

		Shop shop = shopRepo.findById(input.getShopId()).get();

		Item item = ObjectMapper.map(input, Item.class);
		item.setItemId(0);
		item.setActive(true);
		item.setCategory(categoryRepo.findById(input.getCategory()).get());
		item.setCreatedOn(new Date());
		item.setRating(0);
		item.setViews(0);
		item.setLikes(0);
		item.setNoOfRatings(0);
		item.setShop(shop);
		try {
			Item item1 = itemRepo.save(item);

			return ObjectMapper.map(item1, ItemDTO.class);

		} catch (Exception e) {
			throw new Exception(Constants.item_save_failed + " message is " + e.getMessage());
		}
	}
}

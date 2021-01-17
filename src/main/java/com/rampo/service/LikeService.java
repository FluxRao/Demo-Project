package com.rampo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rampo.entity.Brand;
import com.rampo.entity.BrandUserMap;
import com.rampo.entity.Item;
import com.rampo.entity.ItemUserMap;
import com.rampo.entity.Shop;
import com.rampo.entity.ShopUserMap;
import com.rampo.repository.BrandRepository;
import com.rampo.repository.BrandUserMapRepository;
import com.rampo.repository.ItemRepository;
import com.rampo.repository.ItemUserMapRepository;
import com.rampo.repository.ShopRepository;
import com.rampo.repository.ShopUserMapRepository;
import com.rampo.util.Constants;

@Service
public class LikeService {

	@Autowired
	private ItemRepository itemRepo;

	@Autowired
	private ItemUserMapRepository itemUserMapRepo;

	@Autowired
	private ShopUserMapRepository shopUserMapRepo;

	@Autowired
	private ShopRepository shopRepo;

	@Autowired
	private BrandRepository brandRepo;

	@Autowired
	private BrandUserMapRepository brandUserMapRepo;

	public Object likeItem(long itemId, String userName) {

		ItemUserMap ium = itemUserMapRepo.findByItem_ItemIdAndUser_UserName(itemId, userName);
		Item item = itemRepo.findById(itemId).get();

		if (!ium.isDidLike()) {
			ium.setDidLike(true);
			item.setLikes(item.getLikes() + 1);
		}

		if (!ium.isDidView()) {
			ium.setDidView(true);
			item.setViews(item.getViews() + 1);
		}

		itemRepo.save(item);
		itemUserMapRepo.save(ium);
		return Constants.liked;
	}

	public Object likeShop(long shopId, String userName) {
		ShopUserMap sum = shopUserMapRepo.findByShop_ShopIdAndUser_UserName(shopId, userName);
		Shop shop = shopRepo.findById(shopId).get();

		if (!sum.isDidLike()) {
			sum.setDidLike(true);
			shop.setLikes(shop.getLikes() + 1);
		}

		if (!sum.isDidView()) {
			sum.setDidView(true);
			shop.setViews(shop.getViews() + 1);
		}

		shopRepo.save(shop);
		shopUserMapRepo.save(sum);
		return Constants.liked;
	}

	public Object likeBrand(String brandName, String userName) {
		BrandUserMap bum = brandUserMapRepo.findByBrand_BrandNameAndUser_UserName(brandName, userName);
		Brand brand = brandRepo.findById(brandName).get();

		if (!bum.isDidLike()) {
			bum.setDidLike(true);
			brand.setLikes(brand.getLikes() + 1);
		}

		if (!bum.isDidView()) {
			bum.setDidView(true);
			brand.setViews(brand.getViews() + 1);
		}

		brandRepo.save(brand);
		brandUserMapRepo.save(bum);
		return Constants.liked;
	}

}
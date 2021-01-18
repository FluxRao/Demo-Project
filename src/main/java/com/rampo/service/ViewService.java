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

@Service
public class ViewService {

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

	public Object viewItem(long itemId, String userName) {

		ItemUserMap ium = itemUserMapRepo.findByItem_ItemIdAndUser_UserName(itemId, userName);
		Item item = itemRepo.findById(itemId).get();

		if (!ium.isDidView()) {
			ium.setDidView(true);
			item.setViews(item.getViews() + 1);
		}

		itemRepo.save(item);
		itemUserMapRepo.save(ium);
		return null;
	}

	public Object viewShop(long shopId, String userName) {
		ShopUserMap sum = shopUserMapRepo.findByShop_ShopIdAndUser_UserName(shopId, userName);
		Shop shop = shopRepo.findById(shopId).get();

		if (!sum.isDidView()) {
			sum.setDidView(true);
			shop.setViews(shop.getViews() + 1);
		}

		shopRepo.save(shop);
		shopUserMapRepo.save(sum);
		return null;
	}

	public Object viewBrand(String brandName, String userName) {
		BrandUserMap bum = brandUserMapRepo.findByBrand_BrandNameAndUser_UserName(brandName, userName);
		Brand brand = brandRepo.findById(brandName).get();

		if (!bum.isDidView()) {
			bum.setDidView(true);
			brand.setViews(brand.getViews() + 1);
		}

		brandRepo.save(brand);
		brandUserMapRepo.save(bum);
		return null;
	}

}

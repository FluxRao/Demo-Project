package com.rampo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rampo.entity.Brand;
import com.rampo.entity.BrandUserMap;
import com.rampo.entity.Item;
import com.rampo.entity.ItemUserMap;
import com.rampo.entity.Offer;
import com.rampo.entity.OfferUserMap;
import com.rampo.entity.Shop;
import com.rampo.entity.ShopUserMap;
import com.rampo.repository.BrandRepository;
import com.rampo.repository.BrandUserMapRepository;
import com.rampo.repository.ItemRepository;
import com.rampo.repository.ItemUserMapRepository;
import com.rampo.repository.OfferRepository;
import com.rampo.repository.OfferUserMapRepository;
import com.rampo.repository.ShopRepository;
import com.rampo.repository.ShopUserMapRepository;
import com.rampo.repository.UserRepository;

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

	@Autowired
	private OfferRepository offerRepo;

	@Autowired
	private OfferUserMapRepository offerUserMapRepo;

	@Autowired
	private UserRepository userRepo;

	public Object viewItem(long itemId, String userName) {

		ItemUserMap ium = itemUserMapRepo.findByItem_ItemIdAndUser_UserName(itemId, userName);

		if (ium == null) {

			ItemUserMap itemUserMap = new ItemUserMap();
			itemUserMap.setUser(userRepo.findById(userName).get());
			itemUserMap.setItem(itemRepo.findById(itemId).get());
			itemUserMap.setDidLike(false);
			itemUserMap.setDidRate(false);
			itemUserMap.setDidView(false);
			ItemUserMap ium1 = itemUserMapRepo.save(itemUserMap);

			ium = ium1;
		}

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
		if (sum == null) {
			ShopUserMap shopUserMap = new ShopUserMap();
			shopUserMap.setShop(shopRepo.findById(shopId).get());
			shopUserMap.setUser(userRepo.findById(userName).get());
			shopUserMap.setDidLike(false);
			shopUserMap.setDidRate(false);
			shopUserMap.setDidView(false);
			ShopUserMap sum1 = shopUserMapRepo.save(shopUserMap);

			sum = sum1;
		}
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

		if (bum == null) {

			BrandUserMap brandUserMap = new BrandUserMap();
			brandUserMap.setBrand(brandRepo.findById(brandName).get());
			brandUserMap.setUser(userRepo.findById(userName).get());
			brandUserMap.setDidLike(false);
			brandUserMap.setDidRate(false);
			brandUserMap.setDidView(false);
			BrandUserMap bum1 = brandUserMapRepo.save(brandUserMap);

			bum = bum1;
		}

		Brand brand = brandRepo.findById(brandName).get();

		if (!bum.isDidView()) {
			bum.setDidView(true);
			brand.setViews(brand.getViews() + 1);
		}

		brandRepo.save(brand);
		brandUserMapRepo.save(bum);
		return null;
	}

	public Object viewOffer(long offerId, String userName) {
		OfferUserMap oum = offerUserMapRepo.findByOffer_OfferIdAndUser_UserName(offerId, userName);
		if (oum == null) {

			OfferUserMap offerUserMap = new OfferUserMap();
			offerUserMap.setOffer(offerRepo.findById(offerId).get());
			offerUserMap.setUser(userRepo.findById(userName).get());
			offerUserMap.setDidLike(false);
			offerUserMap.setDidRate(false);
			offerUserMap.setDidView(false);
			OfferUserMap oum1 = offerUserMapRepo.save(offerUserMap);

			oum = oum1;
		}
		Offer offer = offerRepo.findById(offerId).get();

		if (!oum.isDidView()) {
			oum.setDidView(true);
			offer.setViews(offer.getViews() + 1);
		}

		offerRepo.save(offer);
		offerUserMapRepo.save(oum);
		return null;
	}

}

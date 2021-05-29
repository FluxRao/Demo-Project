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

	@Autowired
	private OfferRepository offerRepo;

	@Autowired
	private OfferUserMapRepository offerUserMapRepo;

	@Autowired
	private ViewService viewService;

	public void likeItem(long itemId, String userName) {

		viewService.viewItem(itemId, userName);

		ItemUserMap ium = itemUserMapRepo.findByItem_ItemIdAndUser_UserName(itemId, userName);
		Item item = itemRepo.findById(itemId).get();

		if (!ium.isDidLike()) {
			ium.setDidLike(true);
			item.setLikes(item.getLikes() + 1);
		} else {
			ium.setDidLike(false);
			item.setLikes(item.getLikes() - 1);
		}

		if (!ium.isDidView()) {
			ium.setDidView(true);
			item.setViews(item.getViews() + 1);
		}

		itemRepo.save(item);
		itemUserMapRepo.save(ium);
	}

	public void likeShop(long shopId, String userName) {

		viewService.viewShop(shopId, userName);

		ShopUserMap sum = shopUserMapRepo.findByShop_ShopIdAndUser_UserName(shopId, userName);
		Shop shop = shopRepo.findById(shopId).get();

		if (!sum.isDidLike()) {
			sum.setDidLike(true);
			shop.setLikes(shop.getLikes() + 1);
		} else {
			sum.setDidLike(false);
			shop.setLikes(shop.getLikes() - 1);
		}

		if (!sum.isDidView()) {
			sum.setDidView(true);
			shop.setViews(shop.getViews() + 1);
		}

		shopRepo.save(shop);
		shopUserMapRepo.save(sum);
	}

	public void likeBrand(String brandName, String userName) {

		viewService.viewBrand(brandName, userName);

		BrandUserMap bum = brandUserMapRepo.findByBrand_BrandNameAndUser_UserName(brandName, userName);
		Brand brand = brandRepo.findById(brandName).get();

		if (!bum.isDidLike()) {
			bum.setDidLike(true);
			brand.setLikes(brand.getLikes() + 1);
		} else {
			bum.setDidLike(false);
			brand.setLikes(brand.getLikes() - 1);
		}

		if (!bum.isDidView()) {
			bum.setDidView(true);
			brand.setViews(brand.getViews() + 1);
		}

		brandRepo.save(brand);
		brandUserMapRepo.save(bum);
	}

	public void likeOffer(long offerId, String userName) {

		viewService.viewOffer(offerId, userName);

		OfferUserMap oum = offerUserMapRepo.findByOffer_OfferIdAndUser_UserName(offerId, userName);
		Offer offer = offerRepo.findById(offerId).get();

		if (!oum.isDidLike()) {
			oum.setDidLike(true);
			offer.setLikes(offer.getLikes() + 1);
		} else {
			oum.setDidLike(false);
			offer.setLikes(offer.getLikes() - 1);
		}

		if (!oum.isDidView()) {
			oum.setDidView(true);
			offer.setViews(offer.getViews() + 1);
		}

		offerRepo.save(offer);
		offerUserMapRepo.save(oum);
	}
}
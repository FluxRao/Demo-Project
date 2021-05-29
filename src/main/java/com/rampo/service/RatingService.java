package com.rampo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rampo.entity.Item;
import com.rampo.entity.ItemUserMap;
import com.rampo.entity.Offer;
import com.rampo.entity.OfferUserMap;
import com.rampo.entity.Shop;
import com.rampo.entity.ShopUserMap;
import com.rampo.model.input.RatingInput;
import com.rampo.repository.ItemRepository;
import com.rampo.repository.ItemUserMapRepository;
import com.rampo.repository.OfferRepository;
import com.rampo.repository.OfferUserMapRepository;
import com.rampo.repository.ShopRepository;
import com.rampo.repository.ShopUserMapRepository;

@Service
public class RatingService {

	@Autowired
	private ItemRepository itemRepo;

	@Autowired
	private ItemUserMapRepository itemUserMapRepo;

	@Autowired
	private ShopRepository shopRepo;

	@Autowired
	private ShopUserMapRepository shopUserMapRepo;

	@Autowired
	private OfferRepository offerRepo;

	@Autowired
	private OfferUserMapRepository offerUserMapRepo;

	@Autowired
	private ViewService viewService;

	public void rateItem(RatingInput input, String userName) {

		viewService.viewItem(input.getItemId(), userName);
		Item item = itemRepo.findById(input.getItemId()).get();
		ItemUserMap ium = itemUserMapRepo.findByItem_ItemIdAndUser_UserName(input.getItemId(), userName);
		if (ium.isDidRate()) {
			item.setRating(((item.getRating() * item.getNoOfRatings()) - ium.getRating() + input.getRating())
					/ (item.getNoOfRatings()));
			ium.setRating(input.getRating());
		} else {
			item.setRating(
					((item.getRating() * item.getNoOfRatings()) + input.getRating()) / (item.getNoOfRatings() + 1));
			item.setNoOfRatings(item.getNoOfRatings() + 1);
			ium.setDidRate(true);
			ium.setRating(input.getRating());
		}
		itemRepo.save(item);
		itemUserMapRepo.save(ium);
	}

	public void rateShop(RatingInput input, String userName) {

		viewService.viewShop(input.getShopId(), userName);
		Shop shop = shopRepo.findById(input.getShopId()).get();
		ShopUserMap sum = shopUserMapRepo.findByShop_ShopIdAndUser_UserName(input.getShopId(), userName);
		if (sum.isDidRate()) {
			shop.setRating(((shop.getRating() * shop.getNoOfRatings()) - sum.getRating() + input.getRating())
					/ (shop.getNoOfRatings()));
			sum.setRating(input.getRating());
		} else {
			shop.setRating(
					((shop.getRating() * shop.getNoOfRatings()) + input.getRating()) / (shop.getNoOfRatings() + 1));
			shop.setNoOfRatings(shop.getNoOfRatings() + 1);
			sum.setDidRate(true);
			sum.setRating(input.getRating());
		}
		shopRepo.save(shop);
		shopUserMapRepo.save(sum);
	}

	public void rateOffer(RatingInput input, String userName) {

		viewService.viewOffer(input.getOfferId(), userName);
		Offer offer = offerRepo.findById(input.getOfferId()).get();
		OfferUserMap oum = offerUserMapRepo.findByOffer_OfferIdAndUser_UserName(input.getOfferId(), userName);

		if (oum.isDidRate()) {
			offer.setRating(((offer.getRating() * offer.getNoOfRatings()) - oum.getRating() + input.getRating())
					/ offer.getNoOfRatings());
			oum.setRating(input.getRating());
		} else {
			offer.setRating(
					((offer.getRating() * offer.getNoOfRatings()) + input.getRating()) / (offer.getNoOfRatings() + 1));
			offer.setNoOfRatings(offer.getNoOfRatings() + 1);
			oum.setDidRate(true);
			oum.setRating(input.getRating());
		}
		offerRepo.save(offer);
		offerUserMapRepo.save(oum);
	}
}

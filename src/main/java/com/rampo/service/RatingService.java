package com.rampo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rampo.entity.Brand;
import com.rampo.entity.BrandUserMap;
import com.rampo.entity.Item;
import com.rampo.entity.ItemUserMap;
import com.rampo.entity.Shop;
import com.rampo.entity.ShopUserMap;
import com.rampo.model.input.RatingInput;
import com.rampo.repository.BrandRepository;
import com.rampo.repository.BrandUserMapRepository;
import com.rampo.repository.ItemRepository;
import com.rampo.repository.ItemUserMapRepository;
import com.rampo.repository.ShopRepository;
import com.rampo.repository.ShopUserMapRepository;
import com.rampo.util.Constants;

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
	private BrandRepository brandRepo;

	@Autowired
	private BrandUserMapRepository brandUserMapRepo;

	public Object rateItem(RatingInput input, String userName) {

		Item item = itemRepo.findById(input.getItemId()).get();
		ItemUserMap ium = itemUserMapRepo.findByItem_ItemIdAndUser_UserName(input.getItemId(), userName);
		if (ium.isDidRate()) {
			item.setRating(((item.getRating() * item.getNoOfRatings()) - ium.getRating() + input.getRating())
					/ item.getNoOfRatings() + 1);
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

		return Constants.rated;
	}

	public Object rateShop(RatingInput input, String userName) {

		Shop shop = shopRepo.findById(input.getShopId()).get();
		ShopUserMap sum = shopUserMapRepo.findByShop_ShopIdAndUser_UserName(input.getShopId(), userName);
		if (sum.isDidRate()) {
			shop.setRating(((shop.getRating() * shop.getNoOfRatings()) - sum.getRating() + input.getRating())
					/ shop.getNoOfRatings() + 1);
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

		return Constants.rated;
	}

	public Object rateBrand(RatingInput input, String userName) {

		Brand brand = brandRepo.findById(input.getBrandName()).get();
		BrandUserMap bum = brandUserMapRepo.findByBrand_BrandNameAndUser_UserName(input.getBrandName(), userName);
		if (bum.isDidRate()) {
			brand.setRating(((brand.getRating() * brand.getNoOfRatings()) - bum.getRating() + input.getRating())
					/ brand.getNoOfRatings() + 1);
			bum.setRating(input.getRating());
		} else {
			brand.setRating(
					((brand.getRating() * brand.getNoOfRatings()) + input.getRating()) / (brand.getNoOfRatings() + 1));
			brand.setNoOfRatings(brand.getNoOfRatings() + 1);
			bum.setDidRate(true);
			bum.setRating(input.getRating());
		}
		brandRepo.save(brand);
		brandUserMapRepo.save(bum);

		return Constants.rated;
	}

}

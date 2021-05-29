package com.rampo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rampo.entity.ItemOfferConfig;

@Repository
public interface ItemOfferConfigRepo
		extends JpaRepository<ItemOfferConfig, Long>, PagingAndSortingRepository<ItemOfferConfig, Long> {

	public List<ItemOfferConfig> findByOffer_OfferIdAndIsActiveTrue(long offerId);

	public List<ItemOfferConfig> findByItem_ItemIdAndIsActiveTrue(long itemId);

//	@Query(value = "select distinct i.offer from ItemOfferConfig i where i.item.shop.shopid = ?1")
//	public List<Offer> findOffersOfAShop(long shopId);

	public List<ItemOfferConfig> findByItem_Shop_shopIdAndIsActiveTrueAndItem_IsActiveTrueAndOffer_IsActiveTrueAndItem_Shop_IsActiveTrue(
			long shopId);

	public Page<ItemOfferConfig> findByItem_Shop_ShopCityAndIsActiveTrueAndItem_IsActiveTrueAndOffer_IsActiveTrueAndItem_Shop_IsActiveTrue(
			String city, Pageable pageable);
}

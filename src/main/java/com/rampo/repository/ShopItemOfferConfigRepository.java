package com.rampo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rampo.entity.ShopItemOfferConfig;

@Repository
public interface ShopItemOfferConfigRepository
		extends JpaRepository<ShopItemOfferConfig, Long>, PagingAndSortingRepository<ShopItemOfferConfig, Long> {

	public Optional<ShopItemOfferConfig> findByOffer_OfferId(long offerId);

	public List<ShopItemOfferConfig> findByShopItemConfig_configId(long configId);

	public List<ShopItemOfferConfig> findByShopItemConfig_Shop_ShopId(long configId);
}

package com.rampo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rampo.entity.ShopItemConfig;

@Repository
public interface ShopItemConfigRepository
		extends JpaRepository<ShopItemConfig, Long>, PagingAndSortingRepository<ShopItemConfig, Long> {

	public List<ShopItemConfig> findByShop_ShopId(long shopId);
}

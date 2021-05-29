package com.rampo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rampo.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, PagingAndSortingRepository<Item, Long> {

	public List<Item> findByCategory_CatNameAndShop_ShopCityIgnoreCaseAndShop_IsActiveTrueAndCategory_IsActiveTrueAndIsActiveTrue(
			String category, String city);

	public long countByCategory_CatNameAndIsActiveTrueAndCategory_IsActiveTrue(String category);

	public List<Item> findByShop_ShopIdAndIsActiveTrueAndShop_IsActiveTrue(long shopId);
}

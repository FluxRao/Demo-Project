package com.rampo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rampo.entity.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>, PagingAndSortingRepository<Shop, Long> {

	public List<Shop> findByShopNameContainingIgnoreCaseAndShopCityIgnoreCase(String keyword, String city);

	public List<Shop> findByShopName(String name);

	public Page<Shop> findByShopCityIgnoreCaseAndIsActiveTrue(String city, Pageable pageable);

	@Query("select distinct s.shopCity from Shop s")
	public List<String> findCityList();

}

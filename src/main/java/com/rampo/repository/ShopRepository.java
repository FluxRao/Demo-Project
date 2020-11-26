package com.rampo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rampo.entity.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>, PagingAndSortingRepository<Shop, Long> {

	public List<Shop> findByShopNameStartsWith(String keyword);

	public List<Shop> findByShopName(String name);

}

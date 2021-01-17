package com.rampo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rampo.entity.ShopUserMap;

@Repository
public interface ShopUserMapRepository extends JpaRepository<ShopUserMap, Long> {

	public ShopUserMap findByShop_ShopIdAndUser_UserName(long shopId, String userName);
}

package com.rampo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rampo.entity.BrandUserMap;

@Repository
public interface BrandUserMapRepository extends JpaRepository<BrandUserMap, Long> {

	public BrandUserMap findByBrand_BrandNameAndUser_UserName(String brandName, String userName);
}

package com.rampo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rampo.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {

	List<Brand> findByBrandNameContainingIgnoreCase(String keyword);

	List<Brand> findByBrandName(String name);
}

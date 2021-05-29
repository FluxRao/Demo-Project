package com.rampo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rampo.entity.Category;

@Repository
public interface CategoryRepository
		extends JpaRepository<Category, String>, PagingAndSortingRepository<Category, String> {

	public List<Category> findByCatNameContainingIgnoreCase(String keyword);

}

package com.rampo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rampo.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long>, PagingAndSortingRepository<Offer, Long> {

	public List<Offer> findByOfferNameStartsWith(String keyword);

	public List<Offer> findByOfferName(String name);

	public Slice<Offer> findByEndDate(LocalDate endDate, Pageable pageable);
}
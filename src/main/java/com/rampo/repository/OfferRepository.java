package com.rampo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rampo.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long>, PagingAndSortingRepository<Offer, Long> {
	@Query(value = "select * from offer o INNER JOIN itemofferconfig ioc on o.offerid = ioc.offerid INNER JOIN  item i ON ioc.itemid = i.itemid INNER JOIN shop s ON i.shopid = s.shopid where LOWER(s.shopcity) = LOWER(?2) and s.flgactive is true and o.flgactive is true and i.flgactive is true and LOWER(o.offername) Like %?1%", nativeQuery = true)
	public List<Offer> findByOfferNameContainingIgnoreCaseAndIsActiveTrue(String keyword, String city);

	public List<Offer> findByOfferName(String name);

	public Page<Offer> findByIsActiveTrue(Pageable pageable);

	@Query(value = "select * from offer o INNER JOIN itemofferconfig ioc on o.offerid = ioc.offerid INNER JOIN  item i ON ioc.itemid = i.itemid INNER JOIN shop s ON i.shopid = s.shopid where LOWER(s.shopcity) = LOWER(?1) and s.flgactive is true and o.flgactive is true and i.flgactive is true and o.enddate = (?2)", nativeQuery = true)
	public Page<Offer> findByEndDate(String city, String endDate, Pageable pageable);

	@Query(value = "select * from offer o INNER JOIN itemofferconfig ioc on o.offerid = ioc.offerid INNER JOIN  item i ON ioc.itemid = i.itemid INNER JOIN shop s ON i.shopid = s.shopid where LOWER(s.shopcity) = LOWER(?1) and s.flgactive is true and o.flgactive is true and i.flgactive is true", nativeQuery = true)
	public Page<Offer> findOffersByCity(String city, Pageable pageable);
}
package com.rampo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rampo.entity.OfferUserMap;

@Repository
public interface OfferUserMapRepository extends JpaRepository<OfferUserMap, Long> {

	public OfferUserMap findByOffer_OfferIdAndUser_UserName(long offerId, String userName);
}

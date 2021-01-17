package com.rampo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rampo.entity.ItemUserMap;

@Repository
public interface ItemUserMapRepository extends JpaRepository<ItemUserMap, Long> {

	public ItemUserMap findByItem_ItemIdAndUser_UserName(long itemId, String userName);
}

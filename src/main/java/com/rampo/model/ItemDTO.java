package com.rampo.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDTO {

	private long itemId;
	private String itemName;
	private double amount;
	private String detail;
	private String imageUrl1;
	private boolean isActive;
	private BrandDTO brand;
	private String category;
	private double rating;
	private long noOfRatings;
	private long views;
	private Date createdOn;
	private Date modifiedOn;
	private boolean didLike;
	private ShopDTO shop;
}

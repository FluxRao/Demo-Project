package com.rampo.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShopDTO {

	private long shopId;
	private String shopName;
	private String shopDetail;
	private String address;
	private String shopCity;
	private String shopState;
	private String shopCountry;
	private String pincode;
	private boolean isActive;
	private String shopOwner;
	private String contactNo;
	private String eMail;
	private double rating;
	private long noOfRatings;
	private long views;
	private String imageUrl1;
	private String imageUrl2;
	private String imageUrl3;
	private Date createdOn;
	private Date modifiedOn;
	private boolean didLike;
}
